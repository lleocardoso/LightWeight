# 🏋️‍♂️ LightWeight API — Sistema de Gestão de Treinos e Hipertrofia

O **LightWeight API** é uma API REST desenvolvida com **Java 21** e **Spring Boot 3**, voltada para o gerenciamento de treinos de musculação e cálculo de volume de treinamento.

O projeto foi construído com foco na aplicação de boas práticas de desenvolvimento back-end, incluindo arquitetura em camadas, separação de responsabilidades, validação de dados, versionamento de banco de dados e tratamento centralizado de exceções.

---

## ✨ Funcionalidades

* Cadastro de usuários
* Criptografia de senhas com BCrypt
* Criação e gerenciamento de treinos
* Controle de estado do treino (`INATIVO`, `ATIVO`, `CONCLUIDO`)
* Cadastro de exercícios vinculados aos treinos
* Cálculo automático de volume de treinamento
* Persistência com PostgreSQL
* Versionamento de banco de dados com Flyway
* Tratamento global de exceções
* Validação de dados com Jakarta Validation

---

## 🏛️ Arquitetura do Sistema

A aplicação segue o padrão de **Arquitetura em Camadas (Layered Architecture)**, promovendo baixo acoplamento, alta coesão e facilidade de manutenção.

### Fluxo da Aplicação

```text
Cliente HTTP
      │
      ▼
 Controllers
      │
      ▼
   Services
      │
      ▼
 Repositories
      │
      ▼
 PostgreSQL
```

### Componentes

| Camada | Responsabilidade |
|---------|---------|
| **Controllers** | Expõem os endpoints REST, recebem requisições HTTP e retornam respostas apropriadas. |
| **DTOs** | Realizam a transferência de dados entre cliente e servidor, desacoplando as entidades do domínio das entradas e saídas da API. |
| **Services** | Implementam as regras de negócio, validações de domínio e operações relacionadas aos treinos e exercícios. |
| **Repositories** | Responsáveis pelo acesso aos dados utilizando Spring Data JPA. |
| **Entities** | Representam as entidades do domínio e o mapeamento das tabelas do banco de dados. |
| **Enums** | Definem conjuntos de valores constantes utilizados pelas regras de negócio, como os estados do treino. |
---

## 📊 Modelo de Domínio

```text
Usuario (1) ────── (N) Treino
Treino  (1) ────── (N) Exercicio
```

---

## 🧠 Regras de Negócio

### Unicidade de Treino Ativo

Um usuário pode possuir diversos treinos cadastrados, porém apenas um treino pode permanecer com o estado `ATIVO` simultaneamente.

### Cálculo de Volume de Treino

O volume de cada exercício é calculado utilizando a fórmula:

```text
Volume = Carga × Séries × Repetições
```

O volume total do treino corresponde à soma dos volumes de todos os exercícios associados à ficha.

### Ciclo de Vida do Treino

```text
INATIVO → ATIVO → CONCLUIDO
```

---

## 🗄️ Banco de Dados e Persistência

A persistência utiliza PostgreSQL com controle de evolução do esquema realizado através do Flyway.

### Principais Decisões Técnicas

* Utilização de UUID como chave primária.
* Controle de migrations com Flyway.
* Uso de FetchType.LAZY para otimização de consultas.
* Uso de CascadeType.ALL e orphanRemoval para gerenciamento automático de entidades relacionadas.
* Prevenção de recursão infinita em relacionamentos bidirecionais utilizando exclusões apropriadas em métodos gerados pelo Lombok.

---

## 🛠️ Tecnologias e Dependências

| Camada         | Tecnologia                  |
| -------------- | --------------------------- |
| Linguagem      | Java 21                     |
| Framework Core | Spring Boot 3.x             |
| API REST       | Spring Web                  |
| Segurança      | Spring Security + BCrypt    |
| Persistência   | Spring Data JPA (Hibernate) |
| Banco de Dados | PostgreSQL                  |
| Migrations     | Flyway                      |
| Validação      | Jakarta Validation          |
| Produtividade  | Lombok                      |

---

## 🗂️ Estrutura do Projeto

```text
src/main/java/com/LightWeight
├── config
│   └── SecurityConfig.java
├── controller
│   ├── UsuarioController.java
│   ├── TreinoController.java
│   └── ExercicioController.java
├── dto
│   ├── request
│   └── response
├── entity
│   ├── Usuario.java
│   ├── Treino.java
│   └── Exercicio.java
├── enums
│   └── TreinoEstado.java
├── exception
│   ├── GlobalExceptionHandler.java
│   └── custom exceptions
├── repository
│   ├── UsuarioRepository.java
│   ├── TreinoRepository.java
│   └── ExercicioRepository.java
└── service
    ├── UsuarioService.java
    ├── TreinoService.java
    └── ExercicioService.java
```

---

## 🔌 Endpoints Principais

**Base URL:** `http://localhost:8080`

| Método | Endpoint                        | Descrição                                                                           |
| ------ | ------------------------------- | ----------------------------------------------------------------------------------- |
| POST   | `/usuarios`                     | Cria um novo usuário com senha criptografada utilizando BCrypt.                     |
| POST   | `/treinos/usuario/{id}`         | Cria uma nova ficha de treino com estado inicial `INATIVO`.                         |
| PUT    | `/treinos/{id}/ativar`          | Ativa uma ficha de treino, impedindo múltiplos treinos ativos para o mesmo usuário. |
| POST   | `/exercicios/treino/{id}`       | Adiciona um exercício a um treino existente.                                        |
| PUT    | `/treinos/{id}/calcular-volume` | Calcula e persiste o volume total do treino.                                        |

---

## 📄 Exemplos de requisições

### Criar Usuário

```json
{
  "nome": "Leonardo",
  "idade": 24,
  "email": "leo@email.com",
  "senha": "123456"
}
```
### Criar Exercício

```json
{
  "nome": "Supino Reto",
  "agrupamentoMuscular": "PEITO",
  "series": 4,
  "repeticoes": 10,
  "carga": 60.0
}
```

### Resposta do Cálculo de Volume

```json
2400.00
```

O sistema percorre todos os exercícios associados ao treino, calcula o volume individual e retorna o volume total movimentado.

---

## ❌ Exemplo de Resposta de Erro

```json
{
  "status": 400,
  "erro": "Já existe um treino ativo para este usuário."
}
```

---

## ⚙️ Como Executar o Projeto

### Pré-requisitos

* Java 21
* Maven
* PostgreSQL

### 1. Criar o Banco de Dados

Crie um banco chamado:

```sql
CREATE DATABASE lightweight;
```

### 2. Configurar Variáveis de Ambiente

```text
DB_USER=postgres
DB_PASS=sua_senha
```

### 3. Clonar o Projeto

```bash
git clone [https://github.com/lleocardoso/LightWeight.git](https://github.com/lleocardoso/LightWeight.git)
cd LightWeight
```

### 4. Executar a Aplicação

```bash
mvn spring-boot:run
```

A aplicação estará disponível em:

```text
http://localhost:8080
```

---

