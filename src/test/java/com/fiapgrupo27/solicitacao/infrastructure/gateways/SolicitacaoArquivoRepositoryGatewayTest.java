package com.fiapgrupo27.solicitacao.infrastructure.gateways;

import com.fiapgrupo27.solicitacao.domain.entity.SolicitacaoArquivo;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoArquivoEntity;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoArquivoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SolicitacaoArquivoRepositoryGatewayTest {

    private SolicitacaoArquivoRepository repository;
    private SolicitacaoArquivoEntityMapper entityMapper;
    private SolicitacaoArquivoRepositoryGateway gateway;

    @BeforeEach
    void setUp() {
        // Mockando as dependências
        repository = mock(SolicitacaoArquivoRepository.class);
        entityMapper = mock(SolicitacaoArquivoEntityMapper.class);
        
        // Inicializando o gateway com as dependências mockadas
        gateway = new SolicitacaoArquivoRepositoryGateway(repository, entityMapper);
    }

    @Test
    void testSalvar_NullInput() {
        // Testa a exceção caso o input seja null
        assertThrows(NullPointerException.class, () -> {
            gateway.salvar(null);
        });
    }

    @Test
    void testAtualizarStatus() {
        // Dado
        Long idSolicitacao = 1L;
        Long idArquivo = 2L;
        String status = "PROCESSADO";

        // Simula o comportamento do repositório
        doNothing().when(repository).atualizarStatus(idSolicitacao, idArquivo, status);

        // Chama o método do gateway
        gateway.atualizarStatus(idSolicitacao, idArquivo, status);

        // Verifica se o método atualizarStatus foi chamado
        verify(repository, times(1)).atualizarStatus(idSolicitacao, idArquivo, status);
    }

}
