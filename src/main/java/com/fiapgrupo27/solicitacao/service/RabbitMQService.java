package com.fiapgrupo27.solicitacao.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQService {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMensagem(String queueName, String mensagem) {
        rabbitTemplate.convertAndSend(queueName, mensagem);
        System.out.println("Mensagem enviada para a fila: " + queueName);
    }
}
