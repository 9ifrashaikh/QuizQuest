import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DBMSInstructions {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        // Create and configure the instruction frame for DBMS
        JFrame dbmsFrame = new JFrame("Database Management System Instructions");
        dbmsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dbmsFrame.setSize(600, 400);

        // Create a panel for the DBMS instruction page
        JPanel dbmsPanel = new JPanel();
        dbmsPanel.setLayout(new BorderLayout());

        // Create components for the DBMS instruction page
        JLabel titleLabelDBMS = new JLabel("Database Management System Quiz Instructions");
        titleLabelDBMS.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabelDBMS.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel rulesLabelDBMS = new JLabel("Rules:");
        JTextArea rulesTextAreaDBMS = new JTextArea();
        rulesTextAreaDBMS.setText("1. This quiz contains multiple-choice questions (MCQs).\n" +
                "2. Each question has one correct answer.\n" +
                "3. Select the correct option and click 'Submit' to proceed to the next question.");

        JLabel marksLabelDBMS = new JLabel("Marks per Question: 5");
        JLabel numQuestionsLabelDBMS = new JLabel("Number of Questions: 10");

        JLabel optionsLabelDBMS = new JLabel("Options per Question: 4");

        JLabel additionalInfoLabelDBMS = new JLabel("Additional Information:");
        JTextArea additionalInfoTextAreaDBMS = new JTextArea();
        additionalInfoTextAreaDBMS.setText("1. You will have 10 minutes to complete the quiz.\n" +
                "2. Ensure that you understand the concepts thoroughly before attempting the quiz.\n" +
                "3. Good luck!");

        // Create a button for proceeding to the test
        JButton proceedButton = new JButton("Proceed to Test");
        proceedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the DBMSQuestion1 frame
                DBMSQuestion1.createAndShowGUI();
                dbmsFrame.dispose(); // Close the instructions window
            }
        });

        // Add components to the DBMS instruction panel
        dbmsPanel.add(titleLabelDBMS, BorderLayout.NORTH);

        JPanel centerPanelDBMS = new JPanel();
        centerPanelDBMS.setLayout(new GridLayout(6, 1));
        centerPanelDBMS.add(rulesLabelDBMS);
        centerPanelDBMS.add(rulesTextAreaDBMS);
        centerPanelDBMS.add(marksLabelDBMS);
        centerPanelDBMS.add(numQuestionsLabelDBMS);
        centerPanelDBMS.add(optionsLabelDBMS);
        centerPanelDBMS.add(additionalInfoLabelDBMS);
        centerPanelDBMS.add(additionalInfoTextAreaDBMS);
        dbmsPanel.add(centerPanelDBMS, BorderLayout.CENTER);

        // Add the Proceed to Test button to the DBMS instruction panel
        dbmsPanel.add(proceedButton, BorderLayout.SOUTH);

        // Add the DBMS instruction panel to the frame
        dbmsFrame.add(dbmsPanel);

        //Center the frame on the screen
        dbmsFrame.setLocationRelativeTo(null);

        // Make the frame visible
        dbmsFrame.setVisible(true);
    }
}
