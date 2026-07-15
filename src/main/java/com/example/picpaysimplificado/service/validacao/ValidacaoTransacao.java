package com.example.picpaysimplificado.service.validacao;

import com.example.picpaysimplificado.dto.TransacaoRequestDTO;
import com.example.picpaysimplificado.entity.Carteira;

public interface ValidacaoTransacao {

    void validaTransacao(TransacaoRequestDTO dto, Carteira pagador, Carteira recebedor);

}
