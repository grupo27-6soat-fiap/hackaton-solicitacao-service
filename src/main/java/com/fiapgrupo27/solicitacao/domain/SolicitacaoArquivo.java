package com.fiapgrupo27.solicitacao.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "solicitacao_arquivo")
public class SolicitacaoArquivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_arquivo", nullable = false, unique = true)
    private Integer idArquivo;

    @Column(name = "id_solicitacao", nullable = false)
    private Integer idSolicitacao;

    @Column(name = "id_solicitante", nullable = false)
    private Integer idSolicitante;

    @Column(name = "nome_arquivo", length = 300, nullable = false)
    private String nomeArquivo;

    @Column(name = "status", length = 50, nullable = false)
    private String status;

    @Column(name = "data_inclusao", nullable = false)
    private LocalDateTime dataInclusao;

    // Construtor padrão
    public SolicitacaoArquivo() {
    }

    // Construtor com argumentos
    public SolicitacaoArquivo(Integer idSolicitacao, Integer idSolicitante, String nomeArquivo, String status, LocalDateTime dataInclusao) {
        this.idSolicitacao = idSolicitacao;
        this.idSolicitante = idSolicitante;
        this.nomeArquivo = nomeArquivo;
        this.status = status;
        this.dataInclusao = dataInclusao;
    }

    // Getters e Setters
    public Integer getIdArquivo() {
        return idArquivo;
    }

    public Integer getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(Integer idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public Integer getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(Integer idSolicitante) {
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
