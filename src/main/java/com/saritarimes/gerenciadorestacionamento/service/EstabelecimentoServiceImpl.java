package com.saritarimes.gerenciadorestacionamento.service;

import com.saritarimes.gerenciadorestacionamento.model.Estabelecimento;
import com.saritarimes.gerenciadorestacionamento.repository.EstabelecimentoRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

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
    public boolean verificarExistenciaEstabelecimento(Estabelecimento estabelecimento) {
        return estabelecimento != null;
    }

    @Transactional
    public void adicionarEstabelecimento(Estabelecimento estabelecimento) {
        Estabelecimento novoEstabelecimento = new Estabelecimento(
                WordUtils.capitalize(estabelecimento.getNome()),
                estabelecimento.getCnpj(),
                WordUtils.capitalize(estabelecimento.getEndereco()),
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
        Estabelecimento estabelecimento = null;

        if (tipoBusca == 'n') // busca por nome
            estabelecimentoOptional = estabelecimentoRepository.findByNomeIgnoreCase(buscaInserida);
        else if (tipoBusca == 'c') // busca por cnpj
            estabelecimentoOptional = estabelecimentoRepository.findByCnpj(buscaInserida);
        else if (tipoBusca == 't') // busca por telefone
            estabelecimentoOptional = estabelecimentoRepository.findByTelefone(buscaInserida);
        else
            estabelecimentoOptional = Optional.empty();

        if (estabelecimentoOptional.isPresent())
            estabelecimento = estabelecimentoOptional.get();

        return estabelecimento;
    }

    @Transactional
    public void modificarEstabelecimento(Estabelecimento estabelecimento) {
        char variavelAcesso = 'n';
        Estabelecimento estabelecimentoEncontrado = acessarEstabelecimento(estabelecimento.getNome(), variavelAcesso);

        if (verificarExistenciaEstabelecimento(estabelecimentoEncontrado)) {
            estabelecimentoEncontrado.setNome(WordUtils.capitalize(estabelecimento.getNome()));
            estabelecimentoEncontrado.setCnpj(estabelecimento.getCnpj());
            estabelecimentoEncontrado.setEndereco(WordUtils.capitalize(estabelecimento.getEndereco()));
            estabelecimentoEncontrado.setTelefone(estabelecimento.getTelefone());
            estabelecimentoEncontrado.setQuantidadeVagasMotos(estabelecimento.getQuantidadeVagasMotos());
            estabelecimentoEncontrado.setQuantidadeVagasCarros(estabelecimento.getQuantidadeVagasCarros());

            salvarEstabelecimento(estabelecimentoEncontrado);

            ResponseEntity.status(HttpStatus.ACCEPTED).body("Estabelecimento atualizado com sucesso.");
        }
        else
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estabelecimento não encontrado.");
    }

    @Transactional
    public void excluirEstabelecimento(String nome) {
        char variavelAcesso = 'n';
        Estabelecimento estabelecimento = acessarEstabelecimento(nome, variavelAcesso);

        if (verificarExistenciaEstabelecimento(estabelecimento)) {
            estabelecimentoRepository.delete(estabelecimento);
            ResponseEntity.status(HttpStatus.ACCEPTED).body("O estabelecimento foi removido.");
        }
        else
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estabelecimento não encontrado.");
    }
}
