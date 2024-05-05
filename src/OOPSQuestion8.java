import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OOPSQuestion8 {
    private static final int TIMER_DURATION = 60; // 60 seconds
    private static int timeLeft = TIMER_DURATION;
    private static JLabel timerLabel;
    private static Timer timer;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(OOPSQuestion8::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        // Create and configure the frame
        JFrame frame = new JFrame("OOP Quiz - Question 8");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Create panel for question and options
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create question label
        JLabel questionLabel = new JLabel("What is a package in Java?");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(questionLabel, BorderLayout.NORTH);

        // Create options panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));

        // Create option radio buttons
        JRadioButton option1 = new JRadioButton("A. A folder used to organize classes and interfaces");
        JRadioButton option2 = new JRadioButton("B. A file that contains only constants");
        JRadioButton option3 = new JRadioButton("C. A file that contains only methods");
        JRadioButton option4 = new JRadioButton("D. A file that contains only variables");

        // Group the radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);
        group.add(option4);

        // Add components to the options panel
        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);
        optionsPanel.add(option4);

        panel.add(optionsPanel, BorderLayout.CENTER);

        // Create timer label
        timerLabel = new JLabel("Time left: " + format(TIMER_DURATION / 60) + ":" + format(TIMER_DURATION % 60));
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        // Create panel for timer
        JPanel timerPanel = new JPanel();
        timerPanel.add(timerLabel);

        // Add timer panel to the main panel
        panel.add(timerPanel, BorderLayout.WEST);

        // Create "Next" button
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Moving to next question...");
                // Close the current frame
                frame.dispose();
                // Call the method to display Question 9
                OOPSQuestion9.main(new String[]{});
                // Stop the timer
                timer.stop();
            }
        });

        // Add "Next" button to the panel
        panel.add(nextButton, BorderLayout.SOUTH);

        // Add panel to the frame
        frame.add(panel);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);

        // Create and start the timer
        timer = new Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                if (timeLeft <= 0) {
                    timer.stop();
                    JOptionPane.showMessageDialog(frame, "Time's up!");
                    frame.dispose();
                    // Call the method to display Question 9 when time's up
                    OOPSQuestion9.main(new String[]{});
                } else {
                    timerLabel.setText("Time left: " + format(timeLeft / 60) + ":" + format(timeLeft % 60));
                }
            }
        });
        timer.start();
    }

    // Format time as "MM:SS"
    private static String format(int i) {
        String result = String.valueOf(i);
        if (result.length() == 1) {
            result = "0" + result;
        }
        return result;
    }
}
