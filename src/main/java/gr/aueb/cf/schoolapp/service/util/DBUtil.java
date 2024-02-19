package gr.aueb.cf.schoolapp.service.util;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class DBUtil {

    private static final BasicDataSource ds = new BasicDataSource();
    private static Connection connection;

    static {
        ds.setUrl("jdbc:mysql://localhost:3306/schoolapp5db?serverTimezone=UTC");
        ds.setUsername("school5appuser");
        ds.setPassword(System.getenv("SCHOOL_DB_USER_PASSWD"));
        ds.setInitialSize(8);
        ds.setMaxTotal(32);
        ds.setMinIdle(8);

        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    /**
     * No instances of this class should be available.
     */
    public DBUtil() {
    }

    public static Connection getConnection() throws SQLException {
        connection = ds.getConnection();
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
