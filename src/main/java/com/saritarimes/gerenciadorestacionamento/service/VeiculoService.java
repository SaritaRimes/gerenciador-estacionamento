package com.saritarimes.gerenciadorestacionamento.service;

import com.saritarimes.gerenciadorestacionamento.model.Veiculo;
import org.springframework.http.ResponseEntity;

public interface VeiculoService {
    public void salvarVeiculo(Veiculo veiculo);
    public boolean verificarExistenciaVeiculo(Veiculo veiculo);
    public ResponseEntity<String> adicionarVeiculo(Veiculo veiculo);
    public Veiculo acessarVeiculo(String buscaInserida, char tipoBusca);
    public void modificarVeiculo(Veiculo veiculo);
    public void excluirVeiculo(String placa);

}
