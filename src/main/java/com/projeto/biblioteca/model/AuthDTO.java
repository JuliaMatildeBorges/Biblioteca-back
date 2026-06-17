package com.projeto.biblioteca.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthDTO {

    @Email(message = "O email deve ser válido.")
    @NotBlank(message = "O email deve ser preenchido.")
    private String email;

    @NotBlank(message = "A senha deve ser preenchido.")
    private String senha;


}
