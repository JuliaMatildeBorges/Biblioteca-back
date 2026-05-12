package com.projeto.biblioteca.model;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projeto.biblioteca.enums.tipoUsuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @CPF
    @NotNull(message = "O CPF é obrigatório")
    private String cpf;

    @Email
    @NotBlank(message = "O email é obrigatório")
    private String email;

    private tipoUsuario tipoUsuario = com.projeto.biblioteca.enums.tipoUsuario.ALUNO;


}
