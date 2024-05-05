import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Enumeration;

public class OOPSQuestion9 {
    private static final int[] CORRECT_OPTIONS = {0, 2, 3, 0, 0, 3, 0, 0, 0};
    private static JFrame frame;
    private static JPanel panel;
    private static ButtonGroup optionGroup;
    private static String username;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                username = JOptionPane.showInputDialog("Enter your username:");
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        frame = new JFrame("OOP Quiz - Question 9");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel questionLabel = new JLabel("Question: What is the main objective of OOP?");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));

        JRadioButton option1 = new JRadioButton("A. To simplify complex systems by modeling them as objects");
        JRadioButton option2 = new JRadioButton("B. To make programming languages more efficient");
        JRadioButton option3 = new JRadioButton("C. To increase code complexity");
        JRadioButton option4 = new JRadioButton("D. To reduce code reusability");

        optionGroup = new ButtonGroup();
        optionGroup.add(option1);
        optionGroup.add(option2);
        optionGroup.add(option3);
        optionGroup.add(option4);

        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);
        optionsPanel.add(option4);

        panel.add(optionsPanel, BorderLayout.CENTER);

        JButton finishButton = new JButton("Finish");
        finishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int totalScore = 0;
                for (int i = 0; i < CORRECT_OPTIONS.length; i++) {
                    int selectedOption = getSelectedOption(optionGroup);
                    totalScore += calculateScore(selectedOption, i);
                }
                storeScore("OOPS", totalScore, username);
                JOptionPane.showMessageDialog(frame, "Your total score: " + totalScore + "/9");
                JButton showCorrectOptionsButton = new JButton("Show Correct Options");
                showCorrectOptionsButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        displayCorrectOptions();
                    }
                });
                panel.remove(finishButton);
                panel.add(showCorrectOptionsButton, BorderLayout.SOUTH);
                frame.revalidate();
            }
        });

        panel.add(finishButton, BorderLayout.SOUTH);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static int getSelectedOption(ButtonGroup group) {
        Enumeration<AbstractButton> buttons = group.getElements();
        int i = 0;
        while (buttons.hasMoreElements()) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return i;
            }
            i++;
        }
        return -1; // Return -1 if no option is selected
    }

    private static int calculateScore(int selectedOption, int questionNumber) {
        int score = 0;
        if (selectedOption == CORRECT_OPTIONS[questionNumber]) {
            score = 1;
        }
        return score;
    }

    private static void displayCorrectOptions() {
        StringBuilder correctOptions = new StringBuilder("<html><body>");
        for (int i = 0; i < CORRECT_OPTIONS.length; i++) {
            char correctOption = (char) ('A' + CORRECT_OPTIONS[i]);
            correctOptions.append(i + 1).append(". ");
            switch (i) {
                case 0:
                    correctOptions.append("What is the main objective of OOP? ");
                    break;
                case 1:
                    correctOptions.append("What is inheritance in OOP? ");
                    break;
                case 2:
                    correctOptions.append("What is polymorphism in OOP? ");
                    break;
                case 3:
                    correctOptions.append("What is abstraction in OOP? ");
                    break;
                case 4:
                    correctOptions.append("What is a constructor in Java? ");
                    break;
                case 5:
                    correctOptions.append("What is a static method in Java? ");
                    break;
                case 6:
                    correctOptions.append("What is an interface in Java? ");
                    break;
                case 7:
                    correctOptions.append("What is a package in Java? ");
                    break;
                case 8:
                    correctOptions.append("What is the main objective of OOP? ");
                    break;
            }
            correctOptions.append("<br>&nbsp;&nbsp;&nbsp;Answer: ").append(correctOption).append(". ");
            switch (i) {
                case 0:
                    correctOptions.append("To simplify complex systems by modeling them as objects");
                    break;
                case 1:
                    correctOptions.append("The ability to create new classes from existing classes");
                    break;
                case 2:
                    correctOptions.append("The ability of an object to take on many forms");
                    break;
                case 3:
                    correctOptions.append("The ability to hide certain methods from other classes");
                    break;
                case 4:
                    correctOptions.append("A method that is automatically called when an object is created");
                    break;
                case 5:
                    correctOptions.append("A method that belongs to the class rather than any specific instance");
                    break;
                case 6:
                    correctOptions.append("A collection of abstract methods and constants");
                    break;
                case 7:
                    correctOptions.append("A folder used to organize classes and interfaces");
                    break;
                case 8:
                    correctOptions.append("To simplify complex systems by modeling them as objects");
                    break;
            }
            correctOptions.append("<br><br>");
        }
        correctOptions.append("</body></html>");

        JOptionPane.showMessageDialog(frame, correctOptions.toString(), "Correct Options", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void storeScore(String subject, int score, String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "clover07");
            String insertQuery = "INSERT INTO scores (user_name, subject, score, test_date) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, subject);
            preparedStatement.setInt(3, score);
            preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
            System.out.println("Score stored in the database!");
        } catch (SQLException ex) {
            System.out.println("Failed to store score in the database: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Enumeration;

public class OOPSQuestion9 {
    private static final int[] CORRECT_OPTIONS = {0, 0, 1, 0, 3, 1, 2, 0, 0};
    private static JFrame frame;
    private static JPanel panel;
    private static ButtonGroup optionGroup;
    private static String username;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                username = JOptionPane.showInputDialog("Enter your username:");
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        frame = new JFrame("OOP Quiz - Question 9");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel questionLabel = new JLabel("Question: What is the main objective of OOP?");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));

        JRadioButton option1 = new JRadioButton("A. To simplify complex systems by modeling them as objects");
        JRadioButton option2 = new JRadioButton("B. To make programming languages more efficient");
        JRadioButton option3 = new JRadioButton("C. To increase code complexity");
        JRadioButton option4 = new JRadioButton("D. To reduce code reusability");

        optionGroup = new ButtonGroup();
        optionGroup.add(option1);
        optionGroup.add(option2);
        optionGroup.add(option3);
        optionGroup.add(option4);

        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);
        optionsPanel.add(option4);

        panel.add(optionsPanel, BorderLayout.CENTER);

        JButton finishButton = new JButton("Finish");
        finishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int totalScore = 0;
                for (int i = 0; i < CORRECT_OPTIONS.length; i++) {
                    int selectedOption = getSelectedOption(optionGroup);
                    totalScore += calculateScore(selectedOption, i);
                }
                storeScore("OOPS", totalScore, username);
                JOptionPane.showMessageDialog(frame, "Your total score: " + totalScore + "/9");
                JButton showCorrectOptionsButton = new JButton("Show Correct Options");
                showCorrectOptionsButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        displayCorrectOptions();
                    }
                });
                panel.remove(finishButton);
                panel.add(showCorrectOptionsButton, BorderLayout.SOUTH);
                frame.revalidate();
            }
        });

        panel.add(finishButton, BorderLayout.SOUTH);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static int getSelectedOption(ButtonGroup group) {
        Enumeration<AbstractButton> buttons = group.getElements();
        int i = 0;
        while (buttons.hasMoreElements()) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return i;
            }
            i++;
        }
        return -1; // Return -1 if no option is selected
    }

    private static int calculateScore(int selectedOption, int questionNumber) {
        int score = 0;
        if (selectedOption == CORRECT_OPTIONS[questionNumber]) {
            score = 1;
        }
        return score;
    }

    private static void displayCorrectOptions() {
        StringBuilder correctOptions = new StringBuilder("<html><body>");
        for (int i = 0; i < CORRECT_OPTIONS.length; i++) {
            char correctOption = (char) ('A' + CORRECT_OPTIONS[i]);
            correctOptions.append(i + 1).append(". ");
            switch (i) {
                case 0:
                    correctOptions.append("What is the main objective of OOP? ");
                    break;
                case 1:
                    correctOptions.append("What is inheritance in OOP? ");
                    break;
                case 2:
                    correctOptions.append("What is polymorphism in OOP? ");
                    break;
                case 3:
                    correctOptions.append("What is abstraction in OOP? ");
                    break;
                case 4:
                    correctOptions.append("What is a constructor in Java? ");
                    break;
                case 5:
                    correctOptions.append("What is a static method in Java? ");
                    break;
                case 6:
                    correctOptions.append("What is an interface in Java? ");
                    break;
                case 7:
                    correctOptions.append("What is a package in Java? ");
                    break;
                case 8:
                    correctOptions.append("What is the main objective of OOP? ");
                    break;
            }
            correctOptions.append("<br>&nbsp;&nbsp;&nbsp;Answer: ").append(correctOption).append(". ");
            switch (i) {
                case 0:
                    correctOptions.append("To simplify complex systems by modeling them as objects");
                    break;
                case 1:
                    correctOptions.append("The ability to create new classes from existing classes");
                    break;
                case 2:
                    correctOptions.append("The ability of an object to take on many forms");
                    break;
                case 3:
                    correctOptions.append("The ability to hide certain methods from other classes");
                    break;
                case 4:
                    correctOptions.append("A method that is automatically called when an object is created");
                    break;
                case 5:
                    correctOptions.append("A method that belongs to the class rather than any specific instance");
                    break;
                case 6:
                    correctOptions.append("A collection of abstract methods and constants");
                    break;
                case 7:
                    correctOptions.append("A folder used to organize classes and interfaces");
                    break;
                case 8:
                    correctOptions.append("To simplify complex systems by modeling them as objects");
                    break;
            }
            correctOptions.append("<br><br>");
        }
        correctOptions.append("</body></html>");

        JOptionPane.showMessageDialog(frame, correctOptions.toString(), "Correct Options", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void storeScore(String subject, int score, String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "clover07");
            String insertQuery = "INSERT INTO scores (user_name, subject, score, test_date) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, subject);
            preparedStatement.setInt(3, score);
            preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
            System.out.println("Score stored in the database!");
        } catch (SQLException ex) {
            System.out.println("Failed to store score in the database: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

*/