import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean up, down, left, right;

    public KeyManager()
    {
        keys = new boolean[256];
    }

    public void tick()
    {
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
    }

    public void keyPressed(KeyEvent e) //this checks what keys are pressed
    {
        keys[e.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent e) //this checks what keys are released
    {
        keys[e.getKeyCode()] = false;
    }

    public void keyTyped(KeyEvent e)
    {

    }
}

