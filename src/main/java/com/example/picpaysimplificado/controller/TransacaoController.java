package com.example.picpaysimplificado.controller;

import com.example.picpaysimplificado.dto.TransacaoRequestDTO;
import com.example.picpaysimplificado.dto.TransacaoResponseDTO;
import com.example.picpaysimplificado.repository.TransacaoRepository;
import com.example.picpaysimplificado.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<TransacaoResponseDTO> realizarTransacao(@RequestBody @Valid TransacaoRequestDTO transacaoRequestDTO) {

        var transacao = transacaoService.realizarTransacao(transacaoRequestDTO);

        return ResponseEntity.ok(transacao);
    }
}
