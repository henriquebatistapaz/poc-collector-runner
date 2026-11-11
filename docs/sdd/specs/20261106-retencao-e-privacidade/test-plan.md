# Test plan — retenção e privacidade do runner

## Execução

- JDK Temurin 27.0.1, Maven 3.9.16, H2 em memória e apenas chaves sintéticas.
- `mvn -B clean verify` no worktree de implementação.

## Casos

- Configuração zero desativa a exclusão automática; valor negativo falha na inicialização.
- Configuração positiva calcula o cutoff em dias e remove entradas antigas por `created_at`.
- Backfill usa `published_at` ou o horário do startup para linhas legadas, sem apagá-las.
- Chave de takedown ausente/incorreta retorna 403; chave correta remove o source/ID exato.
- Logs de remoção não incluem conteúdo nem identificadores.

## Resultado

Passou: 27 testes, 0 falhas e 0 erros. `mvn -Ppostgres-it verify` foi executado; Failsafe falhou porque Testcontainers não encontrou ambiente Docker (`Could not find a valid Docker environment`).
