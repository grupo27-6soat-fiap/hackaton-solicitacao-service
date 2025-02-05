package com.fiapgrupo27.solicitacao.infrastructure.gateways;

import com.fiapgrupo27.solicitacao.application.gateways.SolicitacaoArquivoGateway;
import com.fiapgrupo27.solicitacao.domain.entity.SolicitacaoArquivo;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoArquivoRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SolicitacaoArquivoRepositoryGateway implements SolicitacaoArquivoGateway {

    private final SolicitacaoArquivoRepository repository;
    private final RabbitTemplate rabbitTemplate;


    public SolicitacaoArquivoRepositoryGateway(SolicitacaoArquivoRepository repository, RabbitTemplate rabbitTemplate) {
        this.repository = repository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void salvar(SolicitacaoArquivo solicitacaoArquivo) {
        repository.save(SolicitacaoArquivoEntityMapper.toEntity(solicitacaoArquivo));
    }

    @Override
    public void atualizarStatus(Long idSolicitacao, Long idArquivo, String status) {
        repository.atualizarStatus(idSolicitacao, idArquivo, status);

    }


}
