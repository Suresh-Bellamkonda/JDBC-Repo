import java.sql.*;
import java.util.Scanner;

public class JDBCFunctionCall {
    //This program demonstrates how to use CallableStatement to call a function in a database.
    public static void main(String[] args) {
        final String url = "jdbc:mysql://localhost:3306/appdb";
        final String user = "root";
        final String password = "root";
        try (Scanner sc = new Scanner(System.in)) {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Load MySQL JDBC Driver
            try (Connection con = DriverManager.getConnection(url, user, password);
                 CallableStatement cst = con.prepareCall("{? = cal fun(?)}")) {
                System.out.print("Enter a number : ");
                int num = sc.nextInt();
                cst.registerOutParameter(1, Types.INTEGER); // Register the output parameter
                cst.setInt(2, num); // Set the input parameter
                try (ResultSet rs = cst.executeQuery()) {
                    if (rs.next()) {
                        int result = rs.getInt(1); // Get the output from the function
                        System.out.println("The result is: " + result);
                    } else {
                        System.out.println("No result returned from the function.");
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error : " + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}

/*
 * The JDBCFunctionCall class demonstrates how to use a CallableStatement to call a function in a MySQL database.
 *
 * Features:
 * - Connects to a MySQL database using JDBC.
 * - Uses CallableStatement to call a stored function.
 * - Accepts user input for the function's input parameter.
 * - Retrieves and displays the result returned by the stored function.
 *
 * Usage:
 * - Ensure the MySQL JDBC driver is added to the project's dependencies.
 * - Update the database URL, username, and password as per your database configuration.
 * - The database should have a stored function defined that matches the call in the code.
 *   Example:
 *   DELIMITER //
 *   CREATE FUNCTION fun(input_number INT) RETURNS INT
 *   BEGIN
 *       RETURN input_number * 2; -- Example logic
 *   END //
 *   DELIMITER ;
 */