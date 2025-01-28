package com.example.solicitacao.infrastructure;

import com.example.solicitacao.domain.SolicitacaoArquivoRepositoryCustom;
import com.example.solicitacao.repository.SolicitacaoArquivoRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SolicitacaoArquivoRepositoryImpl extends BaseRepository implements SolicitacaoArquivoRepositoryCustom {

    public SolicitacaoArquivoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public int atualizarStatus(Long idSolicitacao, Long idArquivo, String status) {
        String sql = "UPDATE solicitacao_arquivo SET status = ? WHERE id_arquivo = ? and id_solicitacao = ?";
        return jdbcTemplate.update(sql, status, idArquivo, idSolicitacao);

    }
}
