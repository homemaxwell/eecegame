import javax.swing.*;
import java.awt.*;

public class Display
{
    private JFrame frame;
    private Canvas canvas;

    private String title;
    private int width, height;

    public Display(String title, int width, int height)
    {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    public void createDisplay()
    {
        frame = new JFrame(title);
        frame.setSize(width, height); //sets the size of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //allows window to close correctly
        frame.setResizable(false); //will make it so the window is stuck to set width and height
        frame.setLocationRelativeTo(null); //will make the window show up in the center of the screen
        frame.setVisible(true);

        canvas = new Canvas(); //instantiate the canvas
        canvas.setPreferredSize(new Dimension(width, height)); //sets the size of the canvas
        canvas.setMaximumSize(new Dimension(width, height)); //makes sure the maximum is the same size
        canvas.setMinimumSize(new Dimension(width, height)); //makes sure the minimum is the same size
        canvas.setFocusable(false); //allows the application to focus

        frame.add(canvas); //adds canvas to the frame
        frame.pack(); //makes the canvas fit on the JFrame

    }

    public Canvas getCanvas()
    {
        return canvas;
    }

    public JFrame getFrame()
    {
        return frame;
    }

}
