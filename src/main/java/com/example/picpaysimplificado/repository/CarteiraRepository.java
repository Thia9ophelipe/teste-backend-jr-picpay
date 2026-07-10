package com.example.picpaysimplificado.repository;

import com.example.picpaysimplificado.entity.Carteira;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

    Optional<Carteira> findByCpfCnpjOrEmail(String cpfCnpj, String email);
}
