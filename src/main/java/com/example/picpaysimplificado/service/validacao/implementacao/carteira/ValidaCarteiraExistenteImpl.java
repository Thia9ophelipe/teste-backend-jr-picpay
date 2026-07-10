package com.example.picpaysimplificado.service.validacao.implementacao.carteira;

import com.example.picpaysimplificado.dto.CarteiraRequestDTO;
import com.example.picpaysimplificado.exception.DadosDeCarteiraJaExistemException;
import com.example.picpaysimplificado.repository.CarteiraRepository;
import com.example.picpaysimplificado.service.validacao.ValidacaoCarteira;
import org.springframework.stereotype.Component;


@Component
public class ValidaCarteiraExistenteImpl implements ValidacaoCarteira {

    private final CarteiraRepository carteiraRepository;

    public ValidaCarteiraExistenteImpl(CarteiraRepository carteiraRepository) {

        this.carteiraRepository = carteiraRepository;
    }

    @Override
    public void validaCarteira(CarteiraRequestDTO dto) {

        var carteiraDb = carteiraRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());

        if (carteiraDb.isPresent()) {
            throw new DadosDeCarteiraJaExistemException();
        }

    }
}
