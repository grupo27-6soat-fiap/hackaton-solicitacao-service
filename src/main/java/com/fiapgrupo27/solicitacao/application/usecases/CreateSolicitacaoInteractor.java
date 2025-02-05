package com.fiapgrupo27.solicitacao.application.usecases;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiapgrupo27.solicitacao.application.gateways.MensagemGateway;
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

    public CreateSolicitacaoInteractor(SolicitacaoGateway solicitacaoGateway, SolicitacaoArquivoGateway solicitacaoArquivoGateway, MensagemGateway mensagemGateway, ObjectMapper objectMapper) {
        this.solicitacaoGateway = solicitacaoGateway;
        this.solicitacaoArquivoGateway = solicitacaoArquivoGateway;
        this.mensagemGateway = mensagemGateway;
        this.objectMapper = objectMapper;
    }

    public Solicitacao createSolicitacao(Solicitacao solicitacao, List<MultipartFile> arquivos, String solicitante) {
        Solicitacao solicitacaoSalva =  solicitacaoGateway.createSolicitacao(solicitacao);


        for (MultipartFile arquivo : arquivos) {
            SolicitacaoArquivo solicitacaoArquivo = new SolicitacaoArquivo(solicitacaoSalva.getIdSolicitacao(), solicitacaoSalva.getIdSolicitante(), arquivo.getOriginalFilename(), "PENDENTE", LocalDateTime.now());
            solicitacaoArquivoGateway.salvar(solicitacaoArquivo);

            try {
                mensagemGateway.enviarMensagem(solicitacaoArquivo,arquivo.getBytes(),solicitante);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return solicitacaoSalva;
    }
}
