package com.saritarimes.gerenciadorestacionamento.repository;

import com.saritarimes.gerenciadorestacionamento.model.Estabelecimento;
import com.saritarimes.gerenciadorestacionamento.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Estabelecimento, Integer> {
}
