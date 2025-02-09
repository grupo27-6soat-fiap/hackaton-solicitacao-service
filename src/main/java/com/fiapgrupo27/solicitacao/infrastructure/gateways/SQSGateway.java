package com.fiapgrupo27.solicitacao.infrastructure.gateways;

import com.fiapgrupo27.solicitacao.application.gateways.MensagemGateway;
import com.fiapgrupo27.solicitacao.domain.entity.SolicitacaoArquivo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.util.HashMap;
import java.util.Map;

@Component
public class SQSGateway implements MensagemGateway {
    private final SqsClient sqsClient;
    private final String queueUrl;
    private final ObjectMapper objectMapper;

    public SQSGateway(SqsClient sqsClient, @Value("${aws.sqs.queue-url}") String queueUrl, ObjectMapper objectMapper) {
        this.sqsClient = sqsClient;
        this.queueUrl = queueUrl;
        this.objectMapper = objectMapper;
    }

    @Override
    public void enviarMensagem(SolicitacaoArquivo arquivo, String fileUrl, String solicitante) {
        try {
            Map<String, Object> mensagem = criarMensagem(arquivo, fileUrl, solicitante);
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

    private Map<String, Object> criarMensagem(SolicitacaoArquivo arquivo, String fileUrl , String solicitante) {
        Map<String, Object> mensagem = new HashMap<>();
        mensagem.put("idSolicitacao", arquivo.getIdSolicitacao());
        mensagem.put("nomeArquivo", arquivo.getNomeArquivo());
        mensagem.put("idArquivo", arquivo.getIdArquivo());
        mensagem.put("conteudoArquivo", fileUrl);
        mensagem.put("solicitante", solicitante);
        return mensagem;
    }
}
