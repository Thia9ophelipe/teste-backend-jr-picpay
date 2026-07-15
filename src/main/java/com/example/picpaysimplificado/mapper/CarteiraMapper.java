package com.example.picpaysimplificado.mapper;

import com.example.picpaysimplificado.dto.CarteiraRequestDTO;
import com.example.picpaysimplificado.entity.Carteira;
import com.example.picpaysimplificado.entity.TipoDeCarteira;
import com.example.picpaysimplificado.exception.TipoDeCarteiraInvalidoException;
import com.example.picpaysimplificado.repository.TipoDeCarteiraRepository;
import org.springframework.stereotype.Component;


@Component
public class CarteiraMapper {

    TipoDeCarteiraRepository tipoDeCarteiraRepository;

    public CarteiraMapper(TipoDeCarteiraRepository tipoDeCarteiraRepository) {
        this.tipoDeCarteiraRepository = tipoDeCarteiraRepository;
    }

    public Carteira toEntity(CarteiraRequestDTO dto){

        TipoDeCarteira tipoDeCarteira = tipoDeCarteiraRepository
                .findById(dto.tipoDeCarteira().getId()).orElse(null);

        return new Carteira(
                dto.nomeCompleto(),
                dto.cpfCnpj(),
                dto.email(),
                dto.senha(),
                tipoDeCarteira
        );
    }
}