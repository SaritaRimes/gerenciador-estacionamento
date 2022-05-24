package com.saritarimes.gerenciadorestacionamento.service;

import com.saritarimes.gerenciadorestacionamento.model.Veiculo;
import com.saritarimes.gerenciadorestacionamento.repository.VeiculoRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VeiculoServiceImpl implements VeiculoService{
    @Autowired
    private VeiculoRepository veiculoRepository;

    @Transactional
    public void salvarVeiculo(Veiculo veiculo) {
        veiculoRepository.save(veiculo);
    }

    @Transactional
    public boolean verificarExistenciaVeiculo(Veiculo veiculo) {
        return veiculo != null;
    }

    @Transactional
    public ResponseEntity<String> adicionarVeiculo(Veiculo veiculo) {
        Veiculo novoVeiculo = new Veiculo(
                WordUtils.capitalize(veiculo.getMarca()),
                WordUtils.capitalize(veiculo.getModelo()),
                veiculo.getCor(),
                veiculo.getPlaca().toUpperCase(),
                veiculo.getTipo()
        );

        salvarVeiculo(novoVeiculo);

        return ResponseEntity.status(HttpStatus.CREATED).body("Veículo adicionado com sucesso.");
    }

    @Transactional
    public Veiculo acessarVeiculo(String buscaInserida, char tipoBusca) {
        Optional<Veiculo> veiculoOptional;
        Veiculo veiculo = null;

        if (tipoBusca == 'p') // busca por placa
            veiculoOptional = veiculoRepository.findByPlacaIgnoreCase(buscaInserida);
        else
            veiculoOptional = Optional.empty(); // ELE FICA PRESENT?

        if (veiculoOptional.isPresent())
            veiculo = veiculoOptional.get();

        return veiculo;
    }

    @Transactional
    public void modificarVeiculo(Veiculo veiculo) {
        char variavelAcesso = 'p';
        Veiculo veiculoEncontrado = acessarVeiculo(veiculo.getPlaca(), variavelAcesso);

        if (verificarExistenciaVeiculo(veiculo)) {
            veiculoEncontrado.setMarca(WordUtils.capitalize(veiculo.getMarca()));
            veiculoEncontrado.setModelo(WordUtils.capitalize(veiculo.getModelo()));
            veiculoEncontrado.setCor(veiculo.getCor());
            veiculoEncontrado.setPlaca(veiculo.getPlaca().toUpperCase());
            veiculoEncontrado.setTipo(veiculo.getTipo());

            salvarVeiculo(veiculoEncontrado);

            ResponseEntity.status(HttpStatus.ACCEPTED).body("Veículo atualizado com sucesso.");
        }
        else
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veículo não encontrado.");
    }

    @Transactional
    public void excluirVeiculo(String placa) {
        char variavelAcesso = 'p';
        Veiculo veiculo = acessarVeiculo(placa, variavelAcesso);

        if (verificarExistenciaVeiculo(veiculo)) {
            veiculoRepository.delete(veiculo);
            ResponseEntity.status(HttpStatus.ACCEPTED).body("O veículo foi removido.");
        }
        else
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veículo não encontrado.");

    }
}
