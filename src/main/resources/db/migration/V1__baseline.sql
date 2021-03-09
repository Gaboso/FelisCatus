CREATE SCHEMA IF NOT EXISTS felis;
ALTER SCHEMA felis OWNER TO postgres;

CREATE TABLE IF NOT EXISTS felis.tb_user
(
    cpf     varchar(14)  NOT NULL,
    address varchar(150),
    name    varchar(150) NOT NULL,
    phone   varchar(16),
    sex     char(1),

    CONSTRAINT pk_user PRIMARY KEY (cpf)
);
ALTER TABLE felis.tb_user
    OWNER TO postgres;