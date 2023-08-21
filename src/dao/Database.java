package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	public static Connection getConnection(String name) throws SQLException {

		if (name != null) {
			return DriverManager.getConnection("jdbc:sqlite:" + name + ".db");
		} 

		return null;
	}
}