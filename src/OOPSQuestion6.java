import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OOPSQuestion6 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        // Create and configure the frame
        JFrame frame = new JFrame("OOP Quiz - Question 6");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Create panel for question and options
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create question label
        JLabel questionLabel = new JLabel("What is a static method in Java?");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Create option radio buttons
        JRadioButton option1 = new JRadioButton("A. A method that is used to perform some specific task by providing necessary data");
        JRadioButton option2 = new JRadioButton("B. A method that is automatically called when an object is created");
        JRadioButton option3 = new JRadioButton("C. A method that belongs to the class rather than any specific instance");
        JRadioButton option4 = new JRadioButton("D. A method that is used to perform some specific task by providing necessary data and receiving a result");

        // Group the radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);
        group.add(option4);

        // Add components to the panel
        panel.add(questionLabel);
        panel.add(option1);
        panel.add(option2);
        panel.add(option3);
        panel.add(option4);

        // Create "Next" button
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Moving to next question...");
                // Close the current frame
                frame.dispose();
                // Call the method to display Question 7
                OOPSQuestion7.main(new String[]{});
            }
        });

        // Add "Next" button to the panel
        panel.add(nextButton);

        // Add panel to the frame
        frame.add(panel);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);
    }
}
