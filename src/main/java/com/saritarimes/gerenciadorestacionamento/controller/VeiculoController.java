package com.saritarimes.gerenciadorestacionamento.controller;

import com.saritarimes.gerenciadorestacionamento.model.Estabelecimento;
import com.saritarimes.gerenciadorestacionamento.model.Veiculo;
import com.saritarimes.gerenciadorestacionamento.service.EstabelecimentoService;
import com.saritarimes.gerenciadorestacionamento.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/veiculos")
public class VeiculoController {
    @Autowired
    VeiculoService veiculoService;
    @Autowired
    EstabelecimentoService estabelecimentoService;

    /*
     *   Cria (cadastra) um novo veiculo
     */
    @PostMapping(path = "/cadastra-veiculo")
    public void cadastrarVeiculo(@RequestBody Veiculo veiculo,
                                 @RequestParam String nomeEstabelecimento) {
        veiculoService.adicionarVeiculo(veiculo, nomeEstabelecimento);
    }

    /*
     *   Acessa um veiculo
     */
    @GetMapping(path = "/acessa-veiculo-placa")
    public Veiculo acessarVeiculoPorPlaca(@RequestParam String placa) {
        char variavelAcesso = 'p';
        Veiculo veiculo = veiculoService.acessarVeiculo(placa, variavelAcesso);

        if (!veiculoService.verificarExistenciaVeiculo(veiculo))
            throw new IllegalArgumentException("Veículo não encontrado.");
//            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veículo não encontrado.");

        return veiculo;
    }

    /*
     *   Atualiza dados de um veiculo
     */
    @PutMapping(path = "/atualiza-veiculo")
    public void atualizarVeiculo(@RequestBody Veiculo veiculo) {
        char variavelAcesso = 'p';

        veiculoService.modificarVeiculo(veiculo);
    }

    /*
     *   Exclui veiculo
     */
    @DeleteMapping(path = "/remove-veiculo")
    public void removerVeiculo(@RequestParam String placa) {
        veiculoService.excluirVeiculo(placa);
    }

}
