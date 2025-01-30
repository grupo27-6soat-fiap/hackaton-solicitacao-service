package com.fiapgrupo27.solicitacao.repository;


import com.fiapgrupo27.solicitacao.domain.SolicitacaoArquivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitacaoArquivoRepository extends JpaRepository<SolicitacaoArquivo, Integer> {
    List<SolicitacaoArquivo> findByIdSolicitacao(Integer idSolicitacao);

}
