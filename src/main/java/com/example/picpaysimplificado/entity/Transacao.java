package com.example.picpaysimplificado.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_transferencia")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "valor")
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "id_pagador")
    private Carteira idPagador;

    @ManyToOne
    @JoinColumn(name = "id_recebedor")
    private Carteira idRecebedor;

    @Column(name = "dataHora")
    private OffsetDateTime dataHora;

    public Transacao() {
    }

    public Transacao(BigDecimal valor, Carteira idPagador, Carteira idRecebedor) {
        this.valor = valor;
        this.idPagador = idPagador;
        this.idRecebedor = idRecebedor;
    }

    public Transacao(BigDecimal valor, Carteira idPagador, Carteira idRecebedor, OffsetDateTime dataHora) {
        this.valor = valor;
        this.idPagador = idPagador;
        this.idRecebedor = idRecebedor;
        this.dataHora = dataHora;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Carteira getIdPagador() {
        return idPagador;
    }

    public void setIdPagador(Carteira idPagador) {
        this.idPagador = idPagador;
    }

    public Carteira getIdRecebedor() {
        return idRecebedor;
    }

    public void setIdRecebedor(Carteira idRecebedor) {
        this.idRecebedor = idRecebedor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Transacao that = (Transacao) o;
        return Objects.equals(id, that.id) && Objects.equals(valor, that.valor) && Objects.equals(idPagador, that.idPagador) && Objects.equals(idRecebedor, that.idRecebedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, valor, idPagador, idRecebedor);
    }
}
