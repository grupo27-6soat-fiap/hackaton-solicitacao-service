package com.fiapgrupo27.solicitacao.application.gateways;

import com.fiapgrupo27.solicitacao.application.dto.SolicitacaoResponseDTO;
import com.fiapgrupo27.solicitacao.domain.entity.Solicitacao;

import java.util.List;

public interface SolicitacaoGateway {
    Solicitacao createSolicitacao(Solicitacao solicitacao);
    List<Solicitacao> obterSolicitacoes(String email);
    List<SolicitacaoResponseDTO> obterSolicitacoesComArquivos(String email);



}
