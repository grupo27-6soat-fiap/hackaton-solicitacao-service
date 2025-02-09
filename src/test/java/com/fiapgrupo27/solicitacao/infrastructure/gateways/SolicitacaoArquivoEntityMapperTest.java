package com.fiapgrupo27.solicitacao.infrastructure.gateways;

import com.fiapgrupo27.solicitacao.domain.entity.SolicitacaoArquivo;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoArquivoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SolicitacaoArquivoEntityMapperTest {

    private SolicitacaoArquivoEntityMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new SolicitacaoArquivoEntityMapper();
    }

    @Test
    void testToEntity() {
        // Arrange
        SolicitacaoArquivo solicitacaoArquivo = new SolicitacaoArquivo(
                1L,
                "arquivo.mp4",
                "PENDING",
                LocalDateTime.now(),
                1L
        );

        // Act
        SolicitacaoArquivoEntity entity = SolicitacaoArquivoEntityMapper.toEntity(solicitacaoArquivo);

        // Assert
        assertNotNull(entity);
        assertEquals(solicitacaoArquivo.getIdSolicitacao(), entity.getIdSolicitacao());
        assertEquals(solicitacaoArquivo.getNomeArquivo(), entity.getNomeArquivo());
        assertEquals(solicitacaoArquivo.getStatus(), entity.getStatus());
        assertEquals(solicitacaoArquivo.getDataInclusao(), entity.getDataInclusao());
    }
}
