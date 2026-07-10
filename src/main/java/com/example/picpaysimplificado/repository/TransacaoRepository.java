package com.example.picpaysimplificado.repository;

import com.example.picpaysimplificado.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransacaoRepository extends JpaRepository<Transacao, UUID> {
}
