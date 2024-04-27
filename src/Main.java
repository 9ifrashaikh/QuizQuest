import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/sys"; // Update with your database URL
        String username = "root"; // Update with your username
        String password = "ifra@1234"; // Update with your password

        System.out.println("Attempting to establish connection...");

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");

           
            connection.close();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1045) {
                System.out.println("Authentication failed! Check your username and password.");
            } else {
                System.out.println("Failed to connect to the database!");
                e.printStackTrace();
            }
        }