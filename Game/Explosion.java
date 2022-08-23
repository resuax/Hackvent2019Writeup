import java.awt.image.BufferedImage;
import java.util.ArrayList;

class Explosion extends GameObject
{
    public boolean completedLoop = false;

    public Explosion (Integer _x, Integer _y)
    {
        super("explosion.png", _x, _y);

        int frameWidth = 39;
        int fra