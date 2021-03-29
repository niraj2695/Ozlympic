package rmit.java.assignment.database;

import java.net.URL;
// imports 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author Niraj Bohra
 * @version 5.0
 * @classDescription SQL Connection file is a connectivity to database
 *
 */
public class SQLConnection {

	// empty constructor

	public SQLConnection() {

	}

	/**
	 * 
	 * @return connection
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Connection createConnection() throws SQLException, ClassNotFoundException {
		Connection connection = null;
		URL url = getClass().getResource("Ozlympic.db");
		// ADD DATA ON SCREEN
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:" + url.getPath());
		connection.setAutoCommit(true);
		return connection;

	}

}
