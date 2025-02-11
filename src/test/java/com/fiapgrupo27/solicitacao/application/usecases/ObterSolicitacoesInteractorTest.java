package com.fiapgrupo27.solicitacao.application.usecases;

import com.fiapgrupo27.solicitacao.application.dto.SolicitacaoResponseDTO;
import com.fiapgrupo27.solicitacao.application.dto.SolicitacaoArquivoDTO;
import com.fiapgrupo27.solicitacao.domain.entity.Solicitacao;
import com.fiapgrupo27.solicitacao.application.gateways.SolicitacaoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ObterSolicitacoesInteractorTest {

    private SolicitacaoGateway solicitacaoGateway;
    private ObterSolicitacoesInteractor obterSolicitacoesInteractor;

    @BeforeEach
    public void setUp() {
        solicitacaoGateway = mock(SolicitacaoGateway.class);
        obterSolicitacoesInteractor = new ObterSolicitacoesInteractor(solicitacaoGateway);
    }

    @Test
    public void testExecute() {
        // Dados de teste
        Solicitacao solicitacao1 = new Solicitacao(1L, LocalDateTime.now(), "email" );
        Solicitacao solicitacao2 = new Solicitacao(2L, LocalDateTime.now(), "email");
        
        // Comportamento esperado do mock
        when(solicitacaoGateway.obterSolicitacoes("email")).thenReturn(Arrays.asList(solicitacao1, solicitacao2));

        // Chamada ao método que estamos testando
        List<Solicitacao> result = obterSolicitacoesInteractor.execute("email");

        // Validações
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getIdSolicitacao());
        assertEquals(2L, result.get(1).getIdSolicitacao());


        // Verificação da interação com o mock
        verify(solicitacaoGateway).obterSolicitacoes("email");
    }

    @Test
    public void testExecuteFull() {
        // Dados de teste com Solicitacao
        Solicitacao solicitacao1 = new Solicitacao(1L, LocalDateTime.now(), "email");
        Solicitacao solicitacao2 = new Solicitacao(2L, LocalDateTime.now(), "email");

        // Criando uma lista de arquivos vazia para os testes (não estamos testando arquivos neste momento)
        List<SolicitacaoArquivoDTO> arquivos = Arrays.asList();

        // Convertendo Solicitacao para SolicitacaoResponseDTO
        SolicitacaoResponseDTO response1 = new SolicitacaoResponseDTO(
                solicitacao1.getIdSolicitacao(),
                solicitacao1.getDataInclusao(),
                arquivos, "email"
        );

        SolicitacaoResponseDTO response2 = new SolicitacaoResponseDTO(
                solicitacao2.getIdSolicitacao(),
                solicitacao2.getDataInclusao(),
                arquivos, "email"
        );

        // Comportamento esperado do mock para obterSolicitacoesComArquivos
        when(solicitacaoGateway.obterSolicitacoesComArquivos("email")).thenReturn(Arrays.asList(response1, response2));

        // Chamada ao método que estamos testando
        List<SolicitacaoResponseDTO> result = obterSolicitacoesInteractor.executeFull("email");

        // Validações
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());  // Ajuste de acordo com o nome do método correto
        assertEquals(2L, result.get(1).getId());

        // Verificação da interação com o mock
        verify(solicitacaoGateway).obterSolicitacoesComArquivos("email");
    }
}
