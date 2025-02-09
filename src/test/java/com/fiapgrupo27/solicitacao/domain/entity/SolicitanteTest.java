package com.fiapgrupo27.solicitacao.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolicitanteTest {

    private Solicitante solicitante;

    @BeforeEach
    void setUp() {
        // Inicializando o objeto solicitante antes de cada teste
        solicitante = new Solicitante();
        solicitante.setIdSolicitante(1L);
        solicitante.setNomeSolicitante("João Silva");
        solicitante.setEmail("joao.silva@example.com");
    }

    @Test
    void testGettersAndSetters() {
        // Testando se os getters e setters estão funcionando corretamente
        assertEquals(1L, solicitante.getIdSolicitante());
        assertEquals("João Silva", solicitante.getNomeSolicitante());
        assertEquals("joao.silva@example.com", solicitante.getEmail());

        // Alterando valores
        solicitante.setIdSolicitante(2L);
        solicitante.setNomeSolicitante("Maria Oliveira");
        solicitante.setEmail("maria.oliveira@example.com");

        // Verificando se as alterações foram bem-sucedidas
        assertEquals(2L, solicitante.getIdSolicitante());
        assertEquals("Maria Oliveira", solicitante.getNomeSolicitante());
        assertEquals("maria.oliveira@example.com", solicitante.getEmail());
    }

    @Test
    void testConstructor() {
        // Testando a criação do objeto solicitante
        Solicitante solicitante = new Solicitante();
        solicitante.setIdSolicitante(3L);
        solicitante.setNomeSolicitante("Carlos Pereira");
        solicitante.setEmail("carlos.pereira@example.com");

        assertEquals(3L, solicitante.getIdSolicitante());
        assertEquals("Carlos Pereira", solicitante.getNomeSolicitante());
        assertEquals("carlos.pereira@example.com", solicitante.getEmail());
    }
}
