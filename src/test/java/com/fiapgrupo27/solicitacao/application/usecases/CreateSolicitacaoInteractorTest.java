package com.fiapgrupo27.solicitacao.application.usecases;

import com.fiapgrupo27.solicitacao.application.gateways.MensagemGateway;
import com.fiapgrupo27.solicitacao.application.gateways.S3Gateway;
import com.fiapgrupo27.solicitacao.application.gateways.SolicitacaoArquivoGateway;
import com.fiapgrupo27.solicitacao.application.gateways.SolicitacaoGateway;
import com.fiapgrupo27.solicitacao.domain.entity.Solicitacao;
import com.fiapgrupo27.solicitacao.domain.entity.SolicitacaoArquivo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CreateSolicitacaoInteractorTest {

    @Mock
    private SolicitacaoGateway solicitacaoGateway;

    @Mock
    private SolicitacaoArquivoGateway solicitacaoArquivoGateway;

    @Mock
    private MensagemGateway mensagemGateway;

    @Mock
    private S3Gateway s3Gateway;

    @Mock
    private MultipartFile arquivo;

    @InjectMocks
    private CreateSolicitacaoInteractor createSolicitacaoInteractor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateSolicitacao() throws IOException {
        // Criando dados de teste
        Solicitacao solicitacao = new Solicitacao(1L, LocalDateTime.now(), "email");
        String solicitante = "Usuario Teste";

        // Mock MultipartFile
        String originalFileName = "arquivo.txt";
        byte[] fileBytes = "Conteúdo do arquivo".getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(fileBytes);
        when(arquivo.getOriginalFilename()).thenReturn(originalFileName);
        when(arquivo.getInputStream()).thenReturn(inputStream);
        when(arquivo.getSize()).thenReturn((long) fileBytes.length);

        // Mock dos Gateways
        when(solicitacaoGateway.createSolicitacao(solicitacao)).thenReturn(solicitacao);
        when(s3Gateway.uploadFile(anyString(), any(), anyLong())).thenReturn("http://example.com/file-url");

        // Criando um objeto válido de SolicitacaoArquivo para ser salvo
        SolicitacaoArquivo solicitacaoArquivoMock = new SolicitacaoArquivo(1L, "arquivo.txt", "PENDENTE", LocalDateTime.now(), 1L);
        when(solicitacaoArquivoGateway.salvar(any(SolicitacaoArquivo.class))).thenReturn(solicitacaoArquivoMock);

        // Executa o método de teste
        Solicitacao result = createSolicitacaoInteractor.createSolicitacao(solicitacao, Collections.singletonList(arquivo));

        // Validações
        assertNotNull(result);
        verify(solicitacaoGateway).createSolicitacao(solicitacao);
        verify(solicitacaoArquivoGateway).salvar(any(SolicitacaoArquivo.class));
        verify(s3Gateway).uploadFile(anyString(), any(), anyLong());
        
        // Aqui garantimos que um objeto válido está sendo passado
        verify(mensagemGateway).enviarMensagem(eq(solicitacaoArquivoMock), eq("http://example.com/file-url"), eq("email"));

        // Validações do arquivo
        verify(arquivo).getOriginalFilename();
        verify(arquivo).getInputStream();
    }

    @Test
    void testCreateSolicitacaoIOException() throws IOException {
        // Configurando os mocks para simular a falha na leitura do arquivo
        Solicitacao solicitacao = new Solicitacao(1L, LocalDateTime.now(), "email");
        List<MultipartFile> arquivos = List.of(arquivo);
        String solicitante = "solicitante@dominio.com";

        // Simulando que o multipartFile lança uma IOException
        when(arquivo.getOriginalFilename()).thenReturn("arquivo.txt");
        when(arquivo.getInputStream()).thenThrow(IOException.class);  // Simulando IOException

        // Verificando se o RuntimeException é lançada corretamente
        assertThrows(RuntimeException.class, () -> {
            createSolicitacaoInteractor.createSolicitacao(solicitacao, arquivos);
        });

        // Verificando se o método do S3 não foi chamado devido ao erro
        verify(s3Gateway, never()).uploadFile(anyString(), any(InputStream.class), anyLong());
    }
}
