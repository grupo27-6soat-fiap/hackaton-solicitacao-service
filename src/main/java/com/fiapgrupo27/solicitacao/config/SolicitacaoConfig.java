package com.fiapgrupo27.solicitacao.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fiapgrupo27.solicitacao.application.gateways.MensagemGateway;
import com.fiapgrupo27.solicitacao.application.gateways.S3Gateway;
import com.fiapgrupo27.solicitacao.application.gateways.SolicitacaoArquivoGateway;
import com.fiapgrupo27.solicitacao.application.gateways.SolicitacaoGateway;
import com.fiapgrupo27.solicitacao.application.usecases.AtualizarStatusSolicitacaoArquivoInteractor;
import com.fiapgrupo27.solicitacao.application.usecases.CreateSolicitacaoArquivoInteractor;
import com.fiapgrupo27.solicitacao.application.usecases.CreateSolicitacaoInteractor;
import com.fiapgrupo27.solicitacao.application.usecases.ObterSolicitacoesInteractor;
import com.fiapgrupo27.solicitacao.infrastructure.controllers.SolicitacaoDTOMapper;
import com.fiapgrupo27.solicitacao.infrastructure.gateways.*;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoArquivoRepository;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoRepository;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest;

import java.net.URI;
import java.util.List;

@Configuration
public class SolicitacaoConfig {
    @Bean
    public CreateSolicitacaoInteractor createSolicitacaoInteractor(SolicitacaoGateway solicitacaoGateway,
                                                                   SolicitacaoArquivoGateway solicitacaoArquivoGateway,
                                                                   MensagemGateway mensagemGateway,
                                                                   ObjectMapper objectMapper,
                                                                   S3Gateway s3Gateway) {
        return new CreateSolicitacaoInteractor(solicitacaoGateway, solicitacaoArquivoGateway, mensagemGateway, objectMapper, s3Gateway);
    }

    @Bean
    public CreateSolicitacaoArquivoInteractor createSolicitacaoArquivoInteractor(
            SolicitacaoArquivoGateway solicitacaoArquivoGateway,
            MensagemGateway mensagemGateway
    ) {
        return new CreateSolicitacaoArquivoInteractor(solicitacaoArquivoGateway, mensagemGateway);
    }
    @Bean
    public AtualizarStatusSolicitacaoArquivoInteractor atualizarStatusSolicitacaoArquivoInteractor(
            SolicitacaoArquivoGateway solicitacaoArquivoGateway
    ) {
        return new AtualizarStatusSolicitacaoArquivoInteractor(solicitacaoArquivoGateway);
    }

    @Bean
    public ObterSolicitacoesInteractor obterSolicitacoesInteractor(
            SolicitacaoGateway solicitacaoGateway
    ) {
        return new ObterSolicitacoesInteractor(solicitacaoGateway);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
    @Bean
    SolicitacaoGateway solicitacaoGateway(SolicitacaoRepository solicitacaoRepository, SolicitacaoEntityMapper solicitacaoEntityMapper, SolicitacaoArquivoRepository arquivoRepository) {
        return new SolicitacaoRepositoryGateway(solicitacaoRepository, solicitacaoEntityMapper, arquivoRepository);
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
    public SqsClient sqsClient() {
        return SqsClient.builder()
                .endpointOverride(URI.create("http://localhost:4566")) // LocalStack
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("test", "test")
                ))
                .build();
    }
    @Bean
    SolicitacaoArquivoEntityMapper solicitacaoArquivoEntityMapper() {
        return new SolicitacaoArquivoEntityMapper();
    }




}
