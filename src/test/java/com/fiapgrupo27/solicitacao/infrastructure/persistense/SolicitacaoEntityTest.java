package com.fiapgrupo27.solicitacao.infrastructure.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoEntity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

class SolicitacaoEntityTest {

    private SolicitacaoEntity solicitacaoEntity;

    @BeforeEach
    void setUp() {
        // Criação de um objeto SolicitacaoEntity antes de cada teste
        solicitacaoEntity = new SolicitacaoEntity(1L, 1L, "PENDENTE", LocalDateTime.now());
    }

    @Test
    void testAtualizarStatus_Valido() {
        // Teste com status válido
        solicitacaoEntity.atualizarStatus("CONCLUIDO");

        // Verifica se o status foi atualizado corretamente
        assertEquals("CONCLUIDO", solicitacaoEntity.getStatus());
    }

    @Test
    void testAtualizarStatus_Invalido() {
        // Teste com status inválido
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            solicitacaoEntity.atualizarStatus("INVALIDO");
        });

        // Verifica se a mensagem de erro é a esperada
        assertEquals("Status inválido", exception.getMessage());
    }

}
