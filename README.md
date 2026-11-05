# Mini Projeto 1 — Social API Adapter

**Status:** especificação inicial; implementação ainda não iniciada  
**Visibilidade:** repositório privado  
**Tipo:** mini projeto independente, inspirado em padrões de integração relatados na experiência profissional. Não contém código, dados, credenciais ou materiais de empregadores.

## Objetivo

Construir um serviço pequeno em Java que receba publicações sintéticas de uma fonte social simulada, converta formatos diferentes para um modelo interno único e permita consultar registros normalizados.

## Problema demonstrado

Integrações externas precisam isolar diferenças entre formatos, validar dados, lidar com campos opcionais e manter o domínio independente do fornecedor.

## Escopo do MVP

- Uma aplicação modular Spring Boot.
- Dois adaptadores simulados que leem fixtures JSON locais em formatos diferentes.
- Modelo canônico SocialPost: id, source, externalId, author, text, publishedAt e permalink.
- Validação de campos obrigatórios e tratamento de datas inválidas.
- Normalização e prevenção de duplicidade por source + externalId.
- Persistência em PostgreSQL.
- API REST para importar fixture e consultar posts com filtros por fonte/período, paginação e busca por ID.
- Testes dos adaptadores, serviço, persistência e API.
- OpenAPI e instruções locais com Docker Compose.

## Endpoints propostos

- POST /api/v1/ingestions/{source} — importar uma fixture simulada.
- GET /api/v1/posts — listar com filtros e paginação.
- GET /api/v1/posts/{id} — consultar um registro normalizado.

## Requisitos e limites

- Sem chamadas a redes sociais reais nem tokens reais.
- Erros consistentes e logs sem dados pessoais desnecessários.
- Fora do escopo: OAuth real, dashboards, análise de sentimento, interface web, Kafka/RabbitMQ e nuvem.

## Critérios de aceite

1. Os dois formatos viram o mesmo modelo canônico.
2. Dados inválidos geram erro claro sem descartar outros registros válidos da fixture.
3. Reimportar a fixture não cria duplicatas.
4. Filtros, paginação e busca por ID têm testes.
5. README documenta execução, exemplos e limitações.

## Stack prevista

Java 21, Spring Boot, Maven, PostgreSQL, JUnit 5, Mockito, Docker Compose e OpenAPI.

## Etapas

1. Definir modelo e fixtures fictícias.
2. Implementar adaptadores e testes de normalização.
3. Adicionar persistência e idempotência.
4. Expor consultas REST.
5. Documentar execução e completar testes.

## Relação com experiência profissional

Exercício independente baseado em competências relatadas (integrações de APIs, normalização e persistência). Não representa o sistema interno de uma empresa, nem afirma que estes detalhes descrevam um produto existente.
