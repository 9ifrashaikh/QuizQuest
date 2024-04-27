import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class OOPSQuestion9 {
    private static final int[] CORRECT_OPTIONS = {0, 2, 3, 2, 2, 3, 0, 0, 0};
    private static JFrame frame;
    private static JPanel panel;
    private static ButtonGroup optionGroup;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        frame = new JFrame("OOP Quiz - Question 9");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel questionLabel = new JLabel("Question: What is the main objective of OOP?");
        panel.add(questionLabel);

        JRadioButton option1 = new JRadioButton("To simplify complex systems by modeling them as objects");
        JRadioButton option2 = new JRadioButton("To make programming languages more efficient");
        JRadioButton option3 = new JRadioButton("To increase code complexity");
        JRadioButton option4 = new JRadioButton("To reduce code reusability");

        optionGroup = new ButtonGroup();
        optionGroup.add(option1);
        optionGroup.add(option2);
        optionGroup.add(option3);
        optionGroup.add(option4);

        panel.add(option1);
        panel.add(option2);
        panel.add(option3);
        panel.add(option4);

        JButton finishButton = new JButton("Finish");
        finishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedOption = getSelectedOption();

                int score = calculateScore(selectedOption);

                storeScore("OOPS", score);

                JOptionPane.showMessageDialog(frame, "Your score: " + score + "/9");

                frame.dispose();
            }
        });

        panel.add(finishButton);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static int getSelectedOption() {
        for (int i = 0; i < CORRECT_OPTIONS.length; i++) {
            if (optionGroup.getElements().nextElement().isSelected()) {
                return i;
            }
        }
        return -1;
    }

    private static int calculateScore(int selectedOption) {
        int score = 0;
        if (selectedOption == CORRECT_OPTIONS[8]) {
            score++;
        }
        return score;
    }

    private static void storeScore(String subject, int score) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "ifra@1234");
            String insertQuery = "INSERT INTO scores (user_name, subject, score, test_date) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, "ifras");
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
