import java.sql.*;

public class JDBCDemo {
    //This class demonstrates basic JDBC operations such as connecting to a database, executing queries, and retrieving results.
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/appdb";
        String user = "root";
        String password = "root";
        Connection con = DriverManager.getConnection(url, user, password);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM address");
        while (rs.next()) {
            //Retrieving data from the ResultSet
            int id = rs.getInt("id");
            String city = rs.getString("city");
            String state = rs.getString("state");
            String street = rs.getString("street");
            System.out.println("ID: " + id + ", City: " + city + ", State: " + state + ", Street: " + street);
        }
        int count = stmt.executeUpdate("INSERT INTO student VALUES (4,12232,'Suresh');");
        System.out.println("Executed successfully, rows affected: " + count);
    }
}

/*
 * The JDBCDemo class demonstrates basic JDBC (Java Database Connectivity) operations.
 * It includes examples of:
 *
 * 1. Loading the MySQL JDBC driver.
 * 2. Establishing a connection to a MySQL database.
 * 3. Executing SQL queries (both SELECT and INSERT).
 * 4. Retrieving and processing results from a ResultSet.
 *
 * Usage:
 * - Ensure the MySQL JDBC driver is added to the project's dependencies.
 * - Update the database URL, username, and password as per your database configuration.
 * - The database should have the required tables (`address` and `student`) with appropriate schema.
 *
 * Example Tables:
 *
 * Table: address
 * Columns: id (int), city (varchar), state (varchar), street (varchar)
 *
 * Table: student
 * Columns: id (int), roll_number (int), name (varchar)
 *
 * Note:
 * - This is a basic example and does not include advanced features like connection pooling or error handling.
 */
