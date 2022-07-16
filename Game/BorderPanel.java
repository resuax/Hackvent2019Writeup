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
                System.