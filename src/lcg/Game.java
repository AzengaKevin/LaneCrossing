package lcg;

import lcg.component.LaneCrossingComponent;
import lcg.display.Display;
import lcg.input.KeyManager;

import java.awt.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Game {

    private ScheduledThreadPoolExecutor executor;

    private String title;
    private int width;
    private int height;

    //Display
    private Display display;

    //Inputs
    private KeyManager keyManager;

    //Components
    private LaneCrossingComponent lcc;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        display = new Display(title, width, height);
        keyManager = new KeyManager();

        lcc = new LaneCrossingComponent(this);
        lcc.addKeyListener(keyManager);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().add(lcc, BorderLayout.CENTER);

        mainLoop();
    }

    private void mainLoop() {

        executor = new ScheduledThreadPoolExecutor(5);

        executor.scheduleAtFixedRate(new RepaintBoard(this), 0L, 20L, TimeUnit.MILLISECONDS);

    }

    public Display getDisplay(){
        return  this.display;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

class RepaintBoard implements Runnable{

    private Game game;

    public RepaintBoard(Game game){
        this.game = game;
    }

    @Override
    public void run() {
        this.game.getDisplay().getFrame().repaint();
    }
}
