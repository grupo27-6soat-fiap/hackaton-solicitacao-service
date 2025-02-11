package com.fiapgrupo27.solicitacao.infrastructure.gateways;

import com.fiapgrupo27.solicitacao.domain.entity.Solicitacao;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoEntity;

import java.time.LocalDateTime;

public class SolicitacaoEntityMapper {
    SolicitacaoEntity toSolicitacaoEntity(Solicitacao solicitacaoDomnainObj) {
        return new SolicitacaoEntity(solicitacaoDomnainObj.getIdSolicitacao(), solicitacaoDomnainObj.getDataInclusao(), solicitacaoDomnainObj.getEmail() );

    }

    Solicitacao toSolicitacaoDomainObj(SolicitacaoEntity solicitacaoEntity) {
        return new Solicitacao(solicitacaoEntity.getIdSolicitacao(), solicitacaoEntity.getDataInclusao(), solicitacaoEntity.getEmail());
    }

    public static Solicitacao toDomain(SolicitacaoEntity entity) {
        return new Solicitacao(
                entity.getIdSolicitacao(),
                entity.getDataInclusao(),
                entity.getEmail()
        );
    }

}
