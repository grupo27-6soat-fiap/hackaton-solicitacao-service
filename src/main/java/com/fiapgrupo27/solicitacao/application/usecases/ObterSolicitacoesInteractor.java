package com.fiapgrupo27.solicitacao.application.usecases;

import com.fiapgrupo27.solicitacao.application.dto.SolicitacaoResponseDTO;
import com.fiapgrupo27.solicitacao.application.gateways.SolicitacaoGateway;
import com.fiapgrupo27.solicitacao.domain.entity.Solicitacao;

import java.util.List;

public class ObterSolicitacoesInteractor {

    private final SolicitacaoGateway solicitacaoGateway;

    public ObterSolicitacoesInteractor(SolicitacaoGateway solicitacaoGateway) {
        this.solicitacaoGateway = solicitacaoGateway;
    }

    public List<Solicitacao> execute(Long idSolicitante) {
        return solicitacaoGateway.obterSolicitacoes(idSolicitante);
    }

    public List<SolicitacaoResponseDTO> executeFull(Long idSolicitante) {
        return solicitacaoGateway.obterSolicitacoesComArquivos(idSolicitante);
    }

}
