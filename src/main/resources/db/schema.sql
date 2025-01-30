CREATE TABLE solicitacao
(
    id            BIGSERIAL PRIMARY KEY,
    id_solicitante    INTEGER     NOT NULL,
    status        VARCHAR(50) NOT NULL,
    data_inclusao TIMESTAMP   NOT NULL
);
