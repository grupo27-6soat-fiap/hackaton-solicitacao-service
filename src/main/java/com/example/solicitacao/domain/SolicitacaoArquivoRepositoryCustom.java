package com.example.solicitacao.domain;

public interface SolicitacaoArquivoRepositoryCustom {
    int atualizarStatus(Long idSolicitacao, Long idArquivo, String status);
}
