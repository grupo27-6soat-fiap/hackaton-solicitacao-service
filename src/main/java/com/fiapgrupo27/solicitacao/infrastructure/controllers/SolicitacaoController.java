package com.fiapgrupo27.solicitacao.infrastructure.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiapgrupo27.solicitacao.application.dto.SolicitacaoResponseDTO;
import com.fiapgrupo27.solicitacao.application.usecases.AtualizarStatusSolicitacaoArquivoInteractor;
import com.fiapgrupo27.solicitacao.application.usecases.CreateSolicitacaoInteractor;
import com.fiapgrupo27.solicitacao.application.usecases.ObterSolicitacoesInteractor;
import com.fiapgrupo27.solicitacao.domain.entity.Solicitacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/solicitacoes")
public class SolicitacaoController {

    private final CreateSolicitacaoInteractor createSolicitacaoInteractor;
    private final SolicitacaoDTOMapper solicitacaoDTOMapper;
    private final AtualizarStatusSolicitacaoArquivoInteractor atualizarStatusSolicitacaoArquivoInteractor;
    private final ObterSolicitacoesInteractor obterSolicitacoesInteractor;


    @Autowired
    public SolicitacaoController(CreateSolicitacaoInteractor createSolicitacaoInteractor, ObjectMapper objectMapper, SolicitacaoDTOMapper solicitacaoDTOMapper, AtualizarStatusSolicitacaoArquivoInteractor atualizarStatusSolicitacaoArquivoInteractor, ObterSolicitacoesInteractor obterSolicitacoesInteractor) {
        this.createSolicitacaoInteractor = createSolicitacaoInteractor;
        this.solicitacaoDTOMapper = solicitacaoDTOMapper;
        this.atualizarStatusSolicitacaoArquivoInteractor = atualizarStatusSolicitacaoArquivoInteractor;
        this.obterSolicitacoesInteractor = obterSolicitacoesInteractor;
    }

    @PostMapping
    public CreateSolicitacaoResponse criarSolicitacao(@RequestParam("arquivos") List<MultipartFile> arquivos,
                                   @RequestParam("solicitante") String solicitante) {

        return solicitacaoDTOMapper.toResponse(createSolicitacaoInteractor.createSolicitacao(solicitacaoDTOMapper.toSolicitacao(solicitante), arquivos, solicitante));

    }

    @PutMapping("/{idSolicitacao}/arquivos/{idArquivo}/status")
    public ResponseEntity<Void> atualizarStatusSolicitacao(
            @PathVariable Long idSolicitacao,
            @PathVariable Long idArquivo,
            @RequestParam String status) {

        atualizarStatusSolicitacaoArquivoInteractor.execute(idSolicitacao, idArquivo, status);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping
//    public ResponseEntity<List<Solicitacao>> obterSolicitacoes(@RequestParam(required = false) Long idSolicitante) {
//        List<Solicitacao> solicitacoes = obterSolicitacoesInteractor.execute(idSolicitante);
//
//        return ResponseEntity.ok(solicitacoes);
//    }

    @GetMapping
    public ResponseEntity<List<SolicitacaoResponseDTO>> obterSolicitacoes(@RequestParam(required = false) Long idSolicitante) {
        List<SolicitacaoResponseDTO> solicitacoes = obterSolicitacoesInteractor.executeFull(idSolicitante);
        return ResponseEntity.ok(solicitacoes);
    }
}
