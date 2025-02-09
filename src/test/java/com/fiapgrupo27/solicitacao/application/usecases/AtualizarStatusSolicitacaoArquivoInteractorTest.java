package com.fiapgrupo27.solicitacao.application.usecases;

import com.fiapgrupo27.solicitacao.application.gateways.SolicitacaoArquivoGateway;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class AtualizarStatusSolicitacaoArquivoInteractorTest {

    @Test
    public void deveAtualizarStatusSolicitacaoArquivo() {
        // Cria o mock do gateway
        SolicitacaoArquivoGateway solicitacaoArquivoGateway = mock(SolicitacaoArquivoGateway.class);

        // Cria o interactor passando o mock
        AtualizarStatusSolicitacaoArquivoInteractor interactor = new AtualizarStatusSolicitacaoArquivoInteractor(solicitacaoArquivoGateway);

        // Dados para o teste
        Long idSolicitacao = 1L;
        Long idArquivo = 100L;
        String status = "APROVADO";

        // Chama o método que deve ser testado
        interactor.execute(idSolicitacao, idArquivo, status);

        // Verifica se o método do gateway foi chamado corretamente
        verify(solicitacaoArquivoGateway, times(1))
                .atualizarStatus(idSolicitacao, idArquivo, status);
    }
}
