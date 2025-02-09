package com.fiapgrupo27.solicitacao.infrastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiapgrupo27.solicitacao.application.dto.SolicitacaoResponseDTO;
import com.fiapgrupo27.solicitacao.infrastructure.controllers.SolicitacaoDTOMapper;
import com.fiapgrupo27.solicitacao.application.usecases.AtualizarStatusSolicitacaoArquivoInteractor;
import com.fiapgrupo27.solicitacao.application.usecases.CreateSolicitacaoInteractor;
import com.fiapgrupo27.solicitacao.application.usecases.ObterSolicitacoesInteractor;
import com.fiapgrupo27.solicitacao.domain.entity.Solicitacao;
import com.fiapgrupo27.solicitacao.application.dto.SolicitacaoArquivoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SolicitacaoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CreateSolicitacaoInteractor createSolicitacaoInteractor;
    
    @Mock
    private SolicitacaoDTOMapper solicitacaoDTOMapper;
    
    @Mock
    private AtualizarStatusSolicitacaoArquivoInteractor atualizarStatusSolicitacaoArquivoInteractor;
    
    @Mock
    private ObterSolicitacoesInteractor obterSolicitacoesInteractor;
    
    @InjectMocks
    private SolicitacaoController solicitacaoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(solicitacaoController).build();
    }

    @Test
    void testCriarSolicitacao() throws Exception {
        // Dados simulados
        MockMultipartFile arquivo = new MockMultipartFile("arquivos", "arquivo.txt", "text/plain", "conteúdo".getBytes());
        String solicitante = "Test Solicitação";

        // Criando o response corretamente para o record CreateSolicitacaoResponse
        CreateSolicitacaoResponse response = new CreateSolicitacaoResponse("CRIADA");

        // Simulação dos comportamentos
        when(solicitacaoDTOMapper.toSolicitacao(solicitante)).thenReturn(new Solicitacao(1L, 1L, solicitante, LocalDateTime.now()));
        when(createSolicitacaoInteractor.createSolicitacao(any(), any(), eq(solicitante))).thenReturn(new Solicitacao(1L, 1L, solicitante, LocalDateTime.now()));
        when(solicitacaoDTOMapper.toResponse(any(Solicitacao.class))).thenReturn(response);

        // Realizando a requisição e verificando o retorno
        mockMvc.perform(multipart("/api/solicitacoes")
                .file(arquivo)
                .param("solicitante", solicitante))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CRIADA"));  // Verificação do valor correto
    }


    @Test
    void testAtualizarStatusSolicitacao() throws Exception {
        // Dados simulados
        Long idSolicitacao = 1L;
        Long idArquivo = 1L;
        String status = "APROVADO";

        doNothing().when(atualizarStatusSolicitacaoArquivoInteractor).execute(idSolicitacao, idArquivo, status);

        mockMvc.perform(put("/api/solicitacoes/{idSolicitacao}/arquivos/{idArquivo}/status", idSolicitacao, idArquivo)
                .param("status", status))
                .andExpect(status().isNoContent());

        // Verifica se o método foi chamado corretamente
        verify(atualizarStatusSolicitacaoArquivoInteractor, times(1)).execute(idSolicitacao, idArquivo, status);
    }

    @Test
    void testObterSolicitacoes() throws Exception {
        // Dados simulados
        SolicitacaoArquivoDTO arquivoDTO = new SolicitacaoArquivoDTO(1L, "Arquivo 1", "PENDENTE");
        List<SolicitacaoResponseDTO> solicitacoes = Arrays.asList(
                new SolicitacaoResponseDTO(1L, 1L, "Solicitante 1", LocalDateTime.now(), Arrays.asList(arquivoDTO)),
                new SolicitacaoResponseDTO(2L, 2L, "Solicitante 2", LocalDateTime.now(), Arrays.asList(arquivoDTO))
        );

        Long idSolicitante = 1L;

        when(obterSolicitacoesInteractor.executeFull(idSolicitante)).thenReturn(solicitacoes);

        mockMvc.perform(get("/api/solicitacoes")
                .param("idSolicitante", String.valueOf(idSolicitante)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(solicitacoes.size()));
    }
}
