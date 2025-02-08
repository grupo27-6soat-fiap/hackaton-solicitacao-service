package com.fiapgrupo27.solicitacao.application.gateways;

import java.io.InputStream;

public interface S3Gateway {
    String uploadFile(String fileName, InputStream fileStream, long contentLength);
}
