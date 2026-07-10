package com.example.picpaysimplificado.exception;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class SaldoInsuficienteException extends PicPayException {

    public SaldoInsuficienteException() {
        super(
                UNPROCESSABLE_ENTITY,
                "Saldo insuficiente",
                "Verifique o valor da transação"
        );
    }
}
