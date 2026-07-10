package com.example.picpaysimplificado.service.validacao.implementacao.transacao;

import com.example.picpaysimplificado.dto.TransacaoRequestDTO;
import com.example.picpaysimplificado.entity.Carteira;
import com.example.picpaysimplificado.entity.EnumTipoDeCarteira;
import com.example.picpaysimplificado.entity.TipoDeCarteira;
import com.example.picpaysimplificado.exception.CarteiraPagadorInvalidaException;
import com.example.picpaysimplificado.exception.UsuarioNaoEncontradoException;
import com.example.picpaysimplificado.repository.CarteiraRepository;
import com.example.picpaysimplificado.repository.TipoDeCarteiraRepository;
import com.example.picpaysimplificado.repository.TransacaoRepository;
import com.example.picpaysimplificado.service.validacao.ValidacaoTransacao;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidaTipoCarteiraPagadorImpl implements ValidacaoTransacao {

    @Override
    public void validaTransacao(TransacaoRequestDTO dto,
                                Carteira pagador,
                                Carteira recebedor) {

        if (pagador.getTipoDeCarteira().getId() != EnumTipoDeCarteira.USUARIO.getId()) {
            throw new CarteiraPagadorInvalidaException();
        }
    }
}