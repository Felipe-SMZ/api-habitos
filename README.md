# 🧠 API de Controle de Hábitos

API REST desenvolvida com **Java + Spring Boot** para gerenciamento de hábitos do dia a dia.

Este projeto foi criado com foco em aprendizado de **arquitetura backend**, boas práticas e construção de APIs RESTful, seguindo padrões utilizados no mercado.

---

## 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Maven

---

## 📌 Funcionalidades

- ✅ Criar um hábito
- ✅ Listar todos os hábitos
- ✅ Buscar hábito por ID
- ✅ Atualizar hábito
- ✅ Deletar hábito
- ✅ Validação de regras de negócio
- ✅ Definição automática de valores padrão (`ativo = true`)

---

## 🧱 Arquitetura

O projeto segue o padrão de arquitetura em camadas:

```

controller → service → repository → model

```

### 🔹 Por que essa arquitetura?

- **Controller** → responsável apenas por lidar com HTTP (entrada/saída)
- **Service** → centraliza regras de negócio (onde a lógica realmente acontece)
- **Repository** → abstrai o acesso ao banco de dados
- **Model (Entity)** → representa a tabela no banco

👉 Essa separação melhora **organização, manutenção e escalabilidade**.

---

## 📦 Estrutura do projeto

```

com.felipe.habito
│
├── controller
├── service
├── repository
├── model
├── dto
├── enums

````

---

## 🧠 Decisões de arquitetura

### 🔹 Uso de DTO (`record`)

Os DTOs foram utilizados para separar:

- Dados de entrada (**Request**)
- Dados de saída (**Response**)

👉 Isso evita expor diretamente a entidade e aumenta a segurança da API.

---

### 🔹 Uso de `record`

O `record` foi escolhido por:

- Reduzir código boilerplate
- Ser imutável
- Ser ideal para transporte de dados (DTO)

---

### 🔹 Uso de `enum` para frequência

Substituição de `String` por `enum`:

```java
DIARIO, SEMANAL
````

👉 Benefícios:

* Evita valores inválidos
* Remove validações manuais
* Torna o código mais seguro

---

### 🔹 Uso de `@PrePersist`

```java
@PrePersist
```

Define automaticamente `ativo = true` antes de salvar.

👉 Evita inconsistência de dados sem depender do client.

---

### 🔹 Tratamento de erros

Uso de:

```java
ResponseStatusException
```

👉 Permite retornar respostas HTTP adequadas (400, 404, etc).

---

## ⚙️ Configuração do banco de dados

### 1. Criar o banco no MySQL

```sql
CREATE DATABASE habito;
```

---

### 2. Configurar a aplicação

No arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/habito?serverTimezone=UTC
spring.profiles.active=local
```

---

### 3. Configurar credenciais (não versionado)

Crie o arquivo `application-local.properties`:

```properties
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

> ⚠️ Esse arquivo não é versionado por segurança.

---

## 🔌 Endpoints

### 📍 Criar hábito

**POST** `/habitos`

```json
{
  "nome": "Estudar Java",
  "descricao": "Estudar 1 hora por dia",
  "frequencia": "DIARIO"
}
```

---

### 📍 Listar hábitos

**GET** `/habitos`

---

### 📍 Buscar por ID

**GET** `/habitos/{id}`

---

### 📍 Atualizar hábito

**PUT** `/habitos/{id}`

---

### 📍 Deletar hábito

**DELETE** `/habitos/{id}`

---

## 🧪 Testes

A API pode ser testada utilizando:

* Insomnia
* Postman

---

## 📈 Melhorias futuras

* [ ] Validação com `@Valid`
* [ ] Tratamento global de exceções (`@ControllerAdvice`)
* [ ] Mapeamento automático (MapStruct)
* [ ] Autenticação e autorização

---

## 👨‍💻 Autor

Desenvolvido por **Felipe Shimizu**

* 🌐 Portfólio: [https://www.devfelipeshimizu.me/](https://www.devfelipeshimizu.me/)
* 💼 LinkedIn: [https://www.linkedin.com/in/felipesshimizu/](https://www.linkedin.com/in/felipesshimizu/)

---

## 📄 Licença

Este projeto é de uso educacional.


