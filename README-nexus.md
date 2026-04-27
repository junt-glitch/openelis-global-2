# OpenELIS Global — ProjectNexus Fork Notes

> This fork is maintained by ProjectNexus (github.com/junt-glitch/openelis-global-2).
> Read CLAUDE.md in the parent project for full context.

## Fork Baseline

- **Upstream repo:** https://github.com/DIGI-UW/OpenELIS-Global-2
- **Upstream remote:** `upstream` (configured in this repo's git remotes)
- **Forked from branch:** develop
- **Upstream commit at fork time:** bf26d0f6b (Update OpenELIS translations from Transifex #3436)
- **Fork date:** 2026-04-27

## What We Change vs. Upstream

All modifications to upstream OpenELIS files are logged in the parent project at:
`plan/UPSTREAM-PATCHES.md` (in junt-glitch/project-nexus)

**Current patches:** None — initial fork is unmodified upstream (except .gitmodules submodule URLs)

## Submodule Status

| Submodule | URL | Status |
|---|---|---|
| plugins | junt-glitch/openelisglobal-plugins | Initialized |
| tools/openelis-analyzer-bridge | junt-glitch/openelis-analyzer-bridge | Initialized |
| tools/analyzer-mock-server | junt-glitch/analyzer-mock-server | Initialized |
| Consolidated-Server | DIGI-UW (unchanged) | Deferred — multi-site, not MVP |
| hapi-fhir-jpaserver-starter | DIGI-UW (unchanged) | Deferred — Phase 4B FHIR |
| dataexport | DIGI-UW (unchanged) | Deferred |
| tools/Liquibase-Outdated | DIGI-UW (unchanged) | Skipped — outdated |
| tools/Password-Migrator | DIGI-UW (unchanged) | Skipped |

## Syncing with Upstream

To pull in upstream OpenELIS changes:
```bash
git fetch upstream
git merge upstream/develop
# Resolve any conflicts; check plan/UPSTREAM-PATCHES.md for patches to reapply
```
