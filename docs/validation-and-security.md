# Shared Library verification and security plan

**Current status:** GitHub source reviewed; Jenkins runtime tests, registry outcomes and code-coverage evidence not yet supplied.

## Suggested validation matrix

| Check | Prerequisite | Expected behavior | Evidence state |
|---|---|---|---|
| Library import | Global Pipeline Library points to correct repo/revision | Jenkins resolves callable `vars` steps | Pending |
| Maven build | Java/Maven project + configured agent | `mvn clean package` completes | Pending |
| Docker build | Docker-enabled agent | Image built under expected tag | Pending |
| Docker login | Jenkins credential with repository scope | Registry auth succeeds without token in logs | Pending |
| Docker push | Authorized Docker Hub repository | Image push returns digest | Pending |
| Deployment function | `deployApp()` code | Logs message only | Source verified; not a real deploy |

## Evidence record

```text
Date (UTC):
Repository commit SHA:
Jenkins controller, plugin and agent versions:
Job/build ID and branch:
Shared Library revision actually loaded:
Stage and command:
Expected result:
Observed result:
Sanitized console log or screenshot:
Known limitations:
```

## Security controls

1. Configure Shared Library trust and SCM credentials under least privilege.
2. Pin and review trusted library versions, especially for multibranch builds.
3. Prefer isolated Docker build agents and restricted daemon access.
4. Do not allow malicious pull-request code to access Docker Hub push credentials.
5. Rotate compromised tokens. Keep literal PATs out of Git, README files and build artifacts.
6. Review whether registry login writes long-lived authentication to a shared Docker config directory.
7. Remove unnecessary bundled libraries and workstation artifacts in a separate reviewable maintenance change.

## Personal learning prompts

Explain why `vars` steps differ from classes in `src`, why the Docker helper receives the Jenkins script context, and which Jenkins log messages show that the library was actually loaded. Prepare to answer these in the TWN validation videos using your own words.
