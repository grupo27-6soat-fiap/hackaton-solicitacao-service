package com.fiapgrupo27.solicitacao.application.usecases;

import com.fiapgrupo27.solicitacao.application.gateways.MensagemGateway;
import com.fiapgrupo27.solicitacao.application.gateways.SolicitacaoArquivoGateway;
import com.fiapgrupo27.solicitacao.domain.entity.SolicitacaoArquivo;

public class CreateSolicitacaoArquivoInteractor {

    private final SolicitacaoArquivoGateway gateway;

    public CreateSolicitacaoArquivoInteractor(SolicitacaoArquivoGateway gateway, MensagemGateway mensagemGateway) {
        this.gateway = gateway;
    }

    public void execute(SolicitacaoArquivo solicitacaoArquivo) {
        gateway.salvar(solicitacaoArquivo);
    }
}
