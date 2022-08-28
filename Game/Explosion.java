import java.awt.image.BufferedImage;
import java.util.ArrayList;

class Explosion extends GameObject
{
    public boolean completedLoop = false;

    public Explosion (Integer _x, Integer _y)
    {
        super("explosion.png", _x, _y);

        int frameWidth = 39;
        int frameHeight = 39;
        int xOffset = 0;
        int yOffset = frameHeight * 3;
        frameCount = 13;
        for (int ii = 0; ii < frameCount; ii++)
            {
                BufferedImage frame
                    = picOfObject.getSubimage(ii * frameWidth + xOffset,
                                              yOffset,
                                              frameWidth, frameHeight);
                animation.add(frame);
            }
    }

    public void updateFrame()
    {
        int frameIndexIncrementCount = 1;
        int initialFrame = 0;
        frameIndex = (frameIndex + frameIndexIncrementCount) % frameCount;
        if (frameIndex == initialFrame)
            {
                completedLoop = true;
            }
    }
}
