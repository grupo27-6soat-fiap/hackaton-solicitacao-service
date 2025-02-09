package com.fiapgrupo27.solicitacao.application.usecases;

import com.fiapgrupo27.solicitacao.application.gateways.SolicitacaoArquivoGateway;

public class AtualizarStatusSolicitacaoArquivoInteractor {
    private final SolicitacaoArquivoGateway solicitacaoArquivoGateway;

    public AtualizarStatusSolicitacaoArquivoInteractor(SolicitacaoArquivoGateway solicitacaoArquivoGateway) {
        this.solicitacaoArquivoGateway = solicitacaoArquivoGateway;
    }

    public void execute(Long idSolicitacao, Long idArquivo, String status) {
        solicitacaoArquivoGateway.atualizarStatus(idSolicitacao, idArquivo, status);
    }
}