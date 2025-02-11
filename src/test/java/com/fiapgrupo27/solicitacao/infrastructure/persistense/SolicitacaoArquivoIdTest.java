package com.fiapgrupo27.solicitacao.infrastructure.persistense;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoArquivoId;

import static org.junit.jupiter.api.Assertions.*;

class SolicitacaoArquivoIdTest {

    private SolicitacaoArquivoId solicitacaoArquivoId1;
    private SolicitacaoArquivoId solicitacaoArquivoId2;

    @BeforeEach
    void setUp() {
        solicitacaoArquivoId1 = new SolicitacaoArquivoId(1L, 2L);
        solicitacaoArquivoId2 = new SolicitacaoArquivoId(1L, 2L);
    }

    @Test
    void testEqualsAndHashCode() {
        // Verifica que dois objetos com os mesmos valores são iguais
        assertEquals(solicitacaoArquivoId1, solicitacaoArquivoId2);
        assertEquals(solicitacaoArquivoId1.hashCode(), solicitacaoArquivoId2.hashCode());
    }

    @Test
    void testNotEquals() {
        // Verifica que objetos com valores diferentes não são iguais
        SolicitacaoArquivoId solicitacaoArquivoId3 = new SolicitacaoArquivoId(2L, 3L);
        assertNotEquals(solicitacaoArquivoId1, solicitacaoArquivoId3);
    }

    @Test
    void testGettersAndSetters() {
        // Verifica se os getters e setters estão funcionando corretamente
        solicitacaoArquivoId1.setIdSolicitacao(3L);
        solicitacaoArquivoId1.setIdArquivo(4L);

        assertEquals(3L, solicitacaoArquivoId1.getIdSolicitacao());
        assertEquals(4L, solicitacaoArquivoId1.getIdArquivo());
    }

    @Test
    void testDefaultConstructor() {
        // Verifica o construtor padrão
        SolicitacaoArquivoId solicitacaoArquivoId = new SolicitacaoArquivoId();
        assertNull(solicitacaoArquivoId.getIdSolicitacao());
        assertNull(solicitacaoArquivoId.getIdArquivo());
    }
}
