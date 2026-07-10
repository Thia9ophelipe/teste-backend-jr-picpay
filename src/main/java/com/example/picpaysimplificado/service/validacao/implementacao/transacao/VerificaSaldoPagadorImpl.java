package com.example.picpaysimplificado.service.validacao.implementacao.transacao;

import com.example.picpaysimplificado.dto.TransacaoRequestDTO;
import com.example.picpaysimplificado.entity.Carteira;
import com.example.picpaysimplificado.exception.SaldoInsuficienteException;
import com.example.picpaysimplificado.exception.UsuarioNaoEncontradoException;
import com.example.picpaysimplificado.repository.CarteiraRepository;
import com.example.picpaysimplificado.service.validacao.ValidacaoTransacao;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class VerificaSaldoPagadorImpl implements ValidacaoTransacao {

    @Override
    public void validaTransacao(TransacaoRequestDTO dto,
                                Carteira pagador,
                                Carteira recebedor) {

        if (pagador.getSaldo().compareTo(dto.valor()) < 0) {
            throw new SaldoInsuficienteException();
        }
    }
}