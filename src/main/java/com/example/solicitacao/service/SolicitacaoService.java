package com.example.solicitacao.service;

import com.example.solicitacao.domain.Solicitacao;
import com.example.solicitacao.domain.SolicitacaoArquivo;
import com.example.solicitacao.domain.SolicitacaoArquivoRepositoryCustom;
import com.example.solicitacao.repository.SolicitacaoArquivoRepository;
import com.example.solicitacao.repository.SolicitacaoRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SolicitacaoService {

    private final SolicitacaoRepository solicitacaoRepository;
    private final SolicitacaoArquivoRepository arquivoRepository;
    private final RabbitTemplate rabbitTemplate;
    private final SolicitacaoArquivoRepositoryCustom arquivoRepositoryCustom;

    @Autowired
    public SolicitacaoService(SolicitacaoRepository solicitacaoRepository,
                              SolicitacaoArquivoRepository arquivoRepository,
                              RabbitTemplate rabbitTemplate,
                              SolicitacaoArquivoRepositoryCustom arquivoRepositoryCustom) {
        this.solicitacaoRepository = solicitacaoRepository;
        this.arquivoRepository = arquivoRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.arquivoRepositoryCustom = arquivoRepositoryCustom;
    }

    public String criarSolicitacaoComArquivos(List<MultipartFile> arquivos) {
        if (arquivos == null || arquivos.isEmpty()) {
            throw new IllegalArgumentException("Nenhum arquivo enviado.");
        }

        // Criar a solicitação
        Solicitacao solicitacao = new Solicitacao(null, 1, "PENDENTE", LocalDateTime.now());
        Solicitacao solicitacaoSalva = solicitacaoRepository.save(solicitacao);


        for (MultipartFile arquivo : arquivos) {
            SolicitacaoArquivo solicitacaoArquivo = new SolicitacaoArquivo(Math.toIntExact(solicitacaoSalva.getId()), 1, arquivo.getOriginalFilename(), "PENDENTE", LocalDateTime.now());
            SolicitacaoArquivo arquivoSalvo = arquivoRepository.save(solicitacaoArquivo);
            //Envia o Arquivo para a Fila
            byte[] arquivoBytes = null;
            try {
                arquivoBytes = arquivo.getBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            rabbitTemplate.convertAndSend("video-processing-queue", criarMensagemMQ(arquivoSalvo, arquivoBytes));
            System.out.println("Arquivo enviado para a fila: " + arquivo.getOriginalFilename());

        }


        return "Solicitação criada com sucesso: ID " + solicitacaoSalva.getId();
    }

    private Map<String, Object> criarMensagemMQ(SolicitacaoArquivo arquivo, byte[] arquivoBytes) {
        Map<String, Object> mensagem = new HashMap<>();
        mensagem.put("idSolicitacao", arquivo.getIdSolicitacao());
        mensagem.put("nomeArquivo", arquivo.getNomeArquivo());
        mensagem.put("idArquivo", arquivo.getIdArquivo());
        mensagem.put("conteudoArquivo", arquivoBytes);

        return mensagem;
    }

    @Transactional
    public void atualizarStatusSolicitacao(Long idSolicitacao, Long idArquivo, String status) {
        int rowsUpdated = arquivoRepositoryCustom.atualizarStatus(idSolicitacao, idArquivo, status);

        if (rowsUpdated == 0) {
            throw new RuntimeException("Nenhuma entrada encontrada para idSolicitacao: " + idSolicitacao + ", idArquivo: " + idArquivo);
        }
    }

}
