# E-commerce Backend System

Este repositório contém o código-fonte de um sistema backend para um e-commerce. O projeto foi desenvolvido utilizando Java e Spring Framework, com suporte para microsserviços, mensagens assíncronas e persistência de dados em MySQL.

---

## Tecnologias Utilizadas

- **Java 17**  
- **Spring Framework**  
  - Spring Boot  
  - Spring Data JPA  
  - Spring Cloud OpenFeign  
  - Spring Cloud Netflix Eureka
  - ...  
- **MySQL**  
- **Apache Kafka**  
- **Docker e Docker Compose**  

---

## Arquitetura do Sistema

O sistema utiliza uma arquitetura baseada em microsserviços, com as seguintes características principais:

1. **Microsserviços**:  
   Cada funcionalidade principal (pedidos, produtos, clientes) está separada em serviços independentes.

2. **Comunicação assíncrona**:  
   Mensagens entre microsserviços são trocadas via **Apache Kafka**.

3. **Comunicação síncrona**:  
   Utilizamos **OpenFeign** para chamadas HTTP entre os microsserviços.

4. **Registro e Descoberta**:  
   Microsserviços se registram e são descobertos dinamicamente via **Eureka Server**.

5. **Persistência de Dados**:  
   Banco de dados **MySQL**, acessado através do Spring Data JPA.

6. **Containerização**:  
   Todo o ambiente é orquestrado via **Docker Compose**.
