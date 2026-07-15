package com.example.picpaysimplificado.config;


import com.example.picpaysimplificado.entity.EnumTipoDeCarteira;
import com.example.picpaysimplificado.repository.TipoDeCarteiraRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final TipoDeCarteiraRepository tipoDeCarteiraRepository;

    public DataLoader(TipoDeCarteiraRepository tipoDeCarteiraRepository) {
        this.tipoDeCarteiraRepository = tipoDeCarteiraRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Arrays.stream(EnumTipoDeCarteira.values())
                .forEach(tipoDeCarteira -> tipoDeCarteiraRepository
                        .save(tipoDeCarteira.get()));
    }
}
