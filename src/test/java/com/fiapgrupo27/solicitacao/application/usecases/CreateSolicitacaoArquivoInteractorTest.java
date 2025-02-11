package com.fiapgrupo27.solicitacao.application.usecases;

import com.fiapgrupo27.solicitacao.application.gateways.MensagemGateway;
import com.fiapgrupo27.solicitacao.application.gateways.SolicitacaoArquivoGateway;
import com.fiapgrupo27.solicitacao.domain.entity.SolicitacaoArquivo;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime; // Adicione esta importação
import static org.mockito.Mockito.*;

public class CreateSolicitacaoArquivoInteractorTest {

    @Test
    public void deveSalvarSolicitacaoArquivo() {
        // Cria os mocks necessários
        SolicitacaoArquivoGateway gatewayMock = mock(SolicitacaoArquivoGateway.class);
        MensagemGateway mensagemGatewayMock = mock(MensagemGateway.class);

        // Cria o interactor passando os mocks
        CreateSolicitacaoArquivoInteractor interactor = new CreateSolicitacaoArquivoInteractor(gatewayMock, mensagemGatewayMock);

        // Dados para o teste
        SolicitacaoArquivo solicitacaoArquivo = new SolicitacaoArquivo(
                1L,  // idSolicitacao
                "arquivo.txt",  // nomeArquivo
                "PENDENTE",  // status
                LocalDateTime.now(),  // dataInclusao
                1L
        );

        // Chama o método que deve ser testado
        interactor.execute(solicitacaoArquivo);

        // Verifica se o método salvar foi chamado no gateway com o objeto correto
        verify(gatewayMock, times(1)).salvar(solicitacaoArquivo);
    }
}
