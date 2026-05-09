
USE simulador_energia;

CREATE TABLE USUARIOS (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cidade VARCHAR(100),
    renda_familiar DECIMAL(10, 2),
    numero_pessoas INT,
    tamanho_telhado VARCHAR(50)
);
USE simulador_energia;
ALTER TABLE USUARIOS DROP COLUMN login;
ALTER TABLE USUARIOS DROP COLUMN senha;
