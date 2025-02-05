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
    private Long idSolicitante;
    private String status;
    private LocalDateTime dataInclusao;

    public SolicitacaoEntity(Long idSolicitacao, Long idSolicitante, String status, LocalDateTime dataInclusao) {
        this.idSolicitacao = idSolicitacao;
        this.idSolicitante = idSolicitante;
        this.status = status;
        this.dataInclusao = dataInclusao;
    }

    public SolicitacaoEntity() {

    }


    public void atualizarStatus(String novoStatus) {
        if (!List.of("PENDENTE", "CONCLUIDO", "ERRO").contains(novoStatus)) {
            throw new IllegalArgumentException("Status inválido");
        }
        this.status = novoStatus;
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

    public Long getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(Long id) {
        this.idSolicitacao = id;
    }

}
