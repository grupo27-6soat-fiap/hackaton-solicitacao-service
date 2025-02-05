package com.fiapgrupo27.solicitacao.application.dto;

import java.time.LocalDateTime;
import java.util.List;

public class SolicitacaoResponseDTO {

    private Long id;
    private Long idSolicitante;
    private String status;
    private LocalDateTime dataInclusao;
    private List<SolicitacaoArquivoDTO> arquivos;

    public SolicitacaoResponseDTO(Long id, Long idSolicitante, String status, LocalDateTime dataInclusao, List<SolicitacaoArquivoDTO> arquivos) {
        this.id = id;
        this.idSolicitante = idSolicitante;
        this.status = status;
        this.dataInclusao = dataInclusao;
        this.arquivos = arquivos;
    }

    public Long getId() {
        return id;
    }

    public Long getIdSolicitante() {
        return idSolicitante;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getDataInclusao() {
        return dataInclusao;
    }

    public List<SolicitacaoArquivoDTO> getArquivos() {
        return arquivos;
    }
}
