package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private Connection jdbcConnection;
	private String url, username, password;

	public DBConnection() {
		//Change the username password here
		this.url = "jdbc:mysql://localhost:3306/busticketbooking";
		this.username = "root";
		this.password = "";
	}

	public Connection getJdbcConnection() throws Exception {
		try {
			if (jdbcConnection == null || jdbcConnection.isClosed()) {

				Class.forName("com.mysql.cj.jdbc.Driver");
				this.jdbcConnection= DriverManager.getConnection(url, username, password);
			}
		} catch (Exception e) {
			System.err.println("Error connecting to DB");
			e.printStackTrace();
			throw e;
		}
		return this.jdbcConnection;
	}
}
