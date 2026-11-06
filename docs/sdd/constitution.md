# Constitution — Social API Adapter Mini Project Principles

Immutable principles every SDD, plan or implementation must honor. If a spec conflicts with this document, this document wins — the spec must be revised.

## 1. Language

Source code, domain names, comments, and project documentation use English.

## 2. Architecture

A single Spring Boot application; package root `com.example.socialadapter`; HTTP adapters and persistence are planned but not implemented. Keep domain contracts separate from future HTTP, provider, persistence, or broker adapters. Add only abstractions needed by an approved SDD.

## 3. Quality

- All new code has tests. A build with passing tests is a prerequisite for completing any task.
- No silent exception swallowing.
- Changes must not reduce existing coverage.
Until behavior exists, keep the scaffold buildable. Add focused tests with each approved behavior; do not claim the MVP is complete while TODOs remain.

## 4. Security

- No secrets in code or versioned configuration — use environment variables/local profiles.
- SQL always parameterized; external input validated at the boundary.
- New dependencies require justification in the SDD and a check for known CVEs.

## 5. Process (Spec-Driven)

- Non-trivial feature → SDD in `docs/sdd/` before code, following TEMPLATE.sdd.md.
- SDD approved by a human before implementation.
- The SDD's acceptance criteria are the final validation contract.
- Relevant architecture changes recorded in the SDD's decisions section (or a referenced ADR).

## 6. Simplicity

- Prefer the existing pattern in the codebase over introducing a new one.
- Do not add abstractions "for the future" without a present use case.

## 7. Bounded automation

- Every automated loop (fix→test, review→fix→re-review) has an attempt budget — default **3 attempts**. Reaching the limit means STOP and escalate to a human with a summary of what was tried and why it keeps failing.
- Escalating is success, not failure — the forbidden outcomes are looping silently and "resolving" the loop by weakening the check (deleting a test, suppressing a warning, softening a finding).
- Each attempt must be a DIFFERENT hypothesis — retrying the same fix twice counts as one attempt and one wasted slot.
