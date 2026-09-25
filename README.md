# API de Transações — Desafio Backend PicPay

## Visão Geral

Esta aplicação consiste em uma API REST desenvolvida em Java utilizando Spring Boot, criada como solução para um desafio de desenvolvimento backend.

O projeto tem como objetivo implementar uma API responsável pelo gerenciamento de carteiras e realização de transações financeiras entre usuários, contemplando diferentes tipos de usuários e regras de negócio relacionadas às transferências.

A aplicação foi desenvolvida com foco em organização, separação de responsabilidades, boas práticas de desenvolvimento backend e utilização dos principais recursos oferecidos pelo ecossistema Spring.

A persistência dos dados é realizada utilizando banco de dados relacional, permitindo que usuários, carteiras e transações sejam armazenados de forma persistente.

## Objetivo

O principal objetivo da aplicação é disponibilizar uma estrutura backend capaz de:

- Cadastrar e gerenciar usuários;
- Diferenciar usuários comuns de lojistas;
- Criar carteiras associadas aos usuários;
- Realizar transferências entre carteiras;
- Validar as regras necessárias para uma transação;
- Registrar as transações realizadas;
- Persistir as informações em banco de dados;
- Expor os recursos por meio de uma API REST.

A aplicação foi estruturada de forma que as regras de negócio permaneçam separadas das responsabilidades de infraestrutura e comunicação HTTP.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Gradle
- MySQL
- Docker
- JUnit 5
- Mockito
- Bean Validation
- Lombok

## Arquitetura

A aplicação foi desenvolvida utilizando uma arquitetura em camadas, buscando manter as responsabilidades bem definidas e facilitar a manutenção e evolução do projeto.

### Controller

Responsável pela comunicação entre o cliente e a aplicação.

Nesta camada são realizadas as operações relacionadas às requisições HTTP, incluindo:

- Recebimento das requisições;
- Validação dos dados recebidos;
- Encaminhamento das operações para a camada de serviço;
- Retorno das respostas HTTP.

### Service

Contém as principais regras de negócio da aplicação.

Nesta camada são realizadas operações como:

- Criação de usuários;
- Criação e gerenciamento de carteiras;
- Validação das regras de transferência;
- Processamento das transações;
- Comunicação com os repositórios;
- Orquestração das operações da aplicação.

A separação dessas regras permite manter os controllers mais simples e concentrar a lógica da aplicação em uma camada específica.

### Repository

Responsável pelo acesso aos dados persistidos.

A aplicação utiliza Spring Data JPA para abstrair as operações de persistência e comunicação com o banco de dados.

Entre as responsabilidades estão:

- Consulta de usuários;
- Consulta de carteiras;
- Persistência de transações;
- Atualização dos dados;
- Busca de informações necessárias para as regras de negócio.

### Entity

As entidades representam os principais modelos persistidos pela aplicação.

Entre os principais modelos estão:

- `Carteira`
- `Transacao`
- `TipoDeCarteira`

As entidades são mapeadas utilizando JPA/Hibernate e representam as estruturas utilizadas para persistência dos dados.

### DTO

Os DTOs são utilizados para transportar informações entre a API e as demais camadas da aplicação.

A utilização dessa abordagem evita que as entidades de persistência sejam utilizadas diretamente como objetos de entrada e saída da API, permitindo maior controle sobre os dados expostos.

### Exception Handler

O tratamento de exceções é centralizado para permitir respostas mais consistentes para situações de erro.

Dessa forma, erros relacionados às regras de negócio, validações ou processamento das requisições podem ser tratados de maneira padronizada.

## Principais Funcionalidades

### Usuários

A aplicação permite trabalhar com diferentes tipos de usuários, de acordo com as regras definidas pelo domínio da aplicação.

Os usuários possuem informações utilizadas para identificação e autenticação dentro do fluxo de transações.

### Carteiras

Cada usuário possui uma carteira utilizada para controlar o saldo disponível para realização das operações financeiras.

A carteira também está relacionada ao tipo de usuário, permitindo diferenciar usuários comuns e lojistas.

### Transações

As transações representam as transferências de valores entre carteiras.

Uma transação possui informações como:

- Identificação da transação;
- Carteira de origem;
- Carteira de destino;
- Valor;
- Data e hora da operação.

O valor financeiro é representado utilizando `BigDecimal`, evitando problemas de precisão comuns em operações monetárias realizadas com tipos de ponto flutuante.

## Fluxo de uma Transação

O fluxo básico de uma transferência pode ser representado da seguinte maneira:

```text
Cliente
   |
   v
Controller
   |
   v
Service
   |
   +----> Validação das regras de negócio
   |
   +----> Consulta carteira de origem
   |
   +----> Consulta carteira de destino
   |
   +----> Validação do saldo
   |
   +----> Processamento da transferência
   |
   +----> Registro da transação
   |
   v
Repository
   |
   v
Banco de Dados
```

A camada de serviço é responsável por coordenar esse fluxo e garantir que as regras necessárias sejam verificadas antes da conclusão da operação.

## Regras de Negócio

A aplicação possui regras relacionadas à realização das transferências.

Entre as principais validações estão:

- Verificação da existência da carteira de origem;
- Verificação da existência da carteira de destino;
- Validação do saldo disponível;
- Validação do valor da transação;
- Verificação do tipo de usuário envolvido na operação;
- Validação das condições necessárias para realização da transferência.

As regras de negócio são mantidas na camada de serviço para evitar que responsabilidades sejam concentradas nos controllers.

## Validações

A aplicação utiliza mecanismos de validação para garantir a integridade dos dados recebidos pela API.

Entre as validações realizadas estão:

- Campos obrigatórios;
- Valores válidos;
- Identificação dos usuários;
- Valores monetários;
- Dados necessários para realização de uma transação.

Além das validações de entrada, as regras específicas do domínio são verificadas durante o processamento da operação.

## Persistência

A aplicação utiliza MySQL como banco de dados relacional.

O acesso aos dados é realizado por meio do Spring Data JPA, permitindo trabalhar com as entidades do domínio sem a necessidade de implementar manualmente as operações básicas de persistência.

A estrutura também permite que o banco seja executado por meio do Docker, facilitando a configuração do ambiente de desenvolvimento.

## Docker

O projeto possui configuração para execução do banco de dados utilizando Docker.

Dessa forma, o ambiente necessário para execução da aplicação pode ser configurado sem a necessidade de realizar manualmente a instalação e configuração do banco de dados no sistema operacional.

O Docker também facilita a reprodução do ambiente em diferentes máquinas.

## Testes

O projeto utiliza JUnit 5 e Mockito para desenvolvimento dos testes automatizados.

Os testes têm como objetivo verificar o comportamento das principais partes da aplicação e garantir que as regras implementadas continuem funcionando conforme esperado.

Entre os cenários considerados estão:

- Operações envolvendo usuários;
- Criação e consulta de carteiras;
- Realização de transações;
- Validação das regras de negócio;
- Tratamento de situações inválidas;
- Comportamentos esperados dos serviços.

## Tratamento de Exceções

As situações de erro são tratadas de forma centralizada, permitindo que a API retorne respostas consistentes para diferentes tipos de falha.

Entre os possíveis cenários estão:

- Usuário não encontrado;
- Carteira não encontrada;
- Saldo insuficiente;
- Dados inválidos;
- Transação não permitida;
- Erros relacionados às regras de negócio.

Essa abordagem evita que cada controller precise implementar individualmente o tratamento das mesmas situações.

## Como Executar

### Pré-requisitos

Para executar o projeto, é necessário possuir:

- Java instalado;
- Gradle ou utilização do Gradle Wrapper;
- Docker;
- Docker Compose, caso seja utilizado o arquivo de composição disponibilizado no projeto.

### Clonando o projeto

```bash
git clone https://github.com/Thia9ophelipe/teste-backend-jr-picpay.git
```

Depois, entre no diretório:

```bash
cd teste-backend-jr-picpay
```

### Executando o banco de dados

Caso esteja utilizando a configuração Docker do projeto:

```bash
docker compose up -d
```

### Executando a aplicação

No Windows:

```bash
gradlew bootRun
```

No Linux/macOS:

```bash
./gradlew bootRun
```

A aplicação será iniciada conforme as configurações definidas no projeto.

## Documentação da API

A API pode ser documentada utilizando ferramentas de documentação compatíveis com OpenAPI/Swagger, quando habilitadas no ambiente da aplicação.

A documentação interativa permite consultar os recursos disponíveis, seus parâmetros, modelos de dados e respostas HTTP.

Quando a interface Swagger estiver habilitada, ela poderá ser acessada pelo navegador utilizando a URL disponibilizada pela aplicação.

## Estrutura do Projeto

A organização do projeto segue a separação de responsabilidades adotada pela aplicação.

```text
src
└── main
    └── java
        └── com
            └── ...
                ├── controller
                ├── service
                ├── repository
                ├── entity
                ├── dto
                └── exception
```

A estrutura permite que cada camada tenha uma responsabilidade específica, facilitando a manutenção e evolução do código.

## Aprendizados

Durante o desenvolvimento deste projeto foram aplicados diversos conceitos relacionados ao desenvolvimento backend com Java e Spring Boot, incluindo:

- Desenvolvimento de APIs REST;
- Arquitetura em camadas;
- Injeção de dependências;
- Spring Data JPA;
- Hibernate;
- Persistência em banco de dados relacional;
- DTOs;
- Validação de dados;
- Tratamento de exceções;
- Testes unitários com JUnit e Mockito;
- Utilização de `BigDecimal` para operações financeiras;
- Containerização com Docker;
- Organização de código seguindo princípios de responsabilidade única e separação de responsabilidades.

## Considerações Finais

Este projeto foi desenvolvido como parte de um desafio técnico para consolidar conhecimentos em desenvolvimento backend utilizando Java e Spring Boot.

A implementação busca aplicar conceitos utilizados em aplicações reais, mantendo as responsabilidades separadas entre as camadas de apresentação, negócio e persistência.

Além de atender às funcionalidades propostas pelo desafio, o projeto serviu como oportunidade para praticar conceitos importantes relacionados a APIs REST, persistência de dados, regras de negócio, testes automatizados e organização de aplicações backend.

A estrutura adotada também permite que a aplicação continue evoluindo, possibilitando a inclusão de novas funcionalidades e melhorias sem comprometer a organização das responsabilidades existentes.
