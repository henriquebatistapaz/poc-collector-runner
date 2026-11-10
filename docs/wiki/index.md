# Social API Adapter Mini — Knowledge Wiki

Curated knowledge base for humans and AI agents. Structural facts live in the code (and the Understand-Anything graph, if installed); this wiki captures what code cannot express: **why** decisions were made, business rules, domain flows and gotchas.

Follows the [Karpathy LLM-wiki pattern](https://gist.github.com/karpathy/442a6bf555914893e9891c11519de94f): this index links every page with wikilinks, grouped by category — consumable by agents in-context and by Understand-Anything's `/understand-knowledge`.

## Architecture

- [[architecture-overview]] — the big picture and main data flow
- [[domain-structure]] — package boundaries and current scaffold

## Domains

- [[social-post-normalization]] — current domain contract and deferred behavior

## Decisions

- Bootstrap decisions are captured in `docs/sdd/bootstrap-skeleton/spec.md`.

## Operations

- Local build and onboarding: `docs/ONBOARDING.md`

---

## Rules for this wiki

- One page per topic, kebab-case filename matching the wikilink.
- Every page listed here — an unlisted page doesn't exist.
- **Doc drift is a bug**: if a change makes a page wrong, updating the page is part of that change (enforced by `/implement-spec` and `@reviewer`).
- Prefer mermaid diagrams over prose for flows and dependencies (GitLab and GitHub render them natively).
