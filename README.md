# Social API Adapter Mini

Mini projeto Java privado para normalizar posts de fontes sintéticas, deduplicar por `(source, externalId)` e persistir os registros em PostgreSQL.

## Implementado

- Adapters Alpha e Beta para fixtures JSON locais sintéticas.
- Modelo canônico `NormalizedSocialPost` e validação dos campos obrigatórios nas fixtures.
- Persistência com Spring Data JPA e chave única no banco para `(source, externalId)`.
- `POST /v1/posts`: retorna `201 Created` para nova publicação e `200 OK` para duplicata.
- `GET /v1/posts`: lista posts persistidos por data de publicação decrescente.
- PostgreSQL local via Compose; testes JUnit usam H2 em memória e não precisam de serviço externo.
- OpenAPI em `/v3/api-docs` e Swagger UI em `/swagger-ui/index.html`.

As fontes Alpha/Beta são fixtures inventadas para testes; o projeto não acessa redes sociais reais.

## Executar testes

```bash
mvn test
```

## Iniciar PostgreSQL local e a API

```bash
docker compose up -d postgres
mvn spring-boot:run
```

A conexão padrão local usa `social_adapter` em `localhost:5432`. Para sobrescrever, configure `DB_URL`, `DB_USERNAME` e `DB_PASSWORD`. `.env.example` contém apenas valores locais ilustrativos; não coloque credenciais reais no Git.

## Swagger e exemplos

Swagger UI: <http://localhost:8080/swagger-ui/index.html>
OpenAPI JSON: <http://localhost:8080/v3/api-docs>

```bash
curl -X POST http://localhost:8080/v1/posts \\
  -H 'Content-Type: application/json' \\
  -d '{"source":"demo","externalId":"post-001","author":"sample-user","text":"Synthetic post","publishedAt":"2026-01-01T12:00:00Z","permalink":"https://example.org/posts/1"}'

curl http://localhost:8080/v1/posts
```

## Escopo futuro

Paginação, filtros, política de retenção e integração controlada dos adapters às rotas precisam de uma especificação aprovada. ActiveMQ Classic só entra se surgir necessidade de processamento assíncrono; Kafka só se replay ou múltiplos consumidores independentes forem requisitos reais.
