package com.LightWeight.dto.response;

import com.LightWeight.dto.request.ExercicioRequestDTO;
import com.LightWeight.enums.AgrupamentoMuscular;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

    //Projection utilizada para exibir o relatório final de um treino após sua conclusão, focando no somatório do volume total de carga movimentada.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreinoFinalizadoResponseDTO {
    private UUID id;
    private LocalDateTime DataHoraInicio;
    private LocalDateTime DataHoraFim;
    private BigDecimal volumeTotal;
    private List<AgrupamentoMuscular> agrupamentosTrabalhados;
    private List <ExercicioRequestDTO> exercicios;
}
