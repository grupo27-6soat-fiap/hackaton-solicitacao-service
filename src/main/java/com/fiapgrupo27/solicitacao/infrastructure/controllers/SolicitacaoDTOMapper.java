package com.fiapgrupo27.solicitacao.infrastructure.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiapgrupo27.solicitacao.domain.entity.Solicitacao;
import com.fiapgrupo27.solicitacao.domain.entity.Solicitante;

import java.time.LocalDateTime;

public class SolicitacaoDTOMapper {
    private final ObjectMapper objectMapper;

    public SolicitacaoDTOMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    CreateSolicitacaoResponse toResponse(Solicitacao solicitacao) {
        return new CreateSolicitacaoResponse("Solicitação criada com sucesso: " + solicitacao.getIdSolicitacao());
    }

    public Solicitacao toSolicitacao(String solicitante){
        Solicitante solicitanteMapper = null;
        try {
            solicitanteMapper = objectMapper.readValue(solicitante, Solicitante.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new Solicitacao(null,solicitanteMapper.getIdSolicitante(),"PENDENTE", LocalDateTime.now());
    }
}
