package com.saritarimes.gerenciadorestacionamento.controller;

import com.saritarimes.gerenciadorestacionamento.model.Veiculo;
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

    /*
     *   Cria (cadastra) um novo veiculo
     */
    @PostMapping(path = "/cadastra-veiculo")
    public void cadastrarVeiculo(@RequestBody Veiculo veiculo) {
        veiculoService.adicionarVeiculo(veiculo);
    }

    /*
     *   Acessa um veiculo
     */
    @GetMapping(path = "/acessa-veiculo-placa")
    public Veiculo acessarVeiculoPorPlaca(@RequestParam String placa) {
        char variavelAcesso = 'p';
        Veiculo veiculo = veiculoService.acessarVeiculo(placa, variavelAcesso);

        if (!veiculoService.verificarExistenciaVeiculo(veiculo))
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veículo não encontrado.");

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
