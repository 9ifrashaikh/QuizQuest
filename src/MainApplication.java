import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainApplication {
    public static void main(String[] args) {
        // Create and configure the main frame
        JFrame frame = new JFrame("Welcome to Your Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create a panel for the landing page
        JPanel landingPanel = new JPanel();
        landingPanel.setLayout(new BorderLayout());
        landingPanel.setBackground(new Color(240, 240, 240)); // Set background color

        // Create a title label with custom font and alignment
        JLabel titleLabel = new JLabel("Welcome to Your Application");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create buttons with custom styles
        JButton loginButton = createStyledButton("Login", Color.decode("#4CAF50"));
        JButton registerButton = createStyledButton("Register", Color.decode("#2196F3"));

        // Add action listeners to the buttons
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the login page
                LoginPage.main(new String[]{});
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the registration page
                RegistrationPage.main(new String[]{});
            }
        });

        // Add components to the landing panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        landingPanel.add(titleLabel, BorderLayout.NORTH);
        landingPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add the landing panel to the frame
        frame.add(landingPanel);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);
    }

    // Method to create styled buttons with hover effects
    private static JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });

        return button;
    }
}
