package DbControllers;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnectionManager {

	private static Connection dbConn;
	private static Statement dbStmt;
	
	public static void establishConnection(String dbURL, String user, String password) throws ClassNotFoundException, SQLException{
		Class.forName("org.sqlite.JDBC");
		dbConn = DriverManager.getConnection(dbURL, user, password);
	}
	
	public static Connection getConnection() {
		return dbConn;
	}
	
	public static Statement getStatement() {
		try {
			if (dbConn.isClosed()) {
				return null;
			}
			if (dbStmt == null) {
				dbStmt = dbConn.createStatement();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dbStmt;
	}
}
