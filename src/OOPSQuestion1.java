import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OOPSQuestion1 {
    private static final int TIMER_DURATION = 60; // 60 seconds
    private static int timeLeft = TIMER_DURATION;
    private static JLabel timerLabel;
    private static Timer timer;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(OOPSQuestion1::createAndShowGUI);
    }

    public static void createAndShowGUI() {
        // Create and configure the frame
        JFrame frame = new JFrame("OOP Quiz - Question 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Create panel for question and options
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create question label
        JLabel questionLabel = new JLabel("What is inheritance in OOP?");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(questionLabel, BorderLayout.NORTH);

        // Create options panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));

        // Create option radio buttons
        JRadioButton option1 = new JRadioButton("A. The ability to create new classes from existing classes");
        JRadioButton option2 = new JRadioButton("B. The ability to access methods of one class from another class");
        JRadioButton option3 = new JRadioButton("C. The ability to override methods of a superclass");
        JRadioButton option4 = new JRadioButton("D. The ability to hide certain methods from other classes");

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
                // Call the method to display Question 2
                OOPSQuestion2.createAndShowGUI();
                // Stop the timer
                timer.stop();
            }
        });

        // Create panel for buttons
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(nextButton, BorderLayout.EAST);

        // Add button panel to the main panel
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Create and start the timer
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                if (timeLeft <= 0) {
                    timer.stop();
                    JOptionPane.showMessageDialog(frame, "Time's up!");
                    frame.dispose();
                    // Handle redirection or any other action after time's up
                } else {
                    timerLabel.setText("Time left: " + format(timeLeft / 60) + ":" + format(timeLeft % 60));
                }
            }
        });
        timer.start();

        // Add panel to the frame
        frame.add(panel);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);
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
