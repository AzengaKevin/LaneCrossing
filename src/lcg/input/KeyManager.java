package lcg.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keyPressed;

    private boolean upPressed, downPressed;

    public KeyManager() {
        keyPressed = new boolean[255];

        upPressed = false;
        downPressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Changes the flag at the Respective KeyCode to true
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        keyPressed[e.getKeyCode()] = true;
        updateFlags();
    }

    /**
     * Changes the flag at the respective code to false when the key is released
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {

        keyPressed[e.getKeyCode()] = false;

        updateFlags();

    }

    private void updateFlags(){
        upPressed = keyPressed[KeyEvent.VK_UP];
        downPressed = keyPressed[KeyEvent.VK_DOWN];
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isUpPressed() {
        return upPressed;
    }
}
