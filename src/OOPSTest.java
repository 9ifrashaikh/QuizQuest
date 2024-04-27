import javax.swing.*;

public class OOPSTest extends JFrame {
    public OOPSTest() {
        // Initialize the frame
        setTitle("OOPS Test"); // Set the title of the frame
        setSize(800, 600); // Set the size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation

        // Add components and logic for the test here
        // For example, you can add questions, options, and buttons here
        // You can also add action listeners to the buttons for submitting answers, etc.
    }

    public static void main(String[] args) {
        // Create an instance of OOPSTest and set it visible
        OOPSTest testFrame = new OOPSTest();
        testFrame.setVisible(true);
    }
}
