# Mini Projeto 1 — Social API Adapter

**Status:** adapters para fixtures sintéticas, normalização, deduplicação em memória, endpoint de ingestão e OpenAPI implementados. Persistência e consultas continuam no TODO.
**Visibilidade:** privado.
**Tipo:** mini projeto independente; não contém código, dados ou credenciais de empregadores.

## Objetivo

Demonstrar como formatos de fontes sociais distintas podem ser convertidos em um modelo Java comum e recebidos por uma API com deduplicação.

## Implementado

- Adapters Alpha e Beta para fixtures JSON locais em formatos diferentes.
- Modelo `NormalizedSocialPost` com fonte, ID externo, autor, texto, data e permalink.
- Validação de dados e datas das fixtures.
- Deduplicação em memória por `(source, externalId)`; os IDs vistos são perdidos ao reiniciar.
- `POST /v1/posts`: `201 Created` para primeira ocorrência e `200 OK` para duplicata.
- OpenAPI em `/v3/api-docs` e Swagger UI em `/swagger-ui/index.html`.
- Testes JUnit para adapters, leitura de JSON, deduplicação e API.

## Executar

```bash
mvn test
mvn spring-boot:run
```

Exemplo de ingestão:

```bash
curl -i http://localhost:8080/v1/posts \
  -H 'Content-Type: application/json' \
  -d '{"source":"demo","externalId":"post-001","author":"sample-user","text":"Synthetic post","publishedAt":"2026-01-01T12:00:00Z","permalink":"https://example.org/posts/1"}'
```

Envie o mesmo JSON novamente para observar a resposta de duplicata. Não há acesso a redes sociais reais nem persistência durável.

## Próximos passos

- Persistência e consultas REST.
- Integração dos adapters de fixture com uma rota controlada.
- Paginação e filtros após a persistência.

Kafka e brokers não fazem parte deste projeto mini.
