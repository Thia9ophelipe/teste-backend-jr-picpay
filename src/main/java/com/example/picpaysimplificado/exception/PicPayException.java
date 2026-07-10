package com.example.picpaysimplificado.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class PicPayException extends RuntimeException {

    private String titulo;
    private HttpStatus status;

    public PicPayException(HttpStatus status,  String titulo, String detalhe) {
        super(detalhe);
        this.status = status;
        this.titulo = titulo;
    };

    public String getTitulo() {
        return titulo;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getDetalhe() {
        return getMessage();
    }
}
