package com.example.picpaysimplificado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransacaoRequestDTO(
        @NotBlank BigDecimal valor,
        @NotNull long idPagador,
        @NotNull long idRecebedor) {

    public TransacaoRequestDTO(BigDecimal valor, long idPagador, long idRecebedor) {
        this.valor = valor;
        this.idPagador = idPagador;
        this.idRecebedor = idRecebedor;
    }

    @Override
    public BigDecimal valor() {
        return valor;
    }

    @Override
    public long idPagador() {
        return idPagador;
    }

    @Override
    public long idRecebedor() {
        return idRecebedor;
    }
}
