package com.fiapgrupo27.solicitacao.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "solicitacao")
public class Solicitacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolicitacao;
    private int idSolicitante;
    private String status;
    private LocalDateTime dataInclusao;

    public Solicitacao(Long idSolicitacao, int idSolicitante, String status, LocalDateTime dataInclusao) {
        this.idSolicitacao = idSolicitacao;
        this.idSolicitante = idSolicitante;
        this.status = status;
        this.dataInclusao = dataInclusao;
    }


    public void atualizarStatus(String novoStatus) {
        if (!List.of("PENDENTE", "CONCLUIDO", "ERRO").contains(novoStatus)) {
            throw new IllegalArgumentException("Status inválido");
        }
        this.status = novoStatus;
    }

    public int getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(int idSolicitante) {
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
