package com.LightWeight.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    //Payload de entrada para a criação de treinos. Focado no rastreamento dos músculos alvos da sessão.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreinoRequestDTO {

    private boolean peito = false;
    private boolean costas = false;
    private boolean quadriceps = false;
    private boolean posterior = false;

}
