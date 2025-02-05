package com.fiapgrupo27.solicitacao.infrastructure.gateways;

import com.fiapgrupo27.solicitacao.domain.entity.SolicitacaoArquivo;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoArquivoEntity;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoArquivoId;

public class SolicitacaoArquivoEntityMapper {
    public static SolicitacaoArquivoEntity toEntity(SolicitacaoArquivo solicitacaoArquivo) {
        return new SolicitacaoArquivoEntity(
                solicitacaoArquivo.getIdSolicitacao(),
                solicitacaoArquivo.getIdSolicitante(),
                solicitacaoArquivo.getNomeArquivo(),
                solicitacaoArquivo.getStatus(),
                solicitacaoArquivo.getDataInclusao()
        );
    }
}
