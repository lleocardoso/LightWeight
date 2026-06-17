package com.LightWeight.service;

import com.LightWeight.dto.request.ExercicioRequestDTO;
import com.LightWeight.dto.response.ExercicioResponseDTO;
import com.LightWeight.entity.Exercicio;
import com.LightWeight.entity.Treino;
import com.LightWeight.exception.RecursoNaoEncontradoException;
import com.LightWeight.repository.ExercicioRepository;
import com.LightWeight.repository.TreinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

    //service que processa a lógica de inserção e listagem de exercícios físicos
@Service
@RequiredArgsConstructor
public class ExercicioService {

    private final ExercicioRepository exercicioRepository;
    private final TreinoRepository treinoRepository;

    @Transactional
    public ExercicioResponseDTO create(UUID treinoId, ExercicioRequestDTO dto) { // Assegura que o exercício só pode ser criado se o treino "pai" existir.
        Treino treino = treinoRepository.findById(treinoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Treino não encontrado com o id: " + treinoId));

        Exercicio exercicio = new Exercicio();
        exercicio.setTreino(treino);
        exercicio.setNome(dto.getNome());
        exercicio.setSeries(dto.getSeries());
        exercicio.setRepeticoes(dto.getRepeticoes());
        exercicio.setCarga(dto.getCarga());
        exercicio.setAgrupamentoMuscular(dto.getAgrupamentoMuscular());

        exercicio = exercicioRepository.save(exercicio);
        return toDto(exercicio);
    }

    @Transactional(readOnly = true)
    public List<ExercicioResponseDTO> findAll() {
        return exercicioRepository.findAll().stream().map(this::toDto).toList();
    }

    private ExercicioResponseDTO toDto(Exercicio exercicio) {
        ExercicioResponseDTO dto = new ExercicioResponseDTO();
        dto.setId(exercicio.getId());
        dto.setTreinoId(exercicio.getTreino().getId());
        dto.setNome(exercicio.getNome());
        dto.setSeries(exercicio.getSeries());
        dto.setRepeticoes(exercicio.getRepeticoes());
        dto.setCarga(exercicio.getCarga());
        dto.setAgrupamento(exercicio.getAgrupamentoMuscular());
        return dto;
    }
}