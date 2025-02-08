package com.fiapgrupo27.solicitacao.domain.entity;


import java.time.LocalDateTime;

public class SolicitacaoArquivo {

    private Long idArquivo;

    private Long idSolicitacao;

    private Long idSolicitante;

    private String nomeArquivo;

    private String status;

    private LocalDateTime dataInclusao;

    // Construtor padrão
    public SolicitacaoArquivo() {
    }

    // Construtor com argumentos
    public SolicitacaoArquivo(Long idSolicitacao, Long idSolicitante, String nomeArquivo, String status, LocalDateTime dataInclusao, Long idArquivo) {
        this.idSolicitacao = idSolicitacao;
        this.idSolicitante = idSolicitante;
        this.nomeArquivo = nomeArquivo;
        this.status = status;
        this.dataInclusao = dataInclusao;
        this.idArquivo = idArquivo;
    }

    // Getters e Setters
    public Long getIdArquivo() {
        return idArquivo;
    }

    public Long getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(Long idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public Long getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(Long idSolicitante) {
        this.idSolicitante = idSolicitante;
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
