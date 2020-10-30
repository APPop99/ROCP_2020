package app.player.dao.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresSqlConnection 
{
	private static Connection connection;

	public PostgresSqlConnection() {
		// TODO Auto-generated constructor stub
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DbUtilProps.DRIVER);
		String url=DbUtilProps.URL;			
		String username=System.getenv("postgresUserName");
		String password=System.getenv("postgresPassword");
		connection=DriverManager.getConnection(url, username, password);
		return connection;
	}
}
