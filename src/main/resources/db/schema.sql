CREATE TABLE solicitacao
(
    id            BIGSERIAL PRIMARY KEY,
    id_cliente    INTEGER     NOT NULL,
    status        VARCHAR(50) NOT NULL,
    data_inclusao TIMESTAMP   NOT NULL
);
