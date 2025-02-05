package com.fiapgrupo27.solicitacao.infrastructure.gateways;

import com.fiapgrupo27.solicitacao.application.gateways.SolicitacaoGateway;
import com.fiapgrupo27.solicitacao.domain.entity.Solicitacao;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoEntity;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoRepository;

public class SolicitacaoRepositoryGateway implements SolicitacaoGateway {

    private final SolicitacaoRepository solicitacaoRepository;
    private final SolicitacaoEntityMapper solicitacaoEntityMapper;

    public SolicitacaoRepositoryGateway(SolicitacaoRepository solicitacaoRepository, SolicitacaoEntityMapper solicitacaoEntityMapper) {
        this.solicitacaoRepository = solicitacaoRepository;
        this.solicitacaoEntityMapper = solicitacaoEntityMapper;
    }

    @Override
    public Solicitacao createSolicitacao(Solicitacao solicitacaoDomainObj) {
        SolicitacaoEntity solicitacaoEntity = solicitacaoEntityMapper.toSolicitacaoEntity(solicitacaoDomainObj);
        SolicitacaoEntity savedObj = solicitacaoRepository.save(solicitacaoEntity);
        return solicitacaoEntityMapper.toSolicitacaoDomainObj(savedObj);
    }
}
