package com.fiapgrupo27.solicitacao.infrastructure.persistense;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoArquivoEntity;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class SolicitacaoArquivoEntityTest {

    private SolicitacaoArquivoEntity solicitacaoArquivoEntity;

    @BeforeEach
    void setUp() {
        solicitacaoArquivoEntity = new SolicitacaoArquivoEntity(
                1L, // idSolicitacao
                "test-file.txt", // nomeArquivo
                "PENDENTE", // status
                LocalDateTime.now() // dataInclusao
        );
    }

    @Test
    void testGetIdArquivo() {
        // Espera que o idArquivo seja null, pois não foi setado
        assertNull(solicitacaoArquivoEntity.getIdArquivo());
    }

    @Test
    void testGetIdSolicitacao() {
        assertEquals(1L, solicitacaoArquivoEntity.getIdSolicitacao());
    }


    @Test
    void testGetNomeArquivo() {
        assertEquals("test-file.txt", solicitacaoArquivoEntity.getNomeArquivo());
    }

    @Test
    void testGetStatus() {
        assertEquals("PENDENTE", solicitacaoArquivoEntity.getStatus());
    }

    @Test
    void testGetDataInclusao() {
        // Espera-se que seja a data de inclusão setada
        assertNotNull(solicitacaoArquivoEntity.getDataInclusao());
    }

    @Test
    void testSetters() {
        solicitacaoArquivoEntity.setIdSolicitacao(3L);
        solicitacaoArquivoEntity.setNomeArquivo("new-file.txt");
        solicitacaoArquivoEntity.setStatus("CONCLUIDO");
        solicitacaoArquivoEntity.setDataInclusao(LocalDateTime.now().minusDays(1));

        assertEquals(3L, solicitacaoArquivoEntity.getIdSolicitacao());
        assertEquals("new-file.txt", solicitacaoArquivoEntity.getNomeArquivo());
        assertEquals("CONCLUIDO", solicitacaoArquivoEntity.getStatus());
        assertNotNull(solicitacaoArquivoEntity.getDataInclusao());
    }
}
