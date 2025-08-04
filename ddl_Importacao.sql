CREATE TABLE importacao
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    cpf             VARCHAR(255)          NULL,
    cliente         VARCHAR(255)          NULL,
    nacimento       date                  NULL,
    evento          VARCHAR(255)          NULL,
    data_evento     date                  NULL,
    tipo_ingresso   VARCHAR(255)          NULL,
    valor           DOUBLE                NULL,
    hora_importacao date              NULL,
    CONSTRAINT pk_importacao PRIMARY KEY (id)
);