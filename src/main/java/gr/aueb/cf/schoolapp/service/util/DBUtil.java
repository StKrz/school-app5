package gr.aueb.cf.schoolapp.service.util;

/* έγινε import απο το dependency το οποίο ήδη έχουμε */
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class DBUtil {

    private static final BasicDataSource ds = new BasicDataSource();
    private static Connection connection;

    // Μέσα σε ένα static ορίζουμε τα διάφορα-config properties,
    // που ορίζουν το Connection
    static {
        ds.setUrl("jdbc:mysql://localhost:3306/schoolapp5db?serverTimezone=UTC");
        ds.setUsername("school5appuser");
        ds.setPassword(System.getenv("SCHOOL_DB_USER_PASSWD"));
        ds.setInitialSize(8);   /* πόσα είναι τα αρχικά connections */
        ds.setMaxTotal(32);     /* μέγιστος αριθμός connections */
        ds.setMinIdle(8);       /* συνήθως είναι τόσα όσα είναι τα αρχικά.
                                   τα ελάχιστα Idles*/
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
