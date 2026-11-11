# SDD: retencao-e-privacidade

**Complexity:** M

**Status:** Approved by the user's batch approval; implementation decisions recorded below.

## 1. Context
Retenção, exclusão/takedown e privacidade não estão definidas.

## 2. Goal
Definir retenção, takedown e privacidade antes de produção.

## 3. Scope
### 3.1 In scope
- Política de retenção e exclusão.

### 3.2 Out of scope
- —

## 4. Requirements
### 4.1 Functional
- FR-01: Pedido de remoção apaga ou anonimiza o post.

### 4.2 Non-functional
- A definir na revisão.

## 5. Architecture and Design
Store `created_at` at ingestion. `POST_RETENTION_DAYS` defaults to `0`, which disables automatic deletion; positive values retain rows for that number of days and prune daily at 03:17 UTC. This opt-in default avoids deleting existing or future data until an operator selects a period. Takedown is available by source/external ID and requires `RUNNER_ADMIN_API_KEY`; without a configured key the operation fails closed. Bind the standalone server to `127.0.0.1` by default. Do not log post content or identifiers during pruning/takedown; log only the number of rows removed.

## 6. Implementation Plan
1. Add ingestion timestamp without changing post identity.
2. Add a scheduled, opt-in cleanup service and authenticated takedown operation.
3. Test the disabled default, configured cutoff, key enforcement, and deletion behavior.

## 7. Acceptance Criteria
- AC-01: Retention is disabled by default and validates nonnegative configuration.
- AC-02: Configured retention removes only rows older than the cutoff based on `created_at`.
- AC-03: Takedown requires the admin key and removes only the exact source/ID pair; invalid/missing keys fail closed.
- AC-04: Logs contain no post content or identifiers.

## 8. Observability and Operations
- A definir.

## 9. Risks and Mitigations
- A definir.

## 10. Rollback
A definir.

## 11. References
- TODO.md do projeto e README (Pending)
