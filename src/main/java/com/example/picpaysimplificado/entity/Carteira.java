package com.example.picpaysimplificado.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "tb_carteira")
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Column(name = "cpf_cnpj",  unique = true)
    private String cpfCnpj;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "saldo")
    private BigDecimal saldo =  BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "id_tipo_carteira")
    private TipoDeCarteira tipoDeCarteira;

    public Carteira() {
    }

    public Carteira(String nomeCompleto, String cpfCnpj, String email, String senha, TipoDeCarteira tipoDeCarteira) {
        this.nomeCompleto = nomeCompleto;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.senha = senha;
        this.tipoDeCarteira = tipoDeCarteira;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public TipoDeCarteira getTipoDeCarteira() {
        return tipoDeCarteira;
    }

    public void setTipoDeCarteira(TipoDeCarteira tipoDeCarteira) {

        this.tipoDeCarteira = tipoDeCarteira;
    }

    public void debitar(BigDecimal valor) {
        saldo = saldo.subtract(valor);
    }

    public void creditar(BigDecimal valor) {
        saldo = saldo.add(valor);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Carteira carteira = (Carteira) o;
        return id == carteira.id && Objects.equals(nomeCompleto, carteira.nomeCompleto) && Objects.equals(cpfCnpj, carteira.cpfCnpj) && Objects.equals(email, carteira.email) && Objects.equals(senha, carteira.senha) && Objects.equals(saldo, carteira.saldo) && Objects.equals(tipoDeCarteira, carteira.tipoDeCarteira);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomeCompleto, cpfCnpj, email, senha, saldo, tipoDeCarteira);
    }
}
