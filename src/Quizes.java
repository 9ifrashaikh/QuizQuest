import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Quizes {
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

        // Create a panel for subject options
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(2, 2, 10, 10));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create buttons for subject options
        JButton oopsButton = new JButton("OOPS");
        JButton dbmsButton = new JButton("DBMS");
        JButton iotButton = new JButton("IOT");
        JButton dsButton = new JButton("DS");

        // Add action listeners to the buttons
        oopsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Redirect to OOPS instructions page
                OOPSInstructions.main(null);
                frame.dispose(); // Close the current frame
            }
        });

        dbmsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Redirect to OOPS instructions page
                DBMSInstructions.main(null);
                frame.dispose(); // Close the current frame
            }
        });

        iotButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Redirect to OOPS instructions page
                IOTInstructions.main(null);
                frame.dispose(); // Close the current frame
            }
        });

        dsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Redirect to OOPS instructions page
                DSInstructions.main(null);
                frame.dispose(); // Close the current frame
            }
        });

        // Add buttons to the options panel
        optionsPanel.add(oopsButton);
        optionsPanel.add(dbmsButton);
        optionsPanel.add(iotButton);
        optionsPanel.add(dsButton);

        // Add components to the dashboard panel
        dashboardPanel.add(welcomeLabel, BorderLayout.NORTH);
        dashboardPanel.add(optionsPanel, BorderLayout.CENTER);

        // Add the dashboard panel to the frame
        frame.add(dashboardPanel);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);
    }
}
