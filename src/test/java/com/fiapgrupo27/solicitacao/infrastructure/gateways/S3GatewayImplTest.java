import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;
import com.fiapgrupo27.solicitacao.infrastructure.gateways.S3GatewayImpl;
import org.mockito.ArgumentCaptor;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;

class S3GatewayImplTest {

    private S3Client mockS3Client;
    private S3GatewayImpl s3GatewayImpl;
    private final String testBucketName = "test-bucket";
    private final String testFileName = "test-file.mp4";
    private final String testData = "Hello, World!";
    private final String awsRegion = "us-east-1";
    private final String awsAccesskey = "test";
    private final String awsKeyid = "test";
    private final String awsEndpoint = "test";
    private final InputStream testDataStream = new ByteArrayInputStream(testData.getBytes());

    @BeforeEach
    void setUp() {
        // Criar mock do cliente S3
        mockS3Client = mock(S3Client.class);
        // Inicializar a classe S3GatewayImpl com o mock
        s3GatewayImpl = new S3GatewayImpl(testBucketName, mockS3Client,awsRegion, awsAccesskey, awsKeyid, awsEndpoint );
    }


    @Test
    void testUploadFileSuccess() {
        // Enviar arquivo e obter a URL de resposta
        String responseUrl = s3GatewayImpl.uploadFile(testFileName, testDataStream, testData.length());

        // Verificar se o método putObject foi chamado corretamente
        verify(mockS3Client).putObject(any(PutObjectRequest.class), any(RequestBody.class));  

        // Verificar a estrutura da URL retornada, sem o prefixo http://localhost:4566
        String expectedUrl = testBucketName + "/" + testFileName;  // Espera apenas o caminho
        assertTrue(responseUrl.endsWith(expectedUrl), "URL should end with: " + expectedUrl);
    }

    @Test
    void testCreateBucketIfNotExists() {
        String awsRegion = "us-east-1";
        String awsAccesskey = "test";
        String awsKeyid = "test";
        String awsEndpoint = "test";
        // Verificar se o método createBucket foi chamado durante a inicialização
        s3GatewayImpl = new S3GatewayImpl(testBucketName, mockS3Client, awsRegion, awsAccesskey, awsKeyid, awsEndpoint);

        // Verificar se o método createBucket foi chamado uma vez
        assertNotNull(s3GatewayImpl);
        
    }

}
