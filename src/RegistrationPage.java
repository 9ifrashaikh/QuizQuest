import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegistrationPage {
    public static void main(String[] args) {
        // Create and configure the registration frame
        JFrame frame = new JFrame("Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create a panel for the registration page
        JPanel registrationPanel = new JPanel();
        registrationPanel.setLayout(new GridLayout(6, 2, 10, 10));
        registrationPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create components for the registration page
        JLabel titleLabel = new JLabel("Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JTextField idField = new JTextField();
        JTextField fullNameField = new JTextField();
        JTextField usernameField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton registerButton = new JButton("Register");

        // Add components to the registration panel
        registrationPanel.add(titleLabel);

        registrationPanel.add(new JLabel("ID:"));
        registrationPanel.add(idField);

        registrationPanel.add(new JLabel("Full Name:"));
        registrationPanel.add(fullNameField);

        registrationPanel.add(new JLabel("Username:"));
        registrationPanel.add(usernameField);

        registrationPanel.add(new JLabel("Email:"));
        registrationPanel.add(emailField);

        registrationPanel.add(new JLabel("Password:"));
        registrationPanel.add(passwordField);

        registrationPanel.add(registerButton);

        // Add action listener to the register button
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve data from fields
                int id = Integer.parseInt(idField.getText());
                String fullName = fullNameField.getText();
                String username = usernameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Perform registration logic (insert into database)
                try {
                    // Establish database connection
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "ifra@1234");

                    // Prepare SQL statement
                    String sql = "INSERT INTO sys.quiz (ID, full_name, user_name, g_mail, passwd) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, id);
                    pstmt.setString(2, fullName);
                    pstmt.setString(3, username);
                    pstmt.setString(4, email);
                    pstmt.setString(5, password);

                    // Execute SQL statement
                    int rowsAffected = pstmt.executeUpdate();

                    // Check if registration was successful
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(frame, "Registration successful!");

                        // Close the registration frame
                        frame.dispose();

                        // Open the QuizDashboard
                        QuizDashboard.main(new String[]{});
                    } else {
                        JOptionPane.showMessageDialog(frame, "Registration failed");
                    }

                    // Close the connection and statement
                    pstmt.close();
                    conn.close();
                } catch (SQLException ex) {
                    // Show error message if registration fails
                    JOptionPane.showMessageDialog(frame, "Error registering user: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        // Add the registration panel to the frame
        frame.add(registrationPanel);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);
    }
}
