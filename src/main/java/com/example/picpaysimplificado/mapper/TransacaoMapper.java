package com.example.picpaysimplificado.mapper;

import com.example.picpaysimplificado.dto.TransacaoRequestDTO;
import com.example.picpaysimplificado.dto.TransacaoResponseDTO;
import com.example.picpaysimplificado.entity.Carteira;
import com.example.picpaysimplificado.entity.Transacao;
import com.example.picpaysimplificado.exception.UsuarioNaoEncontradoException;
import com.example.picpaysimplificado.repository.CarteiraRepository;
import org.springframework.stereotype.Component;


@Component
public class TransacaoMapper {

    CarteiraRepository carteiraRepository;

    public Transacao toEntity(TransacaoRequestDTO dto) {

        Carteira pagador = carteiraRepository.findById(dto.idPagador())
                .orElseThrow(() -> new UsuarioNaoEncontradoException());

        Carteira recebedor = carteiraRepository.findById(dto.idRecebedor())
                .orElseThrow(() -> new UsuarioNaoEncontradoException());

        Transacao transacao = new Transacao();

        transacao.setIdRecebedor(recebedor);
        transacao.setIdPagador(pagador);
        transacao.setValor(dto.valor());

        return transacao;
    }

    public TransacaoResponseDTO toDTO(Transacao transacao) {

        Carteira pagador = carteiraRepository.findById(transacao.getIdPagador().getId())
                .orElseThrow();

        Carteira recebedor = carteiraRepository.findById(transacao.getIdRecebedor().getId())
                .orElseThrow();

        return new TransacaoResponseDTO(
                transacao.getValor(),
                pagador,
                recebedor,
                transacao.getDataHora()
        );
    }
}
