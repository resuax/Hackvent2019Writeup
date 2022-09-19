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
        y -= numberOfSpacesToMove;
    }

    public void moveDown()
    {
        y += numberOfSpacesToMove;
    }

    public void moveRight()
    {
        x += numberOfSpacesToMove;
        if (x > 9)
            {
                x = 0;
            }
    }

    public void moveLeft()
    {
        //Note: Modulo in Java (with negative numbers) is different
        //Ex: -15 % 10 == -5
        //Ex: -1 % 10 == -1
        x -= numberOfSpacesToMove;
        if (x < 0)
            {
                x = 9;
            }
    }

    private int getWidth()
    {
        return getImage().getWidth();
    }
    private int getHeight()
    {
        return getImage().getHeight();
    }
    public int getXAlignment(int width)
    {
        return (width - getWidth())/2;
    }
    public int getYAlignment(int height)
    {
        return (height - getHeight())/2;
    }
}
