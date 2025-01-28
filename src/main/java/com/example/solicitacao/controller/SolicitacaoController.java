package com.example.solicitacao.controller;

import com.example.solicitacao.service.SolicitacaoService;
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

    @Autowired
    public SolicitacaoController(SolicitacaoService solicitacaoService) {
        this.solicitacaoService = solicitacaoService;
    }

    @PostMapping
    public String criarSolicitacao(@RequestParam("arquivos") List<MultipartFile> arquivos) {
        return solicitacaoService.criarSolicitacaoComArquivos(arquivos);
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
}
