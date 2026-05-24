package com.LightWeight.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreinoRequestDTO {

    private boolean peito = false;
    private boolean costas = false;
    private boolean quadriceps = false;
    private boolean posterior = false;

}
