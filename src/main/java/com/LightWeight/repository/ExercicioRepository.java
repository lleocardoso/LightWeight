package com.LightWeight.repository;

import java.util.UUID;
import com.LightWeight.entity.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, UUID> {
    List<Exercicio> findByTreinoId(UUID treinoId);
}
