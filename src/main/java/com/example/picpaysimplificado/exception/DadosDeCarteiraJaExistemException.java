package com.example.picpaysimplificado.exception;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class DadosDeCarteiraJaExistemException extends PicPayException {

    public DadosDeCarteiraJaExistemException() {
        super(
                UNPROCESSABLE_ENTITY,
                "CpfCnpj ou email ja existentes",
                "os dados CpfCnpj ou email informados ja existem no sistema"
        );
    }
}
