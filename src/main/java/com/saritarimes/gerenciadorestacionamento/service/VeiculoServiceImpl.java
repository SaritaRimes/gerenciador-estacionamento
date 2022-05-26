package com.saritarimes.gerenciadorestacionamento.service;

import com.saritarimes.gerenciadorestacionamento.model.Estabelecimento;
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
    @Autowired
    private EstabelecimentoService estabelecimentoService;


    /* ---------- Metodos ---------- */
    @Transactional
    public void salvarVeiculo(Veiculo veiculo) {
        veiculoRepository.save(veiculo);
    }

    @Transactional
    public boolean verificarExistenciaVeiculo(Veiculo veiculo) {
        return veiculo != null;
    }

    @Transactional
    public ResponseEntity<String> adicionarVeiculo(Veiculo veiculo, String nomeEstabelecimento) {
        Estabelecimento estabelecimentoReferente =
                estabelecimentoService.acessarEstabelecimento(nomeEstabelecimento, 'n');

        /* Verificando se o estabelecimento foi encontrado */
        if (!estabelecimentoService.verificarExistenciaEstabelecimento(estabelecimentoReferente))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estabelecimento não encontrado.");

        /* Verificando se ha vagas disponiveis no estabelecimento referente */
        if (veiculo.getTipo() == 'm' || veiculo.getTipo() == 'M') {
            if (estabelecimentoReferente.getQuantidadeVagasMotos() <=
                    estabelecimentoReferente.getQuantidadeMotosEstacionadas())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não há vagas para motos no estacionamento.");
        }
        else if (veiculo.getTipo() == 'c' || veiculo.getTipo() == 'C') {
            if (estabelecimentoReferente.getQuantidadeVagasCarros() <=
                    estabelecimentoReferente.getQuantidadeCarrosEstacionados())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não há vagas para carros no estacionamento.");
        }

        Veiculo novoVeiculo = new Veiculo(
                WordUtils.capitalize(veiculo.getMarca()),
                WordUtils.capitalize(veiculo.getModelo()),
                veiculo.getCor(),
                veiculo.getPlaca().toUpperCase(),
                veiculo.getTipo()
        );
        novoVeiculo.setEstabelecimento(estabelecimentoReferente);
        salvarVeiculo(novoVeiculo);

        /* Incrementando o numero de motos ou carros estacionados */
        char tipoVeiculo = novoVeiculo.getTipo();
        estabelecimentoService.controlarEntradaSaida(estabelecimentoReferente, 'e', tipoVeiculo);

        estabelecimentoService.salvarEstabelecimento(estabelecimentoReferente); // salva os dados do estabelecimento

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
            veiculoEncontrado.setEstabelecimento(veiculo.getEstabelecimento());
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
            Estabelecimento estabelecimentoReferente = veiculo.getEstabelecimento();

            /* Decrementando o numero de motos ou carros estacionados */
            char tipoVeiculo = veiculo.getTipo();
            estabelecimentoService.controlarEntradaSaida(estabelecimentoReferente, 's', tipoVeiculo);

            estabelecimentoService.salvarEstabelecimento(estabelecimentoReferente); // salva os dados do estabelecimento

            veiculoRepository.delete(veiculo); // remove veiculo
            ResponseEntity.status(HttpStatus.ACCEPTED).body("O veículo foi removido.");
        }
        else
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veículo não encontrado.");
    }
}
