package com.fiapgrupo27.solicitacao.application.gateways;

import com.fiapgrupo27.solicitacao.domain.entity.SolicitacaoArquivo;

public interface MensagemGateway {
    void enviarMensagem(SolicitacaoArquivo arquivo, String fileUrl);
}
