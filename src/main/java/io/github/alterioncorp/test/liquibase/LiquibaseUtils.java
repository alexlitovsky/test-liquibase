package io.github.alterioncorp.test.liquibase;

import java.sql.Connection;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

/**
 * Utility class for applying Liquibase changelogs in tests.
 */
public class LiquibaseUtils {

	/** Not instantiable. */
	private LiquibaseUtils() {}

	/**
	 * Applies a Liquibase changelog to the database reachable via the given connection.
	 *
	 * @param connection    an open JDBC connection to the target database
	 * @param changeLogPath classpath-relative path to the Liquibase changelog file
	 * @throws LiquibaseException if Liquibase fails to apply the changelog
	 */
	public static void update(Connection connection, String changeLogPath) throws LiquibaseException {
		Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
		try (Liquibase liquibase = new Liquibase(changeLogPath, new ClassLoaderResourceAccessor(), database)) {
			liquibase.update((String)null);
		}
	}
}
