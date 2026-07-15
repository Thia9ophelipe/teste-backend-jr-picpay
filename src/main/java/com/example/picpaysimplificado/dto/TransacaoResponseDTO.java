package com.example.picpaysimplificado.dto;

import com.example.picpaysimplificado.entity.Carteira;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransacaoResponseDTO(BigDecimal valor,
                                   Carteira pagador,
                                   Carteira recebedor,
                                   OffsetDateTime dataHora) {

    public TransacaoResponseDTO(BigDecimal valor,
                                Carteira pagador,
                                Carteira recebedor,
                                OffsetDateTime dataHora) {
        this.valor = valor;
        this.pagador = pagador;
        this.recebedor = recebedor;
        this.dataHora = dataHora;
    }

    @Override
    public BigDecimal valor() {
        return valor;
    }

    @Override
    public Carteira pagador() {
        return pagador;
    }

    @Override
    public Carteira recebedor() {
        return recebedor;
    }

    @Override
    public OffsetDateTime dataHora() {
        return dataHora;
    }
}
