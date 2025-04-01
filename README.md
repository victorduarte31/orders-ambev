# Sendo totalmente sincero todo o conteudo abaixo foi feito utilizando chatgpt para me ajudar a estruturar o arquivo readme :)

# 🚀 Aplicação Java Spring Boot com MongoDB, Redis e RabbitMQ

Seja bem-vindo! Este projeto é uma aplicação Java baseada em **Spring Boot**, que utiliza uma arquitetura moderna incorporando as tecnologias abaixo:

- 🌿 **Spring Boot** (3.x ou superior)
- 🍃 **Spring Data MongoDB**
- 📦 **RabbitMQ** (com gestão UI)
- 🔥 **Redis** para caching
- 💻 **Java SDK 24 (LTS)** ⚠️
- 🐋 **Docker & Docker Compose** para orquestração e execução

---

## ✅ Tecnologias Utilizadas e Versões:

| Tecnologia      | Versão Recomendada | Container Docker Utilizado      |
|-----------------|--------------------|---------------------------------|
| **Java SDK**    | 21                 | eclipse-temurin:21-jdk e 21-jre |
| **MongoDB**     | latest             | mongo:latest                    |
| **RabbitMQ**    | latest             | rabbitmq:3-management (com UI)  |
| **Redis Cache** | latest             | redis:latest                    |
| **Spring Boot** | 3.x +              |                                 |

> 📌 Caso precise atualizar versões ou dependências, confira seu `pom.xml`.

---

## 🚧 Antes de começar (requisitos necessários):

- Git
- Docker & Docker Compose
    - [Docker Desktop](https://docs.docker.com/get-docker/)
- Opcional (IDE recomendado): [IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/download/)

---

## ⚡ Como Executar o projeto?

### 1. Clone este repositório

```bash
git clone https://github.com/victorduarte31/orders-ambev.git
cd orders-ambev
```

### 2. Rodando com Docker Compose (Recomendado)

Para executar todos os serviços facilmente (MongoDB, Redis, RabbitMQ e Aplicação Spring Boot):

```bash
docker-compose up --build
```

✅ Após subir completamente, a aplicação estará disponível em:  
👉 [http://localhost:8080](http://localhost:8080)

---

### 🚀 Endpoints e Interfaces Disponíveis

| Serviço               | Endpoint                         |
|-----------------------|----------------------------------|
| **Order-Service** 🎯  | [http://localhost:8080](http://localhost:8080) |
| **RabbitMQ UI** 📬    | [http://localhost:15672](http://localhost:15672) (guest/guest)|

🔑 Usuário e Senha padrão do RabbitMQ:  
**Usuário:** `guest`  
**Senha:** `guest`

### 📚 MongoDB Database:

Acesso externo (opcional): Utilize `Mongo Compass` ou qualquer cliente MongoDB:

- **host:** `localhost`
- **porta padrão:** `27017`
- **banco:** `orderdb` (conforme configurado)

---

## 🌱 Estrutura & Organização do Código-fonte
📂 src/main/java/ ├── 📂 controller # Camada REST (endpoint HTTP) ├── 📂 service # Lógica de negócios ├── 📂 dto # Data Transfer Objects ├── 📂 repository # Interfaces para MongoDB ├── 📂 entity # Entidades persistidas no MongoDB ├── 📂 producer # Comunicação com RabbitMQ ├── 📂 config # Configurações gerais (Redis, RabbitMQ...) └── 📜 Application.java # Ponto de entrada principal da aplicação


---

## 🐳 Sobre Docker (Containers e Portas Utilizadas):

| Serviço           | Container Name    | Porta Local Exposta |
|-------------------|-------------------|---------------------|
| **MongoDB**       | mongodb_order     | `27017`             |
| **Redis**         | redis_order       | `6379`              |
| **RabbitMQ**      | rabbitmq_order    | `5672 / 15672 (UI)` |
| **order-service** | order-service     | `8080`              |

> 💡 Todos containers comunicam-se internamente por rede Docker.

---

## 📦 Exemplos de JSONs Utilizados (modelos):

**Exemplo JSON enviado via RabbitMQ:**

```json
{
  "orderId": "pedido-12345",
  "itens": [
    {
      "nomeProduto": "Notebook",
      "quantidade": 1,
      "valorUnitario": 4500.00
    },
    {
      "nomeProduto": "Headset Gamer",
      "quantidade": 2,
      "valorUnitario": 349.99
    }
  ]
}
```

---

## 📌 Dicas úteis:

- Para **parar containers** facilmente após testes:
```bash
docker-compose down
```

- Para acessar os logs da aplicação após subir com Docker Compose:
```bash
docker-compose logs order-service
```

---

## 📚 Próximos passos & Melhorias a considerar:

- [ ] Implementar testes unitários e integração.
- [ ] Segurança avançada com Spring Security OAuth2/JWT.

