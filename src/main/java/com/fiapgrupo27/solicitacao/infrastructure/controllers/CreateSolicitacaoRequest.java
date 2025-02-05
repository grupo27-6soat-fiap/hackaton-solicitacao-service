package com.fiapgrupo27.solicitacao.infrastructure.controllers;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class CreateSolicitacaoRequest {

    List<MultipartFile> arquivos;
    String solicitante;

    public List<MultipartFile> getArquivos() {
        return arquivos;
    }


    public String getSolicitante() {
        return solicitante;
    }
}
