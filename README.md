# FitClub API - Gestão de Treinos com Segurança JWT

API RESTful desenvolvida em **Java 17** e **Spring Boot** para gerenciamento de alunos, instrutores e planos de treino em academias. O projeto foca na segurança e escalabilidade, utilizando autenticação via **Token JWT** e banco de dados NoSQL (**MongoDB**).

---

## Principais Funcionalidades

* **Autenticação Segura:** Registro e login de usuários com geração de Token JWT.
* **Criptografia de Senhas:** Utilização de BCrypt para proteger dados sensíveis.
* **Gestão de Treinos (Módulo Wesley):** Criação e listagem de treinos personalizados para alunos.
* **Arquitetura em Camadas:** Organização do código com Controllers, Services, DTOs e Repositories.

---

## Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Framework:** Spring Boot 3.x
* **Segurança:** Spring Security & JWT
* **Banco de Dados:** MongoDB (NoSQL)
* **Ferramentas:** Lombok, Gradle

---

## Como Rodar e Testar o Projeto

### Pré-requisitos
* Java 17 instalado.
* MongoDB rodando localmente na porta `27017` (ou configure no `application.properties`).

### Passos para rodar
1.  Clone o repositório.
2.  Importe o projeto em sua IDE (IntelliJ ou Eclipse).
3.  Execute a classe `FitclubApplication`. A API estará disponível em `http://localhost:8080`.

### Como Testar (Fluxo de Autenticação)
Para testar os endpoints, você pode utilizar o arquivo de requisições `.http` incluído na pasta `/docs`. O fluxo é:

1.  **POST `/auth/register`:** Crie o seu usuário (USER ou ADMIN).
2.  **POST `/auth/login`:** Faça o login para obter o seu **Token JWT**.
3.  **POST `/treinos`:** Copie o token e use-o no cabeçalho `Authorization: Bearer <TOKEN>` para criar o treino.
4.  **GET `/treinos`:** Liste os treinos, também usando o token.

---

## Sobre o Projeto

Este projeto foi desenvolvido como parte do curso de ADS na **PUC Minas**. A integração de treinos com a segurança JWT foi realizada por **Wesley H. Silva**, demonstrando capacidade de colaboração técnica e integração de módulos de segurança.
**Desenvolvido por Wesley Henrique** *Unindo tecnologia e foco no cliente para entregar resultados reais.*
[LinkedIn](linkedin.com/in/wesley-tech)
