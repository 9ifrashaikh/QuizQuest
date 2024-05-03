import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import java.util.Enumeration;

public class OOPSQuestion9 {
    private static final int[] CORRECT_OPTIONS = {0, 2, 3, 0, 0, 3, 0, 0, 0};
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


        optionGroup = new ButtonGroup();  // Use optionGroup1 for question 1
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
                // Calculate score for each question
                int selectedOption1 = getSelectedOption(optionGroup);
                int selectedOption2 = getSelectedOption(optionGroup);
                int selectedOption3 = getSelectedOption(optionGroup);
                int selectedOption4 = getSelectedOption(optionGroup);
                int selectedOption5 = getSelectedOption(optionGroup);
                int selectedOption6 = getSelectedOption(optionGroup);
                int selectedOption7 = getSelectedOption(optionGroup);
                int selectedOption8 = getSelectedOption(optionGroup);
                int selectedOption9 = getSelectedOption(optionGroup);


                // Repeat for other questions...

                int scoreForQuestion1 = calculateScore(selectedOption1, 0);
                int scoreForQuestion2 = calculateScore(selectedOption2, 1);
                int scoreForQuestion3 = calculateScore(selectedOption3, 2);
                int scoreForQuestion4 = calculateScore(selectedOption4, 3);
                int scoreForQuestion5 = calculateScore(selectedOption5, 4);
                int scoreForQuestion6 = calculateScore(selectedOption6, 5);
                int scoreForQuestion7 = calculateScore(selectedOption7, 6);
                int scoreForQuestion8 = calculateScore(selectedOption8, 7);
                int scoreForQuestion9 = calculateScore(selectedOption9, 8);

                // Repeat for other questions...

                // Calculate total score
                int totalScore = scoreForQuestion1 + scoreForQuestion2 +scoreForQuestion3+scoreForQuestion4+scoreForQuestion5+scoreForQuestion6+scoreForQuestion7+scoreForQuestion8+scoreForQuestion9; /* Add scores for other questions */;

                // Store total score or display it as needed
                storeScore("OOPS", totalScore);

                JOptionPane.showMessageDialog(frame, "Your total score: " + totalScore + "/9");

                frame.dispose();
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
        System.out.println("Selected option for question " + (questionNumber + 1) + ": " + selectedOption);
        System.out.println("Correct option for question " + (questionNumber + 1) + ": " + CORRECT_OPTIONS[questionNumber]);
        if (selectedOption == CORRECT_OPTIONS[questionNumber]) {
            score++;
        }
        return score;
    }



    //int selectedOption = getSelectedOption(); // Get the selected option
   // int questionNumber = 8; // Assuming question number 9 (indexed from 0)
    //int score = calculateScore(selectedOption, questionNumber); // Calculate the score

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