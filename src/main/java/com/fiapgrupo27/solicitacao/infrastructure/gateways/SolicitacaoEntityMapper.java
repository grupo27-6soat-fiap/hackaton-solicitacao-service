package com.fiapgrupo27.solicitacao.infrastructure.gateways;

import com.fiapgrupo27.solicitacao.domain.entity.Solicitacao;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoEntity;

import java.time.LocalDateTime;

public class SolicitacaoEntityMapper {
    SolicitacaoEntity toSolicitacaoEntity(Solicitacao solicitacaoDomnainObj) {
        return new SolicitacaoEntity(solicitacaoDomnainObj.getIdSolicitacao(), solicitacaoDomnainObj.getIdSolicitante(), solicitacaoDomnainObj.getStatus(), solicitacaoDomnainObj.getDataInclusao() );

    }

    Solicitacao toSolicitacaoDomainObj(SolicitacaoEntity solicitacaoEntity) {
        return new Solicitacao(solicitacaoEntity.getIdSolicitacao(), solicitacaoEntity.getIdSolicitante(), solicitacaoEntity.getStatus(), solicitacaoEntity.getDataInclusao());
    }

    public static Solicitacao toDomain(SolicitacaoEntity entity) {
        return new Solicitacao(
                entity.getIdSolicitacao(),
                entity.getIdSolicitante(),
                entity.getStatus(),
                entity.getDataInclusao()
        );
    }

}
