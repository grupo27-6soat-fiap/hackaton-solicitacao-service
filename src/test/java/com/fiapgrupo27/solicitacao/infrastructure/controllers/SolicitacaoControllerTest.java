package com.fiapgrupo27.solicitacao.infrastructure.controllers;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import com.fiapgrupo27.solicitacao.application.dto.SolicitacaoResponseDTO;
import com.fiapgrupo27.solicitacao.application.usecases.AtualizarStatusSolicitacaoArquivoInteractor;
import com.fiapgrupo27.solicitacao.application.usecases.CreateSolicitacaoInteractor;
import com.fiapgrupo27.solicitacao.application.usecases.ObterSolicitacoesInteractor;

import com.fiapgrupo27.solicitacao.domain.entity.Solicitacao;
import com.fiapgrupo27.solicitacao.application.dto.SolicitacaoArquivoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest(properties = "spring.profiles.active=test")
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
    @WithMockUser(username = "testUser", roles = {"USER"}) // 🔥 Simula um usuário autenticado
    void testCriarSolicitacao() throws Exception {
        // Dados simulados
        MockMultipartFile arquivo = new MockMultipartFile("arquivos", "arquivo.txt", "text/plain", "conteúdo".getBytes());
        String email = "email";

        // Criando o response corretamente para o record CreateSolicitacaoResponse
        CreateSolicitacaoResponse response = new CreateSolicitacaoResponse("CRIADA");

        // Simulação dos comportamentos

        when(createSolicitacaoInteractor.createSolicitacao(any(), any())).thenReturn(new Solicitacao(1L, LocalDateTime.now(),email ));
        when(solicitacaoDTOMapper.toResponse(any(Solicitacao.class))).thenReturn(response);

        // Realizando a requisição e verificando o retorno
//        mockMvc.perform(multipart("/api/solicitacoes")
//                .file(arquivo)
//                .param("email", email))
//                .andExpect(jsonPath("$.status").value("CRIADA"));  // Verificação do valor correto

    }




    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    void testObterSolicitacoes() throws Exception {
        // Dados simulados
        SolicitacaoArquivoDTO arquivoDTO = new SolicitacaoArquivoDTO(1L, "Arquivo 1", "PENDENTE");
        List<SolicitacaoResponseDTO> solicitacoes = Arrays.asList(
                new SolicitacaoResponseDTO(1L, LocalDateTime.now(), Arrays.asList(arquivoDTO) ),
                new SolicitacaoResponseDTO(2L, LocalDateTime.now(), Arrays.asList(arquivoDTO))
        );

        String email = "email";

        when(obterSolicitacoesInteractor.executeFull("email")).thenReturn(solicitacoes);

//        mockMvc.perform(get("/api/solicitacoes")
//                        .param("email", "test@example.com")
//                        .with(jwt())) //
//                .andExpect(status().isOk());
    }
}
