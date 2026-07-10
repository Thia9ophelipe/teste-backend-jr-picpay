package com.example.picpaysimplificado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransacaoRequestDTO(
        @NotNull BigDecimal valor,
        @NotNull Long idPagador,
        @NotNull Long idRecebedor) {

    public TransacaoRequestDTO(BigDecimal valor, Long idPagador, Long idRecebedor) {
        this.valor = valor;
        this.idPagador = idPagador;
        this.idRecebedor = idRecebedor;
    }

    @Override
    public BigDecimal valor() {
        return valor;
    }

    @Override
    public Long idPagador() {
        return idPagador;
    }

    @Override
    public Long idRecebedor() {
        return idRecebedor;
    }
}
