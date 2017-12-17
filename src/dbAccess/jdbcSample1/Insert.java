package jdbcSample1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

/**
 *  REMEMBER: This class uses the student table in the school database on the MAMP server
 */
public class Insert {

    private static final String USERNAME = "jacques";
    private static final String PASSWORD = "jacquesflstc09";
    private static final String CONN = "jdbc:mysql://localhost/school";

    public static void main(String[] args) throws SQLException {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(CONN, USERNAME, PASSWORD);
            System.out.println("Connected");

            String query = "INSERT INTO student " +
                    "(id, first_name, last_name, grade)" +
                    "VALUES" +
                    "(?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt   (1, 2000);
            preparedStmt.setString(2, "Bob");
            preparedStmt.setString(3, "Newhart");
            preparedStmt.setInt   (4, 6);

            // execute the preparedstatement
            preparedStmt.execute();

            conn.close();
        } catch (SQLException e) {
            System.err.print(e);
        } finally {
            if (conn != null) {
                conn.close();
                System.out.println("Connection closed");
            }
        }
    }
}