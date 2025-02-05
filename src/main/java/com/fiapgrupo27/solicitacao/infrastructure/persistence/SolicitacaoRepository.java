package com.fiapgrupo27.solicitacao.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitacaoRepository extends JpaRepository<SolicitacaoEntity, Long> {
    List<SolicitacaoEntity> findByIdSolicitante(Long idSolicitante);




}
