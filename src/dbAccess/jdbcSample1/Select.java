package jdbcSample1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

/**
 *  REMEMBER: This class uses the student table in the school database on the MAMP server
 */

public class Select {

    private static final String USERNAME = "jacques";
    private static final String PASSWORD = "jacquesflstc09";
    private static final String CONN = "jdbc:mysql://localhost/school";
    
    public static void main(String[] args) throws SQLException {
    
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(CONN, USERNAME, PASSWORD);    
            System.out.println("Connection opened");
            
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            System.out.println("Statement created");

            rs = stmt.executeQuery("SELECT * FROM student");
            System.out.println("Resultset created");
            
            while (rs.next()) {
                StringBuffer buffer = new StringBuffer();
                
                buffer.append("Student ID:" + rs.getInt("id") + "   ");
                buffer.append("First Name:" + rs.getString("first_name") + "   ");
                buffer.append("Last Name:" + rs.getString("last_Name") + "   ");
                buffer.append("Grade:" + rs.getInt("grade"));
                
                System.out.println(buffer.toString());
            }
        } catch (SQLException e) {
            System.err.print(e);
        } finally {
            if (rs != null) {  
                rs.close();
                System.out.println("Resultset closed");
            }
            if (stmt != null) {
                stmt.close();
                System.out.println("Statement closed");
            }
            if (con != null) {
                con.close();
                System.out.println("Connection closed");
            }
        }
   }
}
