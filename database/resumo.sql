CREATE OR REPLACE VIEW RELATORIO_CONSUMO AS
SELECT 
    u.nome AS usuario,
    a.nome_aparelho,
    a.potencia_watts,
    a.horas_uso_diario * 30 AS horas_mes,
    (a.potencia_watts * a.horas_uso_diario * 30 / 1000) AS consumo_kwh,
    (a.potencia_watts * a.horas_uso_diario * 30 / 1000) * 1.00 AS custo_tradicional,
    (a.potencia_watts * a.horas_uso_diario * 30 / 1000) * 0.30 AS custo_solar,
    ((a.potencia_watts * a.horas_uso_diario * 30 / 1000) * (1.00 - 0.30)) * 12 AS economia_anual
FROM USUARIOS u
JOIN APARELHOS a ON u.id_usuario = a.id_usuario;