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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;


import java.net.URI;


@Configuration
public class SolicitacaoConfig {
    private final String aws_region;
    private final String aws_accesskey;
    private final String aws_keyid;
    private final String aws_endpoint;


    public SolicitacaoConfig(@Value("${cloud.aws.region}")String aws_region, @Value("${cloud.aws.accesskey}")String aws_accesskey, @Value("${cloud.aws.keyid}")String aws_keyid, @Value("${cloud.aws.endpoint}")String aws_endpoint) {

        this.aws_region = aws_region;
        this.aws_accesskey = aws_accesskey;
        this.aws_keyid = aws_keyid;
        this.aws_endpoint = System.getenv().getOrDefault("AWS_ENDPOINT_URL", aws_endpoint);
    }

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
    public S3GatewayImpl s3GatewayImpl(@Value("${cloud.aws.s3.bucket}") String bucketName, @Value("${cloud.aws.region}")String awsRegion, @Value("${cloud.aws.accesskey}")String awsAccesskey, @Value("${cloud.aws.keyid}")String awsKeyid, @Value("${cloud.aws.endpoint}")String awsEndpoint) {
        String bucket = System.getenv().getOrDefault("AWS_S3_BUCKET_NAME", bucketName);
        String newEndpoint = System.getenv().getOrDefault("AWS_ENDPOINT_URL", awsEndpoint);

        if (newEndpoint.contains("amazonaws.com")) {
            newEndpoint = null;
        }else{
            newEndpoint = aws_endpoint;
        }
        System.out.println(" ========== S3GatewayImpl ============" + newEndpoint);
        return new S3GatewayImpl(bucket, awsRegion, awsAccesskey, awsKeyid, newEndpoint);
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
    public SqsClient sqsClient() {
        if (aws_endpoint.contains("amazonaws.com")) {
            return SqsClient.builder()
//                .endpointOverride(URI.create(System.getenv().getOrDefault("AWS_ENDPOINT_URL", aws_endpoint)))
                    .region(Region.of(System.getenv().getOrDefault("AWS_REGION", aws_region)))
                    .credentialsProvider(StaticCredentialsProvider.create(
                            AwsBasicCredentials.create(
                                    System.getenv().getOrDefault("AWS_ACCESS_KEY_ID", aws_keyid),
                                    System.getenv().getOrDefault("AWS_SECRET_ACCESS_KEY", aws_accesskey)
                            )
                    ))
                    .build();

        }else{
            return SqsClient.builder()
                .endpointOverride(URI.create(aws_endpoint))
                    .region(Region.of(System.getenv().getOrDefault("AWS_REGION", aws_region)))
                    .credentialsProvider(StaticCredentialsProvider.create(
                            AwsBasicCredentials.create(
                                    System.getenv().getOrDefault("AWS_ACCESS_KEY_ID", aws_keyid),
                                    System.getenv().getOrDefault("AWS_SECRET_ACCESS_KEY", aws_accesskey)
                            )
                    ))
                    .build();
        }

    }
    @Bean
    SolicitacaoArquivoEntityMapper solicitacaoArquivoEntityMapper() {
        return new SolicitacaoArquivoEntityMapper();
    }




}
