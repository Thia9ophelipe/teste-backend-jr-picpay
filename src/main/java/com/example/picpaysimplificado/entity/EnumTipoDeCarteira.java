package com.example.picpaysimplificado.entity;

public enum EnumTipoDeCarteira {

    USUARIO(1L, "usuario"),
    LOJISTA(2L,"lojista");

    private final long id;
    private final String descricao;

    EnumTipoDeCarteira(long id, String descricao) {

        this.id = id;
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public TipoDeCarteira get() {

        return new TipoDeCarteira(getId(), getDescricao());
    }
}