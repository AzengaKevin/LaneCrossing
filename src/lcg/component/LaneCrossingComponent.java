package lcg.component;

import lcg.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

public class LaneCrossingComponent extends JComponent {
    private Game game;

    /**
     * Tracks the width of the vertical rectangle and the horizontal rectangle
     */
    private double vRectWidth = 100;
    private double hRectHeight = 50;

    /**
     * Tracks the current position of the circles in the respective rectangle tunnels
     */
    private double yPosYTrajectory = 0;
    private double xPosXTrajectory = 0;

    private double x;
    private double distanceX;
    private double xSpeed = 1;

    /**
     * Chooses that starting default speed
     */

    private double factor;

    private double y;
    private double distanceY;
    private double ySpeed = 1;

    /**
     * Takes in the game variable to access the size of the window and the key input flags
     * @param game
     */
    public LaneCrossingComponent(Game game) {
        this.game = game;

        calculateDistanceAndSpeed();
    }

    /**
     * This method is called to initialized some variables and to optimize the speed so that the circle finish
     * at the same time irregardless of the size of the circle and the distance covered
     */
    private void calculateDistanceAndSpeed() {
        distanceY = game.getHeight() - vRectWidth;
        distanceX = game.getWidth() - hRectHeight;

        factor = Math.sqrt(game.getWidth() * game.getWidth() + game.getHeight() * game.getHeight());

        xSpeed = distanceX / factor;
        ySpeed = distanceY / factor;

        addMouseListener(new MouseListener() {

            /**
             * This method is called when the mose is clicked on any part of the component
             * @param e
             */

            @Override
            public void mouseClicked(MouseEvent e) {
                yPosYTrajectory = 0;
                xPosXTrajectory = 0;

                calculateDistanceAndSpeed();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    /**
     * The method that renders the component on the screen
     * <p>
     * The method is overriden from the super JComponent class
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(new Color(0xe0f7fa));

        g2d.fillRect(0, 0, getWidth(), getHeight());

        drawVerticalRect(g2d);
        drawHorizontalRect(g2d);

        updateSpeed();

        updateCirclePositions();

        drawCircles(g2d);

    }

    /**
     * Checks whether there is a key press and updates the speed of the balls accordingly
     */
    private void updateSpeed() {
        if (game.getKeyManager().isUpPressed()) {
            xSpeed *= .75;
            ySpeed *= .75;
        } else if (game.getKeyManager().isDownPressed()) {
            xSpeed *= 1.25;
            ySpeed *= 1.25;
        }
    }

    /**
     * Draws the circles in there respective tunnels using the passed Graphics2D instance
     *
     * @param g2d
     */
    private void drawCircles(Graphics2D g2d) {

        g2d.setColor(new Color(0xe57373));
        g2d.fill(new Ellipse2D.Double(x, yPosYTrajectory, vRectWidth, vRectWidth));

        g2d.setColor(new Color(0x81c784));
        g2d.fill(new Ellipse2D.Double(xPosXTrajectory, y, hRectHeight, hRectHeight));

    }

    /**
     * Updates position of the circles after every 20 Milliseconds
     */
    private void updateCirclePositions() {
        yPosYTrajectory += ySpeed;
        if (yPosYTrajectory >= (game.getHeight() - vRectWidth)) {
            ySpeed = 0;
            yPosYTrajectory = game.getHeight() - vRectWidth;
        }
        xPosXTrajectory += xSpeed;

        if (xPosXTrajectory >= game.getWidth() - hRectHeight) {
            xSpeed = 0;
            xPosXTrajectory = game.getWidth() - hRectHeight;
        }
    }

    /**
     * Draws the vertical rectangle with the passed Graphics2D instance
     * @param g2d
     */
    private void drawVerticalRect(Graphics2D g2d) {
        x = game.getWidth() / 2 - vRectWidth / 2;

        g2d.setColor(Color.WHITE);
        g2d.fillRect((int) x, 0, (int) vRectWidth, game.getHeight());
    }

    /**
     * Draws the horizontal rectangle after every 20 Milliseconds
     * @param g2d
     */
    private void drawHorizontalRect(Graphics2D g2d) {
        y = game.getHeight() / 2 - hRectHeight / 2;

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, (int) y, game.getWidth(), (int) hRectHeight);
    }
}
