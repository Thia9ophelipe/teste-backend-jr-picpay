package com.example.picpaysimplificado.exception;

import org.springframework.http.HttpStatus;

public class TipoDeCarteiraInvalidoException extends PicPayException{

    public TipoDeCarteiraInvalidoException() {
        super(HttpStatus.UNPROCESSABLE_ENTITY,
                "Tipo de carteira inválido",
                "A carteira deve ser do tipo USUARIO ou LOJISTA");
    }
}
