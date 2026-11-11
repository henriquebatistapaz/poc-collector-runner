# SDD: rota-de-ingestao-das-fixtures

**Complexity:** M

**Status:** Approved by the user's batch approval; decision is to reuse the dashboard contract.

## 1. Context
The dashboard already exposes the controlled `POST /v1/collections` route. `DefaultCollectionGateway` invokes Alpha/Beta fixture adapters internally and writes normalized items through the dashboard's PostgreSQL writer.

## 2. Goal
Avoid adding a second fixture-only HTTP ingress route to the runner.

## 3. Scope
### 3.1 In scope
- Verify the dashboard route continues to invoke the runner's fixture adapters.

### 3.2 Out of scope
- —

## 4. Requirements
### 4.1 Functional
- FR-01: Fixtures entram pela rota aprovada.

### 4.2 Non-functional
- A definir na revisão.

## 5. Architecture and Design
Decision: no new route is needed. `POST /v1/posts` remains a legacy endpoint for already normalized synthetic posts; it does not accept raw Alpha/Beta fixture JSON. The dashboard's authenticated collection route is the only fixture-ingest contract.

## 6. Implementation Plan
1. Keep fixture parsing in the runner gateway.
2. Verify the existing dashboard contract and legacy normalized-post contract; do not create a duplicate route.

## 7. Acceptance Criteria
- AC-01: Dashboard `POST /v1/collections` covers Alpha/Beta fixtures through the runner gateway.
- AC-02: Legacy `POST /v1/posts` remains a normalized-post endpoint and stays backward compatible.

## 8. Observability and Operations
- A definir.

## 9. Risks and Mitigations
- A definir.

## 10. Rollback
A definir.

## 11. References
- TODO.md do projeto
