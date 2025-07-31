# Desafio DIO: Microsserviços com Spring Cloud

![Badge de Versão](https://img.shields.io/badge/vers%C3%A3o-1.0.0-blue)
![Badge de Status](https://img.shields.io/badge/status-em%20desenvolvimento-green)
[![Licença MIT](https://img.shields.io/badge/Licen%C3%A7a-MIT-green)](LICENSE.md)

## 📝 Descrição do Projeto

Este projeto é parte do desafio proposto pela [Digital Innovation One (DIO)](https://www.dio.me/) para a construção de uma arquitetura de microsserviços utilizando o ecossistema Spring Cloud. O principal objetivo é desenvolver um sistema distribuído robusto, demonstrando a implementação de serviços independentes, comunicação eficiente entre eles, descoberta de serviços, balanceamento de carga e gerenciamento centralizado de configurações.

Este repositório contém a estrutura base de um gateway de API, serviços de produtos e pedidos, e as configurações de infraestrutura necessárias para orquestrar esses componentes.

## 📚 Sumário

*   [✨ Funcionalidades](#-funcionalidades)
*   [🚀 Tecnologias Utilizadas](#-tecnologias-utilizadas)
*   [🛠️ Pré-requisitos](#-pr%C3%A9-requisitos)
*   [⚙️ Como Executar o Projeto](#-como-executar-o-projeto)
*   [�� Uso da Aplicação](#-uso-da-aplica%C3%A7%C3%A3o)
*   [🤝 Contribuição](#-contribui%C3%A7%C3%A3o)
*   [�� Licença](#-licen%C3%A7a)
*   [🧑‍�� Autor](#-autor)

## ✨ Funcionalidades

*   **Registro e Descoberta de Serviço (Service Discovery):** Implementação do Eureka Server para permitir que os microsserviços se registrem e se encontrem dinamicamente.
*   **Gateway API:** Configuração do Spring Cloud Gateway para roteamento de requisições, balanceamento de carga e aplicação de políticas de segurança para os microsserviços.
*   **Configuração Centralizada:** Uso do Spring Cloud Config Server para gerenciar as configurações de todos os microsserviços de forma centralizada e dinâmica.
*   **Comunicação entre Serviços:** Exemplo de comunicação síncrona entre microsserviços.
*   **Segurança (Exemplo Básico):** Implementação de um filtro de segurança simples via token Bearer no API Gateway para proteger os endpoints dos microsserviços.

## 🚀 Tecnologias Utilizadas

*   **Java 17**
*   **Spring Boot 3.2.5**
*   **Spring Cloud 2023.0.1**
*   **Spring Cloud Netflix Eureka Server/Client**
*   **Spring Cloud Gateway**
*   **Spring Cloud Config**
*   **Spring Security (WebFlux)**
*   **Lombok**
*   **Maven**
*   **Docker & Docker Compose** (para ambiente de desenvolvimento e infraestrutura)

## 🛠️ Pré-requisitos

Antes de executar o projeto, certifique-se de que você possui os seguintes softwares instalados em sua máquina:

*   [**Java Development Kit (JDK) 17 ou superior**](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
*   [**Apache Maven 3.6.3 ou superior**](https://maven.apache.org/download.cgi)
*   [**Docker Desktop (incluindo Docker Compose)**](https://www.docker.com/get-started)

## ⚙️ Como Executar o Projeto

Siga os passos abaixo para configurar e executar todos os microsserviços localmente:

1.  **Clone o Repositório:**
    ```bash
    git clone https://github.com/Gui-Kol/desafio-dio-microsservicos-spring-cloud.git
    ```

2.  **Navegue até o Diretório Raiz do Projeto:**
    ```bash
    cd desafio-dio-microsservicos-spring-cloud
    ```

3.  **Inicie os Contêineres Docker de Infraestrutura (Eureka Server e Config Server):**
    Este projeto utiliza Docker Compose para levantar o Eureka Server e o Config Server, que são fundamentais para o funcionamento dos microsserviços.

    ```bash
    docker-compose up -d
    ```
    Isso iniciará os contêineres em background. Aguarde alguns instantes para que estejam totalmente operacionais.

4.  **Compile e Execute os Microsserviços (em terminais separados):**
    Para cada microsserviço (api-gateway, product-service, order-service), você precisará navegar até seu diretório e executá-lo. Abra um novo terminal para cada serviço.

    *   **Config Server (já iniciado via Docker Compose)**: Verifique se está rodando.
    *   **Eureka Server (já iniciado via Docker Compose)**: Verifique se está rodando.

    **Para `product-service`:**
    ```bash
    cd product-service
    mvn clean install
    mvn spring-boot:run
    ```

    **Para `order-service`:**
    ```bash
    cd ../order-service # Volte para a raiz e navegue para order-service
    mvn clean install
    mvn spring-boot:run
    ```

    **Para `api-gateway`:**
    ```bash
    cd ../api-gateway # Volte para a raiz e navegue para api-gateway
    mvn clean install
    mvn spring-boot:run
    ```

    Certifique-se de que todos os serviços foram iniciados com sucesso e se registraram no Eureka.

## 💡 Uso da Aplicação

Após iniciar todos os serviços, o sistema estará disponível nos seguintes endpoints principais:

*   **Eureka Dashboard:** `http://localhost:8761` (Você verá todos os microsserviços registrados aqui).
*   **API Gateway:** `http://localhost:8700` (Este é o ponto de entrada para os microsserviços).

### Testando os Endpoints (via API Gateway)

Para acessar os endpoints dos microsserviços através do API Gateway, você precisará incluir um cabeçalho de autenticação.

**Token de Autenticação (FIXO PARA DESENVOLVIMENTO):**
`Authorization: Bearer secret-token`

**Exemplos de Requisições:**

*   **Acessar Product Service (via Gateway):**
    ```
    GET http://localhost:8700/api/products
    Header: Authorization: Bearer secret-token
    ```

*   **Acessar Order Service (via Gateway):**
    ```
    GET http://localhost:8700/api/orders/simulate
    Header: Authorization: Bearer secret-token  ```



*   **Guilherme Kolndorfer** - [GitHub](https://github.com/Gui-Kol) | [LinkedIn](https://www.linkedin.com/in/guilherme-kolndorfer-b6836122b/)

---
