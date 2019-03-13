import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature
{
    //Animations
    private Animation animRight;
    private Animation animLeft;

    public Player(Handler handler, float x, float y)
    {
        super(handler,x,y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 4;
        bounds.y = 2;
        bounds.width = 28;
        bounds.height = 126;

        //Animations
        animRight = new Animation(50, Assets.player_right);
        animLeft = new Animation(50, Assets.player_left);
    }

    public void tick()
    {
        animeRight.tick();
        animeLeft.tick();
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }

    private void getInput()
    {
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up)
            yMove = -direction;
        if(handler.getKeyManager().down)
            yMove = direction;
        if(handler.getKeyManager().left)
            xMove = -direction;
        if(handler.getKeyManager().right)
            xMove = direction;
    }

    public void render(Graphics g)
    {
        g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);

        //g.setColor(Color.red);
        /**.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
                bounds.width, bounds.height);
         */
    }

    private BufferedImage getCurrentAnimationFrame()
    {
        if(xMove < 0) //moving left animation
        {
            return animLeft.getCurrentFrames();
        }
        else if(xMove > 0) //moving right animation
        {
            return animRight.getCurrentFrames();
        }
        else if(yMove < 0) //jumping while moving right animation
        {
            return Assets.player_jump[0];
        }
        else if(yMove < 0) //jumping while moving left animation
        {
            return Assets.player_jump[1];
        }
        else if(xMove == 0 && yMove == 0) //standing still animation
        {
            return Assets.player_right[0];
        }
        return Assets.player_left[0];
    }
}
