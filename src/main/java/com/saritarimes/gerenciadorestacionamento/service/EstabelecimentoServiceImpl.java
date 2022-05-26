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

    /* ---------- Metodos ---------- */
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
                estabelecimento.getQuantidadeVagasCarros(),
                estabelecimento.getQuantidadeMotosEstacionadas(),
                estabelecimento.getQuantidadeCarrosEstacionados()
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
            estabelecimentoEncontrado.setQuantidadeMotosEstacionadas(estabelecimento.getQuantidadeMotosEstacionadas());
            estabelecimentoEncontrado.setQuantidadeCarrosEstacionados(estabelecimento.getQuantidadeCarrosEstacionados());

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

    @Transactional
    public void controlarEntradaSaida(Estabelecimento estabelecimento, char entradaOuSaida, char tipoVeiculo) {
        int quantidadeVeiculosAtual;

        if (tipoVeiculo == 'm' || tipoVeiculo == 'M') {
            quantidadeVeiculosAtual = estabelecimento.getQuantidadeMotosEstacionadas();

            if (entradaOuSaida == 'e') { // entrada de veiculo
                if (quantidadeVeiculosAtual < 0) // garantindo que nao tenhamos um numero negativo de motos
                    quantidadeVeiculosAtual = 0;

                estabelecimento.setQuantidadeMotosEstacionadas(quantidadeVeiculosAtual + 1);
            }
            else if (entradaOuSaida == 's') { // saida de veiculo
                if (quantidadeVeiculosAtual <= 0) // garantindo que nao tenhamos um numero negativo de motos
                    quantidadeVeiculosAtual = 1;

                estabelecimento.setQuantidadeMotosEstacionadas(quantidadeVeiculosAtual - 1);
            }
        }
        else if (tipoVeiculo == 'c' || tipoVeiculo == 'C') {
            quantidadeVeiculosAtual = estabelecimento.getQuantidadeCarrosEstacionados();

            if (entradaOuSaida == 'e') { // entrada de veiculo
                if (quantidadeVeiculosAtual < 0) // garantindo que nao tenhamos um numero negativo de carros
                    quantidadeVeiculosAtual = 0;

                estabelecimento.setQuantidadeCarrosEstacionados(quantidadeVeiculosAtual + 1);
            }
            else if (entradaOuSaida == 's') { // saida de veiculo
                if (quantidadeVeiculosAtual <= 0) // garantindo que nao tenhamos um numero negativo de carros
                    quantidadeVeiculosAtual = 1;

                estabelecimento.setQuantidadeCarrosEstacionados(quantidadeVeiculosAtual - 1);
            }
        }

        salvarEstabelecimento(estabelecimento);

//        int quantidadeVeiculosAtual;
//        if (novoVeiculo.getTipo() == 'm' || veiculo.getTipo() == 'M') {
//            quantidadeVeiculosAtual = estabelecimentoReferente.getQuantidadeMotosEstacionadas();
//
//            if (quantidadeVeiculosAtual < 0) // garantindo que nao tenhamos um numero negativo de motos
//                quantidadeVeiculosAtual = 0;
//
//            // Somando 1 na quantidade de motos do estabelecimento em questao
//            estabelecimentoReferente.setQuantidadeMotosEstacionadas(quantidadeVeiculosAtual + 1);
//        }
//        else if (novoVeiculo.getTipo() == 'c' || novoVeiculo.getTipo() == 'C') {
//            quantidadeVeiculosAtual = estabelecimentoReferente.getQuantidadeCarrosEstacionados();
//
//            if (quantidadeVeiculosAtual < 0) // garantindo que nao tenhamos um numero negativo de carros
//                quantidadeVeiculosAtual = 0;
//
//            // Somando 1 na quantidade de carros do estabelecimento em questao
//            estabelecimentoReferente.setQuantidadeCarrosEstacionados(quantidadeVeiculosAtual + 1);
//        }

//            int quantidadeVeiculosAtual;
//            if (veiculo.getTipo() == 'm' || veiculo.getTipo() == 'M') {
//                quantidadeVeiculosAtual = estabelecimentoReferente.getQuantidadeMotosEstacionadas();
//
//                if (quantidadeVeiculosAtual <= 0) // garantindo que nao tenhamos um numero negativo de motos
//                    quantidadeVeiculosAtual = 1;
//
//                // Subtraindo 1 na quantidade de motos do estabelecimento em questao
//                estabelecimentoReferente.setQuantidadeMotosEstacionadas(quantidadeVeiculosAtual - 1);
//            }
//            else if (veiculo.getTipo() == 'c' || veiculo.getTipo() == 'C') {
//                quantidadeVeiculosAtual = estabelecimentoReferente.getQuantidadeCarrosEstacionados();
//
//                if (quantidadeVeiculosAtual <= 0) // garantindo que nao tenhamos um numero negativo de carros
//                    quantidadeVeiculosAtual = 1;
//
//                // Subtraindo 1 na quantidade de carros do estabelecimento em questao
//                estabelecimentoReferente.setQuantidadeCarrosEstacionados(quantidadeVeiculosAtual - 1);
//            }
    }
}
