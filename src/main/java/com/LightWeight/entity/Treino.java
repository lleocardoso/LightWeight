package com.LightWeight.entity;

import com.LightWeight.enums.AgrupamentoMuscular;
import com.LightWeight.enums.TreinoEstado;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(exclude = {"usuario", "exercicios", "agrupamentosTrabalhados"})
@ToString(exclude = {"usuario", "exercicios", "agrupamentosTrabalhados"})
@Entity
@Table(name = "treinos")
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TreinoEstado estado = TreinoEstado.INATIVO;

    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;

    @Column
    private BigDecimal volumeTotal;

    @OneToMany(mappedBy = "treino", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exercicio> exercicios = new ArrayList<>();
}