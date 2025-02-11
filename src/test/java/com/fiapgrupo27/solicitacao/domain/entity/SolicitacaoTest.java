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
        solicitacao = new Solicitacao(1L, LocalDateTime.now(), "email" );
    }

    @Test
    void testGettersAndSetters() {
        // Teste dos getters e setters
        assertEquals(1L, solicitacao.getIdSolicitacao());
        assertNotNull(solicitacao.getDataInclusao());
        assertNotNull(solicitacao.getEmail());

        // Alterando valores
        solicitacao.setIdSolicitacao(3L);
        solicitacao.setDataInclusao(LocalDateTime.of(2025, 2, 9, 12, 0, 0, 0));
        solicitacao.setEmail("email1");

        // Verificando se as alterações funcionaram
        assertEquals(3L, solicitacao.getIdSolicitacao());
        assertEquals("email1", solicitacao.getEmail());
        assertEquals(LocalDateTime.of(2025, 2, 9, 12, 0, 0, 0), solicitacao.getDataInclusao());
    }

    @Test
    void testConstructor() {
        // Testando o construtor
        LocalDateTime dataInclusao = LocalDateTime.now();
        Solicitacao solicitacao = new Solicitacao(1L, dataInclusao, "email" );

        assertEquals(1L, solicitacao.getIdSolicitacao());
        assertEquals(dataInclusao, solicitacao.getDataInclusao());
        assertEquals("email", solicitacao.getEmail());
    }




}
