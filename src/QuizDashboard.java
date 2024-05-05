/*
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
}*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;




public class QuizDashboard {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Username not provided.");
            System.exit(1);
        }
        String username = args[0];

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
                // Connect to the database
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "clover07")) {
                    // Execute the query to fetch scores for the logged-in user
                    String query = "SELECT * FROM sys.scores WHERE user_name = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, username); // Use the username fetched from the login
                    ResultSet resultSet = preparedStatement.executeQuery();

                    // Create a table to display scores
                    JTable table = new JTable(buildTableModel(resultSet));

                    // Show the table in a scrollable dialog
                    JOptionPane.showMessageDialog(frame, new JScrollPane(table), "View Scores", JOptionPane.PLAIN_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Failed to fetch scores: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
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

    public static DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();

        // Get column names
        int columnCount = metaData.getColumnCount();
        Vector<String> columnNames = new Vector<>();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // Get data rows
        Vector<Vector<Object>> data = new Vector<>();
        while (resultSet.next()) {
            Vector<Object> row = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                row.add(resultSet.getObject(columnIndex));
            }
            data.add(row);
        }

        return new DefaultTableModel(data, columnNames);
    }
}