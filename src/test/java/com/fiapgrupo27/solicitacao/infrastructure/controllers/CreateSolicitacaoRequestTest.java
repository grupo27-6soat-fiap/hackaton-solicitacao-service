package com.fiapgrupo27.solicitacao.infrastructure.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Inicializa mocks automaticamente
class CreateSolicitacaoRequestTest {

    @Mock
    private MultipartFile arquivoMock;

    @Test
    void testGetArquivos() {
        // Setup
        CreateSolicitacaoRequest request = new CreateSolicitacaoRequest();
        List<MultipartFile> arquivos = List.of(arquivoMock);

        // Atributo direto sem setter
        request.arquivos = arquivos;

        // Verificar se o getter retorna corretamente o valor
        assertEquals(arquivos, request.getArquivos());
    }

    @Test
    void testGetSolicitante() {
        // Setup
        CreateSolicitacaoRequest request = new CreateSolicitacaoRequest();
        String solicitante = "João da Silva";

        // Atributo direto sem setter
        request.solicitante = solicitante;

        // Verificar se o getter retorna corretamente o valor
        assertEquals(solicitante, request.getSolicitante());
    }
}
