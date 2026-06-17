package com.LightWeight.repository;

import com.LightWeight.entity.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.Optional;

    //Camada de persistência para a entidade Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    //O Spring Data interpreta o nome do metodo e gera o SQL dinamicamente.
    boolean existsByEmail(String email);

    // Verifica se o e-mail já existe em outra conta que não seja a do ID atual
    boolean existsByEmailAndIdNot(String email, UUID id);

    Optional<Usuario> findByEmail(String email);
}
