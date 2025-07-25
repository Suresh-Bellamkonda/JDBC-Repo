import java.sql.*;
import java.util.Scanner;

public class StudentDetailsFilling {
    // This class is used to insert Student details into the database using JDBC.
    public static void main(String[] args) {
        final String url = "jdbc:mysql://localhost:3306/appdb";
        final String user = "root";
        final String password = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
        }
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement();     //Using Statement to execute SQL queries
             Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.print("Enter ID(or 0 to exit) : ");
                int id = sc.nextInt();
                sc.nextLine(); // Consume the newline character
                if (id <= 0) break;
                System.out.print("Enter Name : ");
                String name = sc.nextLine();
                System.out.print("Enter mobile number : ");
                long mobile = sc.nextLong();
                if (name.isEmpty()) {
                    System.out.println("Name cannot be empty. Please try again.");
                    continue;
                }
                stmt.executeUpdate("INSERT INTO student (id, name, mobile) VALUES (" + id + ", '" + name + "', " + mobile + ")");
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}

/**
 * The StudentDetailsFilling class is used to insert student details into a MySQL database using JDBC.
 * It provides a simple console-based interface for users to input student information.
 *
 * Features:
 * - Connects to a MySQL database using JDBC.
 * - Accepts user input for student details (ID, Name, and Mobile Number).
 * - Inserts the provided details into the `student` table in the database.
 * - Allows users to exit the input loop by entering 0 as the ID.
 *
 * Usage:
 * - Ensure the MySQL JDBC driver is added to the project's dependencies.
 * - Update the database URL, username, and password as per your database configuration.
 * - The database should have a `student` table with the following schema:
 *
 *   Table: student
 *   Columns: id (int), name (varchar), mobile (bigint)
 */