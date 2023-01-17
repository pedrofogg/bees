create table usuarios(

    id BIGINT NOT NULL auto_increment,
    login VARCHAR(100) NOT NULL,
    senha VARCHAR(255) NOT NULL,

    PRIMARY KEY(id)

);


INSERT INTO `usuarios` (`login`, `senha`) VALUES ('pedro', '$2a$12$baUIfKSZJgjqI3iD/F1XVe/MaioySP.zuRK8kjgBMitZE/zNHsLde');
-- senha 11223344
