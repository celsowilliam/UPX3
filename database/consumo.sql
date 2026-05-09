USE simulador_energia;

CREATE TABLE CONSUMO (
    id_consumo INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    id_aparelho INT,
    mes_referencia DATE,                  -- mês/ano do cálculo
    consumo_kwh DECIMAL(10,2) NOT NULL,   -- consumo mensal em kWh
    custo_reais DECIMAL(10,2) NOT NULL,   -- custo em R$
    FOREIGN KEY (id_usuario) REFERENCES USUARIOS(id_usuario),
    FOREIGN KEY (id_aparelho) REFERENCES APARELHOS(id_aparelho)
);