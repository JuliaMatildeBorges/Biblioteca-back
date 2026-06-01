package com.projeto.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projeto.biblioteca.enums.TipoEspaco;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EspacoDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "O nome do espaço é obrigatório")
    private String nome;

    @NotNull(message = "O tipo do espaço é obrigatório")
    private TipoEspaco tipo;

    @NotNull(message = "A capacidade é obrigatória")
    @Min(value = 1, message = "A capacidade mínima é 1")
    @Max(value = 5, message = "A capacidade máxima permitida é 5")
    private Integer capacidade;

    private String observacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoEspaco getTipo() {
        return tipo;
    }

    public void setTipo(TipoEspaco tipo) {
        this.tipo = tipo;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
