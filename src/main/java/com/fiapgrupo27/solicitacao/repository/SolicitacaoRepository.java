package com.fiapgrupo27.solicitacao.repository;

import com.fiapgrupo27.solicitacao.domain.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
}
