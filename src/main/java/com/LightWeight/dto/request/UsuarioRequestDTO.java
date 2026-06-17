package com.LightWeight.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    //Objeto que encapsula e valida os dados de entrada para criação ou edição de usuários, assim evitando a exposição direta da entidade do banco na API externa.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 150, message = "Nome deve ter no máximo 150 caracteres")
    private String nome;

    @NotNull(message = "Idade é obrigatória")
    @Min(value = 1, message = "Idade deve ser no mínimo 1")
    @Max(value = 100, message = "Idade deve ser no máximo 100")
    private Integer idade;

    @NotBlank(message = "E-mail deve ser obrigatório" )
    @Email(message = "E-mail deve ter formato válido")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
    private String senha;


}
