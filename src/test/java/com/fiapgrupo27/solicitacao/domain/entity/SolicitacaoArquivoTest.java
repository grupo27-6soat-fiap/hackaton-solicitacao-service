package com.fiapgrupo27.solicitacao.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SolicitacaoArquivoTest {

    private SolicitacaoArquivo solicitacaoArquivo;

    @BeforeEach
    void setUp() {
        // Inicializando o objeto solicitacaoArquivo antes de cada teste
        solicitacaoArquivo = new SolicitacaoArquivo();
        solicitacaoArquivo.setIdSolicitacao(1L);
        solicitacaoArquivo.setIdSolicitante(2L);
        solicitacaoArquivo.setNomeArquivo("documento.pdf");
        solicitacaoArquivo.setStatus("PENDENTE");
        solicitacaoArquivo.setDataInclusao(LocalDateTime.now());
        solicitacaoArquivo.setIdArquivo(101L);
    }

    @Test
    void testGettersAndSetters() {
        // Testando se os getters e setters estão funcionando corretamente
        assertEquals(1L, solicitacaoArquivo.getIdSolicitacao());
        assertEquals(2L, solicitacaoArquivo.getIdSolicitante());
        assertEquals("documento.pdf", solicitacaoArquivo.getNomeArquivo());
        assertEquals("PENDENTE", solicitacaoArquivo.getStatus());
        assertNotNull(solicitacaoArquivo.getDataInclusao());
        assertEquals(101L, solicitacaoArquivo.getIdArquivo());

        // Alterando valores
        solicitacaoArquivo.setIdSolicitacao(3L);
        solicitacaoArquivo.setIdSolicitante(4L);
        solicitacaoArquivo.setNomeArquivo("relatorio.docx");
        solicitacaoArquivo.setStatus("CONCLUIDO");
        solicitacaoArquivo.setDataInclusao(LocalDateTime.of(2025, 2, 9, 12, 0, 0, 0));
        solicitacaoArquivo.setIdArquivo(102L);

        // Verificando se as alterações foram bem-sucedidas
        assertEquals(3L, solicitacaoArquivo.getIdSolicitacao());
        assertEquals(4L, solicitacaoArquivo.getIdSolicitante());
        assertEquals("relatorio.docx", solicitacaoArquivo.getNomeArquivo());
        assertEquals("CONCLUIDO", solicitacaoArquivo.getStatus());
        assertEquals(LocalDateTime.of(2025, 2, 9, 12, 0, 0, 0), solicitacaoArquivo.getDataInclusao());
        assertEquals(102L, solicitacaoArquivo.getIdArquivo());
    }

    @Test
    void testConstructor() {
        // Testando a criação do objeto solicitacaoArquivo
        SolicitacaoArquivo solicitacaoArquivo = new SolicitacaoArquivo(5L, 6L, "foto.png", "PENDENTE", LocalDateTime.of(2025, 2, 9, 12, 0, 0, 0), 103L);

        assertEquals(5L, solicitacaoArquivo.getIdSolicitacao());
        assertEquals(6L, solicitacaoArquivo.getIdSolicitante());
        assertEquals("foto.png", solicitacaoArquivo.getNomeArquivo());
        assertEquals("PENDENTE", solicitacaoArquivo.getStatus());
        assertEquals(LocalDateTime.of(2025, 2, 9, 12, 0, 0, 0), solicitacaoArquivo.getDataInclusao());
        assertEquals(103L, solicitacaoArquivo.getIdArquivo());
    }

}
