package com.saritarimes.gerenciadorestacionamento.service;

import com.saritarimes.gerenciadorestacionamento.model.Estabelecimento;

public interface EstabelecimentoService {
    void salvarEstabelecimento(Estabelecimento estabelecimento);
    boolean verificarExistenciaEstabelecimento(Estabelecimento estabelecimento);
    void adicionarEstabelecimento(Estabelecimento estabelecimento);
    Estabelecimento acessarEstabelecimento(String buscaInserida, char tipoBusca);
    void modificarEstabelecimento(Estabelecimento estabelecimento);
    void excluirEstabelecimento(String nome);
    void controlarEntradaSaida(Estabelecimento estabelecimento, char entradaOuSaida, char tipoVeiculo);
}
