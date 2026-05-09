USE simulador_energia;

SELECT u.nome AS Usuario,
       GROUP_CONCAT(a.nome_aparelho SEPARATOR ', ') AS Aparelhos
FROM USUARIOS u
JOIN APARELHOS a ON u.id_usuario = a.id_usuario
GROUP BY u.id_usuario;