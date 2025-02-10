package com.fiapgrupo27.solicitacao.infrastructure.persistence;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "solicitacao")
public class SolicitacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolicitacao;
    private String email;
    private LocalDateTime dataInclusao;

    public SolicitacaoEntity(Long idSolicitacao, LocalDateTime dataInclusao, String email) {
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

    public SolicitacaoEntity() {

    }


    public LocalDateTime getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDateTime dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Long getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(Long id) {
        this.idSolicitacao = id;
    }

}
