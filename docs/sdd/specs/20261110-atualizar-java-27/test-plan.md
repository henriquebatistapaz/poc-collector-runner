# Test plan — Java 27 no poc-collector-runner

## Escopo e pré-condições

- Registrar o baseline no JDK 21.0.13 / Maven 3.9.16 com repositório Maven temporário.
- Executar a candidata em OpenJDK 27.0.1 (Homebrew) / Maven 3.9.16; usar um repositório local isolado com as builds candidatas do `poc-collector` e `poc-rss-collector`.
- Para AC-01b/AC-02, usar Docker com PostgreSQL 16 descartável e compilar o dashboard contra o JAR candidato; nunca usar dados ou credenciais reais.
- Observar as portas e rotas antes/depois; não iniciar conectores para fontes reais.

## Critérios e verificações

| Critério | Camada | Caso / comando | Estado |
|---|---|---|---|
| AC-01a | Ambiente/build | `java -version`, `mvn -v`, effective POM, `maven.compiler.release` e major do JAR (`javap -verbose`). | Passou; Java 27.0.1, Boot 4.1.2, release 27 |
| AC-01b | Build/teste + integração | `mvn -B clean verify` e `mvn -B -Ppostgres-it verify` com candidato do coletor instalado no repositório temporário. | `clean verify` passou (27 testes); `-Ppostgres-it verify` executado e falhou porque Testcontainers não encontrou Docker |
| AC-02 | Integração consumidor | `CollectedItemWriterPostgresIT`, testes de `DefaultCollectionGateway` e `mvn clean test` do dashboard usando JAR candidato. | Dashboard compilou/testou contra o runner atualizado (39 testes); PostgreSQL IT falhou por falta de Docker |
| AC-03 | Segurança/contrato | Repetir testes de validação, autenticação, OAuth e feeds; comparar portas/binds e revisar logs. | Parcial; verificar |
| AC-04 | Manual/rollback | Build baseline em checkout descartável com JDK 21 e build candidato com JDK 27; comparar os contratos e os dados JSON persistidos. | A criar |
| AC-05 | Configuração/build | `mvn help:effective-pom` confirma Spring Boot 4.x; executar todos os testes unitários e de integração. | Boot 4.1.2 confirmado; unitários passaram; integração Docker pendente |

## Casos negativos e de borda

- Item com fonte, ID, título ou URL ausente/malformado continua rejeitado como no baseline.
- Repetir o mesmo item sob duas execuções concorrentes; unicidade e transação PostgreSQL continuam valendo.
- Banco indisponível: a operação falha sem indicar sucesso nem incluir credenciais nos logs.
- OAuth negado e feed inválido continuam fail-closed usando somente transportes/fixtures falsos.
- Rodar testes sem Docker para confirmar que o perfil `postgres-it` permanece isolado; o gate de integração deve exigir Docker.

## Roteiro exploratório

1. No checkout baseline, registrar commit, JDK/Maven, release, major do JAR, portas e rotas; executar os testes e o perfil `postgres-it`.
2. Repetir no candidato JDK 27, com Maven/repositório local isolados e builds candidatas de `poc-collector` e `poc-rss-collector`.
3. Executar o perfil Postgres contra container descartável; verificar duplicação, rollback e timestamp.
4. Construir/testar `poc-dashboard` com o runner candidato; confirmar as interfaces públicas esperadas.
5. Comparar resultados baseline/candidato e logs; confirmar ausência de novos binds e dados reais.
6. Em checkout descartável do baseline, repetir `mvn clean verify` no JDK 21 para comprovar o retorno sem mexer na branch de trabalho.

## Dados de teste

- Fixtures sociais sintéticas existentes em `src/test/resources` e os itens criados nos testes de persistência.
- OAuth sintético: resposta HTTP 401 `fixture-denied`, sem token real.
- Feed inválido: XML truncado `<rss><channel><item>`.
- Banco indisponível: URL JDBC local inválida no teste de integração; nenhuma senha real.

## Fora do plano

- Deploy, publicação de artefatos, chamadas a Reddit/WordPress/Google e alteração de regras de negócio.
- Migração de esquema ou alteração dos contratos públicos do runner/dashboard.

## Evidência da implementação (11/11/2026)

Atendido em parte: JDK 27.0.1 / Maven 3.9.16, Spring Boot 4.1.2, Lombok 1.18.48, springdoc 3.2.0 e Testcontainers 2.0.5. `clean verify` e `clean install` passaram com 22 testes. Dashboard compilou e testou contra o artefato candidato. Docker/PostgreSQL, comparação de exposição e rollback continuam pendentes.


Evidência adicional: o candidato foi instalado no repositório Maven isolado para consumo pelo dashboard. Failsafe reporta `Could not find a valid Docker environment` em `CollectedItemWriterPostgresIT`; esse gate exige um daemon Docker ativo.
