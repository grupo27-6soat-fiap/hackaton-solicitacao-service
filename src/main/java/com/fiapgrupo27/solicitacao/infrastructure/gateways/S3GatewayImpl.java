package com.fiapgrupo27.solicitacao.infrastructure.gateways;

import com.fiapgrupo27.solicitacao.application.gateways.S3Gateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.core.sync.RequestBody;



import java.io.InputStream;
import java.net.URI;
import java.nio.file.Paths;

@Component
public class S3GatewayImpl implements S3Gateway {

    private final S3Client s3Client;
    private final String bucketName;

    public S3GatewayImpl(@Value("${cloud.aws.s3.bucket}") String bucketName) {
        this.bucketName = bucketName;

        this.s3Client = S3Client.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("test", "test")))
                .httpClient(ApacheHttpClient.create())  // 🔥 Usa um cliente HTTP compatível
                .serviceConfiguration(S3Configuration.builder()
                        .pathStyleAccessEnabled(true) // 🔥 Força Path-Style Access
                        .build())
                .endpointOverride(URI.create("http://localhost:4566")) // 🔥 LocalStack como endpoint
                .build();

        // Criar bucket se não existir
        createBucketIfNotExists();
    }

    private void createBucketIfNotExists() {
        s3Client.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());
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
        return "http://localhost:4566/" + bucketName + "/" + fileName; // 🔥 Retorna URL do arquivo salvo
    }

}
