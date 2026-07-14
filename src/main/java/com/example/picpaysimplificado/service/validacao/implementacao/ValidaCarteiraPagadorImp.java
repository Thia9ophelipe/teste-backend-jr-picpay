package com.example.picpaysimplificado.service.validacao.implementacao;

import com.example.picpaysimplificado.dto.TransacaoRequestDTO;
import com.example.picpaysimplificado.entity.Carteira;
import com.example.picpaysimplificado.entity.TipoDeCarteira;
import com.example.picpaysimplificado.exception.CarteiraPagadorInvalidaException;
import com.example.picpaysimplificado.exception.UsuarioNaoEncontradoException;
import com.example.picpaysimplificado.repository.CarteiraRepository;
import com.example.picpaysimplificado.repository.TipoDeCarteiraRepository;
import com.example.picpaysimplificado.repository.TransacaoRepository;
import com.example.picpaysimplificado.service.validacao.ValidacaoTransacao;

public class ValidaCarteiraPagadorImp implements ValidacaoTransacao {

    TransacaoRequestDTO dto;
    TipoDeCarteira tipoDeCarteira;
    CarteiraRepository carteiraRepository;
    TransacaoRepository transacaoRepository;
    TipoDeCarteiraRepository tipoDeCarteiraRepository;


    public ValidaCarteiraPagadorImp(TransacaoRequestDTO dto,
                                    CarteiraRepository carteiraRepository) {
        this.dto = dto;
        this.tipoDeCarteira = tipoDeCarteira;
        this.carteiraRepository = carteiraRepository;
    }

    @Override
    public void validaTransacao(TransacaoRequestDTO dto) {

        Carteira carteiraPagador = carteiraRepository.findById(dto.idPagador())
                .orElseThrow(() -> new UsuarioNaoEncontradoException());

        if (carteiraPagador.getTipoDeCarteira().getDescricao().equals("LOJISTA")) {
            throw new CarteiraPagadorInvalidaException();
        }
    }
}
