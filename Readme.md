# UPX3 — Simulador de Energia Solar

## O que é o projeto

O **Simulador de Energia Solar** é um sistema de linha de comando desenvolvido em Java que permite ao usuário calcular quanto ele poderia economizar na conta de luz ao instalar painéis solares na sua residência. O sistema leva em conta a localização do usuário (pelo DDD), o tamanho do telhado disponível e os dados reais de consumo elétrico ao longo do ano para gerar uma simulação personalizada.

---

## Como funciona

O usuário se cadastra informando seus dados pessoais e residenciais. Após o login, ele insere os valores de consumo e custo da sua conta de energia mês a mês. Com essas informações, o sistema calcula:

- Quantos painéis solares seriam necessários para cobrir o consumo anual
- Quantos painéis cabem no telhado da residência
- Quanto energia esses painéis gerariam por ano
- Qual seria o custo residual de energia após a instalação
- A economia mensal e anual estimada
- O investimento total necessário e o tempo de retorno (payback)

Todos os resultados são salvos no banco de dados para que o usuário possa consultar seu histórico de simulações a qualquer momento.

---

## Estrutura do projeto

```
UPX3/
├── conexao/
│   └── Conexao.java          # Gerencia a conexão com o banco MySQL
├── database/
│   ├── simulador.sql         # Criação do banco de dados
│   ├── usuarios.sql          # Tabela de usuários
│   ├── aparelhos.sql         # Tabela de aparelhos
│   ├── consumo.sql           # Tabela de consumo mensal
│   └── economia.sql          # Tabela de economia solar
├── lib/
│   └── mysql-connector-j-9.4.0.jar
└── src/
    ├── app/
    │   └── Main.java         # Ponto de entrada do sistema
    ├── dao/
    │   ├── UsuarioDAO.java   # Operações de banco para usuários
    │   ├── AparelhoDAO.java  # Operações de banco para aparelhos
    │   ├── ConsumoDAO.java   # Operações de banco para consumo
    │   └── EconomiaSolarDAO.java # Operações de banco para economia solar
    └── modelo/
        ├── Sistema.java      # Lógica principal e menus
        ├── Usuario.java      # Modelo do usuário com validações
        ├── Aparelho.java     # Modelo de aparelho elétrico
        ├── MesConta.java     # Modelo de um mês de consumo
        └── PlacaSolar.java   # Cálculos de geração solar por DDD
```

---

## Banco de dados

Execute os SQLs na seguinte ordem:

```sql
-- 1. Criar o banco
source database/simulador.sql

-- 2. Criar as tabelas
source database/usuarios.sql
source database/aparelhos.sql
source database/consumo.sql
source database/economia.sql
```

**Tabelas:**

| Tabela | O que armazena |
|---|---|
| `USUARIOS` | Dados de cadastro e login do usuário |
| `APARELHOS` | Aparelhos elétricos cadastrados por usuário |
| `CONSUMO` | Consumo mensal em kWh e custo em R$ |
| `ECONOMIA_SOLAR` | Resultado das simulações solares |

---

## Tecnologias utilizadas

- Java 17+
- MySQL 8+
- JDBC (mysql-connector-j 9.4.0)

---

## Como executar

1. Configure o banco de dados conforme a seção acima
2. Ajuste as credenciais em `conexao/Conexao.java` se necessário
3. Compile o projeto incluindo o `.jar` do driver MySQL no classpath
4. Execute a classe `src.app.Main`

---

## DDDs suportados

O sistema atende residências do estado de São Paulo. Cada DDD possui um índice de irradiação solar diferente, usado para calcular a geração estimada dos painéis:

| DDD | Região | Irradiação (kWh/m²/ano) |
|---|---|---|
| 11 | São Paulo (capital) | 1500 |
| 12 | São José dos Campos | 1550 |
| 13 | Santos | 1600 |
| 14 | Bauru | 1600 |
| 15 | Sorocaba | 1550 |
| 16 | Ribeirão Preto | 1650 |
| 17 | São José do Rio Preto | 1650 |
| 18 | Presidente Prudente | 1600 |
| 19 | Campinas | 1550 |

---

## Validações do sistema

- Email deve conter `@` e `.`
- Senha deve ter no mínimo 8 caracteres, uma letra maiúscula e um número
- DDD deve ser um dos DDDs válidos de SP (11 a 19)
- Renda familiar não pode ser negativa
- Número de pessoas deve ser maior que zero