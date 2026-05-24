package com.LightWeight.dto.response;

import com.LightWeight.enums.AgrupamentoMuscular;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExercicioResponseDTO {
    private UUID id;
    private UUID treinoId;
    private String nome;
    private Integer series;
    private Integer repeticoes;
    private BigDecimal carga;
    private AgrupamentoMuscular agrupamento;
}
