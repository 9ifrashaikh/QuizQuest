import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DSQuestion1 {
    private static JFrame frame;
    private static JPanel panel;
    private static ButtonGroup optionGroup;
    private static int totalQuestions;
    private static int correctMarks;

    public static void createAndShowGUI() {
        totalQuestions = 10; // Total number of questions for DS
        correctMarks = 5; // Correct marks per question

        frame = new JFrame("DS Quiz - Question 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel questionLabel = new JLabel("Question: The primary purpose of a Data Structures (DS) is to:");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));

        JRadioButton option1 = new JRadioButton("A. Manage large amounts of text documents");
        JRadioButton option2 = new JRadioButton("B. Organize, store, and manipulate data in a structured way");
        JRadioButton option3 = new JRadioButton("C. Securely browse the internet");
        JRadioButton option4 = new JRadioButton("D. Perform complex mathematical calculations");

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
                int[] selectedOptions = getSelectedOptions();
                int score = calculateScore(selectedOptions);

                // Store the score in the database
                storeScore("DS", score);

                JOptionPane.showMessageDialog(frame, "Your score: " + score + "/" + (totalQuestions * correctMarks));

                frame.dispose();
            }
        });

        panel.add(finishButton, BorderLayout.SOUTH);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static int[] getSelectedOptions() {
        int[] selectedOptions = new int[4];
        selectedOptions[0] = optionGroup.getElements().nextElement().isSelected() ? 0 : -1;
        selectedOptions[1] = optionGroup.getElements().nextElement().isSelected() ? 1 : -1;
        selectedOptions[2] = optionGroup.getElements().nextElement().isSelected() ? 2 : -1;
        selectedOptions[3] = optionGroup.getElements().nextElement().isSelected() ? 3 : -1;
        return selectedOptions;
    }

    private static int calculateScore(int[] selectedOptions) {
        int score = 0;
        for (int option : selectedOptions) {
            if (option == 2) { // Correct option is B
                score += correctMarks;
                break;
            }
        }
        return score;
    }

    private static void storeScore(String subject, int score) {
        // Implement the database storage logic here
        // For simplicity, we're not implementing actual database storage in this example
        System.out.println("Score stored in the database for subject " + subject + ": " + score);
    }
}