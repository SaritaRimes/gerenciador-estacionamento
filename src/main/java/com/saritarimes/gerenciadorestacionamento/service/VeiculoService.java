package com.saritarimes.gerenciadorestacionamento.service;

import com.saritarimes.gerenciadorestacionamento.model.Veiculo;

public interface VeiculoService {
    void salvarVeiculo(Veiculo veiculo);
    boolean verificarExistenciaVeiculo(Veiculo veiculo);
    void adicionarVeiculo(Veiculo veiculo, String nomeEstabelecimento);
    Veiculo acessarVeiculo(String buscaInserida, char tipoBusca);
    void modificarVeiculo(Veiculo veiculo);
    void excluirVeiculo(String placa);
}
