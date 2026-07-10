package com.example.picpaysimplificado.exception;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class CarteiraPagadorInvalidaException extends PicPayException {

    public CarteiraPagadorInvalidaException() {
        super(
                UNPROCESSABLE_ENTITY,
                "Tipo de carteira não autorizada a realizar transações",
                "Lojistas não podem realizar transações!"
        );
    }
}
