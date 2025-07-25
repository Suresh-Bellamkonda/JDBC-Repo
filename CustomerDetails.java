import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerDetails {
    /*
     *Insert multiple records into customer class table using PreparedStatement and batch processing.
     * This code demonstrates how to insert multiple customer records into a database when ID is greater than 0.
     */
    public static void main(String[] args) {
        final String url = "jdbc:mysql://localhost:3306/appdb";
        final String user = "root";
        final String password = "root";
        String insertQuery = "INSERT INTO customer (id, name, age, address) VALUES (?, ?, ?, ?)";
        try (Scanner sc = new Scanner(System.in)) {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Load MySQL JDBC Driver
            try (Connection con = DriverManager.getConnection(url, user, password);
                 PreparedStatement pst = con.prepareStatement(insertQuery)) {
                con.setAutoCommit(false); // Disable auto-commit to manage transactions manually
                while (true) {
                    System.out.print("Enter Customer ID (0 or negative value to exit): ");
                    int id = sc.nextInt();
                    if (id <= 0) {
                        System.out.println("Exiting... No more records to insert.");
                        break;      // Exit the loop if ID is 0 or negative
                    }
                    System.out.print("Enter Customer Name: ");
                    String name = sc.next();
                    System.out.print("Enter Customer Age: ");
                    int age = sc.nextInt();
                    System.out.print("Enter Customer Address: ");
                    String address = sc.next();
                    // Set parameters for the prepared statement
                    pst.setInt(1, id);
                    pst.setString(2, name);
                    pst.setInt(3, age);
                    pst.setString(4, address);
                    pst.addBatch(); // Add the current record to the batch
                }
                int[] results = pst.executeBatch(); // Execute the batch to insert multiple records at once
                con.commit(); // Commit the transaction
                System.out.println("Inserted " + results.length + " records successfully.");
            } catch (SQLException e) {
                System.out.println("Error : " + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}

/*
 * The CustomerDetails class demonstrates how to insert multiple customer records into a MySQL database
 * using JDBC, PreparedStatement, and batch processing.
 *
 * Features:
 * - Connects to a MySQL database using JDBC.
 * - Accepts user input for customer details (ID, Name, Age, and Address).
 * - Uses PreparedStatement for secure and efficient database operations.
 * - Supports batch processing to insert multiple records in a single transaction.
 * - Allows users to exit the input loop by entering 0 or a negative value as the ID.
 *
 * Usage:
 * - Ensure the MySQL JDBC driver is added to the project's dependencies.
 * - Update the database URL, username, and password as per your database configuration.
 * - The database should have a `customer` table with the following schema:
 *
 *   Table: customer
 *   Columns: id (int), name (varchar), age (int), address (varchar)
 *
 * Note:
 * - Batch processing improves performance by reducing the number of database round-trips.
 * - Ensure proper exception handling and resource management.
 */