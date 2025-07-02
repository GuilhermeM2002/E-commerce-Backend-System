# E-commerce Backend System

This repository contains the source code for an e-commerce backend system. The project was developed using Java and the Spring Framework, with support for microservices, asynchronous messaging, and data persistence in MySQL.

---

## Technologies Used

- **Java 17**  
- **Spring Framework**  
  - Spring Boot  
  - Spring Data JPA  
  - Spring Cloud OpenFeign  
  - Spring Cloud Netflix Eureka  
  - ...  
- **MySQL**  
- **Apache Kafka**  
- **Docker and Docker Compose**  

---

## System Architecture

The system follows a microservices-based architecture with the following key features:

1. **Microservices**:  
   Each main functionality (orders, products, customers) is separated into independent services.

2. **Asynchronous Communication**:  
   Messages between microservices are exchanged via **Apache Kafka**.

3. **Synchronous Communication**:  
   **OpenFeign** is used for HTTP calls between microservices.

4. **Service Discovery and Registration**:  
   Microservices are dynamically registered and discovered via **Eureka Server**.

5. **Data Persistence**:  
   **MySQL** database accessed through Spring Data JPA.

6. **Containerization**:  
   The entire environment is orchestrated using **Docker Compose**.

