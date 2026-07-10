package com.example.picpaysimplificado.service;

import com.example.picpaysimplificado.dto.TransacaoRequestDTO;
import com.example.picpaysimplificado.dto.TransacaoResponseDTO;
import com.example.picpaysimplificado.entity.Carteira;
import com.example.picpaysimplificado.entity.Transacao;
import com.example.picpaysimplificado.exception.UsuarioNaoEncontradoException;
import com.example.picpaysimplificado.mapper.TransacaoMapper;
import com.example.picpaysimplificado.repository.CarteiraRepository;
import com.example.picpaysimplificado.repository.TransacaoRepository;
import com.example.picpaysimplificado.service.validacao.ValidacaoTransacao;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@Transactional
public class TransacaoService {

    private final List<ValidacaoTransacao> validacoesTransacao;
    private final TransacaoRepository transacaoRepository;
    private final CarteiraRepository carteiraRepository;
    private final TransacaoMapper transacaoMapper;

    public TransacaoService(List<ValidacaoTransacao> validacoesTransacao,
                            TransacaoRepository transacaoRepository,
                            CarteiraRepository carteiraRepository,
                            TransacaoMapper transacaoMapper
    ) {

        this.validacoesTransacao = validacoesTransacao;
        this.transacaoRepository = transacaoRepository;
        this.carteiraRepository = carteiraRepository;
        this.transacaoMapper = transacaoMapper;
    }

    public TransacaoResponseDTO realizarTransacao(TransacaoRequestDTO dto) {

        Carteira pagador = carteiraRepository.findById(dto.idPagador())
                .orElseThrow(UsuarioNaoEncontradoException::new);
        Carteira recebedor = carteiraRepository.findById(dto.idRecebedor())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        validacoesTransacao.forEach(validacaoTransacao -> validacaoTransacao
                .validaTransacao(dto, pagador, recebedor));

        pagador.debitar(dto.valor());
        carteiraRepository.save(pagador);

        recebedor.creditar(dto.valor());
        carteiraRepository.save(recebedor);

        Transacao transacao = transacaoMapper.toEntity(dto);
        transacao.setDataHora(OffsetDateTime.now());
        transacaoRepository.save(transacao);

        return transacaoMapper.toDTO(transacao);
    }
}
