package com.saritarimes.gerenciadorestacionamento.service;

import com.saritarimes.gerenciadorestacionamento.model.Estabelecimento;
import com.saritarimes.gerenciadorestacionamento.repository.EstabelecimentoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class EstabelecimentoServiceImplTest {
    private EstabelecimentoServiceImpl estabelecimentoService;
    @Mock
    private EstabelecimentoRepository estabelecimentoRepositoryMock;
    private AutoCloseable closeable;

    @BeforeEach
    public void beforeEach() {
        closeable = MockitoAnnotations.openMocks(this);
        this.estabelecimentoService = new EstabelecimentoServiceImpl(estabelecimentoRepositoryMock);
    }

    @AfterEach
    public void closeService() throws Exception {
        closeable.close();
    }

    private final Estabelecimento estabelecimentoNulo = null;
    private final Estabelecimento estabelecimentoTeste = new Estabelecimento(
            "Carro Seguro",
            "11122233344455",
            "Rua João da Silva",
            "2390908080",
            15,
            10,
            0,
            0
    );


    /* Verificar existencia de estabelecimento */
    @Test
    void deveRetornarFalseSeOEstabelecimentoNaoExistir() {
        boolean teste = estabelecimentoService.verificarExistenciaEstabelecimento(estabelecimentoNulo);

        assertFalse(teste);
    }

    @Test
    void deveRetornarTrueSeOEstabelecimentoExistir() {
        boolean teste = estabelecimentoService.verificarExistenciaEstabelecimento(estabelecimentoTeste);

        assertTrue(teste);
    }
}