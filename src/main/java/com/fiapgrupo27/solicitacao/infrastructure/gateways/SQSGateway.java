package com.fiapgrupo27.solicitacao.infrastructure.gateways;

import com.fiapgrupo27.solicitacao.application.gateways.MensagemGateway;
import com.fiapgrupo27.solicitacao.domain.entity.SolicitacaoArquivo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.QueueDoesNotExistException;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.util.HashMap;
import java.util.Map;

@Component
public class SQSGateway implements MensagemGateway {
    private final SqsClient sqsClient;
    private String queueUrl;
    private final ObjectMapper objectMapper;

    public SQSGateway(SqsClient sqsClient, @Value("${aws.sqs.queue-url}") String queueUrl, ObjectMapper objectMapper) {
        this.sqsClient = sqsClient;
        this.queueUrl = queueUrl;
        this.objectMapper = objectMapper;
        ensureQueueExists();
    }
    private void ensureQueueExists() {
        try {
            GetQueueUrlRequest getQueueUrlRequest = GetQueueUrlRequest.builder()
                    .queueName(getQueueNameFromUrl(queueUrl))
                    .build();
            this.queueUrl = sqsClient.getQueueUrl(getQueueUrlRequest).queueUrl();
        } catch (QueueDoesNotExistException e) {
            CreateQueueRequest createQueueRequest = CreateQueueRequest.builder()
                    .queueName(getQueueNameFromUrl(queueUrl))
                    .build();
            this.queueUrl = sqsClient.createQueue(createQueueRequest).queueUrl();
        }
    }
    private String getQueueNameFromUrl(String queueUrl) {
        return queueUrl.substring(queueUrl.lastIndexOf("/") + 1);
    }

    @Override
    public void enviarMensagem(SolicitacaoArquivo arquivo, String fileUrl) {
        try {
            Map<String, Object> mensagem = criarMensagem(arquivo, fileUrl);
            String mensagemJson = objectMapper.writeValueAsString(mensagem);

            SendMessageRequest sendMsgRequest = SendMessageRequest.builder()
                    .queueUrl(System.getenv().getOrDefault("AWS_SQS_QUEUE_URL", queueUrl))
                    .messageBody(mensagemJson)
                    .build();

            sqsClient.sendMessage(sendMsgRequest);
            System.out.println("Mensagem enviada para SQS: " + mensagemJson);
        } catch (Exception e) {
            System.err.println("Erro ao enviar mensagem para SQS: " + e.getMessage());
        }
    }

    private Map<String, Object> criarMensagem(SolicitacaoArquivo arquivo, String fileUrl ) {
        Map<String, Object> mensagem = new HashMap<>();
        mensagem.put("idSolicitacao", arquivo.getIdSolicitacao());
        mensagem.put("nomeArquivo", arquivo.getNomeArquivo());
        mensagem.put("idArquivo", arquivo.getIdArquivo());
        mensagem.put("conteudoArquivo", fileUrl);
        return mensagem;
    }
}
