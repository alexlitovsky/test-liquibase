# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

`test-liquibase` is a Maven library (`io.github.alterioncorp:test-liquibase`) that provides a utility method for applying Liquibase changelogs in tests. It is intended to be used as a test dependency by other projects.

## Build Commands

```bash
mvn clean install       # Build and install to local Maven repo
mvn clean package       # Build JAR only
mvn compile             # Compile sources
mvn javadoc:javadoc     # Generate Javadocs
```

CI uses `mvn clean verify -U` for non-main branches and `mvn clean deploy -U -Psign` for main (publishes to Maven Central).

There are no tests in this repository. The library is validated by consumers.

## Versioning

- `develop` and feature branches always use a `SNAPSHOT` version (e.g. `1.0-SNAPSHOT`)
- Release process:
  1. Merge `develop` into `main`
  2. Change version on `main` to release version (e.g. `1.0.0`)
  3. Tag `main` with the release version
  4. Push `main`

## Architecture

The entire library is one class: `LiquibaseUtils` (`src/main/java/io/github/alterioncorp/test/liquibase/LiquibaseUtils.java`).

- All methods are static; the class is not instantiated.
- The `update` method declares `throws LiquibaseException`; callers are responsible for handling it.
- `liquibase-core` is declared as `provided` scope, meaning consumers must include it on their classpath.

## Java & Maven

- Java 21 (source and target)
- Inherits from `io.github.alterioncorp:parent-pom:1.0.0` for shared plugin/repository configuration
- Source and Javadoc JARs are attached at build time via `maven-source-plugin` and `maven-javadoc-plugin`
- Artifacts are published to Maven Central via `central-publishing-maven-plugin` (inherited from parent-pom)
- GPG signing is activated via the `sign` profile (`-Psign`), managed by parent-pom
