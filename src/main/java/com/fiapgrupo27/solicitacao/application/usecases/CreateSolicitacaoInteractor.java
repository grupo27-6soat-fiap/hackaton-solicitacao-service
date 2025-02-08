package com.fiapgrupo27.solicitacao.application.usecases;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiapgrupo27.solicitacao.application.gateways.MensagemGateway;
import com.fiapgrupo27.solicitacao.application.gateways.S3Gateway;
import com.fiapgrupo27.solicitacao.application.gateways.SolicitacaoArquivoGateway;
import com.fiapgrupo27.solicitacao.application.gateways.SolicitacaoGateway;
import com.fiapgrupo27.solicitacao.domain.entity.Solicitacao;
import com.fiapgrupo27.solicitacao.domain.entity.SolicitacaoArquivo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class CreateSolicitacaoInteractor {
    private SolicitacaoGateway solicitacaoGateway;
    private final SolicitacaoArquivoGateway solicitacaoArquivoGateway;
    private final MensagemGateway mensagemGateway;
    private final ObjectMapper objectMapper;
    private final S3Gateway s3Gateway;

    public CreateSolicitacaoInteractor(SolicitacaoGateway solicitacaoGateway, SolicitacaoArquivoGateway solicitacaoArquivoGateway, MensagemGateway mensagemGateway, ObjectMapper objectMapper, S3Gateway s3Gateway) {
        this.solicitacaoGateway = solicitacaoGateway;
        this.solicitacaoArquivoGateway = solicitacaoArquivoGateway;
        this.mensagemGateway = mensagemGateway;
        this.objectMapper = objectMapper;
        this.s3Gateway = s3Gateway;
    }

    public Solicitacao createSolicitacao(Solicitacao solicitacao, List<MultipartFile> arquivos, String solicitante) {
        Solicitacao solicitacaoSalva =  solicitacaoGateway.createSolicitacao(solicitacao);
        String nomeArquivoAlterado;


        for (MultipartFile arquivo : arquivos) {
            nomeArquivoAlterado = solicitacaoSalva.getIdSolicitacao() + "/" + arquivo.getOriginalFilename();
            SolicitacaoArquivo solicitacaoArquivo = new SolicitacaoArquivo(solicitacaoSalva.getIdSolicitacao(), solicitacaoSalva.getIdSolicitante(), nomeArquivoAlterado, "PENDENTE", LocalDateTime.now());
            solicitacaoArquivoGateway.salvar(solicitacaoArquivo);

            try {
                String fileUrl = s3Gateway.uploadFile(nomeArquivoAlterado, arquivo.getInputStream(), arquivo.getSize());

                mensagemGateway.enviarMensagem(solicitacaoArquivo, fileUrl, solicitante);

//                mensagemGateway.enviarMensagem(solicitacaoArquivo,arquivo.getBytes(),solicitante);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return solicitacaoSalva;
    }
}
