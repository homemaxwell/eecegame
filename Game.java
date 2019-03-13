import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable

    
{
    private Display display;
    private int width, height;
    public String title;

    private Thread thread;
    private boolean running = false;

    private BufferStrategy bs;
    private Graphics g;

    //Where the states are
    private State gameState;
    private State menuState;
    //private State settingsState;

    //Input
    private KeyManager keyManager;

    //Camera
    private GameCamera gameCamera;

    //handler
    private Handler handler;

    public Game(String title, int width, int height)
    {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
    }

    public void init()
    {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        //settingsState = new SettingState(keyManager);

        State.setState(gameState);
    }


    private void tick()
    {
        keyManager.tick();

        if(State.getState() != null)
            State.getState().tick();
    }

    private void render()
    {
        bs = display.getCanvas().getBufferStrategy(); //gets the canvas from Display class, basically frame rate
        if(bs == null)
        {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics(); //graphics object allows us to draw things to the canvas
        //Clear Screen
        g.clearRect(0, 0, width, height);
        //Draw Here

        if(State.getState() != null)
            State.getState().render(g);

        //End Drawing!
        bs.show();
        g.dispose();
    }

    public void run()
    {
        init();

        int fps = 144;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running)
        {
            now  = System.nanoTime();
            delta += (now - lastTime) / timePerTick; //determines amount of time between each frame
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000)
            {
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop(); //stops in case while loop does not stop running


    }

    public KeyManager getKeyManager()
    {
        return keyManager;
    }

    public GameCamera getGameCamera()
    {
        return gameCamera;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public synchronized void start()
    {
        if(running) //this is in case start() is accidentally called again
            return;
        running = true;
        thread = new Thread(this);
        thread.start(); //this will call the run() method
    }

    public synchronized void stop()
    {
        if(!running) //this is in case stop() is accidentally called again
            return;
        running = false;
        try {
            thread.join(); //close down thread
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
