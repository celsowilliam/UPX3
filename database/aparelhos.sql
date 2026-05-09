USE simulador_energia;

CREATE TABLE APARELHOS (
    id_aparelho INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    nome_aparelho VARCHAR(100) NOT NULL,
    potencia_watts DECIMAL(10,2) NOT NULL,   -- potência do aparelho em watts
    horas_uso_diario DECIMAL(5,2) NOT NULL,           -- média de horas de uso por day
    FOREIGN KEY (id_usuario) REFERENCES USUARIOS(id_usuario)
);