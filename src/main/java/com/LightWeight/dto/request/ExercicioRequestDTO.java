package com.LightWeight.dto.request;

import com.LightWeight.enums.AgrupamentoMuscular;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

    //Validador de entrada de dados biomecânicos e de volume de treino; Impede que o usuário insira valores irreais (ex: cargas negativas ou séries absurdas).
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExercicioRequestDTO {
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String nome;

    @NotNull(message = "Séries é obrigatório")
    @Min(value = 1, message = "séries deve ser no mínimo 1")
    @Max(value = 20, message = "Séries deve ser no máximo 20")
    private Integer series;

    @NotNull(message = "Repetições é obrigatório")
    @Min(value = 1, message = "Repetições deve ser no mínimo 1")
    @Max(value = 100, message = "Repetições deve ser no máximo 100")
    private Integer repeticoes;

    @NotNull(message = "Carga é obrigatória")
    @DecimalMin(value = "0.01", message = "Carga deve ser maior que zero")
    @DecimalMax(value = "500.0", message = "Carga deve ser no máximo 500kg")
    private BigDecimal carga;

    @NotNull(message = "Agrupamento muscular é obrigatório")
    private AgrupamentoMuscular agrupamentoMuscular;
}