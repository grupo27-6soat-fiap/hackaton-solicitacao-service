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
        Solicitacao solicitacao1 = new Solicitacao(1L, 1L, "PENDENTE", LocalDateTime.now());
        Solicitacao solicitacao2 = new Solicitacao(2L, 1L, "FINALIZADO", LocalDateTime.now());
        
        // Comportamento esperado do mock
        when(solicitacaoGateway.obterSolicitacoes(1L)).thenReturn(Arrays.asList(solicitacao1, solicitacao2));

        // Chamada ao método que estamos testando
        List<Solicitacao> result = obterSolicitacoesInteractor.execute(1L);

        // Validações
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getIdSolicitacao());
        assertEquals("PENDENTE", result.get(0).getStatus());
        assertEquals(2L, result.get(1).getIdSolicitacao());
        assertEquals("FINALIZADO", result.get(1).getStatus());

        // Verificação da interação com o mock
        verify(solicitacaoGateway).obterSolicitacoes(1L);
    }

    @Test
    public void testExecuteFull() {
        // Dados de teste com Solicitacao
        Solicitacao solicitacao1 = new Solicitacao(1L, 1L, "PENDENTE", LocalDateTime.now());
        Solicitacao solicitacao2 = new Solicitacao(2L, 1L, "FINALIZADO", LocalDateTime.now());

        // Criando uma lista de arquivos vazia para os testes (não estamos testando arquivos neste momento)
        List<SolicitacaoArquivoDTO> arquivos = Arrays.asList();

        // Convertendo Solicitacao para SolicitacaoResponseDTO
        SolicitacaoResponseDTO response1 = new SolicitacaoResponseDTO(
                solicitacao1.getIdSolicitacao(),
                solicitacao1.getIdSolicitante(),
                solicitacao1.getStatus(),
                solicitacao1.getDataInclusao(),
                arquivos
        );

        SolicitacaoResponseDTO response2 = new SolicitacaoResponseDTO(
                solicitacao2.getIdSolicitacao(),
                solicitacao2.getIdSolicitante(),
                solicitacao2.getStatus(),
                solicitacao2.getDataInclusao(),
                arquivos
        );

        // Comportamento esperado do mock para obterSolicitacoesComArquivos
        when(solicitacaoGateway.obterSolicitacoesComArquivos(1L)).thenReturn(Arrays.asList(response1, response2));

        // Chamada ao método que estamos testando
        List<SolicitacaoResponseDTO> result = obterSolicitacoesInteractor.executeFull(1L);

        // Validações
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());  // Ajuste de acordo com o nome do método correto
        assertEquals("PENDENTE", result.get(0).getStatus());
        assertEquals(2L, result.get(1).getId());
        assertEquals("FINALIZADO", result.get(1).getStatus());

        // Verificação da interação com o mock
        verify(solicitacaoGateway).obterSolicitacoesComArquivos(1L);
    }
}
