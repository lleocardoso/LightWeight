package com.LightWeight.repository;

import com.LightWeight.entity.Treino;
import com.LightWeight.enums.TreinoEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.List;

    //Interface de persistência para a entidade Treino.
@Repository
public interface TreinoRepository extends JpaRepository<Treino,UUID> {
    List <Treino> findAllByUsuarioId(UUID usuarioId);

    boolean existsByUsuarioIdAndEstado(UUID usuarioId, TreinoEstado estado);
}
