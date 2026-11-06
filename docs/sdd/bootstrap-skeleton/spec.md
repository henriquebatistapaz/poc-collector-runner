# SDD: Bootstrap Java skeleton and TODOs

**Complexity:** S

## 1. Context
This repository contains an initial product specification in `README.md`, but did not yet have a code scaffold or AI-assisted project workflow.

## 2. Goal
Provide a minimal Java 21/Spring Boot/Maven structure and project guidance so later work can proceed in small, reviewable steps.

## 3. Scope
### 3.1 In scope
- One Spring Boot application entry point and minimal domain/application contracts.
- Maven build metadata, `.gitignore`, AI-Init documentation, and an actionable TODO list.
- Preserve the original product scope in `README.md`; update its status to reflect that the skeleton exists.

### 3.2 Out of scope
- Business behavior, REST endpoints, database schema/persistence, broker configuration, real external integrations, fixtures, CI, and tests.
- Any implementation beyond the skeleton and TODOs.

## 4. Requirements
### 4.1 Functional
- FR-01: The repository has a Java 21 Spring Boot application entry point.
- FR-02: The initial domain/application contracts are represented by small types with TODO documentation.
- FR-03: Remaining MVP work is listed in `TODO.md`.

### 4.2 Non-functional
- NFR-01: The project compiles with Maven and Java 21.
- NFR-02: No secrets, real provider calls, or non-synthetic data are introduced.
- NFR-03: The repository remains private; no commit or push is part of this task.

## 5. Architecture and Design
Single Maven module. `domain` contains small model types; `application` contains service/port contracts; the root package contains the Spring Boot entry point. No adapters or runtime integrations are implemented.

## 6. Implementation Plan
1. Add the Maven build and Java package structure.
2. Add minimal project-specific types and TODO documentation.
3. Install the lean AI-Init prompts, agents, skills, and project guidance.
4. Compile the skeleton without running tests.

## 7. Acceptance Criteria
- AC-01: Maven package succeeds with tests skipped.
- AC-02: `TODO.md` clearly identifies deferred MVP work.
- AC-03: The existing product specification remains present in `README.md`.
- AC-04: No commit or push is created.

## 8. Observability and Operations
No runtime operations are implemented. Run locally with `mvn spring-boot:run` after dependencies are available.

## 9. Risks and Mitigations
- Risk: Skeleton contracts may change as behavior is specified. Mitigation: treat them as provisional and update them in a follow-up SDD.

## 10. Rollback
Remove the scaffold and AI-Init files, restore the previous README status line, and retain the original product specification.

## 11. References
- `README.md` — initial product specification.
- `TODO.md` — deferred implementation work.
