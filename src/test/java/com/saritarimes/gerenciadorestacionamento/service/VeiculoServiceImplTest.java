package com.saritarimes.gerenciadorestacionamento.service;

import com.saritarimes.gerenciadorestacionamento.model.Veiculo;
import com.saritarimes.gerenciadorestacionamento.repository.VeiculoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class VeiculoServiceImplTest {

    private VeiculoServiceImpl veiculoService;
    @Mock
    private VeiculoRepository veiculoRepositoryMock;
    private AutoCloseable closeable;

    @BeforeEach
    public void beforeEach() {
        closeable = MockitoAnnotations.openMocks(this);
        this.veiculoService = new VeiculoServiceImpl(veiculoRepositoryMock);
    }

    @AfterEach
    public void closeService() throws Exception {
        closeable.close();
    }

    private final Veiculo veiculoNulo = null;
    private final Veiculo veiculoTeste = new Veiculo(
            "Volkswagen",
            "Fox",
            "branca",
            "knb1234",
            'c'
    );


    @Test
    void deveRetornarFalseSeOVeiculoNaoExistir() {
        boolean teste = veiculoService.verificarExistenciaVeiculo(veiculoNulo);

        assertFalse(teste);
    }

    void deveRetornarTrueSeOVeiculoExistir() {
        boolean teste = veiculoService.verificarExistenciaVeiculo(veiculoTeste);

        assertTrue(teste);
    }

    @Test
    void adicionarVeiculo() {
    }

    @Test
    void acessarVeiculo() {
    }

    @Test
    void modificarVeiculo() {
    }

    @Test
    void excluirVeiculo() {
    }
}