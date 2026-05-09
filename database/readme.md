# Simulador de Energia

Estrutura SQL para simulação de consumo de energia elétrica e economia com energia solar.

## Estrutura dos arquivos

- `simulador.sql` → Criação do banco de dados `simulador_energia`.
- `usuarios.sql` → Criação da tabela de usuários.
- `aparelhos.sql` → Criação da tabela de aparelhos.
- `consumo.sql` → Criação da tabela de consumo mensal.
- `economia.sql` → Criação da tabela de economia solar.
- `resumo.sql` → Criação das views de relatório e resumo.
- `pesquisa.sql` → Consultas de exemplo para testes.

## Como executar

1. Abra o MySQL Workbench ou outro cliente SQL.
2. Execute os arquivos na seguinte ordem:
   - `simulador.sql`
   - `usuarios.sql`
   - `aparelhos.sql`
   - `consumo.sql`
   - `economia.sql`
   - `resumo.sql`
3. Opcional: rode `pesquisa.sql` para validar consultas e testar a estrutura.

## Observações

- Todas as tabelas estão **limpas**, sem dados populados.  
- O objetivo é que a aplicação em Java faça a inserção dos dados via código.  
- Comentários dentro dos arquivos ajudam na interpretação futura.  
- Estrutura modular: cada arquivo pode ser rodado isoladamente para manutenção.

---

Esse repositório foi pensado para ser **simples, objetivo e fácil de manter**, mesmo depois de bastante tempo.  