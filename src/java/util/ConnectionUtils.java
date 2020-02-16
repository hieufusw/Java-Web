package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionUtils {
    
    public static Connection connect() throws SQLException {
        Connection jdbcConnection = null;
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            } 
            jdbcConnection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BanHang", "sa", "123456");
        }
        return jdbcConnection;
    }
    
    public static void disconnect(Connection jdbcConnection) throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
}
