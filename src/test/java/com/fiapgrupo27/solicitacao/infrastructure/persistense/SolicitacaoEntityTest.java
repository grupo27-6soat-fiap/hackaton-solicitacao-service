package com.fiapgrupo27.solicitacao.infrastructure.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fiapgrupo27.solicitacao.infrastructure.persistence.SolicitacaoEntity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

class SolicitacaoEntityTest {

    private SolicitacaoEntity solicitacaoEntity;

    @BeforeEach
    void setUp() {
        // Criação de um objeto SolicitacaoEntity antes de cada teste
        solicitacaoEntity = new SolicitacaoEntity(1L, LocalDateTime.now(), "email");
    }



}
