# 🏥 API — VibeWork

Esta API foi desenvolvida para fornecer uma infraestrutura completa para o VibeWork, uma plataforma voltada ao equilíbrio entre produtividade, organização pessoal e bem-estar no trabalho híbrido.
Por meio dela, é possível gerenciar rotinas semanais, registrar relatórios de produtividade e bem-estar, criar e consultar eventos no calendário, além de oferecer recursos fundamentais para que usuários acompanhem sua própria evolução ao longo do tempo.
O propósito central da API é facilitar a vida do usuário em ambientes híbridos, criando um ecossistema confiável, ágil e integrável, capaz de aumentar a organização pessoal, promover autoconhecimento, apoiar uma gestão saudável da rotina e fornecer dados estruturados que auxiliem na tomada de decisões.

---

## 🌐 Link da API Hospedada
> 🔗 **URL:** _[https://api-vibe-work.onrender.com](https://api-vibe-work.onrender.com)]_

---

## ⚙️ Tecnologias Utilizadas

- **Java 17+**
- **Jakarta EE / JAX-RS** (para endpoints REST)
- **Maven** (gerenciamento de dependências)
- **JDBC** (conexão com o banco)
- **JSON (Jackson / GSON)** (formato de resposta)

---

## 🧩 Estrutura do Projeto

```
src/
├── beans/
│ ├── enums/
│ │  └── TipoTrabalho.java
│ ├── EventoCalendario.java
│ ├── RelatorioBemEstar.java
│ ├── RelatorioProdutividade.java
│ ├── RotinaSemanal.java
│ └── Usuario.java
│
├── dao/
│ ├── EventoCalendarioDAO.java
│ ├── RelatorioBemEstarDAO.java
│ ├── RelatorioProdutividadeDAO.java
│ ├── RotinaSemanalDAO.java
│ └── UsuarioDAO.java
│
├── bo/
│ ├── EventoCalendarioBO.java
│ ├── RelatorioBemEstarBO.java
│ ├── RelatorioProdutividadeBO.java
│ ├── RotinaSemanalBO.java
│ └── UsuarioBO.java
│
├── CorsFilter.java
├── EventoCalendario.java
├── RelatorioBemEstarResource.java
├── RelatorioProdutividadeResource.java
├── RotinaSemanalResource.java
└── UsuarioResource.java
│
├── conexoes/
│ └── ConexaoFactory.java
│
└── excecoes/
└── RequestsExcecoes.java
```
---

## 🚀 Funcionalidades Principais

### Rotina Semanal
- Registro, edição e exclusão da rotina semanal do usuário
- Definição de dias presenciais, remotos e folgas
- Consulta rápida da rotina salva para integração com o app

### Calendário e Eventos
- Criação de novos eventos com título, descrição e data
- Listagem de eventos por mês ou por dia
- Edição de eventos (título e descrição)
- Exclusão de eventos existentes
- Suporte a datas no formato timestamp para maior precisão

### Relatórios de Bem-Estar
- Criação de relatórios contendo nível de energia, pausas, estresse e descrição
- Listagem completa do histórico do usuário
- Exclusão de relatórios específicos

### Relatórios de Produtividade
- Registro de relatórios com horas trabalhadas, dias presenciais/remotos e descrição
- Consulta de relatórios por usuário
- Exclusão individual de registros

---

## 🔒 Tratamento de Erros e Conexão

A API inclui mecanismos de tratamento de erros para garantir estabilidade, especialmente em operações que envolvem banco de dados. Em caso de falha de conexão ou erro SQL, a API tenta restabelecer a conexão de forma segura antes de repetir a operação.

Exemplo de tratamento:

```java
catch (Exception e) {
        if (e instanceof SQLException sqlError) {
        // Caso de conexão perdida ou sessão inválida
        if (sqlError.getErrorCode() == 17008) {
        this.conexao.close();
            this.conexao = new ConexaoFactory().conexao();
            return relatorioProdutividadeDAO.selecionarRelatorioPorIdUsuario(conexao, id);
        }
                }
                throw e; // Propaga para o Resource tratar corretamente
}
```
Assim, apenas o erro de conexão fechada (17008) é tratado diretamente.
Qualquer outro erro é lançado novamente (throw e) para ser capturado e tratado no nível do Resource, mantendo a arquitetura limpa e previsível.

---

🧠 Arquitetura

A API segue uma arquitetura em camadas (MVC expandido):

DAO (Data Access Object): acesso direto ao banco de dados
BO (Business Object): regras de negócio e tratamento de exceções
Resource (Controller REST): camada de exposição via HTTP

### 🧭 Endpoints — Resumo

| Método | Endpoint                                     | Descrição |
|:--------:|:---------------------------------------------|:---------------------------------------------|
| Método     | Endpoint                                     | Descrição                                              |
| **GET**    | `/usuario`                                   | Retorna todos os usuários cadastrados.                 |
| **GET**    | `/usuario/{id}`                              | Retorna um usuário específico pelo ID.                 |
| **POST**   | `/usuario`                                   | Cadastra um novo usuário.                              |
| **PUT**    | `/usuario`                                   | Atualiza os dados de um usuário existente.             |
| **DELETE** | `/usuario/{id}`                              | Remove um usuário do sistema.                          |
| **GET**    | `/rotina_semanal/{id}`                       | Retorna a rotina semanal de um usuário.                |
| **POST**   | `/rotina_semanal/{id}`                       | Cria ou substitui a rotina semanal do usuário.         |
| **PUT**    | `/rotina_semanal/{id}`                       | Atualiza dias específicos da rotina.                   |
| **DELETE** | `/rotina_semanal/{id}`                       | Remove a rotina semanal do usuário.                    |
| **GET**    | `/evento_calendario/{id}`                    | Retorna todos os eventos do usuário.                   |
| **GET**    | `/evento_calendario/{id}/{idEvento}`         | Retorna um evento específico.                          |
| **POST**   | `/evento_calendario/{id}`                    | Cria um novo evento no calendário.                     |
| **PUT**    | `/evento_calendario/{id}`                    | Atualiza título ou descrição de um evento.             |
| **DELETE** | `/evento_calendario/{id}/{idEvento}`         | Remove um evento do calendário.                        |
| **GET**    | `/relatorioProdutividade/{id}`               | Lista todos os relatórios de produtividade do usuário. |
| **POST**   | `/relatorioProdutividade/{id}`               | Cria um novo relatório de produtividade.               |
| **DELETE** | `/relatorioProdutividade/{id}/{idRelatorio}` | Remove um relatório de produtividade.                  |
| **GET**    | `/relatorioBemEstar/{id}`                    | Lista todos os relatórios de bem-estar do usuário.     |
| **POST**   | `/relatorioBemEstar/{id}`                    | Cria um novo relatório de bem-estar.                   |
| **DELETE** | `/relatorioBemEstar/{id}/{idRelatorio}`      | Remove um relatório de bem-estar.                      |

---

🧪 Exemplos de Requisição
POST /usuario

Body (JSON)
```JSON
{
  "nome": "Ana Silva",
  "email": "ana.silva@example.com",
  "senha": "minhasenha123",
  "preferenciaTrabalho": "Remoto"
}
```
POST /rotina_semanal

Body (JSON)
```JSON
{
  "segunda": "PRESENCIAL",
  "terca": "REMOTO",
  "quarta": "REMOTO",
  "quinta": "PRESENCIAL",
  "sexta": "FOLGA",
  "sabado": "FOLGA",
  "domingo": "FOLGA",
  "anotacao": "Rotina ótima!",
  "usuario": {
    "id": 1
  }
}

```

---

🧰 Como Executar Localmente

**1.** Clonar o repositório
```
git clone https://github.com/pmiranda27/api-vibe-work.git
```

**2.** Importar no IntelliJ / Eclipse

**3.** Importe como projeto Maven.

**4.** Configurar o banco de dados

**5.** Crie o banco e ajuste as credenciais no ConexaoFactory.java.

**6.** Executar o servidor

**7.** Faça o deploy no Tomcat, Payara, Render ou GlassFish.

**8.** Testar os endpoints

**9.** Utilize Postman ou Insomnia para fazer requisições.

---

📈 Objetivo do Projeto

Este projeto foi desenvolvido como parte do Global Solution, um desafio voltado para criar ferramentas tecnológicas inovadoras dentro do tema “O Futuro do Trabalho”.

---

👥 Autores

Pedro Miranda — **RM:** 562682  
André Rosa Colombo — **RM:** 563112  
José Diogo da Silva Neves — **RM:** 562341

---

📎 Licença

_Este projeto é de uso acadêmico e não possui fins comerciais.
Sinta-se à vontade para estudar, adaptar e contribuir._