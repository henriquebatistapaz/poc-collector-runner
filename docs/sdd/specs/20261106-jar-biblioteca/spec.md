# SDD: jar-biblioteca

**Complexity:** S

> SDD retroativa: documenta o que já foi entregue (commit 96639a6). Escrita em 2026-11-06 a partir do código e do histórico do repo; status `Done`.

## 1. Context
O hub passou a usar o runner como dependência.

## 2. Goal
Publicar o jar comum sem perder o executável.

## 3. Scope
### 3.1 In scope
- Repackage com classifier.

### 3.2 Out of scope
- —

## 4. Requirements
### 4.1 Functional
- FR-01: `mvn package` gera os dois jars.

### 4.2 Non-functional
- —

## 5. Architecture and Design
spring-boot-maven-plugin com classifier.

## 6. Implementation Plan
Já implementado. Principais pontos no código:
- `pom.xml`

## 7. Acceptance Criteria
- AC-01: Consumidor compila contra o jar comum.

## 8. Observability and Operations
- Sem requisitos específicos além dos logs padrão da aplicação.

## 9. Risks and Mitigations
- —

## 10. Rollback
Reverter os commits/PRs listados nas referências.

## 11. References
- commit 96639a6
