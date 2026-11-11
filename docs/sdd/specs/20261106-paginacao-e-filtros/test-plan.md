# Test plan — paginação e filtros do runner

## Execução

- JDK Temurin 27.0.1, Maven 3.9.16, H2 em memória.
- `mvn -B clean verify` no worktree de implementação.

## Casos

- Combinar origem, substring, limites de data, página e tamanho; verificar total e resposta estável.
- Rejeitar page negativo, size fora de 1–100 e intervalo invertido com HTTP 400.
- Confirmar que `GET /v1/posts` mantém formato de array.

## Resultado

Passou: 27 testes, 0 falhas e 0 erros. A rota legada e a nova busca HTTP estão cobertas.
