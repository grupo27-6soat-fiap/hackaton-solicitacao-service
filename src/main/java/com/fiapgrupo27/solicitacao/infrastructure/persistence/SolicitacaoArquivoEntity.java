package com.fiapgrupo27.solicitacao.infrastructure.persistence;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitacao_arquivo") // Ajuste o schema conforme necessário
public class SolicitacaoArquivoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Garante auto-increment no banco
    private Long idArquivo;

    @Column(nullable = false)
    private Long idSolicitacao;

    @Column(nullable = false)
    private Long idSolicitante;

    @Column(nullable = false)
    private String nomeArquivo; // 🚀 Novo campo obrigatório

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime dataInclusao;

    public SolicitacaoArquivoEntity() {}

    public SolicitacaoArquivoEntity(Long idSolicitacao,  Long idSolicitante, String nomeArquivo, String status, LocalDateTime dataInclusao) {
        this.idSolicitacao = idSolicitacao;
        this.idSolicitante = idSolicitante;
        this.nomeArquivo = nomeArquivo;
        this.status = status;
        this.dataInclusao = dataInclusao;
    }

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
