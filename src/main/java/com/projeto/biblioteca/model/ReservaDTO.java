package com.projeto.biblioteca.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projeto.biblioteca.enums.StatusReserva;
import com.projeto.biblioteca.enums.TipoEspaco;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull(message = "O usuário é obrigatório")
    private Long usuarioId;

    @NotNull(message = "O espaço é obrigatório")
    private Long espacoId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String usuarioNome;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String usuarioEmail;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String espacoNome;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private TipoEspaco espacoTipo;

    @NotBlank(message = "O curso é obrigatório")
    private String curso;

    @NotNull(message = "A quantidade de alunos é obrigatória")
    @Min(value = 1, message = "Informe pelo menos 1 aluno")
    private Integer quantidadeAlunos;

    @NotNull(message = "O início da reserva é obrigatório")
    @Future(message = "O início da reserva deve ser futuro")
    private LocalDateTime inicio;

    @NotNull(message = "O fim da reserva é obrigatório")
    private LocalDateTime fim;

    private StatusReserva status = StatusReserva.RESERVADA;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getEspacoId() {
        return espacoId;
    }

    public void setEspacoId(Long espacoId) {
        this.espacoId = espacoId;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    public String getEspacoNome() {
        return espacoNome;
    }

    public void setEspacoNome(String espacoNome) {
        this.espacoNome = espacoNome;
    }

    public TipoEspaco getEspacoTipo() {
        return espacoTipo;
    }

    public void setEspacoTipo(TipoEspaco espacoTipo) {
        this.espacoTipo = espacoTipo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Integer getQuantidadeAlunos() {
        return quantidadeAlunos;
    }

    public void setQuantidadeAlunos(Integer quantidadeAlunos) {
        this.quantidadeAlunos = quantidadeAlunos;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }
}
