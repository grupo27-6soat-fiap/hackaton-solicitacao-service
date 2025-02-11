package com.fiapgrupo27.solicitacao.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SolicitacaoArquivoRepository extends JpaRepository<SolicitacaoArquivoEntity, SolicitacaoArquivoId> {
    @Modifying
    @Transactional
    @Query("UPDATE SolicitacaoArquivoEntity s SET s.status = :status WHERE s.idSolicitacao = :idSolicitacao AND s.idArquivo = :idArquivo")
    void atualizarStatus(Long idSolicitacao, Long idArquivo, String status);

    List<SolicitacaoArquivoEntity> findByIdSolicitacao(Long idSolicitacao);

}
