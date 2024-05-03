import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DSInstructions {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        // Create and configure the instruction frame for DS
        JFrame dsFrame = new JFrame("Data Structures Quiz Instructions");
        dsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dsFrame.setSize(600, 400);

        // Create a panel for the DS instruction page
        JPanel dsPanel = new JPanel();
        dsPanel.setLayout(new BorderLayout());

        // Create components for the DS instruction page
        JLabel titleLabelDS = new JLabel("Data Structures Quiz Instructions");
        titleLabelDS.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabelDS.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel rulesLabelDS = new JLabel("Rules:");
        JTextArea rulesTextAreaDS = new JTextArea();
        rulesTextAreaDS.setText("1. This quiz contains multiple-choice questions (MCQs).\n" +
                "2. Each question has one correct answer.\n" +
                "3. Select the correct option and click 'Submit' to proceed to the next question.");

        JLabel marksLabelDS = new JLabel("Marks per Question: 5");
        JLabel numQuestionsLabelDS = new JLabel("Number of Questions: 10");

        JLabel optionsLabelDS = new JLabel("Options per Question: 4");

        JLabel additionalInfoLabelDS = new JLabel("Additional Information:");
        JTextArea additionalInfoTextAreaDS = new JTextArea();
        additionalInfoTextAreaDS.setText("1. You will have 10 minutes to complete the quiz.\n" +
                "2. Ensure that you understand the concepts thoroughly before attempting the quiz.\n" +
                "3. Good luck!");

        // Create a button for proceeding to the test
        JButton proceedButton = new JButton("Proceed to Test");
        proceedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the DSQuestion1 frame
                new DSQuestion1().createAndShowGUI();
                dsFrame.dispose(); // Close the instructions window
            }
        });

        // Add components to the DS instruction panel
        dsPanel.add(titleLabelDS, BorderLayout.NORTH);

        JPanel centerPanelDS = new JPanel();
        centerPanelDS.setLayout(new GridLayout(6, 1));
        centerPanelDS.add(rulesLabelDS);
        centerPanelDS.add(rulesTextAreaDS);
        centerPanelDS.add(marksLabelDS);
        centerPanelDS.add(numQuestionsLabelDS);
        centerPanelDS.add(optionsLabelDS);
        centerPanelDS.add(additionalInfoLabelDS);
        centerPanelDS.add(additionalInfoTextAreaDS);
        dsPanel.add(centerPanelDS, BorderLayout.CENTER);

        // Add the Proceed to Test button to the DS instruction panel
        dsPanel.add(proceedButton, BorderLayout.SOUTH);

        // Add the DS instruction panel to the frame
        dsFrame.add(dsPanel);

        //Center the frame on the screen
        dsFrame.setLocationRelativeTo(null);

        // Make the frame visible
        dsFrame.setVisible(true);
    }
}