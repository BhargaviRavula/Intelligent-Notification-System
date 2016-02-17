package dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class DBConnectionFactory {

	public static Connection getConnectionObject() throws Exception {
		SQLServerDataSource ds = new SQLServerDataSource();
		ds.setServerName("ADMIN-HP");
		ds.setInstanceName("sql2012");
		ds.setDatabaseName("INSINSTANCE");
		ds.setPortNumber(1435);
		ds.setUser("admin");
		ds.setPassword("admin");
		Connection conn;
		try {
			conn = ds.getConnection();
		} catch (SQLServerException e) {
			throw new Exception(e);
		}
		return conn;
		 		
	}

	public static void destroyConnection(Connection connection)
			throws Exception {

		try {      
			connection.close();
		} catch (SQLException e) {
			throw new Exception(e);
		}
	}

}
