# SDD: paginacao-e-filtros

**Complexity:** S

**Status:** Approved by the user's batch approval; implementation decisions recorded below.

## 1. Context
A consulta de posts não pagina nem filtra. A lista atual é consumida por integrações existentes.

## 2. Goal
Paginar e filtrar posts.

## 3. Scope
### 3.1 In scope
- Paginação e filtros.

### 3.2 Out of scope
- —

## 4. Requirements
### 4.1 Functional
- FR-01: Listagem paginada com filtros aprovados.

### 4.2 Non-functional
- A definir na revisão.

## 5. Architecture and Design
Manter `GET /v1/posts` com seu array de resposta existente. Adicionar `GET /v1/posts/search` com `page` (base zero, padrão 0), `size` (padrão 20, máximo 100), `source` (correspondência exata sem diferenciar maiúsculas), `q` (substring em author/text), `from` e `to` (limites inclusivos de `publishedAt`, ISO-8601). Rejeitar parâmetros inválidos com 400. Ordenar por publicação decrescente, depois criação e ID decrescentes. A resposta inclui `items`, `page`, `size`, `totalElements` e `totalPages`.

## 6. Implementation Plan
1. Manter a rota legada intacta.
2. Adicionar consulta paginada e validações de limites.
3. Verificar filtros combinados, limites e compatibilidade por testes H2.

## 7. Acceptance Criteria
- AC-01: A rota `/search` respeita página e tamanho, filtros e ordenação; parâmetros fora dos limites retornam 400.
- AC-02: `GET /v1/posts` preserva formato e ordenação anteriores.

## 8. Observability and Operations
- A definir.

## 9. Risks and Mitigations
- A definir.

## 10. Rollback
A definir.

## 11. References
- TODO.md do projeto
