# test-liquibase

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)

A small utility library for applying Liquibase changelogs in tests.

## Usage

Add the dependency to your project. Because Liquibase is declared `provided` in this library, you must also include it directly.

**Maven:**
```xml
<dependency>
    <groupId>io.github.alterioncorp</groupId>
    <artifactId>test-liquibase</artifactId>
    <version>1.0.0</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.liquibase</groupId>
    <artifactId>liquibase-core</artifactId>
    <version>4.33.0</version>
    <scope>test</scope>
</dependency>
```

## API

All methods are static on `io.github.alterioncorp.test.liquibase.LiquibaseUtils` and declare `throws LiquibaseException`.

| Method | Description |
|---|---|
| `update(Connection connection, String changeLogPath)` | Applies a Liquibase changelog to the database reachable via the given connection |

**Example:**
```java
try (Connection conn = DerbyEmbeddedUtils.openConnection("mydb")) {
    LiquibaseUtils.update(conn, "db/changelog.xml");
}
```

## License

Licensed under the [Apache License 2.0](LICENSE).
