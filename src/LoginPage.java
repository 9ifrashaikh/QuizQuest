import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage {
    public static void main(String[] args) {
        // Create and configure the login frame
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create a panel for the login page
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(6, 2, 10, 10));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create components for the login page
        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JTextField idField = new JTextField();
        JTextField fullNameField = new JTextField();
        JTextField usernameField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        // Add components to the login panel
        loginPanel.add(titleLabel);

        loginPanel.add(new JLabel("ID:"));
        loginPanel.add(idField);

        loginPanel.add(new JLabel("Full Name:"));
        loginPanel.add(fullNameField);

        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);

        loginPanel.add(new JLabel("Email:"));
        loginPanel.add(emailField);

        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);

        loginPanel.add(loginButton);

        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve data from fields
                int id = Integer.parseInt(idField.getText());
                String fullName = fullNameField.getText();
                String username = usernameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Perform authentication logic (query database)
                try {
                    // Establish database connection
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "ifra@1234");

                    // Prepare SQL statement
                    String sql = "SELECT * FROM sys.quiz WHERE ID=? AND full_name=? AND user_name=? AND g_mail=? AND passwd=?";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, id);
                    pstmt.setString(2, fullName);
                    pstmt.setString(3, username);
                    pstmt.setString(4, email);
                    pstmt.setString(5, password);

                    // Execute SQL statement
                    ResultSet rs = pstmt.executeQuery();

                    // Check if user exists
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(frame, "Login successful!");

                        // Close the login frame
                        frame.dispose();

                        // Open the QuizDashboard
                        QuizDashboard.main(new String[]{});
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid credentials");
                    }

                    // Close the connection, statement, and result set
                    rs.close();
                    pstmt.close();
                    conn.close();
                } catch (SQLException ex) {
                    // Show error message if authentication fails
                    JOptionPane.showMessageDialog(frame, "Error logging in: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        // Add the login panel to the frame
        frame.add(loginPanel);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);
    }
}

