package com.fiapgrupo27.solicitacao.domain;

import com.fiapgrupo27.solicitacao.dto.SolicitacaoDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SolicitacaoArquivoRepositoryCustom {
    int atualizarStatus(Long idSolicitacao, Long idArquivo, String status);
    List<SolicitacaoDTO> obterSolicitacoes(@Param("idSolicitante") Integer idSolicitante);

}
