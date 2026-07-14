package com.example.picpaysimplificado.service;

import com.example.picpaysimplificado.dto.TransacaoRequestDTO;
import com.example.picpaysimplificado.dto.TransacaoResponseDTO;
import com.example.picpaysimplificado.entity.Transacao;
import com.example.picpaysimplificado.mapper.TransacaoMapper;
import com.example.picpaysimplificado.repository.TransacaoRepository;
import com.example.picpaysimplificado.service.validacao.ValidacaoTransacao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    List<ValidacaoTransacao> validacoesTransacao;
    TransacaoRepository transacaoRepository;
    TransacaoMapper transacaoMapper;

    public TransacaoService(List<ValidacaoTransacao> validacoesTransacao,
                            TransacaoRepository transacaoRepository,
                            TransacaoMapper transacaoMapper) {

        this.validacoesTransacao = validacoesTransacao;
        this.transacaoRepository = transacaoRepository;
        this.transacaoMapper = transacaoMapper;
    }

    public TransacaoResponseDTO realizarTransacao(TransacaoRequestDTO dto) {

        validacoesTransacao.forEach(validacaoTransacao -> validacaoTransacao.validaTransacao(dto));

        Transacao transacao = transacaoMapper.toEntity(dto);
        transacaoRepository.save(transacao);
        TransacaoResponseDTO transacaoResponseDTO = transacaoMapper.toDTO(transacao);

        return transacaoResponseDTO;
    }
}
