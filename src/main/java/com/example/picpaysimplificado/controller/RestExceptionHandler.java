package com.example.picpaysimplificado.controller;

import com.example.picpaysimplificado.exception.PicPayException;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tools.jackson.databind.exc.InvalidFormatException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(PicPayException.class)
    public ProblemDetail handlePicPayException(PicPayException e) {

        ProblemDetail problem = ProblemDetail.forStatus(e.getStatus());
        problem.setDetail(e.getDetalhe());
        problem.setTitle(e.getTitulo());

        return problem;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        ProblemDetail problem = ProblemDetail.forStatus(e.getStatusCode());
        problem.setTitle("Verifique se o preenchimento dos campos foi feito corretamente");

        String fieldErrors = e.getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList()
                .toString();

        problem.setDetail(fieldErrors);

        return problem;

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        problem.setTitle("Requisição inválida");

        if (e.getCause() != null) {
            problem.setDetail(e.getMessage());
        } else {
            problem.setDetail(e.getMessage());
        }

        return problem;
    }
}
