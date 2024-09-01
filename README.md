# Santander Dev Week 2023

Java RESTful API criada para a Santander Dev Week.

## Principais Tecnologias
 - **Java 17**: Utilizaremos a versão LTS mais recente do Java para tirar vantagem das últimas inovações que essa linguagem robusta e amplamente utilizada oferece;
 - **Spring Boot 3**: Trabalharemos com a mais nova versão do Spring Boot, que maximiza a produtividade do desenvolvedor por meio de sua poderosa premissa de autoconfiguração;
 - **Spring Data JPA**: Exploraremos como essa ferramenta pode simplificar nossa camada de acesso aos dados, facilitando a integração com bancos de dados SQL;
 - **OpenAPI (Swagger)**: Vamos criar uma documentação de API eficaz e fácil de entender usando a OpenAPI (Swagger), perfeitamente alinhada com a alta produtividade que o Spring Boot oferece;
 - **Railway**: facilita o deploy e monitoramento de nossas soluções na nuvem, além de oferecer diversos bancos de dados como serviço e pipelines de CI/CD.

## [Link do Figma](https://www.figma.com/file/0ZsjwjsYlYd3timxqMWlbj/SANTANDER---Projeto-Web%2FMobile?type=design&node-id=1421%3A432&mode=design&t=6dPQuerScEQH0zAn-1)

O Figma foi utilizado para a abstração do domínio desta API, sendo útil na análise e projeto da solução.

## Diagrama de Classes (Domínio da API)

```mermaid
classDiagram
  class User {
    -String name
    -Account account
    -Feature[] features
    -Card card
    -News[] news
  }

  class Account {
    -String number
    -String agency
    -Number balance
    -Number limit
  }

  class Feature {
    -String icon
    -String description
  }

  class Card {
    -String number
    -Number limit
  }

  class News {
    -String icon
    -String description
  }

  class Transfer {
    -Long id
    -User fromUser
    -User toUser
    -BigDecimal amount
    -LocalDateTime transferDate
  }

  User "1" *-- "1" Account : has
  User "1" *-- "N" Feature : has
  User "1" *-- "1" Card : has
  User "1" *-- "N" News : has
  User "1" "1" -- "N" Transfer : initiates
  User "1" "1" -- "N" Transfer : receives
  Transfer "1" --> "1" User : fromUser
  Transfer "1" --> "1" User : toUser


```

## IMPORTANTE

Este projeto foi construído com um viés totalmente educacional para a DIO. Por isso, disponibilizamos uma versão mais robusta dele no repositório oficial da DIO:

### [digitalinnovationone/santander-dev-week-2023-api](https://github.com/digitalinnovationone/santander-dev-week-2023-api)

Lá incluímos todas os endpoints de CRUD, além de aplicar boas práticas (uso de DTOs e refinamento na documentação da OpenAPI). Sendo assim, caso queira um desafio/referência mais completa é só acessar 👊🤩

## Funcionalidades da API

### Gerenciamento de Usuários

- **Cadastrar Usuário**: Criação de um novo usuário.
- **Atualizar Usuário**: Atualização de informações do usuário existente.
- **Deletar Usuário**: Exclusão de um usuário pelo seu ID.
- **Listar Usuários**: Obtenção de todos os usuários cadastrados.
- **Buscar por ID**: Busca de usuário pelo ID.
- **Buscar por Número de Conta**: Busca de usuário pelo número de conta.
- **Adicionar Feature e News**: Adição de recursos e notícias para usuários específicos.

### Transferências

- **Transferência entre Contas**: Realização de transferências entre contas de diferentes usuários.
- **Listar Transferências**: Exibição de todas as transferências realizadas, incluindo detalhes do remetente, destinatário e valor transferido.

## Endpoints da API

| Método  | Endpoint                         | Descrição                                                   |
|---------|----------------------------------|-------------------------------------------------------------|
| `GET`   | `/users`                         | Lista todos os usuários cadastrados.                        |
| `GET`   | `/users/{id}`                    | Retorna detalhes de um usuário específico pelo ID.          |
| `POST`  | `/users`                         | Cria um novo usuário.                                       |
| `PUT`   | `/users/{id}`                    | Atualiza informações de um usuário específico.              |
| `DELETE`| `/users/{id}`                    | Remove um usuário pelo ID.                                  |
| `GET`   | `/users/account/{accountNumber}` | Busca usuário pelo número da conta.                         |
| `POST`  | `/users/{userId}/features`       | Adiciona uma nova feature ao usuário.                       |
| `POST`  | `/users/{userId}/news`           | Adiciona uma nova notícia ao usuário.                       |
| `POST`  | `/transfers`                     | Realiza uma transferência entre contas de usuários.         |
| `GET`   | `/transfers`                     | Lista todas as transferências realizadas.                   |
| `POST`  | `/transfers`                     | Realiza uma transferência entre contas de usuários.         |
| `GET`   | `/transfers`                     | Lista todas as transferências realizadas.                   |


