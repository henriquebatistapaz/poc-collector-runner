# SDD: persistencia-de-posts-postgres

**Complexity:** M

> SDD retroativa: documenta o que já foi entregue (commit 1eeecfb). Escrita em 2026-11-06 a partir do código e do histórico do repo; status `Done`.

## 1. Context
A deduplicação em memória perdia tudo ao reiniciar.

## 2. Goal
Persistir posts normalizados.

## 3. Scope
### 3.1 In scope
- `SocialPostEntity`, `SocialPostRepository`, `SocialPostPersistenceService`.

### 3.2 Out of scope
- —

## 4. Requirements
### 4.1 Functional
- FR-01: Post novo é gravado; repetido não duplica.

### 4.2 Non-functional
- —

## 5. Architecture and Design
Spring Data JPA.

## 6. Implementation Plan
Já implementado. Principais pontos no código:
- `persistence/**`
- `application/SocialPostPersistenceService`

## 7. Acceptance Criteria
- AC-01: Teste de persistência verde.

## 8. Observability and Operations
- Sem requisitos específicos além dos logs padrão da aplicação.

## 9. Risks and Mitigations
- —

## 10. Rollback
Reverter os commits/PRs listados nas referências.

## 11. References
- commit 1eeecfb
