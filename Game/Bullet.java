import java.awt.image.BufferedImage;
import java.util.ArrayList;

class Bullet extends GameObject
{

    public Bullet(Integer _x)
    {
        super("bullets.png", _x, 9);

        int frameWidth = 24;
        int frameHeight = 25;
        int xOffset = 0;
        int yOffset = 0;
        frameCount = 4;
        for (int ii = 0; ii < frameCount; ii++)
            {
                BufferedImage frame
                    = picOfObject.getSubimage(ii * frameWidth + xOffset,
                                              yOffset,
                                              frameWidth, frameHeight);
                animation.add(frame);
            }
    }
}
