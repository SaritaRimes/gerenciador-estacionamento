package com.saritarimes.gerenciadorestacionamento.service;

import com.saritarimes.gerenciadorestacionamento.model.Estabelecimento;

public interface EstabelecimentoService {
    public void salvarEstabelecimento(Estabelecimento estabelecimento);
    public void adicionarEstabelecimento(Estabelecimento estabelecimento);
    public Estabelecimento acessarEstabelecimento(String buscaInserida, char tipoBusca);
    public void excluirEstabelecimento(String nome);
}
