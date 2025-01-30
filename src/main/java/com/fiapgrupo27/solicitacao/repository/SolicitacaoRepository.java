package com.fiapgrupo27.solicitacao.repository;

import com.fiapgrupo27.solicitacao.domain.Solicitacao;
import com.fiapgrupo27.solicitacao.dto.SolicitacaoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {



}
