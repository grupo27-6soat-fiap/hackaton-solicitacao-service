package com.fiapgrupo27.solicitacao.infrastructure;

import com.fiapgrupo27.solicitacao.domain.SolicitacaoArquivoRepositoryCustom;
import com.fiapgrupo27.solicitacao.dto.SolicitacaoArquivoDTO;
import com.fiapgrupo27.solicitacao.dto.SolicitacaoDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<SolicitacaoDTO> obterSolicitacoes(Integer idSolicitante) {
        String sql = "SELECT " +
                "s.id_solicitacao, s.id_solicitante, s.status, s.data_inclusao, " +
                "sa.id_arquivo, sa.nome_arquivo, sa.status AS status_arquivo, sa.data_inclusao AS data_arquivo " +
                "FROM solicitacao s " +
                "LEFT JOIN solicitacao_arquivo sa ON s.id_solicitacao = sa.id_solicitacao " +
                (idSolicitante != null ? "WHERE s.id_solicitante = ?" : ""); // Aplica filtro opcionalmente

        Map<Long, SolicitacaoDTO> solicitacaoMap = new HashMap<>();

        jdbcTemplate.query(sql,
                idSolicitante != null ? new Object[]{idSolicitante} : new Object[]{},
                (rs) -> {
                    Long idSolicitacao = rs.getLong("id_solicitacao");
                    int idCli = rs.getInt("id_solicitante");
                    String status = rs.getString("status");
                    LocalDateTime dataInclusao = rs.getTimestamp("data_inclusao").toLocalDateTime();

                    Long idArquivo = rs.getLong("id_arquivo");
                    String nomeArquivo = rs.getString("nome_arquivo");
                    String statusArquivo = rs.getString("status_arquivo");
                    LocalDateTime dataArquivo = rs.getTimestamp("data_arquivo") != null
                            ? rs.getTimestamp("data_arquivo").toLocalDateTime()
                            : null;

                    // Obtém ou cria a solicitação no mapa
                    SolicitacaoDTO solicitacaoDTO = solicitacaoMap.computeIfAbsent(idSolicitacao, id ->
                            new SolicitacaoDTO(id, idCli, status, dataInclusao, new ArrayList<>()));

                    // Se houver um arquivo associado, adiciona à lista
                    if (idArquivo != null && idArquivo > 0) {
                        solicitacaoDTO.getArquivos().add(new SolicitacaoArquivoDTO(idArquivo, nomeArquivo, statusArquivo, dataArquivo));
                    }
                });

        return new ArrayList<>(solicitacaoMap.values());
}}

