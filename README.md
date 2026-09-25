# API de Transações — Desafio Backend PicPay

## Visão Geral

Esta aplicação consiste em uma API REST desenvolvida em Java 21 utilizando Spring Boot, criada como solução para um desafio de desenvolvimento backend.

O projeto implementa uma versão simplificada de uma plataforma de pagamentos, permitindo trabalhar com usuários, carteiras e transferências financeiras entre usuários.

A aplicação foi desenvolvida com foco em organização, separação de responsabilidades, boas práticas de desenvolvimento backend e utilização dos principais recursos oferecidos pelo ecossistema Spring.

A persistência dos dados é realizada utilizando MySQL, com acesso por meio do Spring Data JPA e Hibernate.

## Objetivo

O principal objetivo da aplicação é disponibilizar uma estrutura backend capaz de:

- Trabalhar com usuários comuns e lojistas;
- Associar carteiras aos usuários;
- Realizar transferências entre carteiras;
- Validar as regras necessárias para uma transferência;
- Registrar as transações realizadas;
- Persistir as informações em banco de dados;
- Expor os recursos por meio de uma API REST.

A aplicação foi estruturada de forma que as regras de negócio permaneçam separadas das responsabilidades de infraestrutura e comunicação HTTP.

## Tecnologias Utilizadas

- Java 21
- Spring Boot 4.1.0
- Spring Web
- Spring Data JPA
- Hibernate
- Gradle
- MySQL
- Docker
- MapStruct
- Lombok
- Bean Validation
- JUnit 5
- Mockito
- OpenFeign

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

- Processamento de usuários e carteiras;
- Validação das regras de transferência;
- Verificação das condições necessárias para uma operação;
- Processamento das transações;
- Comunicação com os repositórios;
- Orquestração das operações da aplicação.

A separação dessas regras permite manter os controllers mais simples e concentrar a lógica da aplicação em uma camada específica.

### Repository

Responsável pelo acesso aos dados persistidos.

A aplicação utiliza Spring Data JPA para abstrair as operações de persistência e comunicação com o banco de dados.

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

### Mapper

O projeto utiliza MapStruct para realizar o mapeamento entre objetos de domínio e DTOs.

Essa abordagem reduz código repetitivo e mantém a conversão dos objetos isolada das demais responsabilidades da aplicação.

### Exception Handler

O tratamento de exceções é centralizado para permitir respostas mais consistentes para situações de erro.

Dessa forma, erros relacionados às regras de negócio, validações ou processamento das requisições podem ser tratados de maneira padronizada.

## Principais Funcionalidades

### Usuários

A aplicação trabalha com diferentes tipos de usuários, permitindo distinguir usuários comuns de lojistas.

Cada usuário possui uma carteira utilizada nas operações financeiras.

### Carteiras

As carteiras representam o saldo financeiro associado a cada usuário.

O saldo é utilizado durante o processamento das transferências, permitindo verificar se o pagador possui recursos suficientes antes da realização da operação.

### Transações

As transações representam as transferências de valores entre carteiras.

Uma transação possui informações como:

- Identificação;
- Carteira de origem;
- Carteira de destino;
- Valor;
- Data e hora da operação.

O valor financeiro é representado utilizando `BigDecimal`, evitando problemas de precisão comuns em operações monetárias realizadas com tipos de ponto flutuante.

## Exemplo de Utilização

Um exemplo básico do fluxo de utilização da API pode ser representado da seguinte maneira:

```text
1. Usuário possui uma carteira
          |
          v
2. Cliente solicita uma transferência
          |
          v
3. API valida os dados da operação
          |
          v
4. Service verifica as regras de negócio
          |
          v
5. Saldo do pagador é validado
          |
          v
6. Valor é transferido para o recebedor
          |
          v
7. Transação é registrada no banco
```

Esse fluxo mantém a regra de negócio concentrada na camada de serviço, enquanto o controller permanece responsável pela comunicação HTTP.

## Endpoint de Transferência

A operação principal da aplicação é a transferência de valores entre usuários.

### POST `/transfer`

Realiza uma transferência entre duas carteiras.

#### Exemplo de requisição

```http
POST http://localhost:8080/transfer
Content-Type: application/json
```

```json
{
  "value": 100.00,
  "payer": 4,
  "payee": 15
}
```

Nesse exemplo:

- `value` representa o valor da transferência;
- `payer` representa o usuário que está realizando o pagamento;
- `payee` representa o usuário que receberá o valor.

#### Exemplo utilizando cURL

```bash
curl --location 'http://localhost:8080/transfer' --header 'Content-Type: application/json' --data '{
    "value": 100.00,
    "payer": 4,
    "payee": 15
}'
```

#### Exemplo de fluxo

Considerando inicialmente:

```text
Usuário 4
Saldo: R$ 500,00

Usuário 15
Saldo: R$ 200,00
```

Após uma transferência de R$ 100,00:

```text
Usuário 4
Saldo: R$ 400,00

Usuário 15
Saldo: R$ 300,00
```

A operação também gera o registro correspondente da transferência.

> Os identificadores utilizados no exemplo são ilustrativos e devem ser substituídos pelos IDs existentes no banco de dados da aplicação.

## Regras de Negócio

A aplicação possui regras relacionadas à realização das transferências.

Entre as principais validações estão:

- O valor da transferência deve ser válido;
- O pagador deve possuir uma carteira;
- O recebedor deve possuir uma carteira;
- O pagador deve possuir saldo suficiente;
- A transferência deve respeitar o tipo de usuário envolvido;
- A operação deve ser processada de acordo com as regras definidas para o domínio.

### Exemplo — Saldo insuficiente

Supondo:

```text
Saldo do pagador: R$ 50,00
Valor da transferência: R$ 100,00
```

A operação não deve ser concluída, pois o pagador não possui saldo suficiente.

Um cenário desse tipo deve resultar em uma resposta de erro, sem que o saldo das carteiras seja alterado.

### Exemplo — Carteira inexistente

Caso seja informado um usuário que não possua uma carteira válida para a operação, a transferência não deve ser processada.

```json
{
  "value": 100.00,
  "payer": 4,
  "payee": 9999
}
```

Nesse caso, a aplicação deve interromper o processamento e retornar o erro correspondente.

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

As principais informações persistidas estão relacionadas a:

- Usuários;
- Tipos de carteira;
- Carteiras;
- Transferências.

## Docker

O projeto possui configuração para execução do banco de dados utilizando Docker.

Dessa forma, o ambiente necessário para execução da aplicação pode ser configurado sem a necessidade de realizar manualmente a instalação e configuração do banco de dados no sistema operacional.

### Exemplo

Com o Docker configurado, o banco pode ser iniciado utilizando:

```bash
docker compose up -d
```

Para verificar os containers em execução:

```bash
docker ps
```

Para interromper os containers:

```bash
docker compose down
```

## Testes

O projeto utiliza JUnit 5 e recursos do ecossistema Spring para desenvolvimento dos testes automatizados.

Os testes têm como objetivo verificar o comportamento das principais partes da aplicação e garantir que as regras implementadas continuem funcionando conforme esperado.

Entre os cenários considerados estão:

- Operações envolvendo usuários;
- Criação e consulta de carteiras;
- Realização de transações;
- Validação das regras de negócio;
- Tratamento de situações inválidas;
- Comportamentos esperados dos serviços.

### Executando os testes

Utilizando o Gradle Wrapper:

No Windows:

```bash
gradlew test
```

No Linux/macOS:

```bash
./gradlew test
```

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

### Exemplo de erro

Uma tentativa de transferência com dados inválidos pode resultar em uma resposta HTTP de erro contendo informações sobre o problema encontrado.

```json
{
  "status": 400,
  "message": "Dados da requisição inválidos."
}
```

A estrutura exata da resposta depende da exceção gerada durante o processamento.

## Como Executar

### Pré-requisitos

Para executar o projeto, é necessário possuir:

- Java 21;
- Docker;
- Docker Compose;
- Git.

O projeto utiliza Gradle Wrapper, portanto não é necessário instalar o Gradle separadamente.

### Clonando o projeto

```bash
git clone https://github.com/Thia9ophelipe/teste-backend-jr-picpay.git
```

Depois, entre no diretório:

```bash
cd teste-backend-jr-picpay
```

### Executando o banco de dados

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

Após a inicialização, a API estará disponível no endereço configurado pela aplicação, normalmente:

```text
http://localhost:8080
```

## Documentação da API

A documentação da API pode ser disponibilizada por meio de ferramentas compatíveis com OpenAPI/Swagger, quando habilitadas no ambiente da aplicação.

A documentação interativa permite consultar os recursos disponíveis, seus parâmetros, modelos de dados e respostas HTTP.

Além da documentação interativa, os exemplos deste README podem ser utilizados para testar a API utilizando ferramentas como cURL, Postman ou Insomnia.

## Exemplo de Teste Manual

Depois de iniciar a aplicação e o banco de dados, um fluxo simples de teste pode ser:

### 1. Verificar os dados disponíveis

Utilize os usuários/carteiras existentes no banco de dados ou os dados carregados pela aplicação.

### 2. Realizar uma transferência

```bash
curl --location 'http://localhost:8080/transfer' --header 'Content-Type: application/json' --data '{
    "value": 50.00,
    "payer": 1,
    "payee": 2
}'
```

### 3. Verificar o resultado

A operação deve validar as regras de negócio e, caso esteja tudo correto:

- Debitar o valor da carteira do pagador;
- Creditar o valor na carteira do recebedor;
- Registrar a transação;
- Retornar a resposta correspondente à operação.

## Estrutura do Projeto

A organização do projeto segue a separação de responsabilidades adotada pela aplicação.

```text
src
└── main
    └── java
        └── com
            └── example
                └── picpaysimplificado
                    ├── controller
                    ├── service
                    ├── repository
                    ├── entity
                    ├── dto
                    ├── mapper
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
- MapStruct;
- Validação de dados;
- Tratamento de exceções;
- Testes automatizados;
- Utilização de `BigDecimal` para operações financeiras;
- Containerização com Docker;
- Integração com serviços externos;
- Organização de código seguindo princípios de responsabilidade única e separação de responsabilidades.

## Considerações Finais

Este projeto foi desenvolvido como parte de um desafio técnico para consolidar conhecimentos em desenvolvimento backend utilizando Java e Spring Boot.

A implementação busca aplicar conceitos utilizados em aplicações reais, mantendo as responsabilidades separadas entre as camadas de apresentação, negócio e persistência.

Além de atender às funcionalidades propostas pelo desafio, o projeto serviu como oportunidade para praticar conceitos importantes relacionados a APIs REST, persistência de dados, regras de negócio, testes automatizados, integração com serviços externos e organização de aplicações backend.

A estrutura adotada também permite que a aplicação continue evoluindo, possibilitando a inclusão de novas funcionalidades e melhorias sem comprometer a organização das responsabilidades existentes.
