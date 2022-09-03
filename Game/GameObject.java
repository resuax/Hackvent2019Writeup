import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

class GameObject
{
    public ArrayList<BufferedImage> animation = new ArrayList<BufferedImage>();
    BufferedImage picOfObject;
    public Integer x, y;
    int frameIndex = 0;
    int frameCount;
    int numberOfSpacesToMove = 1;

    public GameObject(String picFile, int _x, int _y)
    {
        picOfObject = null;
        try
            {
                picOfObject = ImageIO.read(new File(picFile));
            }
        catch (IOException ioe)
            {
                System.err.println("Pic could not be loaded.");
                ioe.printStackTrace(System.err);
                System.exit(-1);
            }
        x = _x;
        y = _y;
    }

    public void updateFrame()
    {
        int frameIndexIncrementCount = 1;
        frameIndex = (frameIndex + frameIndexIncrementCount) % frameCount;
    }

    public BufferedImage getImage()
    {
        return animation.get(frameIndex);
    }

    public void moveUp()
    {
        y -= numberOfSpacesToMove