USE simulador_energia;

CREATE TABLE ECONOMIA_SOLAR (
    id_economia INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    id_aparelho INT,
    mes_referencia DATE,
    consumo_kwh DECIMAL(10,2) NOT NULL,       -- consumo mensal em kWh
    custo_tradicional DECIMAL(10,2) NOT NULL, -- custo com energia da distribuidora
    custo_solar DECIMAL(10,2) NOT NULL,       -- custo simulado com energia solar
    economia_mensal DECIMAL(10,2) NOT NULL,   -- diferença mensal
    economia_anual DECIMAL(10,2) NOT NULL,    -- diferença anual
    FOREIGN KEY (id_usuario) REFERENCES USUARIOS(id_usuario),
    FOREIGN KEY (id_aparelho) REFERENCES APARELHOS(id_aparelho)
);