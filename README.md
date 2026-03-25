# 🧠 API de Controle de Hábitos

API REST desenvolvida com **Java + Spring Boot** para gerenciamento de hábitos do dia a dia.

Este projeto foi criado com foco em aprendizado de arquitetura backend, boas práticas e construção de APIs RESTful.

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
- ✅ Validação de regras de negócio
- ✅ Definição automática de valores padrão (`ativo = true`)

---

## 🧱 Arquitetura

O projeto segue a arquitetura em camadas:

```

controller → service → repository → model

```

- **Controller** → recebe as requisições HTTP  
- **Service** → contém regras de negócio  
- **Repository** → comunicação com o banco de dados  
- **Model (Entity)** → representação da tabela no banco  

---

## 📦 Estrutura do projeto

```

com.felipe.habito
│
├── controller
├── service
├── repository
├── model

````

---

## ⚙️ Configuração do banco de dados

Crie o banco no MySQL:

```sql
CREATE DATABASE habito;
````

Configure no `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/habito?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
```

---

## 🔌 Endpoints

### 📍 Criar hábito

**POST** `/habitos`

#### Body:

```json
{
  "nome": "Estudar Java",
  "descricao": "Estudar 1 hora por dia",
  "frequencia": "DIARIO"
}
```

#### Resposta:

```json
{
  "id": 1,
  "nome": "Estudar Java",
  "descricao": "Estudar 1 hora por dia",
  "frequencia": "DIARIO",
  "ativo": true
}
```

---

### 📍 Listar hábitos

**GET** `/habitos`

#### Resposta:

```json
[
  {
    "id": 1,
    "nome": "Estudar Java",
    "descricao": "Estudar 1 hora por dia",
    "frequencia": "DIARIO",
    "ativo": true
  }
]
```

---

## 🧠 Regras de negócio

* Nome é obrigatório
* Frequência deve ser:

  * `DIARIO`
  * `SEMANAL`
* Campo `ativo` é automaticamente definido como `true` ao persistir (`@PrePersist`)

---

## 🧪 Testes

A API pode ser testada utilizando ferramentas como:

* Insomnia
* Postman

---

## 📈 Melhorias futuras

* [ ] Uso de DTO (`record`)
* [ ] Enum para frequência
* [ ] Tratamento global de exceções
* [ ] Endpoint de atualização e exclusão
* [ ] Integração com autenticação

---

## 👨‍💻 Autor

Desenvolvido por **Felipe Shimizu**

* Portfólio: [https://www.devfelipeshimizu.me/](https://www.devfelipeshimizu.me/)
* LinkedIn: [https://www.linkedin.com/in/felipesshimizu/](https://www.linkedin.com/in/felipesshimizu/)

---

## 📄 Licença

Este projeto é de uso educacional.

