package com.projeto.biblioteca.entity;


import com.projeto.biblioteca.enums.tipoUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
@EqualsAndHashCode(callSuper = false)
public class Usuario extends BaseEntity {

    

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;       

    @Column(name = "CPF", nullable = false, unique = true)
    private String cpf;

    @Column(name = "tipo_usuario", nullable = false)
    private tipoUsuario tipo = tipoUsuario.ALUNO;



}

