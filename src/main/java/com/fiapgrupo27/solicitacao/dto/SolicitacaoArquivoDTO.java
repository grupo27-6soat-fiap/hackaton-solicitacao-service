package com.fiapgrupo27.solicitacao.dto;

import java.time.LocalDateTime;

public class SolicitacaoArquivoDTO {
    private Long idArquivo;
    private String nomeArquivo;
    private String status;
    private LocalDateTime dataInclusao;

    public SolicitacaoArquivoDTO(Long idArquivo, String nomeArquivo, String status, LocalDateTime dataInclusao) {
        this.idArquivo = idArquivo;
        this.nomeArquivo = nomeArquivo;
        this.status = status;
        this.dataInclusao = dataInclusao;
    }

    // Getters e Setters
    public Long getIdArquivo() {
        return idArquivo;
    }

    public void setIdArquivo(Long idArquivo) {
        this.idArquivo = idArquivo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDateTime dataInclusao) {
        this.dataInclusao = dataInclusao;
    }
}
