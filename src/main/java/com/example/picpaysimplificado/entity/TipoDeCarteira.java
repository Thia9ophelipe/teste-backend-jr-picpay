package com.example.picpaysimplificado.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_tipo_carteira")
public class TipoDeCarteira {

    @Id
    private long id;

    @Column(name = "descricao")
    private String descricao;

    public TipoDeCarteira() {
    }

    public TipoDeCarteira(long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoDeCarteira get() {

        return new TipoDeCarteira(id, descricao);
    }
}