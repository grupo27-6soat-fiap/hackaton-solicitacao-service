package com.fiapgrupo27.solicitacao.infrastructure.gateways;

import com.fiapgrupo27.solicitacao.application.gateways.SolicitacaoArquivoGateway;
import com.fiapgrupo27.solicitacao.domain.entity.SolicitacaoArquivo;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoArquivoEntity;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoArquivoRepository;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoEntity;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SolicitacaoArquivoRepositoryGateway implements SolicitacaoArquivoGateway {

    private final SolicitacaoArquivoRepository repository;

    private final SolicitacaoArquivoEntityMapper entityMapper;


    public SolicitacaoArquivoRepositoryGateway(SolicitacaoArquivoRepository repository, SolicitacaoArquivoEntityMapper entityMapper) {
        this.repository = repository;
        this.entityMapper = entityMapper;
    }

    @Override
    public SolicitacaoArquivo salvar(SolicitacaoArquivo solicitacaoArquivo) {

        SolicitacaoArquivoEntity savedObj = repository.save(SolicitacaoArquivoEntityMapper.toEntity(solicitacaoArquivo));
        return entityMapper.toEntity(savedObj);
    }

    @Override
    public void atualizarStatus(Long idSolicitacao, Long idArquivo, String status) {
        repository.atualizarStatus(idSolicitacao, idArquivo, status);

    }


}
