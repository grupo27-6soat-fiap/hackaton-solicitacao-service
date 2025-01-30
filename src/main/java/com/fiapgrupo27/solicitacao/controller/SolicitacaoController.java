package com.fiapgrupo27.solicitacao.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiapgrupo27.solicitacao.domain.Solicitante;
import com.fiapgrupo27.solicitacao.dto.SolicitacaoDTO;
import com.fiapgrupo27.solicitacao.service.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api/solicitacoes")
public class SolicitacaoController {

    private final SolicitacaoService solicitacaoService;
    private final ObjectMapper objectMapper; // Para converter JSON (String) para um objeto Java


    @Autowired
    public SolicitacaoController(SolicitacaoService solicitacaoService, ObjectMapper objectMapper) {
        this.solicitacaoService = solicitacaoService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public String criarSolicitacao(@RequestParam("arquivos") List<MultipartFile> arquivos,
                                   @RequestParam("solicitante") String solicitante) {
        // Converte a String JSON para um objeto Solicitante
        Solicitante solicitanteMapper = null;
        try {
            solicitanteMapper = objectMapper.readValue(solicitante, Solicitante.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return solicitacaoService.criarSolicitacaoComArquivos(arquivos, solicitanteMapper);
    }

    @PutMapping("/{idSolicitacao}/arquivos/{idArquivo}/status")
    public ResponseEntity<String> atualizarStatusSolicitacao(
            @PathVariable Long idSolicitacao,
            @PathVariable Long idArquivo,
            @RequestParam String status) {
        try {
            solicitacaoService.atualizarStatusSolicitacao(idSolicitacao, idArquivo, status);
            return ResponseEntity.ok("Status atualizado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar o status: " + e.getMessage());
        }
    }

    @GetMapping
    public List<SolicitacaoDTO> obterSolicitacoes(@RequestParam(required = false) Integer idCliente) {
        return solicitacaoService.obterSolicitacoes(idCliente);
    }
}
