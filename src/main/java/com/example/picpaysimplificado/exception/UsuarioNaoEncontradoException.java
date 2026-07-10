package com.example.picpaysimplificado.exception;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class UsuarioNaoEncontradoException extends PicPayException {

    public UsuarioNaoEncontradoException() {

        super(
                UNPROCESSABLE_ENTITY,
                "O usuário não foi encontrado",
                "Pagador ou recebedor não cadastrados no sistema"
        );
    }
}
