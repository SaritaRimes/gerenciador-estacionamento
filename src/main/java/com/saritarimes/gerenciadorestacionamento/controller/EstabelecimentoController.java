package com.saritarimes.gerenciadorestacionamento.controller;

import com.saritarimes.gerenciadorestacionamento.model.Estabelecimento;
import com.saritarimes.gerenciadorestacionamento.service.EstabelecimentoService;
import com.saritarimes.gerenciadorestacionamento.service.EstabelecimentoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/estabelecimento")
public class EstabelecimentoController {
    @Autowired
    private EstabelecimentoService estabelecimentoService;

    /*
     *   Cria (cadastra) um novo estabelecimento
     */
    @PostMapping(path = "/cadastra-estabelecimento")
    public void cadastrarEstabelecimento(@RequestBody Estabelecimento estabelecimento) {
        estabelecimentoService.adicionarEstabelecimento(estabelecimento);
    }

    /*
     *   Acessa um estabelecimento
     */
    @GetMapping(path = "/acessa-estabelecimento-nome")
    public Estabelecimento acessarEstabelecimentoPorNome(@RequestParam String nome) {
        char variavelAcesso = 'n';
        Estabelecimento estabelecimento = estabelecimentoService.acessarEstabelecimento(nome, variavelAcesso);

        if (!estabelecimentoService.verificarExistenciaEstabelecimento(estabelecimento))
             ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estabelecimento não encontrado.");

        return estabelecimento;
    }

    @GetMapping(path = "/acessa-estabelecimento-cnpj")
    public Estabelecimento acessarEstabelecimentoPorCnpj(@RequestParam String cnpj) {
        char variavelAcesso = 'c';
        Estabelecimento estabelecimento = estabelecimentoService.acessarEstabelecimento(cnpj, variavelAcesso);

        if (!estabelecimentoService.verificarExistenciaEstabelecimento(estabelecimento))
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estabelecimento não encontrado.");

        return estabelecimento;
    }

    @GetMapping(path = "/acessa-estabelecimento-telefone")
    public Estabelecimento acessarEstabelecimentoPorTelefone(@RequestParam String telefone) {
        char variavelAcesso = 't';

        return estabelecimentoService.acessarEstabelecimento(telefone, variavelAcesso);
    }

    /*
     *   Atualiza dados de um estabelecimento
     */
    @PutMapping(path = "/atualiza-estabelecimento")
    public void atualizarEstabelecimento(@RequestBody Estabelecimento estabelecimento) {
        estabelecimentoService.modificarEstabelecimento(estabelecimento);
    }

    /*
     *   Exclui estabelecimento
     */
    @DeleteMapping(path = "/remove-estabelecimento")
    public void removerEstabelecimento(@RequestParam String nome) {
        estabelecimentoService.excluirEstabelecimento(nome);
    }
}
