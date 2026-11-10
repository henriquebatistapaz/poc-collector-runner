# Onboarding — Social API Adapter Mini

## First steps
1. Read `README.md` for the product scope and `TODO.md` for deferred work.
2. Read `AGENTS.md` and `docs/sdd/constitution.md` before changing code.
3. Review `docs/sdd/INDEX.md`; the bootstrap spec is Draft and covers only the initial skeleton.
4. Use `/new-spec` for the next behavior. A human must approve its SDD before implementation.

## Current scaffold
- Java 21, Maven, Spring Boot 3.4.5.
- One application module; no endpoint, database, broker, CI, or business behavior is implemented.
- Run `mvn -DskipTests package` to compile/package and `mvn spring-boot:run` to start the empty application.

## AI-assisted workflow
Issue or idea → SDD → `@qa` review → human approval → test plan → implementation → validation → pull request. `/next` reads the current project state. This repository is private; review all changes locally before deciding whether to commit or publish.

## Local environment
Use a Java 21 JDK and Maven. Do not add secrets or real service credentials. Platform CLI authentication is not configured by this setup; configure `gh` before opening pull requests.
