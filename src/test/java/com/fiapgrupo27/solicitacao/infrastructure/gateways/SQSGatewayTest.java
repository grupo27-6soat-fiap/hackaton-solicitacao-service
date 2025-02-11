package com.fiapgrupo27.solicitacao.infrastructure.gateways;

import com.fiapgrupo27.solicitacao.domain.entity.SolicitacaoArquivo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SQSGatewayTest {

    @Mock
    private SqsClient sqsClient;

    @Mock
    private ObjectMapper objectMapper;

    private SQSGateway sqsGateway;

    private final String queueUrl = "https://sqs.us-east-1.amazonaws.com/123456789012/minha-fila";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock do comportamento de getQueueUrl
        GetQueueUrlResponse getQueueUrlResponse = GetQueueUrlResponse.builder()
                .queueUrl(queueUrl)
                .build();
        when(sqsClient.getQueueUrl(any(GetQueueUrlRequest.class))).thenReturn(getQueueUrlResponse);

        // Inicializando o SQSGateway com mocks
        sqsGateway = new SQSGateway(sqsClient, queueUrl, objectMapper);
    }

    @Test
    void enviarMensagem_DeveEnviarMensagemParaSQS() throws Exception {
        // Criando um objeto SolicitacaoArquivo válido
        SolicitacaoArquivo arquivo = new SolicitacaoArquivo(1L, "documento.mp4", "Ativo", LocalDateTime.now(), 1L);
        String fileUrl = "https://meusarquivos.com/documento.mp4";
        String solicitante = "Usuário Teste";

        // Criando um mapa manualmente para evitar NullPointerException
        Map<String, Object> mensagem = new HashMap<>();
        mensagem.put("idSolicitacao", arquivo.getIdSolicitacao());
        mensagem.put("nomeArquivo", arquivo.getNomeArquivo());
        mensagem.put("idArquivo", arquivo.getIdArquivo());
        mensagem.put("conteudoArquivo", fileUrl);
        mensagem.put("solicitante", solicitante);

        // Simulando a conversão do objeto para JSON
        String mensagemJson = "{\"idSolicitacao\":1,\"nomeArquivo\":\"documento.mp4\",\"idArquivo\":1,\"conteudoArquivo\":\"https://meusarquivos.com/documento.mp4\",\"solicitante\":\"Usuário Teste\"}";
        when(objectMapper.writeValueAsString(any(Map.class))).thenReturn(mensagemJson);

        // Verificando se o SQS recebeu a mensagem corretamente
        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(mensagemJson)
                .build();

        // Simulando o comportamento do SQS para enviar a mensagem
        when(sqsClient.sendMessage(any(SendMessageRequest.class))).thenReturn(null);  // Você pode ajustar o retorno para simular o comportamento do sendMessage

        // Executando o método que estamos testando
        sqsGateway.enviarMensagem(arquivo, fileUrl, "email");

        // Verificando se o SQS recebeu a mensagem corretamente
        verify(sqsClient, times(1)).sendMessage(eq(sendMessageRequest));  // Comparando a mensagem enviada
    }
}
