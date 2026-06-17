package com.LightWeight.entity;

import com.LightWeight.enums.AgrupamentoMuscular;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

    //Entidade que mapeia os detalhes físicos de cada exercício executado dentro de um treino.
@Data
@EqualsAndHashCode(exclude = "treino")
@ToString(exclude = "treino")
@Entity
@Table(name = "exercicios")

public class Exercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treino_id", nullable = false)
    private Treino treino;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private Integer series;

    @Column(nullable = false)
    private Integer repeticoes;

    @Column
    private BigDecimal carga;

    @Enumerated(EnumType.STRING)
    @Column(name = "agrupamentomuscular", nullable = false, length = 20)
    private AgrupamentoMuscular agrupamentoMuscular;
}
