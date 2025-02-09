package com.fiapgrupo27.solicitacao.config;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiapgrupo27.solicitacao.application.gateways.MensagemGateway;
import com.fiapgrupo27.solicitacao.application.gateways.S3Gateway;
import com.fiapgrupo27.solicitacao.application.gateways.SolicitacaoArquivoGateway;
import com.fiapgrupo27.solicitacao.application.gateways.SolicitacaoGateway;
import com.fiapgrupo27.solicitacao.application.usecases.AtualizarStatusSolicitacaoArquivoInteractor;
import com.fiapgrupo27.solicitacao.application.usecases.CreateSolicitacaoArquivoInteractor;
import com.fiapgrupo27.solicitacao.application.usecases.CreateSolicitacaoInteractor;
import com.fiapgrupo27.solicitacao.application.usecases.ObterSolicitacoesInteractor;
import com.fiapgrupo27.solicitacao.infrastructure.gateways.SolicitacaoEntityMapper;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoArquivoRepository;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SolicitacaoConfig.class)
public class SolicitacaoConfigTest {

    @Autowired
    private ApplicationContext context;

    @MockBean
    private SolicitacaoRepository solicitacaoRepository;

    @MockBean
    private SolicitacaoEntityMapper solicitacaoEntityMapper;

    @MockBean
    private SolicitacaoArquivoGateway solicitacaoArquivoGateway;

    @MockBean
    private MensagemGateway mensagemGateway;

    @MockBean
    private ObjectMapper objectMapper;

    @MockBean
    private S3Gateway s3Gateway;

    @MockBean
    private SolicitacaoArquivoRepository solicitacaoArquivoRepository;

    @Test
    public void testCreateSolicitacaoInteractorBean() {
        CreateSolicitacaoInteractor bean = context.getBean(CreateSolicitacaoInteractor.class);
        assertNotNull(bean);
    }

    @Test
    public void testCreateSolicitacaoArquivoInteractorBean() {
        CreateSolicitacaoArquivoInteractor bean = context.getBean(CreateSolicitacaoArquivoInteractor.class);
        assertNotNull(bean);
    }

    @Test
    public void testAtualizarStatusSolicitacaoArquivoInteractorBean() {
        AtualizarStatusSolicitacaoArquivoInteractor bean = context.getBean(AtualizarStatusSolicitacaoArquivoInteractor.class);
        assertNotNull(bean);
    }

    @Test
    public void testObterSolicitacoesInteractorBean() {
        ObterSolicitacoesInteractor bean = context.getBean(ObterSolicitacoesInteractor.class);
        assertNotNull(bean);
    }

    @Test
    public void testObjectMapperBean() {
        ObjectMapper bean = context.getBean(ObjectMapper.class);
        assertNotNull(bean);
    }

    @Test
    public void testSolicitacaoGatewayBean() {
        SolicitacaoGateway bean = context.getBean(SolicitacaoGateway.class);
        assertNotNull(bean);
    }

    @Test
    public void testSolicitacaoEntityMapperBean() {
        SolicitacaoEntityMapper bean = context.getBean(SolicitacaoEntityMapper.class);
        assertNotNull(bean);
    }

    @Test
    public void allBeansShouldBeDistinct() {
        ConfigurableApplicationContext configurableContext = (ConfigurableApplicationContext) context;
        long distinctBeanCount = context.getBeanDefinitionNames().length;
        long totalBeanCount = configurableContext.getBeanFactory().getBeanDefinitionCount();
        assertEquals(distinctBeanCount, totalBeanCount);
    }
}
