package com.saritarimes.gerenciadorestacionamento.repository;

import com.saritarimes.gerenciadorestacionamento.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {
    Optional<Veiculo> findByPlacaIgnoreCase(String placa);
}
