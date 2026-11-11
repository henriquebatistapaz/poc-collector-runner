# SDD: normalizacao-de-fixtures-alpha-beta

**Complexity:** M

> SDD retroativa: documenta o que já foi entregue (commits e48f40e e 5155d0c). Escrita em 2026-11-06 a partir do código e do histórico do repo; status `Done`.

## 1. Context
Era preciso testar coleta social sem APIs reais.

## 2. Goal
Normalizar posts de duas fontes sintéticas num formato único.

## 3. Scope
### 3.1 In scope
- `SocialSourceAdapter`, `AlphaSocialSourceAdapter`, `BetaSocialSourceAdapter`, `FixtureJson`, `NormalizedSocialPost`.

### 3.2 Out of scope
- —

## 4. Requirements
### 4.1 Functional
- FR-01: Fixture Alpha e Beta viram `NormalizedSocialPost`.
- FR-02: Fixture inválida é rejeitada.

### 4.2 Non-functional
- —

## 5. Architecture and Design
Um adapter por fonte atrás de interface.

## 6. Implementation Plan
Já implementado. Principais pontos no código:
- `application/*SocialSourceAdapter`
- `domain/NormalizedSocialPost`

## 7. Acceptance Criteria
- AC-01: Testes dos adapters verdes.

## 8. Observability and Operations
- Sem requisitos específicos além dos logs padrão da aplicação.

## 9. Risks and Mitigations
- —

## 10. Rollback
Reverter os commits/PRs listados nas referências.

## 11. References
- commits e48f40e e 5155d0c
