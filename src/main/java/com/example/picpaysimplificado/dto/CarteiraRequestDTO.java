package com.example.picpaysimplificado.dto;

import com.example.picpaysimplificado.entity.EnumTipoDeCarteira;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public record CarteiraRequestDTO(@NotBlank String nomeCompleto,
                                 @NotBlank String cpfCnpj,
                                 @NotBlank String email,
                                 @NotBlank String senha,
                                 @NotNull EnumTipoDeCarteira tipoDeCarteira) {

    public CarteiraRequestDTO(@NotBlank String nomeCompleto,
                              @NotBlank String cpfCnpj,
                              @NotBlank String email,
                              @NotBlank String senha,
                              @NotNull EnumTipoDeCarteira tipoDeCarteira) {

        this.nomeCompleto = nomeCompleto;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.senha = senha;
        this.tipoDeCarteira = tipoDeCarteira;
    }

    public @NotBlank String nomeCompleto() {
        return nomeCompleto;
    }

    public @NotBlank String cpfCnpj() {
        return cpfCnpj;
    }

    public @NotBlank String email() {
        return email;
    }

    public @NotBlank String senha() {
        return senha;
    }

    public @NotNull EnumTipoDeCarteira tipoDeCarteira() {
        return tipoDeCarteira;
    }


    @Override
    public String toString() {
        return "CarteiraRequestDTO[" +
                "nomeCompleto=" + nomeCompleto + ", " +
                "cpfCnpj=" + cpfCnpj + ", " +
                "email=" + email + ", " +
                "senha=" + senha + ", " +
                "tipoDeCarteira=" + tipoDeCarteira + ']';
    }

}