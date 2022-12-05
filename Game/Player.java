
import java.awt.image.BufferedImage;
import java.util.ArrayList;

class Player extends GameObject
{
    public Player(World world)
    {
        super("wbloodcells.png",
              (int)(Math.random() * world.columns), world.rows-1);
        // Add different pics of user for animation effect.
        int xOffset = 94;
        int yOffset = 0;
        int frameWidth = 94;
        int frameHeight = 93;
        frameCount = 4;
        for (int ii = 0; ii < frameCount; ii++)
            {
                BufferedImage frame
                    = picOfObject.getSubimage(ii * frameWidth+xOffset, yOffset,
                                              frameWidth, frameHeight);
                animation.add(frame);
            }
    }
}