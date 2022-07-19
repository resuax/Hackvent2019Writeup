import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics;
import java.io.File;

class BorderPanel extends JPanel
{
    BufferedImage backgroundSprite;
    public BorderPanel()
    {
        try
            {
                backgroundSprite = ImageIO.read(new File("redpixel.jpg"));
            }
        catch (IOException ioe)
            {
                System.err.println("no pictures for you!");
                ioe.printStackTrace(System.err);
            }
    }
    
    public void paintComponent(Graphics g)
    {
        int x, y;
        x = y = 0;
        g.drawImage(backgroundSprite, x, y, getWidth(), getHeight(), this);
    }
}
