package com.example.picpaysimplificado.service;


import com.example.picpaysimplificado.dto.CarteiraRequestDTO;
import com.example.picpaysimplificado.entity.Carteira;
import com.example.picpaysimplificado.mapper.CarteiraMapper;
import com.example.picpaysimplificado.repository.CarteiraRepository;
import com.example.picpaysimplificado.repository.TipoDeCarteiraRepository;
import com.example.picpaysimplificado.service.validacao.ValidacaoCarteira;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarteiraService {

    private final CarteiraMapper mapper;
    private final List<ValidacaoCarteira> validacoes;
    private final CarteiraRepository carteiraRepository;
    private final TipoDeCarteiraRepository tipoDeCarteiraRepository;

    public CarteiraService(CarteiraMapper mapper,
                           CarteiraRepository carteiraRepository,
                           List<ValidacaoCarteira> validacoes,
                           TipoDeCarteiraRepository tipoDeCarteiraRepository) {
        this.mapper = mapper;
        this.validacoes = validacoes;
        this.carteiraRepository = carteiraRepository;
        this.tipoDeCarteiraRepository = tipoDeCarteiraRepository;
    }

    public Carteira criarCarteira(CarteiraRequestDTO dto) {

        validacoes.forEach(v -> v.validaCarteira(dto));

        Carteira carteira = mapper.toEntity(dto);

        carteiraRepository.save(carteira);

        return carteira;
    }
}
