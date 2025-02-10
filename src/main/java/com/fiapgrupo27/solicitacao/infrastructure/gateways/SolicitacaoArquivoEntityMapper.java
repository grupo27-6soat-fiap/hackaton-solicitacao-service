package com.fiapgrupo27.solicitacao.infrastructure.gateways;


import com.fiapgrupo27.solicitacao.domain.entity.Solicitacao;
import com.fiapgrupo27.solicitacao.domain.entity.SolicitacaoArquivo;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoArquivoEntity;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoEntity;

public class SolicitacaoArquivoEntityMapper {
    public static SolicitacaoArquivoEntity toEntity(SolicitacaoArquivo solicitacaoArquivo) {
        return new SolicitacaoArquivoEntity(
                solicitacaoArquivo.getIdSolicitacao(),
                solicitacaoArquivo.getNomeArquivo(),
                solicitacaoArquivo.getStatus(),
                solicitacaoArquivo.getDataInclusao()
        );
    }

    SolicitacaoArquivo toEntity(SolicitacaoArquivoEntity solicitacaoArquivoEntity) {
        return new SolicitacaoArquivo(solicitacaoArquivoEntity.getIdSolicitacao(),
                                      solicitacaoArquivoEntity.getNomeArquivo(),
                                      solicitacaoArquivoEntity.getStatus(),
                                      solicitacaoArquivoEntity.getDataInclusao(),
                                      solicitacaoArquivoEntity.getIdArquivo());
    }


}
