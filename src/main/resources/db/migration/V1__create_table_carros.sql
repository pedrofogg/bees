create table carros(
    id BIGINT NOT NULL auto_increment,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    disponivel TINYINT(1) NOT NULL,
    PRIMARY KEY(id)
);