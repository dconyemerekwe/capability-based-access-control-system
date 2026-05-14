# Type-Safe Capability Access Control (CBAC)
A robust security framework engineered in Java that implements Security-by-Design and Least Privilege principles. This system leverages Java’s strong type system to ensure that security constraints are enforced at the architectural level, making unauthorised operations unreachable by design.

## Security Vision & DevSecOps
As an aspiring Security Engineer, I believe that high-integrity systems require security to be a foundational constraint rather than a middleware check. This project is a practical exploration of Policy-as-Code, a cornerstone of DevSecOps, where access rights are treated as unforgeable, type-safe capabilities.

By "shifting security left" into the codebase's type system, I've eliminated entire classes of access-control vulnerabilities, ensuring Asset Protection and Software Correctness before the code ever reaches a runtime environment.

## Core Architecture
The system is modeled after the Object-Capability security pattern, which replaces broad permissions with specific, unforgeable tokens.

* Policy Engine: Acts as the "Security Kernel," evaluating user roles, resource scopes, and ownership to make authoritative access decisions.

* Capability Factory: A secure issuance service that generates type-safe tokens (`Capability<Read>`, `Capability<Write>`) based on authorised requests.

* Execution Service: The final gatekeeper. It utilises Java Generics to ensure it is technically impossible to trigger an action without the specific type-token.

## Key Security Features
* Type-Safe Tokens: Leverages `<T extends Action>` to ensure a "Read" capability cannot physically perform a "Write" operation, preventing capability leakage.

* Fail-Closed Design: The system defaults to a `DENY` state; tokens are only issued upon positive authorisation from the Policy Engine.

* Ownership-Based Access: Incorporates context-aware security by requiring resource ownership for high-sensitivity modifications (e.g., student record updates).

* Auditing & Forensics: Integrated logging via AuditLogger provides a full forensic trail of every `ALLOW` and `DENY` decision.

##  Access Decision Matrix
| Role | Public Resources | Internal Resources | Confidential Resources | Ownership Required? |
| :--- | :--- | :--- | :--- | :--- |
| **GUEST** | Read Only | Denied | Denied | No |
| **STUDENT** | Read Only | Read Only | Read Only | Yes (for Write) |
| **TEACHER** | Read/Write | Read/Write | Read Only | No |
| **ADMIN** | Read/Write | Read/Write | Read/Write | No |

## Testing & Validation
The project includes a comprehensive JUnit 5 suite to validate authorisation boundaries and ensure that privilege escalation is impossible.
```Bash
mvn test
```
