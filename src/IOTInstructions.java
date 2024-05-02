import javax.swing.*;
import java.awt.*;

public class IOTInstructions {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        // Create and configure the instruction frame for IOT
        JFrame iotFrame = new JFrame("Internet of Things Quiz Instructions");
        iotFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iotFrame.setSize(600, 400);

        // Create a panel for the IOT instruction page
        JPanel iotPanel = new JPanel();
        iotPanel.setLayout(new BorderLayout());

        // Create components for the IOT instruction page
        JLabel titleLabelIOT = new JLabel("Internet of Things Quiz Instructions");
        titleLabelIOT.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabelIOT.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel rulesLabelIOT = new JLabel("Rules:");
        JTextArea rulesTextAreaIOT = new JTextArea();
        rulesTextAreaIOT.setText("1. This quiz contains multiple-choice questions (MCQs).\n" +
                "2. Each question has one correct answer.\n" +
                "3. Select the correct option and click 'Submit' to proceed to the next question.");

        JLabel marksLabelIOT = new JLabel("Marks per Question: 5");
        JLabel numQuestionsLabelIOT = new JLabel("Number of Questions: 10");

        JLabel optionsLabelIOT = new JLabel("Options per Question: 4");

        JLabel additionalInfoLabelIOT = new JLabel("Additional Information:");
        JTextArea additionalInfoTextAreaIOT = new JTextArea();
        additionalInfoTextAreaIOT.setText("1. You will have 10 minutes to complete the quiz.\n" +
                "2. Ensure that you understand the concepts thoroughly before attempting the quiz.\n" +
                "3. Good luck!");

        // Add components to the IOT instruction panel
        iotPanel.add(titleLabelIOT, BorderLayout.NORTH);

        JPanel centerPanelIOT = new JPanel();
        centerPanelIOT.setLayout(new GridLayout(6, 1));
        centerPanelIOT.add(rulesLabelIOT);
        centerPanelIOT.add(rulesTextAreaIOT);
        centerPanelIOT.add(marksLabelIOT);
        centerPanelIOT.add(numQuestionsLabelIOT);
        centerPanelIOT.add(optionsLabelIOT);
        centerPanelIOT.add(additionalInfoLabelIOT);
        centerPanelIOT.add(additionalInfoTextAreaIOT);
        iotPanel.add(centerPanelIOT, BorderLayout.CENTER);

        // Add the IOT instruction panel to the frame
        iotFrame.add(iotPanel);

        // Center the frame on the screen
        iotFrame.setLocationRelativeTo(null);

        // Make the frame visible
        iotFrame.setVisible(true);
    }
}
