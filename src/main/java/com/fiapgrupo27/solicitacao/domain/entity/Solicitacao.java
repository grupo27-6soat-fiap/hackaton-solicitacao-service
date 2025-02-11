package com.fiapgrupo27.solicitacao.domain.entity;

import java.time.LocalDateTime;

public class Solicitacao {

    private Long idSolicitacao;
    private String email;
    private LocalDateTime dataInclusao;

    public Solicitacao(Long idSolicitacao,
                       LocalDateTime dataInclusao,
                       String email) {
        this.idSolicitacao = idSolicitacao;
        this.dataInclusao = dataInclusao;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(Long idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }



    public LocalDateTime getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDateTime dataInclusao) {
        this.dataInclusao = dataInclusao;
    }
}
