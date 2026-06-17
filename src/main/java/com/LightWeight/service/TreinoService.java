package com.LightWeight.service;

import com.LightWeight.dto.request.TreinoRequestDTO;
import com.LightWeight.dto.response.TreinoResponseDTO;
import com.LightWeight.entity.Treino;
import com.LightWeight.entity.Usuario;
import com.LightWeight.enums.TreinoEstado;
import com.LightWeight.exception.ConflitoDeDadosException;
import com.LightWeight.exception.RecursoNaoEncontradoException;
import com.LightWeight.repository.TreinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TreinoService {

    private final TreinoRepository treinoRepository;
    private final UsuarioService usuarioService;

    @Transactional
    public TreinoResponseDTO create(UUID usuarioId, TreinoRequestDTO dto) {

        Usuario usuario = usuarioService.findEntityById(usuarioId);

        // Regra de Negócio: Impede a coexistência de dois treinos rodando simultaneamente em aberto.
        if (treinoRepository.existsByUsuarioIdAndEstado(usuarioId, TreinoEstado.ATIVO)) {
            throw new ConflitoDeDadosException(
                    "O usuário já possui um treino ativo.");
        }

        Treino treino = new Treino();
        treino.setUsuario(usuario);
        treino.setEstado(TreinoEstado.INATIVO);

        treino = treinoRepository.save(treino);

        return toDto(treino);
    }

    @Transactional(readOnly = true)
    public List<TreinoResponseDTO> findAllByUsuario(UUID usuarioId) {

        usuarioService.findEntityById(usuarioId);

        return treinoRepository.findAllByUsuarioId(usuarioId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public TreinoResponseDTO findById(UUID id) {
        return toDto(findEntityById(id));
    }

    @Transactional
    public void delete(UUID id) {
        treinoRepository.delete(findEntityById(id));
    }

    private Treino findEntityById(UUID id) {
        return treinoRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Treino não encontrado com o id: " + id));
    }

    private TreinoResponseDTO toDto(Treino treino) {
        TreinoResponseDTO dto = new TreinoResponseDTO();
        dto.setId(treino.getId());
        dto.setUsuarioId(treino.getUsuario().getId());
        dto.setEstado(treino.getEstado());
        return dto;
    }

    @Transactional(readOnly = true)
    public List<TreinoResponseDTO> findAll() {
        return treinoRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    //Calcula o volume de toneladas deslocadas: Somatório de (Carga * Séries * Repetições) por exercício.
    @Transactional
    public BigDecimal calcularVolumeTotal(UUID treinoId) {
        Treino treino = findEntityById(treinoId);

        BigDecimal volume = treino.getExercicios().stream()
                .map(e -> e.getCarga()
                        .multiply(BigDecimal.valueOf(e.getSeries()))
                        .multiply(BigDecimal.valueOf(e.getRepeticoes())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        treino.setVolumeTotal(volume);
        treinoRepository.save(treino);

        return volume;
    }
}