package com.fiapgrupo27.solicitacao.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiapgrupo27.solicitacao.application.gateways.MensagemGateway;
import com.fiapgrupo27.solicitacao.application.gateways.SolicitacaoArquivoGateway;
import com.fiapgrupo27.solicitacao.application.gateways.SolicitacaoGateway;
import com.fiapgrupo27.solicitacao.application.usecases.CreateSolicitacaoArquivoInteractor;
import com.fiapgrupo27.solicitacao.application.usecases.CreateSolicitacaoInteractor;
import com.fiapgrupo27.solicitacao.infrastructure.controllers.SolicitacaoDTOMapper;
import com.fiapgrupo27.solicitacao.infrastructure.gateways.RabbitMQGateway;
import com.fiapgrupo27.solicitacao.infrastructure.gateways.SolicitacaoEntityMapper;
import com.fiapgrupo27.solicitacao.infrastructure.gateways.SolicitacaoRepositoryGateway;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoRepository;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Configuration
public class SolicitacaoConfig {
    @Bean
    public CreateSolicitacaoInteractor createSolicitacaoInteractor(SolicitacaoGateway solicitacaoGateway,
                                                                   SolicitacaoArquivoGateway solicitacaoArquivoGateway,
                                                                   MensagemGateway mensagemGateway,
                                                                   ObjectMapper objectMapper) {
        return new CreateSolicitacaoInteractor(solicitacaoGateway, solicitacaoArquivoGateway, mensagemGateway, objectMapper);
    }

    @Bean
    public CreateSolicitacaoArquivoInteractor createSolicitacaoArquivoInteractor(
            SolicitacaoArquivoGateway solicitacaoArquivoGateway,
            MensagemGateway mensagemGateway
    ) {
        return new CreateSolicitacaoArquivoInteractor(solicitacaoArquivoGateway, mensagemGateway);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
    @Bean
    SolicitacaoGateway solicitacaoGateway(SolicitacaoRepository solicitacaoRepository, SolicitacaoEntityMapper solicitacaoEntityMapper) {
        return new SolicitacaoRepositoryGateway(solicitacaoRepository, solicitacaoEntityMapper);
    }
    @Bean
    RabbitMQGateway rabbitMQGateway(RabbitTemplate rabbitTemplate) {
        return new RabbitMQGateway(rabbitTemplate);
    }

    @Bean
    SolicitacaoEntityMapper solicitacaoEntityMapper() {
        return new SolicitacaoEntityMapper();
    }

    @Bean
    SolicitacaoDTOMapper solicitacaoDTOMapper(ObjectMapper objectMapper) {
        return new SolicitacaoDTOMapper(objectMapper);
    }

    @Bean
    public Queue videoProcessingQueue() {
        return new Queue("video-processing-queue", true);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }




}
