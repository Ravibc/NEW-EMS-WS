package employee.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Conn {

	Connection c;
	Statement s;

	public Conn() {
		try {
			// Load the SQLite JDBC driver
			Class.forName("org.sqlite.JDBC");

			// Establish connection to the database
			c = DriverManager.getConnection("jdbc:sqlite:EMPLOYEE.db");

			// Create a statement object for executing SQL statements
			s = c.createStatement();
		} catch (ClassNotFoundException e) {
			// Handle the case where the SQLite JDBC driver class is not found
			System.err.println("SQLite JDBC driver not found: " + e.getMessage());
		} catch (SQLException e) {
			// Handle SQL exceptions, such as connection issues or SQL syntax errors
			System.err.println("Error connecting to the database: " + e.getMessage());
		}
	}

	public PreparedStatement prepareStatement(String query) throws SQLException {
		return c.prepareStatement(query);
	}
}
