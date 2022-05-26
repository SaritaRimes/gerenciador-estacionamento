package com.saritarimes.gerenciadorestacionamento.service;

import com.saritarimes.gerenciadorestacionamento.model.Estabelecimento;

public interface EstabelecimentoService {
    public void salvarEstabelecimento(Estabelecimento estabelecimento);
    public boolean verificarExistenciaEstabelecimento(Estabelecimento estabelecimento);
    public void adicionarEstabelecimento(Estabelecimento estabelecimento);
    public Estabelecimento acessarEstabelecimento(String buscaInserida, char tipoBusca);
    public void modificarEstabelecimento(Estabelecimento estabelecimento);
    public void excluirEstabelecimento(String nome);
    public void controlarEntradaSaida(Estabelecimento estabelecimento, char entradaOuSaida, char tipoVeiculo);
}
