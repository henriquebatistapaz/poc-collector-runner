# AGENTS.md — Social API Adapter Mini

Operational guide for AI agents (Copilot, Claude Code, Codex, Cursor, etc.).
Detailed context: @README.md, @docs/ONBOARDING.md, @docs/sdd/constitution.md, @docs/wiki/index.md.

## The project in one line

A Java 27 / Spring Boot 4.1.2 embeddable runner for source-specific collection and persistence, with query and retention operations for synthetic social posts.

## Essential commands

```bash
# Local infrastructure (if any)
Not configured yet; see TODO.md.

# Full build / dependency sync
mvn -DskipTests package

# Build/check a single module (if multi-module — remove otherwise)
Not applicable: single Maven module.

# Tests (always run before considering a task done)
mvn test # doc-check: run

# Format / lint / type-check (the agent's fast feedback loop — from the harness)
Not configured yet; add quality tooling in a future SDD.

# Run locally
mvn spring-boot:run
```

A single Spring Boot application; package root `com.example.socialadapter`; HTTP adapters and persistence are planned but not implemented.

## Mandatory conventions

Java 27, Maven, Spring Boot 4.1.2. Keep source-specific translation behind `SocialSourceAdapter`; keep the canonical post model independent of provider formats and use parameterized SQL for all queries.

## Project skills

Recurring procedures specific to this project documented in `.github/skills/`: `add-social-source-adapter`, `evolve-canonical-post`.

## Workflow (Spec-Driven Development)

Platform: GitHub (`gh` CLI; configure authentication before opening PRs).

Toda spec nasce em `docs/sdd/specs/<YYYYMMDD>-<slug-kebab>/spec.md` (`<YYYYMMDD>` = data da criação, nunca renomeada; `<slug-kebab>` = nome curto) e é registrada no `docs/sdd/INDEX.md`. Specs antigas no formato `docs/sdd/<nome>/` ficaram como ponteiros de compatibilidade.

Work enters from an issue (`/spec-from-issue <url>`), a direct request, or a `/tech-debt`/`/retro`/`/metrics` finding (`/new-spec`) — a trivial bug skips the spec (failing test → fix at the root → validate).

From the spec on, one funnel: `@qa` review + human approval → `/test-plan` → `/implement-spec` → `/validate-implementation` (gate: ACs, build, `@reviewer`/`@qa`/`@architect`) → `/open-mr` (branch + MR/PR) → CI → human review → merge.

Verdict NOT READY (or blocking findings from any review agent)? `/fix-findings` applies them — at the root, keeping the spec in sync — then the gate runs again. Review agents point; they never edit.

Shortcut: `/next` detects the current state and runs the appropriate step itself, stopping at human gates.

Bug fixes: root cause, not symptom — see the `fix-root-cause` skill before writing any fix.

1. **Spec first**: every non-trivial feature starts with an SDD in `docs/sdd/<name>/spec.md` (one directory per spec, holding spec.md, test-plan.md and other feature artifacts), following `docs/sdd/TEMPLATE.sdd.md`. Register it in `docs/sdd/INDEX.md`. No per-spec prompt files — the cycle prompts take the spec path as input.
2. **Principles**: honor `docs/sdd/constitution.md` in any plan or implementation.
3. **Implementation**: follow the SDD plan; validate against the acceptance criteria before finishing (`/implement-spec`).
4. **Delivery**: `/validate-implementation` must return READY TO SHIP (green build, ACs met, no blockers) before `/open-mr` packages the branch and opens the MR/PR.

## Model routing (cost control)

Available tiers in this team's Copilot/editor: Use the available model picker: fast for routine SDD edits, standard by default, stronger reasoning for cross-cutting or security-sensitive design. No fixed model names are required.

Recommended model per step × spec Complexity (S/M/L from the spec header). The agent recommends; the human switches in the model picker:

| Step | S | M | L |
|---|---|---|---|
| Spec writing / test plan | light | standard | standard |
| Implementation | standard | standard | **heavy** |
| Validation / reviews (@qa @reviewer @architect) | light | standard | **heavy** |
| Fix findings / root-cause work | standard | standard | **heavy** |
| Periodic (/metrics /tech-debt /retro) | standard | standard | standard |

Rules of thumb: escalate for REASONING (design, root cause, cross-service contracts), not for typing volume; never downgrade the review below the tier that implemented; in doubt, one tier up — a wrong cheap run costs more than a right expensive one.

## Armadilhas (aprendidas do jeito difícil)

Problemas do projeto que já custaram uma correção. **Leia antes de planejar ou codar** — se a mudança tocar uma área listada aqui, siga a regra e diga isso no plano. Entradas são adicionadas pelo `/fix-findings` e pelo `/retro`.

<!-- Uma linha cada: - **<área>** — <o que quebra e por quê>. Regra: <o que fazer>. (fonte: <spec ou MR/PR>, <AAAA-MM-DD>) -->
_Nenhuma ainda._

## Do not

- Do not save specs outside `docs/sdd/`.
- Do not commit secrets or credentials.
- Do not introduce new dependencies without justifying them in the corresponding SDD.
- Do not consider a task done with failing tests or a broken build.
- Do not leave `docs/wiki/` or this file outdated when a change alters what they describe — doc drift is a bug.
All provider inputs and examples must remain synthetic. Do not add real social network calls or credentials. Do not mark a Draft spec Approved or implement beyond the user-approved skeleton/TODO scope.
- Os arquivos do ai-init (`.ai-init.json`, `AGENTS.md`, `CLAUDE.md`, `.github/copilot-instructions.md`, `.github/prompts|agents|instructions|skills`) e os docs do ciclo (`docs/sdd`, `docs/wiki`, `docs/ONBOARDING.md`) são links para `../ai-init-internal/poc-collector-runner/` e NÃO são versionados neste repo. Edite normalmente; o commit vai no repo `ai-init-internal`.
