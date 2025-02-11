package com.fiapgrupo27.solicitacao.application.dto;

public class SolicitacaoArquivoDTO {
    private Long idArquivo;
    private String nomeArquivo;
    private String status;

    public SolicitacaoArquivoDTO(Long idArquivo, String nomeArquivo, String status) {
        this.idArquivo = idArquivo;
        this.nomeArquivo = nomeArquivo;
        this.status = status;
    }

    public Long getIdArquivo() {
        return idArquivo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public String getStatus() {
        return status;
    }
}
