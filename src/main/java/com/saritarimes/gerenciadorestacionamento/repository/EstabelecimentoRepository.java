package com.saritarimes.gerenciadorestacionamento.repository;

import com.saritarimes.gerenciadorestacionamento.model.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Integer> {
    Optional<Estabelecimento> findByNomeIgnoreCase(String nome);

    Optional<Estabelecimento> findByCnpj(String cnpj);

    Optional<Estabelecimento> findByTelefone(String telefone);
}
