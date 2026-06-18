# 📅 ScheduleEase API

API REST para gerenciamento de agendamentos desenvolvida com Spring Boot. O sistema permite cadastrar clientes, profissionais e serviços, além de criar agendamentos com validação automática de conflito de horário.

## 🎯 Objetivo

Projeto pessoal de aprendizado com foco em boas práticas de desenvolvimento de APIs REST, regras de negócio na camada de serviço e arquitetura em camadas.

---

## 🚀 Tecnologias

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA** + Hibernate
- **PostgreSQL**
- **MapStruct** — mapeamento entre entidades e DTOs
- **Lombok**
- **Bean Validation** — validação de entrada
- **Maven**

---

## 🏗️ Arquitetura

O projeto segue a arquitetura em camadas com separação clara de responsabilidades:

```
src/
└── main/java/simao/project/agendamento/
    ├── controllers/     # Recebe e responde requisições HTTP
    ├── services/        # Lógica de negócio e regras de domínio
    ├── repositories/    # Acesso ao banco de dados
    ├── entitys/         # Entidades JPA
    ├── dtos/            # Objetos de transferência de dados
    │   ├── client/
    │   ├── professional/
    │   ├── scheduling/
    │   └── serviceItem/
    ├── mappers/         # Conversão entre entidades e DTOs (MapStruct)
    └── exceptions/      # Exceções customizadas e handler global
```

---

## 📌 Endpoints

### Clientes `/api/clients`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/clients` | Lista todos os clientes |
| `GET` | `/api/clients/{id}` | Busca cliente por ID |
| `POST` | `/api/clients` | Cria um novo cliente |
| `PUT` | `/api/clients/{id}` | Atualiza um cliente |
| `DELETE` | `/api/clients/{id}` | Remove um cliente |

### Profissionais `/api/professionals`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/professionals` | Lista todos os profissionais |
| `GET` | `/api/professionals/{id}` | Busca profissional por ID |
| `POST` | `/api/professionals` | Cria um novo profissional |
| `PUT` | `/api/professionals/{id}` | Atualiza um profissional |
| `DELETE` | `/api/professionals/{id}` | Remove um profissional |

### Serviços `/api/services`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/services` | Lista todos os serviços |
| `GET` | `/api/services/{id}` | Busca serviço por ID |
| `POST` | `/api/services` | Cria um novo serviço |
| `PUT` | `/api/services/{id}` | Atualiza um serviço |
| `DELETE` | `/api/services/{id}` | Remove um serviço |

### Agendamentos `/api/appointments`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/appointments` | Lista todos os agendamentos |
| `GET` | `/api/appointments/{id}` | Busca agendamento por ID |
| `POST` | `/api/appointments` | Cria um novo agendamento |
| `PUT` | `/api/appointments/{id}` | Atualiza status do agendamento |
| `DELETE` | `/api/appointments/{id}` | Remove um agendamento |

---

## ⚙️ Como rodar

**Pré-requisitos:** Java 17+, Maven e PostgreSQL

Configure suas credenciais no `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/scheduleease
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

```bash
# Clonar o repositório
git clone https://github.com/seu-usuario/scheduleease-api.git
cd scheduleease-api

# Rodar a aplicação
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

---

## 📦 Exemplos de requisição

**Criar um cliente:**
```http
POST /api/clients
Content-Type: application/json

{
  "name": "João Silva",
  "email": "joao@email.com",
  "phone": "(31) 99999-9999"
}
```

**Criar um agendamento:**
```http
POST /api/appointments
Content-Type: application/json

{
  "startDateTime": "20/06/2026 14:00",
  "clientId": 1,
  "professionalId": 1,
  "serviceItemId": 1,
  "observation": "Primeira consulta"
}
```

**Atualizar status do agendamento:**
```http
PUT /api/appointments/1
Content-Type: application/json

{
  "status": "COMPLETED"
}
```

---

## 🔒 Regras de negócio

- **Conflito de horário** — não é possível criar um agendamento se o profissional já tiver outro no mesmo intervalo de tempo
- **Cancelamento com antecedência** — agendamentos só podem ser cancelados com no mínimo 1 hora de antecedência
- **Cálculo automático do fim** — o `endDateTime` é calculado automaticamente com base na duração do serviço, sem necessidade de informar manualmente
- **Status do agendamento** — os status possíveis são `SCHEDULED`, `COMPLETED`, `CANCELED` e `NO_SHOW`

---

## 🧪 Testes

```bash
./mvnw test
```

---

## 💡 O que aprendi

- Arquitetura em camadas com separação clara de responsabilidades
- Validação de regras de negócio na camada de serviço
- Detecção de conflito de horário com JPQL
- Uso de MapStruct para mapeamento entre entidades e DTOs
- DTOs separados por operação (request, response e update)
- Tratamento centralizado de exceções com `@RestControllerAdvice`
- Boas práticas com `@Transactional` em operações de escrita

---

## 📈 Próximos passos

- [ ] Autenticação com Spring Security + JWT
- [ ] Testes unitários com JUnit 5 e Mockito
- [ ] Paginação nos endpoints de listagem
- [ ] Documentação com Swagger/OpenAPI
