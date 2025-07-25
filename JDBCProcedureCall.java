import java.sql.*;
import java.util.Scanner;

public class JDBCProcedureCall {
    // This class is used to call a stored procedure in a database using JDBC.
    public static void main(String[] args) {
        final String url = "jdbc:mysql://localhost:3306/appdb";
        final String user = "root";
        final String password = "root";
        String procedureCall = "{call sqr_cube(?, ?, ?)}"; // Define the stored procedure call
        try (Scanner sc = new Scanner(System.in)) {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Load MySQL JDBC Driver
            try (Connection con = DriverManager.getConnection(url, user, password);
                 CallableStatement cst = con.prepareCall(procedureCall)) {
                System.out.print("Enter a number : ");
                int num = sc.nextInt();
                cst.setInt(1, num); // Set the input parameter for the procedure
                cst.registerOutParameter(2, Types.INTEGER); // Register the first output parameter
                cst.registerOutParameter(3, Types.INTEGER); // Register the second output parameter
                cst.execute(); // Execute the stored procedure
                int square = cst.getInt(2); // Get the first output parameter (square)
                int cube = cst.getInt(3); // Get the second output parameter (cube)
                System.out.println("Square of " + num + " is: " + square);
                System.out.println("Cube of " + num + " is: " + cube);
            } catch (SQLException e) {
                System.out.println("Error : " + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}

/*
 * The JDBCProcedureCall class demonstrates how to call a stored procedure in a MySQL database using JDBC.
 *
 * Features:
 * - Connects to a MySQL database using JDBC.
 * - Uses CallableStatement to call a stored procedure.
 * - Accepts user input for the procedure's input parameter.
 * - Retrieves and displays the results returned by the stored procedure (e.g., square and cube of a number).
 *
 * Usage:
 * - Ensure the MySQL JDBC driver is added to the project's dependencies.
 * - Update the database URL, username, and password as per your database configuration.
 * - The database should have a stored procedure defined that matches the call in the code.
 *   Example:
 *   DELIMITER //
 *   CREATE PROCEDURE sqr_cube(IN input_number INT, OUT square INT, OUT cube INT)
 *   BEGIN
 *       SET square = input_number * input_number;
 *       SET cube = input_number * input_number * input_number;
 *   END //
 *   DELIMITER ;
 *
 * Notes:
 * - CallableStatement is used for executing stored procedures, which can handle both input and output parameters.
 * - Ensure the stored procedure exists in the database before running the program.
 */
