package com.fiapgrupo27.solicitacao.domain;

public interface SolicitacaoArquivoRepositoryCustom {
    int atualizarStatus(Long idSolicitacao, Long idArquivo, String status);
}
