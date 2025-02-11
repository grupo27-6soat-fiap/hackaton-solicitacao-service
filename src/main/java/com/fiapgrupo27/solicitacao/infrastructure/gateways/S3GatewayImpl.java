package com.fiapgrupo27.solicitacao.infrastructure.gateways;

import com.fiapgrupo27.solicitacao.application.gateways.S3Gateway;
import software.amazon.awssdk.services.s3.S3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.S3Exception;


import java.io.InputStream;
import java.net.URI;
import java.nio.file.Paths;


public class S3GatewayImpl implements S3Gateway {

    private final S3Client s3Client;
    private final String bucketName;
    private final String aws_region;
    private final String aws_accesskey;
    private final String aws_keyid;
    private final String aws_endpoint;



    public S3GatewayImpl(@Value("${cloud.aws.s3.bucket}") String bucketName, @Value("${cloud.aws.region}")String awsRegion, @Value("${cloud.aws.accesskey}")String awsAccesskey, @Value("${cloud.aws.keyid}")String awsKeyid, @Value("${cloud.aws.endpoint}")String awsEndpoint) {
        this.bucketName = System.getenv().getOrDefault("AWS_S3_BUCKET_NAME", bucketName);;
        this.aws_region = awsRegion;
        this.aws_accesskey = awsAccesskey;
        this.aws_keyid = awsKeyid;
        this.aws_endpoint = System.getenv().getOrDefault("AWS_ENDPOINT_URL", awsEndpoint);
        System.out.println("AWWWWSSSSSSSSS ENDPOINT ======================================" + aws_endpoint);
        if (aws_endpoint.contains("amazonaws.com")) {
            this.s3Client = S3Client.builder()
                    .region(Region.of(System.getenv().getOrDefault("AWS_REGION", aws_region)))
                    .credentialsProvider(StaticCredentialsProvider.create(
                            AwsBasicCredentials.create(
                                    System.getenv().getOrDefault("AWS_ACCESS_KEY_ID", aws_keyid),
                                    System.getenv().getOrDefault("AWS_SECRET_ACCESS_KEY", aws_accesskey)
                            )
                    ))
                    .httpClient(ApacheHttpClient.create())
                    .serviceConfiguration(S3Configuration.builder()
                            .pathStyleAccessEnabled(true)
                            .build())
//                .endpointOverride(URI.create(System.getenv().getOrDefault("AWS_ENDPOINT_URL", aws_endpoint)))
                    .build();

        }else{
            this.s3Client = S3Client.builder()
                    .region(Region.of(System.getenv().getOrDefault("AWS_REGION", aws_region)))
                    .credentialsProvider(StaticCredentialsProvider.create(
                            AwsBasicCredentials.create(
                                    System.getenv().getOrDefault("AWS_ACCESS_KEY_ID", aws_keyid),
                                    System.getenv().getOrDefault("AWS_SECRET_ACCESS_KEY", aws_accesskey)
                            )
                    ))
                    .httpClient(ApacheHttpClient.create())
                    .serviceConfiguration(S3Configuration.builder()
                            .pathStyleAccessEnabled(true)
                            .build())
                .endpointOverride(URI.create(System.getenv().getOrDefault("AWS_ENDPOINT_URL", aws_endpoint)))
                    .build();
        }


        // Criar bucket se não existir
        createBucketIfNotExists();
    }

     // Novo construtor para testes
     public S3GatewayImpl(String bucketName, S3Client s3Client, String awsRegion, String awsAccesskey,String awsKeyid, String awsEndpoint) {
        this.bucketName = bucketName;
        this.s3Client = s3Client;
        this.aws_region = awsRegion;
        this.aws_accesskey = awsAccesskey;
        this.aws_keyid = awsKeyid;
        this.aws_endpoint = awsEndpoint;
    }

    private void createBucketIfNotExists() {
        try {
            // Verifica se o bucket já existe
            s3Client.headBucket(HeadBucketRequest.builder().bucket(bucketName).build());
            System.out.println("Bucket já existe: " + bucketName);
        } catch (S3Exception e) {
            if (e.statusCode() == 404) {
                // Se não existir, cria o bucket
                System.out.println("Criando bucket: " + bucketName);
                s3Client.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());
            } else {
                throw e;
            }
        }
    }

    @Override
    public String uploadFile(String fileName, InputStream fileStream, long fileSize) {
        // 🔥 Agora o upload ocorre em modo "chunked", sem carregar tudo na memória
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentLength(fileSize) // 🔥 Evita problemas com uploads grandes
                .build();

        s3Client.putObject(request, RequestBody.fromInputStream(fileStream, fileSize));

        return getFileUrl(fileName);



    }

    private String getFileUrl(String fileName) {
        return aws_endpoint + "/" + bucketName + "/" + fileName; // 🔥 Retorna URL do arquivo salvo
    }


}
