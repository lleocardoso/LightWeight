package com.LightWeight.dto.response;

import com.LightWeight.dto.request.ExercicioRequestDTO;
import com.LightWeight.enums.TreinoEstado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreinoResponseDTO {
    private UUID id;
    private UUID usuarioId;
    private TreinoEstado estado;
    private boolean peito;
    private boolean costas;
    private boolean quadriceps;
    private boolean superior;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private List<ExercicioRequestDTO> exercicios;
}
