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
| [`bootstrap-skeleton/`](bootstrap-skeleton/spec.md) | — | Draft | Initial scaffold and TODOs only; no MVP behavior |
| [`in-memory-social-deduplication/`](in-memory-social-deduplication/spec.md) | — | In progress | User-authorized in-memory first-seen deduplication by source and external ID; local AI-Ready file only |
