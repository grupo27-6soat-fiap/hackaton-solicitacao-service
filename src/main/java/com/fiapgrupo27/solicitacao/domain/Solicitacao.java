package com.fiapgrupo27.solicitacao.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "solicitacao")
public class Solicitacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int idCliente;
    private String status;
    private LocalDateTime dataInclusao;

    public Solicitacao(Long id, int idCliente, String status, LocalDateTime dataInclusao) {
        this.id = id;
        this.idCliente = idCliente;
        this.status = status;
        this.dataInclusao = dataInclusao;
    }


    public void atualizarStatus(String novoStatus) {
        if (!List.of("PENDENTE", "CONCLUIDO", "ERRO").contains(novoStatus)) {
            throw new IllegalArgumentException("Status inválido");
        }
        this.status = novoStatus;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
