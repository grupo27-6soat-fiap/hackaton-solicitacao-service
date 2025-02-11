package com.fiapgrupo27.solicitacao.infrastructure.persistence;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SolicitacaoArquivoId implements Serializable {
    private Long idSolicitacao;
    private Long idArquivo;

    public SolicitacaoArquivoId() {}

    public SolicitacaoArquivoId(Long idSolicitacao, Long idArquivo) {
        this.idSolicitacao = idSolicitacao;
        this.idArquivo = idArquivo;
    }

    public Long getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(Long idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public Long getIdArquivo() {
        return idArquivo;
    }

    public void setIdArquivo(Long idArquivo) {
        this.idArquivo = idArquivo;
    }

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
