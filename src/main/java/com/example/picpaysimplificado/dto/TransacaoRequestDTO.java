package com.example.picpaysimplificado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransacaoRequestDTO(

        this.valor = valor;
        this.idPagador = idPagador;
        this.idRecebedor = idRecebedor;
    }

    @Override
    public BigDecimal valor() {
        return valor;
    }

    @Override
        return idPagador;
    }

    @Override
        return idRecebedor;
    }
}
