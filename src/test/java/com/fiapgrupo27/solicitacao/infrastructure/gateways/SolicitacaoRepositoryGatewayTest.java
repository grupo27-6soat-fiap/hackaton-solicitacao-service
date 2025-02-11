package com.fiapgrupo27.solicitacao.infrastructure.gateways;

//java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fiapgrupo27.solicitacao.application.dto.SolicitacaoArquivoDTO;
import com.fiapgrupo27.solicitacao.application.dto.SolicitacaoResponseDTO;
import com.fiapgrupo27.solicitacao.domain.entity.Solicitacao;
import com.fiapgrupo27.solicitacao.domain.entity.SolicitacaoArquivo;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoArquivoEntity;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoArquivoRepository;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoEntity;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoRepository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class SolicitacaoRepositoryGatewayTest {

    @Mock
    private SolicitacaoRepository solicitacaoRepository;
    
    @Mock
    private SolicitacaoEntityMapper solicitacaoEntityMapper;
    
    @Mock
    private SolicitacaoArquivoRepository arquivoRepository;

    private SolicitacaoRepositoryGateway gateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        gateway = new SolicitacaoRepositoryGateway(solicitacaoRepository, solicitacaoEntityMapper, arquivoRepository);
    }

    @Test
    void createSolicitacao_Success() {
        Solicitacao inputSolicitacao = new Solicitacao(null, null, null); // Assuming constructor and setters required
        SolicitacaoEntity mappedEntity = new SolicitacaoEntity();
        SolicitacaoEntity savedEntity = new SolicitacaoEntity();
        Solicitacao expectedDomain = new Solicitacao(null, null, null);

        when(solicitacaoEntityMapper.toSolicitacaoEntity(inputSolicitacao)).thenReturn(mappedEntity);
        when(solicitacaoRepository.save(mappedEntity)).thenReturn(savedEntity);
        when(solicitacaoEntityMapper.toSolicitacaoDomainObj(savedEntity)).thenReturn(expectedDomain);

        Solicitacao result = gateway.createSolicitacao(inputSolicitacao);

        assertEquals(expectedDomain, result);
    }

    @Test
    public void testObterSolicitacoes() {
        SolicitacaoEntity entity = new SolicitacaoEntity(1L, null, "email");

        when(solicitacaoRepository.findByEmail("email"))
                .thenReturn(List.of(entity));  // Retorna a lista de entidades

        // Criando a instância da classe Solicitacao diretamente
        Solicitacao solicitacao = new Solicitacao(1L, null, "email");
        
        List<Solicitacao> result = gateway.obterSolicitacoes("email");

        // Verificando se o resultado contém a solicitação esperada
        assertEquals(1, result.size());

    }

}
