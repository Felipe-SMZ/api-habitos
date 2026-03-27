# 🧠 API de Controle de Hábitos

API REST desenvolvida com **Java + Spring Boot** para gerenciamento de hábitos do dia a dia.

Este projeto foi criado com foco em aprendizado de **arquitetura backend**, boas práticas e construção de APIs RESTful, seguindo padrões utilizados no mercado.

---

## 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Validation
- MySQL
- Maven

---

## 📌 Funcionalidades

- ✅ Criar um hábito
- ✅ Listar todos os hábitos
- ✅ Buscar hábito por ID
- ✅ Atualizar hábito
- ✅ Deletar hábito
- ✅ Validação de entrada com `@Valid` e Bean Validation
- ✅ Definição automática de valores padrão (`ativo = true`)
- ✅ Conversão entre entidade e DTO via Mappers
- ✅ Tratamento global de exceções com `@RestControllerAdvice`
- ✅ Cadastro e gerenciamento de usuários
- ✅ Relacionamento `1:N` entre `Usuario` e `Habito`

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
│   ├── request
│   └── response
├── mapper
├── exception
└── enums
```

---

## 🧠 Decisões de arquitetura

### 🔹 Uso de DTO (`record`)

Os DTOs foram utilizados para separar:

- Dados de entrada (**Request**)
- Dados de saída (**Response**)

👉 Isso evita expor diretamente a entidade e aumenta a segurança da API. A senha do usuário, por exemplo, nunca é exposta no response.

---

### 🔹 Uso de `record`

O `record` foi escolhido por:

- Reduzir código boilerplate
- Ser imutável
- Ser ideal para transporte de dados (DTO)

---

### 🔹 Validação de entrada com `@Valid`

As validações de formato e obrigatoriedade ficam no DTO, mantendo o Service focado apenas em regras de negócio.

```java
@NotBlank(message = "Nome é obrigatório")
@Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
String nome,

@NotNull(message = "Frequência é obrigatória")
Frequencia frequencia
```

👉 Separa responsabilidades: validação de entrada no DTO, regras de negócio no Service.

---

### 🔹 Uso de Mappers

Classes responsáveis pela conversão entre entidade e DTOs de request/response.

```
RequestDTO  → Entity   (toEntity)
Entity      → ResponseDTO  (toDTO)
```

👉 Elimina repetição no Controller e centraliza a conversão em um único lugar.

---

### 🔹 Tratamento global de exceções

Uso de `@RestControllerAdvice` para interceptar exceções de toda a aplicação em um único lugar, retornando respostas padronizadas e legíveis.

**Exceções tratadas:**

| Exceção | Status | Descrição |
|---|---|---|
| `MethodArgumentNotValidException` | 400 | Falha na validação do DTO (`@Valid`) |
| `ResponseStatusException` | dinâmico | Erros de negócio (ex: recurso não encontrado) |

**Formato padrão de erro:**

```json
{
  "timestamp": "2026-03-27T20:20:20.851Z",
  "status": 409,
  "error": "Conflict",
  "message": ["E-mail já cadastrado"],
  "path": "/usuarios/1"
}
```

👉 Garante respostas consistentes e elimina a necessidade de tratar erros em cada Controller individualmente.

---

### 🔹 Relacionamento `1:N` entre Usuario e Habito

Um usuário pode ter muitos hábitos. O relacionamento é mapeado com `@OneToMany` em `Usuario` e `@ManyToOne` em `Habito`, com `FetchType.LAZY` para evitar consultas desnecessárias ao banco.

---

### 🔹 Validação de email único

O `UsuarioService` valida se o email já está cadastrado antes de salvar ou atualizar, diferenciando se o email pertence ao próprio usuário ou a outro — evitando falsos conflitos no `PUT`.

---

### 🔹 Uso de `enum` para frequência

```java
DIARIO, SEMANAL
```

👉 Evita valores inválidos e remove validações manuais.

---

### 🔹 Uso de `@PrePersist` e `@PreUpdate`

- `@PrePersist` → define `ativo = true` em `Habito` e `createdAt` em `Usuario` automaticamente
- `@PreUpdate` → atualiza `updatedAt` em `Usuario` a cada modificação

👉 Evita inconsistência de dados sem depender do client.

---

## ⚙️ Configuração do banco de dados

### 1. Criar o banco no MySQL

```sql
CREATE DATABASE habito;
```

### 2. Configurar a aplicação

No arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/habito?serverTimezone=UTC
spring.profiles.active=local
```

### 3. Configurar credenciais (não versionado)

Crie o arquivo `application-local.properties`:

```properties
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

> ⚠️ Esse arquivo não é versionado por segurança.

---

## 🔌 Endpoints

### 👤 Usuários

#### 📍 Criar usuário
**POST** `/usuarios`

```json
{
  "nome": "Felipe Shimizu",
  "email": "felipe@email.com",
  "senha": "senha123"
}
```

#### 📍 Atualizar usuário
**PUT** `/usuarios/{id}`

```json
{
  "nome": "Felipe Shimizu Novo",
  "email": "felipe@email.com",
  "senha": "senha123"
}
```

#### 📍 Deletar usuário
**DELETE** `/usuarios/{id}`

---

### 📋 Hábitos

#### 📍 Criar hábito
**POST** `/habitos`

```json
{
  "nome": "Estudar Java",
  "descricao": "Estudar 1 hora por dia",
  "frequencia": "DIARIO"
}
```

#### 📍 Listar hábitos
**GET** `/habitos`

#### 📍 Buscar por ID
**GET** `/habitos/{id}`

#### 📍 Atualizar hábito
**PUT** `/habitos/{id}`

```json
{
  "nome": "Estudar Java",
  "descricao": "Estudar 2 horas por dia",
  "frequencia": "SEMANAL"
}
```

#### 📍 Deletar hábito
**DELETE** `/habitos/{id}`

---

## 🧪 Testes

A API pode ser testada utilizando:

- Insomnia
- Postman

---

## 📈 Melhorias futuras

- [ ] Autenticação com Spring Security
- [ ] Autorização via JWT
- [ ] Roles e controle de acesso por perfil
- [ ] Mapeamento automático (MapStruct)

---

## 👨‍💻 Autor

Desenvolvido por **Felipe Shimizu**

- 🌐 Portfólio: [https://www.devfelipeshimizu.me/](https://www.devfelipeshimizu.me/)
- 💼 LinkedIn: [https://www.linkedin.com/in/felipesshimizu/](https://www.linkedin.com/in/felipesshimizu/)

---

## 📄 Licença

Este projeto é de uso educacional.