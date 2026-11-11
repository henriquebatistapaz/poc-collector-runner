# SDD: <feature-name>

**Complexity:** <S | M | L>
<!-- S: single module, no real design decisions, obvious change (trivial bug, config, small endpoint)
     M: several files/modules, standard patterns, some design choices
     L: cross-service contracts, migrations, concurrency, security-sensitive, or novel design / heavy reasoning -->
**Security-sensitive:** <no | yes — which areas: auth, authorization, personal data, payments, secrets/crypto, uploads, outbound URLs, infra>
<!-- yes when the change touches a SENSITIVE glob from .ai-init/hooks/guard.conf or handles any area above.
     yes → section 9.1 Threats is required, @qa checks it at Gate 1 and @security reviews the implementation. -->

## 1. Context
Describe the problem, motivation and current state.

## 2. Goal
Define the expected outcome and business value.

## 3. Scope
### 3.1 In scope
- Item

### 3.2 Out of scope
- Item

## 4. Requirements
### 4.1 Functional
- FR-01: ...

### 4.2 Non-functional
- NFR-01: ...

## 5. Architecture and Design
Describe components, contracts, flows and relevant decisions.

## 6. Implementation Plan
1. Step 1
2. Step 2

## 7. Acceptance Criteria
- AC-01: ... (verifiable by automated test whenever possible)

## 8. Observability and Operations
- Logs, metrics, alerts and runbook.

## 9. Risks and Mitigations
- Risk -> mitigation

### 9.1 Threats (required when Security-sensitive: yes — delete otherwise)
Answer each for THIS feature, concretely. Testable mitigations point to an AC.

| Question | Threat here | Mitigation (where in the code) | Verified by |
|---|---|---|---|
| Untrusted input — what comes from outside, and how is it validated? | | | AC-… |
| Authorization — who may act on which resource? Can user A pass user B's id? | | | AC-… |
| Personal data — what is collected/stored, who reads it, kept out of logs? | | | AC-… |
| Secrets — which credentials, where they live, never in client/logs? | | | |

## 10. Rollback
Describe the safe reversal strategy.

## 11. References
- Originating issue
- Related documents
