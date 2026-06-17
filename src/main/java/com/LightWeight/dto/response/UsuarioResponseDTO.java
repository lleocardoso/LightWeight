package com.LightWeight.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

    //Objeto de saída estruturado para exibir dados públicos do usuário.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {
    private UUID id;
    private String nome;
    private Integer idade;
    private String email;
}
