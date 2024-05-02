import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OOPSInstructions {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        // Create and configure the instruction frame for OOPS
        JFrame oopsFrame = new JFrame("Object-Oriented Programming Instructions");
        oopsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        oopsFrame.setSize(600, 500);

        // Create a panel for the OOPS instruction page
        JPanel oopsPanel = new JPanel();
        oopsPanel.setLayout(new BorderLayout());

        // Create components for the OOPS instruction page
        JLabel titleLabelOOPS = new JLabel("Object-Oriented Programming Quiz Instructions");
        titleLabelOOPS.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabelOOPS.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel rulesLabelOOPS = new JLabel("Rules:");
        JTextArea rulesTextAreaOOPS = new JTextArea();
        rulesTextAreaOOPS.setText("1. This quiz contains multiple-choice questions (MCQs).\n" +
                "2. Each question has one correct answer.\n" +
                "3. Select the correct option and click 'Submit' to proceed to the next question.");
        rulesTextAreaOOPS.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel marksLabelOOPS = new JLabel("Marks per Question: 5");
        JLabel numQuestionsLabelOOPS = new JLabel("Number of Questions: 10");

        JLabel optionsLabelOOPS = new JLabel("Options per Question: 4");

        JLabel additionalInfoLabelOOPS = new JLabel("Additional Information:");
        JTextArea additionalInfoTextAreaOOPS = new JTextArea();
        additionalInfoTextAreaOOPS.setText("1. You will have 10 minutes to complete the quiz.\n" +
                "2. Ensure that you understand the concepts thoroughly before attempting the quiz.\n" +
                "3. Good luck!");
        additionalInfoTextAreaOOPS.setFont(new Font("Arial", Font.PLAIN, 16));

        // Create a button for proceeding to the test
        JButton proceedButton = new JButton("Proceed to Test");
        proceedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the OOPSQuestion1 frame
                new OOPSQuestion1().createAndShowGUI();
                oopsFrame.dispose(); // Close the instructions window
            }
        });

        // Add components to the OOPS instruction panel
        oopsPanel.add(titleLabelOOPS, BorderLayout.NORTH);

        JPanel centerPanelOOPS = new JPanel();
        centerPanelOOPS.setLayout(new GridLayout(6, 1));
        centerPanelOOPS.add(rulesLabelOOPS);
        centerPanelOOPS.add(rulesTextAreaOOPS);
        centerPanelOOPS.add(marksLabelOOPS);
        centerPanelOOPS.add(numQuestionsLabelOOPS);
        centerPanelOOPS.add(optionsLabelOOPS);
        centerPanelOOPS.add(additionalInfoLabelOOPS);
        centerPanelOOPS.add(additionalInfoTextAreaOOPS);
        oopsPanel.add(centerPanelOOPS, BorderLayout.CENTER);

        // Add the Proceed to Test button to the OOPS instruction panel
        oopsPanel.add(proceedButton, BorderLayout.SOUTH);

        // Add the OOPS instruction panel to the frame
        oopsFrame.add(oopsPanel);

        //Center the frame on the screen
        oopsFrame.setLocationRelativeTo(null);

        // Make the frame visible
        oopsFrame.setVisible(true);

        // Adjust the size of the frame to fit the content
        oopsFrame.pack();
    }
}