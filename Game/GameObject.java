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

    