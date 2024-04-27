import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizDashboard {
    public static void main(String[] args) {
        // Create and configure the main frame
        JFrame frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create a panel for the dashboard
        JPanel dashboardPanel = new JPanel();
        dashboardPanel.setLayout(new BorderLayout());

        // Create a welcome message label
        JLabel welcomeLabel = new JLabel("Welcome to Quiz Application");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create a navigation panel with buttons
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new GridLayout(4, 1, 10, 10));
        navPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton takeQuizButton = new JButton("Take Quiz");
        JButton viewScoresButton = new JButton("View Scores");
        JButton accountSettingsButton = new JButton("Account Settings");
        JButton logoutButton = new JButton("Logout");

        // Add action listeners to the buttons
        takeQuizButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the dashboard frame
                frame.dispose();

                // Open the Quizzes frame
                Quizes.main(new String[]{});
            }
        });

        viewScoresButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Redirecting to View Scores page...");
            }
        });

        accountSettingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Redirecting to Account Settings page...");
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Logging out...");
                // Add code to handle logout functionality here
            }
        });

        // Add buttons to the navigation panel
        navPanel.add(takeQuizButton);
        navPanel.add(viewScoresButton);
        navPanel.add(accountSettingsButton);
        navPanel.add(logoutButton);

        // Add components to the dashboard panel
        dashboardPanel.add(welcomeLabel, BorderLayout.NORTH);
        dashboardPanel.add(navPanel, BorderLayout.CENTER);

        // Add the dashboard panel to the frame
        frame.add(dashboardPanel);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);
    }
}
