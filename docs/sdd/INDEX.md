# SDD Index

The **Status column is the state machine of the cycle** — prompts read it to decide what runs, and `/implement-spec` refuses specs that aren't `Approved`. Keep it accurate.

| Status | Meaning | Who sets it |
|---|---|---|
| `Draft` | Spec written, not approved — may still change | `/new-spec` · `/spec-from-issue` |
| `Approved` | **Gate 1 passed — the human approval act IS changing this cell** (edit it yourself, or tell the agent "approved" and it updates + commits) | **Human only** |
| `In progress` | Being implemented | `/implement-spec` (on start) |
| `Shipped` | MR/PR open, waiting CI + human review (Gate 2) | `/open-mr` |
| `Done` | Merged | `/next` (on detecting the merge) or human |
| `Blocked` | Waiting on something external — say what in Notes | Anyone (state the reason) |

Rules: agents NEVER set `Approved`. A spec that needs rework after approval goes back to `Draft` (approval is re-required). Every spec directory must have a row here — an unlisted spec doesn't exist for the cycle.

| Spec | Issue | Status | Notes |
|------|-------|--------|-------|
| [`20261106-bootstrap-skeleton/`](specs/20261106-bootstrap-skeleton/spec.md) | — | Done | Initial scaffold and TODOs only; no MVP behavior |
| [`20261106-in-memory-social-deduplication/`](specs/20261106-in-memory-social-deduplication/spec.md) | — | Done | User-authorized in-memory first-seen deduplication by source and external ID; local AI-Init file only |
| [`20261106-normalizacao-de-fixtures-alpha-beta/`](specs/20261106-normalizacao-de-fixtures-alpha-beta/spec.md) | — | Done | Adapters Alpha/Beta que normalizam fixtures sociais sintéticas |
| [`20261106-persistencia-de-posts-postgres/`](specs/20261106-persistencia-de-posts-postgres/spec.md) | — | Done | Persistência de posts normalizados no PostgreSQL |
| [`20261106-jar-biblioteca/`](specs/20261106-jar-biblioteca/spec.md) | — | Done | Build gera jar comum (biblioteca) e executável |
| [`20261106-contrato-de-persistencia-do-dashboard/`](specs/20261106-contrato-de-persistencia-do-dashboard/spec.md) | — | Done | Integra as bibliotecas de coleta ao contrato de persistência do dashboard |
| [`20261106-renomeacao-collector-runner-mini/`](specs/20261106-renomeacao-collector-runner-mini/spec.md) | — | Done | Renomeação para poc-collector-runner e referências ao poc-dashboard |
| [`20261106-versoes-maven-em-properties/`](specs/20261106-versoes-maven-em-properties/spec.md) | — | Done | Versões de dependências centralizadas em properties |
| [`20261106-paginacao-e-filtros/`](specs/20261106-paginacao-e-filtros/spec.md) | — | Done | Rascunho a partir do TODO.md do projeto |
| [`20261106-retencao-e-privacidade/`](specs/20261106-retencao-e-privacidade/spec.md) | — | Done | Rascunho a partir do TODO.md do projeto e README (Pending) |
| [`20261106-rota-de-ingestao-das-fixtures/`](specs/20261106-rota-de-ingestao-das-fixtures/spec.md) | — | Done | Rascunho a partir do TODO.md do projeto |
| [`20261106-ai-init-adocao-v2/`](specs/20261106-ai-init-adocao-v2/spec.md) | — | In progress | `fc05cc8` + `.ai-init`, workflows e contexto agora versionados na branch local; doc-check, guard `.env` e agent-setup verificados; GitHub CI aguarda publicação e revisão por IA depende de `ANTHROPIC_API_KEY` |
| [`20261110-atualizar-java-27/`](specs/20261110-atualizar-java-27/spec.md) | — | In progress | JDK 27.0.1.0.3, Boot 4.1.2; `mvn -B clean verify` passou com 27 testes em 2026-11-11; perfil `postgres-it` ainda depende de Docker e a branch permanece local |
| [`20261118-fontes-abertas-wikipedia-gitlab-lobsters/`](specs/20261118-fontes-abertas-wikipedia-gitlab-lobsters/spec.md) | — | Approved | born from macro-spec 20261118-fontes-abertas-wikipedia-gitlab-lobsters — Gate 1 inherited |
| [`20261119-padronizar-maven-wrapper/`](specs/20261119-padronizar-maven-wrapper/spec.md) | — | Done | Spec filha da macro 20261119-padronizar-maven-wrapper; wrapper Maven para 1 POM(s), sem alterar JDK ou comportamento. |
