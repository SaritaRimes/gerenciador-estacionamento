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
    EstabelecimentoService estabelecimentoService;

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

        return estabelecimentoService.acessarEstabelecimento(nome, variavelAcesso);
    }

    @GetMapping(path = "/acessa-estabelecimento-cnpj")
    public Estabelecimento acessarEstabelecimentoPorCnpj(@RequestParam String cnpj) {
        char variavelAcesso = 'c';

        return estabelecimentoService.acessarEstabelecimento(cnpj, variavelAcesso);
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
    public ResponseEntity<?> atualizarEstabelecimento(@RequestBody Estabelecimento estabelecimento) {
        char variavelAcesso = 'n';

        Estabelecimento estabelecimentoEncontrado = estabelecimentoService.acessarEstabelecimento(estabelecimento.getNome(), variavelAcesso);

        if (estabelecimentoEncontrado != null) {
            estabelecimentoEncontrado.setNome(estabelecimento.getNome());
            estabelecimentoEncontrado.setCnpj(estabelecimento.getCnpj());
            estabelecimentoEncontrado.setEndereco(estabelecimento.getEndereco());
            estabelecimentoEncontrado.setTelefone(estabelecimento.getTelefone());
            estabelecimentoEncontrado.setQuantidadeVagasMotos(estabelecimento.getQuantidadeVagasMotos());
            estabelecimentoEncontrado.setQuantidadeVagasCarros(estabelecimento.getQuantidadeVagasCarros());

            estabelecimentoService.salvarEstabelecimento(estabelecimentoEncontrado);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Estabelecimento atualizado com sucesso.");
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estabelecimento não encontrado.");
    }

    /*
    *   Exclui estabelecimento
    */
    public void removerEstabelecimento(@RequestParam String nome) {
        estabelecimentoService.excluirEstabelecimento(nome);
    }
}
