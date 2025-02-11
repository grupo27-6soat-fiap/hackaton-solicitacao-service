package com.fiapgrupo27.solicitacao.application.dto;

import java.time.LocalDateTime;
import java.util.List;

public class SolicitacaoResponseDTO {

    private Long id;
    private LocalDateTime dataInclusao;
    private List<SolicitacaoArquivoDTO> arquivos;
    private String email;

    public SolicitacaoResponseDTO(Long id,  LocalDateTime dataInclusao, List<SolicitacaoArquivoDTO> arquivos, String email) {
        this.id = id;
        this.dataInclusao = dataInclusao;
        this.arquivos = arquivos;
        this.email = email;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataInclusao() {
        return dataInclusao;
    }

    public List<SolicitacaoArquivoDTO> getArquivos() {
        return arquivos;
    }
}
