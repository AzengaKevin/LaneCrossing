package lcg.display;

import javax.swing.*;

public class Display {

    private JFrame frame;

    /**
     * Gets and initializes the title, height and width of the frame
     *
     * @param title
     * @param width
     * @param height
     */
    public Display(String title, int width, int height) {
        createFrame(title, width, height);
    }

    /**
     * Creates the instance of the frame
     * @param title
     * @param width
     * @param height
     */
    private void createFrame(String title, int width, int height) {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Returns the frame instance
     * @return
     */
    public JFrame getFrame() {
        return this.frame;
    }
}
