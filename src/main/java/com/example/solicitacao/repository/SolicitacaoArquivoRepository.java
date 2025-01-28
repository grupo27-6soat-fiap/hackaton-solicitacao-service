package com.example.solicitacao.repository;


import com.example.solicitacao.domain.SolicitacaoArquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SolicitacaoArquivoRepository extends JpaRepository<SolicitacaoArquivo, Integer> {
    List<SolicitacaoArquivo> findByIdSolicitacao(Integer idSolicitacao);

}
