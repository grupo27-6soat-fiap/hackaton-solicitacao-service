package com.fiapgrupo27.solicitacao.application.gateways;

import com.fiapgrupo27.solicitacao.domain.entity.SolicitacaoArquivo;

public interface SolicitacaoArquivoGateway {
    SolicitacaoArquivo salvar(SolicitacaoArquivo solicitacaoArquivo);
    void atualizarStatus(Long idSolicitacao, Long idArquivo, String status);

}
