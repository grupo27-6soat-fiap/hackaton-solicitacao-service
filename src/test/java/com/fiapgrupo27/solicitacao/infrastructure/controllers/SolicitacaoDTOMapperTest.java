package com.fiapgrupo27.solicitacao.infrastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fiapgrupo27.solicitacao.domain.entity.Solicitacao;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class SolicitacaoDTOMapperTest {

    private final ObjectMapper objectMapper = mock(ObjectMapper.class);
    private final SolicitacaoDTOMapper solicitacaoDTOMapper = new SolicitacaoDTOMapper(objectMapper);

    @Test
    public void testToResponse() {
        Solicitacao solicitacao = new Solicitacao(1L, LocalDateTime.now(), "email");
        CreateSolicitacaoResponse response = solicitacaoDTOMapper.toResponse(solicitacao);

        // Verificando o retorno da resposta
        assertEquals("Solicitação criada com sucesso: 1", response.status());
    }



}
