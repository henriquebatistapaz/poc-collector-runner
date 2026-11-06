# TODO

Initial implementation slice is complete; the remaining MVP work is listed below.

- [x] Define two synthetic JSON fixture formats and their provenance-free examples.
- [x] Implement adapters that map fixtures into the canonical post model.
- [x] Validate required fields and ISO-8601 publication timestamps.
- [x] Add in-memory duplicate handling by source plus external ID; state resets on restart.
- [ ] Add durable duplicate handling with persistence.
- [ ] Add persistence and REST queries only after a follow-up SDD is approved.
- [x] Add focused unit tests for both formats and malformed timestamps.
- [x] Expose synthetic post ingestion with OpenAPI and Swagger UI.
- [ ] Revisit this checklist as decisions are made; do not expand scope silently.
