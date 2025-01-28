package com.example.solicitacao.domain;

public interface SolicitacaoArquivoRepository {
    SolicitacaoArquivo salvar(SolicitacaoArquivo solicitacaoArquivo);

    void atualizarStatus(int idArquivo, Long idSolicitacao, String status);
}
