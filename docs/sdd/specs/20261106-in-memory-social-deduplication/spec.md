# SDD: In-memory social post deduplication

**Complexity:** S
**Status:** In progress (directly authorized by the project owner)

## 1. Context
The Alpha and Beta adapters normalize synthetic fixture records into `NormalizedSocialPost`. Re-importing data can yield the same provider post more than once.

## 2. Goal
Suppress duplicate normalized posts using the compound identity `(source, externalId)` without adding persistence, and expose the first small ingestion API with OpenAPI documentation.

## 3. Scope
### In scope
- A small in-memory application service that accepts normalized posts and returns only first-seen records.
- Preserve input order and distinguish equal external IDs from different sources.
- Keep duplicate state across calls while the process is running.
- Focused unit tests and README/TODO updates.
- `POST /v1/posts` returning `201 Created` for first-seen posts and `200 OK` for duplicates.
- OpenAPI JSON and Swagger UI endpoints.

### Out of scope
- Database persistence, fixture import endpoints, pagination, external APIs, and distributed deduplication.

## 4. Requirements
- FR-01: The first post for a `(source, externalId)` pair is returned.
- FR-02: Later posts with the same pair are omitted, including across separate calls.
- FR-03: A matching external ID from a different source is retained.
- FR-04: Input order is preserved for returned posts.
- FR-05: `POST /v1/posts` accepts a normalized synthetic post and reports accepted or duplicate status.
- FR-06: OpenAPI JSON documents the post ingestion endpoint and Swagger UI is available.
- NFR-01: State is process-local and resets on restart.
- NFR-02: Only synthetic records are used.

## 5. Architecture and decisions
Use an application-level in-memory set of compound identities. Do not change the canonical domain model or couple identity to one provider.

## 6. Acceptance criteria
1. A repeated pair is emitted once within and across calls.
2. Equal IDs from Alpha and Beta are both retained.
3. Distinct IDs from the same source are retained.
4. Unit and HTTP/OpenAPI tests pass and no persistence dependency is added.

## 7. Rollback
Remove the in-memory deduplication service and restore the TODO marker; adapters and canonical model remain unchanged.
