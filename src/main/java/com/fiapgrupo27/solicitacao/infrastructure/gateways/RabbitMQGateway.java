package com.fiapgrupo27.solicitacao.infrastructure.gateways;

import com.fiapgrupo27.solicitacao.application.gateways.MensagemGateway;
import com.fiapgrupo27.solicitacao.domain.entity.SolicitacaoArquivo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.HashMap;
import java.util.Map;

public class RabbitMQGateway implements MensagemGateway {
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQGateway(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void enviarMensagem(SolicitacaoArquivo arquivo, byte[] arquivoBytes, String solicitante) {
        rabbitTemplate.convertAndSend("video-processing-queue", criarMensagemMQ(arquivo, arquivoBytes, solicitante));
        System.out.println("Arquivo enviado para a fila: " + arquivo.getNomeArquivo());


    }

    private Map<String, Object> criarMensagemMQ(SolicitacaoArquivo arquivo, byte[] arquivoBytes, String solicitante) {
        Map<String, Object> mensagem = new HashMap<>();
        mensagem.put("idSolicitacao", arquivo.getIdSolicitacao());
        mensagem.put("nomeArquivo", arquivo.getNomeArquivo());
        mensagem.put("idArquivo", arquivo.getIdArquivo());
        mensagem.put("conteudoArquivo", arquivoBytes);
        mensagem.put("solicitante", solicitante);

        return mensagem;
    }
}
