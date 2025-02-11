package com.fiapgrupo27.solicitacao.infrastructure.gateways;

import com.fiapgrupo27.solicitacao.domain.entity.Solicitacao;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SolicitacaoEntityMapperTest {

    private SolicitacaoEntityMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new SolicitacaoEntityMapper();
    }

    @Test
    void testToSolicitacaoEntity() {
        // Arrange
        Solicitacao solicitacaoDomain = new Solicitacao(
                1L,
                LocalDateTime.now(),
                "email"

        );

        // Act
        SolicitacaoEntity entity = mapper.toSolicitacaoEntity(solicitacaoDomain);

        // Assert
        assertNotNull(entity);
        assertEquals(solicitacaoDomain.getIdSolicitacao(), entity.getIdSolicitacao());
        assertEquals(solicitacaoDomain.getEmail(), entity.getEmail());
        assertEquals(solicitacaoDomain.getDataInclusao(), entity.getDataInclusao());
    }

    @Test
    void testToSolicitacaoDomainObj() {
        // Arrange
        SolicitacaoEntity entity = new SolicitacaoEntity(
                1L,
                LocalDateTime.now(),
                "email"

        );

        // Act
        Solicitacao solicitacaoDomain = mapper.toSolicitacaoDomainObj(entity);

        // Assert
        assertNotNull(solicitacaoDomain);
        assertEquals(entity.getIdSolicitacao(), solicitacaoDomain.getIdSolicitacao());
        assertEquals(entity.getDataInclusao(), solicitacaoDomain.getDataInclusao());
    }

    @Test
    void testToDomain() {
        // Arrange
        SolicitacaoEntity entity = new SolicitacaoEntity(
                1L,
                LocalDateTime.now(),
                "email"

        );

        // Act
        Solicitacao solicitacaoDomain = SolicitacaoEntityMapper.toDomain(entity);

        // Assert
        assertNotNull(solicitacaoDomain);
        assertEquals(entity.getIdSolicitacao(), solicitacaoDomain.getIdSolicitacao());
        assertEquals(entity.getEmail(), solicitacaoDomain.getEmail());
        assertEquals(entity.getDataInclusao(), solicitacaoDomain.getDataInclusao());
    }
}
