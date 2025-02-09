package com.fiapgrupo27.solicitacao.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SolicitacaoTest {

    private Solicitacao solicitacao;

    @BeforeEach
    void setUp() {
        // Criação de um objeto Solicitacao antes de cada teste
        solicitacao = new Solicitacao(1L, 2L, "PENDENTE", LocalDateTime.now());
    }

    @Test
    void testGettersAndSetters() {
        // Teste dos getters e setters
        assertEquals(1L, solicitacao.getIdSolicitacao());
        assertEquals(2L, solicitacao.getIdSolicitante());
        assertEquals("PENDENTE", solicitacao.getStatus());
        assertNotNull(solicitacao.getDataInclusao());

        // Alterando valores
        solicitacao.setIdSolicitacao(3L);
        solicitacao.setIdSolicitante(4L);
        solicitacao.setStatus("CONCLUIDO");
        solicitacao.setDataInclusao(LocalDateTime.of(2025, 2, 9, 12, 0, 0, 0));

        // Verificando se as alterações funcionaram
        assertEquals(3L, solicitacao.getIdSolicitacao());
        assertEquals(4L, solicitacao.getIdSolicitante());
        assertEquals("CONCLUIDO", solicitacao.getStatus());
        assertEquals(LocalDateTime.of(2025, 2, 9, 12, 0, 0, 0), solicitacao.getDataInclusao());
    }

    @Test
    void testConstructor() {
        // Testando o construtor
        LocalDateTime dataInclusao = LocalDateTime.now();
        Solicitacao solicitacao = new Solicitacao(1L, 2L, "PENDENTE", dataInclusao);

        assertEquals(1L, solicitacao.getIdSolicitacao());
        assertEquals(2L, solicitacao.getIdSolicitante());
        assertEquals("PENDENTE", solicitacao.getStatus());
        assertEquals(dataInclusao, solicitacao.getDataInclusao());
    }

    @Test
    void testValidStatus() {
        // Testando status válido
        Solicitacao solicitacao = new Solicitacao(1L, 2L, "PENDENTE", LocalDateTime.now());
        assertEquals("PENDENTE", solicitacao.getStatus());
    }

    @Test
    void testInvalidStatus() {
        // Testando status inválido
        Solicitacao solicitacao = new Solicitacao(1L, 2L, "INVALIDO", LocalDateTime.now());
        assertNotEquals("PENDENTE", solicitacao.getStatus());
    }
}
