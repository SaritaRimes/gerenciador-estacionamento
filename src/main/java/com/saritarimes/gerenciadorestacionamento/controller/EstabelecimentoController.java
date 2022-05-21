package com.saritarimes.gerenciadorestacionamento.controller;

import com.saritarimes.gerenciadorestacionamento.model.Estabelecimento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/estabelecimento")
public class EstabelecimentoController {
    /*
    *   Cria (cadastra) um novo estabelecimento
    */
    @PostMapping(path = "/cadastra-estabelecimento")
    public void cadastrarEstabelecimento(@RequestParam String nome, @RequestParam String cnpj,
                                         @RequestParam String endereco, @RequestParam String telefone,
                                         @RequestParam int quantidadeVagasMotos,
                                         @RequestParam int quantidadeVagasCarros) {

    }

    /*
    *   Atualiza dados de um estabelecimento
    */

    /*
    *   Exclui estabelecimento
    */
}
