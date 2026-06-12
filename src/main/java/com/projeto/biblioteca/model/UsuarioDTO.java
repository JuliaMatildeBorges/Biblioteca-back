package com.projeto.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projeto.biblioteca.enums.Instituicao;
import com.projeto.biblioteca.enums.PerfilUsuario;
import com.projeto.biblioteca.enums.TipoUsuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Email(message = "O email deve ser válido")
    @NotBlank(message = "O email é obrigatório")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório")
    private String cpf;

    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;

    @NotNull(message = "A instituição é obrigatória")
    private Instituicao instituicao;

    @NotNull(message = "O tipo é obrigatório")
    private TipoUsuario tipo;

    private PerfilUsuario perfil = PerfilUsuario.USUARIO;

  
}
