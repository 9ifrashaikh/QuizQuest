import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage {
    public static void main(String[] args) {
        // Define colors
        Color backgroundColor = new Color(240, 248, 255); // Light blue background
        Color buttonColor = new Color(70, 130, 180); // Steel blue for buttons
        Color textColor = new Color(47, 79, 79); // Dark slate gray for text

        // Create and configure the login frame
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250); // Increased width for more space
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Create a panel for the login page with a background color
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBackground(backgroundColor); // Set background color
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create constraints for GridBagLayout
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        // Create components for the login page
        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(textColor); // Set text color
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        loginPanel.add(titleLabel, constraints);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameLabel.setForeground(textColor);
        JTextField usernameField = new JTextField();
        constraints.gridwidth = 2; // Span across two columns
        constraints.gridx = 0;
        constraints.gridy = 1;
        loginPanel.add(usernameLabel, constraints);
        constraints.gridy = 2;
        loginPanel.add(usernameField, constraints);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setForeground(textColor);
        JPasswordField passwordField = new JPasswordField();
        constraints.gridy = 3;
        loginPanel.add(passwordLabel, constraints);
        constraints.gridy = 4;
        loginPanel.add(passwordField, constraints);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(buttonColor); // Set button background color
        loginButton.setForeground(Color.WHITE); // Set button text color
        loginButton.setFocusPainted(false); // Remove button border
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        loginPanel.add(loginButton, constraints);

        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve data from fields
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Perform authentication logic (query database)
                try {
                    // Establish database connection
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "ifra@1234");

                    // Prepare SQL statement
                    String sql = "SELECT * FROM sys.logins WHERE user_name=? AND passw=?";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, username);
                    pstmt.setString(2, password);

                    // Execute SQL statement
                    ResultSet rs = pstmt.executeQuery();

                    // Check if user exists
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(frame, "Login successful!");

                        // Close the login frame
                        frame.dispose();

                        // Open the QuizDashboard
                        QuizDashboard.main(new String[]{username});
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

        // Add drop shadow effect to login panel
        loginPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        // Add the login panel to the frame
        frame.add(loginPanel);

        // Make the frame visible
        frame.setVisible(true);
    }
}
