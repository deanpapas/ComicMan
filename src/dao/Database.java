package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	public static Connection getConnection(String name) throws SQLException {
		String databaseName = "jdbc:sqlite:" + name + ".db";
		if (name != null) {
			return DriverManager.getConnection(databaseName);
		}

		return null;
	}
}