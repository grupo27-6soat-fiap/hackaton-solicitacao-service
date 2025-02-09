package com.fiapgrupo27.solicitacao.infrastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fiapgrupo27.solicitacao.domain.entity.Solicitacao;
import com.fiapgrupo27.solicitacao.domain.entity.Solicitante;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class SolicitacaoDTOMapperTest {

    private final ObjectMapper objectMapper = mock(ObjectMapper.class);
    private final SolicitacaoDTOMapper solicitacaoDTOMapper = new SolicitacaoDTOMapper(objectMapper);

    @Test
    public void testToResponse() {
        Solicitacao solicitacao = new Solicitacao(1L, 1L, "PENDENTE", null);
        CreateSolicitacaoResponse response = solicitacaoDTOMapper.toResponse(solicitacao);

        // Verificando o retorno da resposta
        assertEquals("Solicitação criada com sucesso: 1", response.status());
    }

    @Test
    public void testToSolicitacao() throws Exception {
        String solicitanteJson = "{\"idSolicitante\": 1, \"nomeSolicitante\": \"Lucas\", \"email\": \"lucas@example.com\"}";

        // Criando um mock para o objeto solicitante
        Solicitante solicitante = new Solicitante();
        solicitante.setIdSolicitante(1L);
        solicitante.setNomeSolicitante("Lucas");
        solicitante.setEmail("lucas@example.com");

        // Configurando o mock do ObjectMapper
        when(objectMapper.readValue(solicitanteJson, Solicitante.class)).thenReturn(solicitante);

        // Testando o método toSolicitacao
        Solicitacao solicitacao = solicitacaoDTOMapper.toSolicitacao(solicitanteJson);

        // Verificando os valores
        assertNotNull(solicitacao);
        assertEquals(1L, solicitacao.getIdSolicitante());
        assertEquals("PENDENTE", solicitacao.getStatus());
    }

    @Test
    public void testToSolicitacao_JsonProcessingException() throws Exception {
        String solicitanteJson = "{\"idSolicitante\": 1, \"nomeSolicitante\": \"Lucas\", \"email\": \"lucas@example.com\"}";
    
        // Configurando o mock do ObjectMapper para lançar uma exceção
        when(objectMapper.readValue(solicitanteJson, Solicitante.class)).thenThrow(new JsonProcessingException("Erro") {});
    
        // Testando o método toSolicitacao quando ocorre uma exceção
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            solicitacaoDTOMapper.toSolicitacao(solicitanteJson);
        });
    
        // Verificando que a exceção contém a mensagem esperada
        assertTrue(exception.getMessage().contains("Erro"));
    }    
}
