package com.fiapgrupo27.solicitacao.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class SolicitacaoArquivoId implements Serializable {

    private Integer idSolicitacao;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idArquivo;

    // Construtor padrão
    public SolicitacaoArquivoId() {
    }

    // Construtor com argumentos
    public SolicitacaoArquivoId(Integer idSolicitacao, Integer idArquivo) {
        this.idSolicitacao = idSolicitacao;
        this.idArquivo = idArquivo;
    }

    // Getters e Setters
    public Integer getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(Integer idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public Integer getIdArquivo() {
        return idArquivo;
    }

    public void setIdArquivo(Integer idArquivo) {
        this.idArquivo = idArquivo;
    }

    // Métodos equals e hashCode (necessários para chaves compostas)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolicitacaoArquivoId that = (SolicitacaoArquivoId) o;
        return Objects.equals(idSolicitacao, that.idSolicitacao) &&
                Objects.equals(idArquivo, that.idArquivo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSolicitacao, idArquivo);
    }
}
