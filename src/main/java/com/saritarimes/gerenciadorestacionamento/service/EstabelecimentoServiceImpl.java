package com.saritarimes.gerenciadorestacionamento.service;

import com.saritarimes.gerenciadorestacionamento.model.Estabelecimento;
import com.saritarimes.gerenciadorestacionamento.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EstabelecimentoServiceImpl implements EstabelecimentoService {
    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @Transactional
    public void salvarEstabelecimento(Estabelecimento estabelecimento) {
        estabelecimentoRepository.save(estabelecimento);
    }

    @Transactional
    public void adicionarEstabelecimento(Estabelecimento estabelecimento) {
        Estabelecimento novoEstabelecimento = new Estabelecimento(
                estabelecimento.getNome(),
                estabelecimento.getCnpj(),
                estabelecimento.getEndereco(),
                estabelecimento.getTelefone(),
                estabelecimento.getQuantidadeVagasMotos(),
                estabelecimento.getQuantidadeVagasCarros()
        );

        estabelecimentoRepository.save(novoEstabelecimento);

        ResponseEntity.status(HttpStatus.CREATED).body("Estabelecimento adicionado com sucesso.");
    }

    @Transactional
    public Estabelecimento acessarEstabelecimento(String buscaInserida, char tipoBusca) {
        Optional<Estabelecimento> estabelecimentoOptional;
        Estabelecimento estabelecimento = new Estabelecimento();

        if (tipoBusca == 'n')
            estabelecimentoOptional = estabelecimentoRepository.findByNome(buscaInserida);
        else if (tipoBusca == 'c')
            estabelecimentoOptional = estabelecimentoRepository.findByCnpj(buscaInserida);
        else if (tipoBusca == 't')
            estabelecimentoOptional = estabelecimentoRepository.findByTelefone(buscaInserida);
        else
            estabelecimentoOptional = Optional.empty();

        if (estabelecimentoOptional.isPresent())
            estabelecimento = estabelecimentoOptional.get();

        return estabelecimento;
    }

    @Transactional
    public void excluirEstabelecimento(String nome) {
        char variavelAcesso = 'n';

        estabelecimentoRepository.delete(acessarEstabelecimento(nome, variavelAcesso));
    }

}
