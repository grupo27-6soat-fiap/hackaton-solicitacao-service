package com.fiapgrupo27.solicitacao.dto;

import java.time.LocalDateTime;
import java.util.List;

public class SolicitacaoDTO {
    private Long idSolicitacao;
    private Integer idSolicitante;
    private String status;
    private LocalDateTime dataInclusao;
    private List<SolicitacaoArquivoDTO> arquivos;

    public SolicitacaoDTO(Long idSolicitacao, Integer idSolicitante, String status, LocalDateTime dataInclusao, List<SolicitacaoArquivoDTO> arquivos) {
        this.idSolicitacao = idSolicitacao;
        this.idSolicitante = idSolicitante;
        this.status = status;
        this.dataInclusao = dataInclusao;
        this.arquivos = arquivos;
    }

    // Getters e Setters
    public Long getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(Long idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public Integer getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(Integer idSolicitante) {
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

    public List<SolicitacaoArquivoDTO> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<SolicitacaoArquivoDTO> arquivos) {
        this.arquivos = arquivos;
    }
}
