package com.fiapgrupo27.solicitacao.infrastructure.gateways;

import com.fiapgrupo27.solicitacao.application.dto.SolicitacaoArquivoDTO;
import com.fiapgrupo27.solicitacao.application.dto.SolicitacaoResponseDTO;
import com.fiapgrupo27.solicitacao.application.gateways.SolicitacaoGateway;
import com.fiapgrupo27.solicitacao.domain.entity.Solicitacao;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoArquivoRepository;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoEntity;
import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SolicitacaoRepositoryGateway implements SolicitacaoGateway {

    private final SolicitacaoRepository solicitacaoRepository;
    private final SolicitacaoEntityMapper solicitacaoEntityMapper;
    private final SolicitacaoArquivoRepository arquivoRepository;


    public SolicitacaoRepositoryGateway(SolicitacaoRepository solicitacaoRepository, SolicitacaoEntityMapper solicitacaoEntityMapper, SolicitacaoArquivoRepository arquivoRepository) {
        this.solicitacaoRepository = solicitacaoRepository;
        this.solicitacaoEntityMapper = solicitacaoEntityMapper;
        this.arquivoRepository = arquivoRepository;
    }

    @Override
    public Solicitacao createSolicitacao(Solicitacao solicitacaoDomainObj) {
        SolicitacaoEntity solicitacaoEntity = solicitacaoEntityMapper.toSolicitacaoEntity(solicitacaoDomainObj);
        SolicitacaoEntity savedObj = solicitacaoRepository.save(solicitacaoEntity);
        return solicitacaoEntityMapper.toSolicitacaoDomainObj(savedObj);
    }

    @Override
    public List<Solicitacao> obterSolicitacoes(String email) {
        if (email != null) {
            return solicitacaoRepository.findByEmail(email)
                    .stream()
                    .map(SolicitacaoEntityMapper::toDomain)
                    .collect(Collectors.toList());
        }
        return solicitacaoRepository.findAll()
                .stream()
                .map(SolicitacaoEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<SolicitacaoResponseDTO> obterSolicitacoesComArquivos(String email) {
        return solicitacaoRepository.findByEmail(email)
                .stream()
                .map(solicitacao -> new SolicitacaoResponseDTO(
                        solicitacao.getIdSolicitacao(),
                        solicitacao.getDataInclusao(),
                        arquivoRepository.findByIdSolicitacao(solicitacao.getIdSolicitacao())
                                .stream()
                                .map(arquivo -> new SolicitacaoArquivoDTO(
                                        arquivo.getIdArquivo(),
                                        arquivo.getNomeArquivo(),
                                        arquivo.getStatus()
                                ))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}
