package com.fiapgrupo27.solicitacao.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiapgrupo27.solicitacao.domain.Solicitacao;
import com.fiapgrupo27.solicitacao.domain.SolicitacaoArquivo;
import com.fiapgrupo27.solicitacao.domain.SolicitacaoArquivoRepositoryCustom;
import com.fiapgrupo27.solicitacao.domain.Solicitante;
import com.fiapgrupo27.solicitacao.dto.SolicitacaoDTO;
import com.fiapgrupo27.solicitacao.repository.SolicitacaoArquivoRepository;
import com.fiapgrupo27.solicitacao.repository.SolicitacaoRepository;
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
    private final ObjectMapper objectMapper = new ObjectMapper(); // Jackson para converter objetos em JSON


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

    public String criarSolicitacaoComArquivos(List<MultipartFile> arquivos, Solicitante solicitante) {
        if (arquivos == null || arquivos.isEmpty()) {
            throw new IllegalArgumentException("Nenhum arquivo enviado.");
        }

        // Criar a solicitação
        Solicitacao solicitacao = new Solicitacao(null, solicitante.getIdSolicitante(), "PENDENTE", LocalDateTime.now());
        Solicitacao solicitacaoSalva = solicitacaoRepository.save(solicitacao);


        for (MultipartFile arquivo : arquivos) {
            SolicitacaoArquivo solicitacaoArquivo = new SolicitacaoArquivo(Math.toIntExact(solicitacaoSalva.getId()), solicitante.getIdSolicitante(), arquivo.getOriginalFilename(), "PENDENTE", LocalDateTime.now());
            SolicitacaoArquivo arquivoSalvo = arquivoRepository.save(solicitacaoArquivo);
            //Envia o Arquivo para a Fila
            byte[] arquivoBytes = null;
            try {
                arquivoBytes = arquivo.getBytes();
                String solicitanteJson = objectMapper.writeValueAsString(solicitante);
                rabbitTemplate.convertAndSend("video-processing-queue", criarMensagemMQ(arquivoSalvo, arquivoBytes, solicitanteJson));
                System.out.println("Arquivo enviado para a fila: " + arquivo.getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }


        return "Solicitação criada com sucesso: ID " + solicitacaoSalva.getId();
    }

    public List<SolicitacaoDTO> obterSolicitacoes(Integer idCliente) {
        return arquivoRepositoryCustom.obterSolicitacoes(idCliente);
    }

    private Map<String, Object> criarMensagemMQ(SolicitacaoArquivo arquivo, byte[] arquivoBytes, String solicitante) {
        Map<String, Object> mensagem = new HashMap<>();
        mensagem.put("idSolicitacao", arquivo.getIdSolicitacao());
        mensagem.put("nomeArquivo", arquivo.getNomeArquivo());
        mensagem.put("idArquivo", arquivo.getIdArquivo());
        mensagem.put("conteudoArquivo", arquivoBytes);
        mensagem.put("solicitante", solicitante);

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
