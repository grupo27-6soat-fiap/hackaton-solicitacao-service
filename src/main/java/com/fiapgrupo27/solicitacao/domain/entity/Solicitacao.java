package com.fiapgrupo27.solicitacao.domain.entity;

import java.time.LocalDateTime;

public class Solicitacao {

    private Long idSolicitacao;
    private Long idSolicitante;
    private String status;
    private LocalDateTime dataInclusao;

    public Solicitacao(Long idSolicitacao, Long idSolicitante, String status, LocalDateTime dataInclusao) {
        this.idSolicitacao = idSolicitacao;
        this.idSolicitante = idSolicitante;
        this.status = status;
        this.dataInclusao = dataInclusao;
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
