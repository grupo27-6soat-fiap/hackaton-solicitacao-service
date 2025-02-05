package com.fiapgrupo27.solicitacao.infrastructure.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiapgrupo27.solicitacao.application.usecases.CreateSolicitacaoInteractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/solicitacoes")
public class SolicitacaoController {

    private final CreateSolicitacaoInteractor createSolicitacaoInteractor;
    private final SolicitacaoDTOMapper solicitacaoDTOMapper;


    @Autowired
    public SolicitacaoController(CreateSolicitacaoInteractor createSolicitacaoInteractor, ObjectMapper objectMapper, SolicitacaoDTOMapper solicitacaoDTOMapper) {
        this.createSolicitacaoInteractor = createSolicitacaoInteractor;
        this.solicitacaoDTOMapper = solicitacaoDTOMapper;
    }

    @PostMapping
    public CreateSolicitacaoResponse criarSolicitacao(@RequestParam("arquivos") List<MultipartFile> arquivos,
                                   @RequestParam("solicitante") String solicitante) {

        return solicitacaoDTOMapper.toResponse(createSolicitacaoInteractor.createSolicitacao(solicitacaoDTOMapper.toSolicitacao(solicitante), arquivos, solicitante));

    }
}
