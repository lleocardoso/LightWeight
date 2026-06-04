package com.LightWeight.service;

import com.LightWeight.exception.RecursoNaoEncontradoException;
import com.LightWeight.dto.request.UsuarioRequestDTO;
import com.LightWeight.dto.response.UsuarioResponseDTO;
import com.LightWeight.entity.Usuario;
import com.LightWeight.exception.ConflitoDeDadosException;
import com.LightWeight.repository.UsuarioRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UsuarioResponseDTO create(UsuarioRequestDTO dto){
        if (usuarioRepository.existsByEmail(dto.getEmail())){
            throw new ConflitoDeDadosException("E-mail já cadastrado" + dto.getEmail());
        }
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setIdade(dto.getIdade());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        return toDto(usuarioRepository.save(usuario));
    }
    @Transactional(readOnly = true)
    public UsuarioResponseDTO findById(UUID id){
        return toDto(findEntityById(id));
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional
    public UsuarioResponseDTO update(UUID id, UsuarioRequestDTO dto){
        Usuario usuario = findEntityById(id);
        if (usuarioRepository.existsByEmailAndIdNot(dto.getEmail(), id)){
            throw new ConflitoDeDadosException("E-mail já cadastrado: " + dto.getEmail());
        }
        usuario.setNome(dto.getNome());
        usuario.setIdade(dto.getIdade());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        return toDto(usuarioRepository.save(usuario));
    }

    @Transactional
    public void delete(UUID id) {
        findEntityById(id);
        usuarioRepository.deleteById(id);
    }

    public Usuario findEntityById(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com id: " + id));
    }

    private UsuarioResponseDTO toDto(Usuario u) {
        return new UsuarioResponseDTO(u.getId(), u.getNome(), u.getIdade(), u.getEmail());
    }
}
