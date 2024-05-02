import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegistrationPage {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(240, 240, 240));

        JPanel registrationPanel = new JPanel(new GridBagLayout());
        registrationPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        registrationPanel.setBackground(new Color(240, 240, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel titleLabel = new JLabel("Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(70, 130, 180));
        registrationPanel.add(titleLabel, gbc);

        JTextField fullNameField = new JTextField(20);
        JTextField usernameField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(70, 130, 180));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));

        gbc.gridy++;
        registrationPanel.add(createFieldWithLabel("Full Name:", fullNameField), gbc);

        gbc.gridy++;
        registrationPanel.add(createFieldWithLabel("Username:", usernameField), gbc);

        gbc.gridy++;
        registrationPanel.add(createFieldWithLabel("Email:", emailField), gbc);

        gbc.gridy++;
        registrationPanel.add(createFieldWithLabel("Password:", passwordField), gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 1;
        registrationPanel.add(registerButton, gbc);

        frame.add(registrationPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve data from fields
                String fullName = fullNameField.getText();
                String username = usernameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Perform registration logic (insert into database)
                try {
                    // Establish database connection
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "clover07");

                    // Prepare SQL statement
                    String sql = "INSERT INTO sys.quiz (full_name, user_name, g_mail, passwd) VALUES (?, ?, ?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    pstmt.setString(1, fullName);
                    pstmt.setString(2, username);
                    pstmt.setString(3, email);
                    pstmt.setString(4, password);

                    // Execute SQL statement
                    int rowsAffected = pstmt.executeUpdate();

                    // Check if registration was successful
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(frame, "Registration successful!");

                        // Retrieve the auto-generated ID
                        ResultSet generatedKeys = pstmt.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            int registrationID = generatedKeys.getInt(1);
                            JOptionPane.showMessageDialog(frame, "Your Registration ID is: " + registrationID);
                        }

                        // Close the registration frame
                        frame.dispose();
                        // Open the QuizDashboard
                        // QuizDashboard.main(new String[]{});
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
    }

    private static JPanel createFieldWithLabel(String labelText, JComponent component) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JLabel label = new JLabel(labelText);
        label.setForeground(new Color(70, 130, 180));
        label.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(label);
        panel.add(component);
        panel.setBackground(new Color(240, 240, 240));
        return panel;
    }
}
