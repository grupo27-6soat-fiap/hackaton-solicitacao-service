package com.fiapgrupo27.solicitacao.infrastructure;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BaseRepository {
    protected final JdbcTemplate jdbcTemplate;

    public BaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    protected int executarAtualizacao(String sql, Object... parametros) {
        return jdbcTemplate.update(sql, parametros);
    }

    protected <T> T executarConsulta(String sql, Object[] parametros, Class<T> tipoRetorno) {
        return jdbcTemplate.queryForObject(sql, parametros, tipoRetorno);
    }
}
