# SDD: contrato-de-persistencia-do-dashboard

**Complexity:** L

> SDD retroativa: documenta o que já foi entregue (commit 04a7978). Escrita em 2026-11-06 a partir do código e do histórico do repo; status `Done`.

## 1. Context
O poc-dashboard embute o runner e é dono da persistência.

## 2. Goal
Expor um runner que coleta de todas as fontes e escreve pelo contrato do dashboard.

## 3. Scope
### 3.1 In scope
- `CollectionRunner`, `CollectionGateway`, `DefaultCollectionGateway`, `CollectionSource`, `CollectedItemInput`, `CollectedItemWriter`.

### 3.2 Out of scope
- —

## 4. Requirements
### 4.1 Functional
- FR-01: Runner coleta RSS/fontes/fixtures e entrega itens ao writer do consumidor.

### 4.2 Non-functional
- —

## 5. Architecture and Design
Interfaces em `integration`; o consumidor implementa o writer.

## 6. Implementation Plan
Já implementado. Principais pontos no código:
- `integration/**`

## 7. Acceptance Criteria
- AC-01: Testes do gateway verdes; poc-dashboard compila e coleta.

## 8. Observability and Operations
- Sem requisitos específicos além dos logs padrão da aplicação.

## 9. Risks and Mitigations
- —

## 10. Rollback
Reverter os commits/PRs listados nas referências.

## 11. References
- commit 04a7978
