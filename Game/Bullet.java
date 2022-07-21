import java.awt.image.BufferedImage;
import java.util.ArrayList;

class Bullet extends GameObject
{

    public Bullet(Integer _x)
    {
        super("bullets.png", _x, 9);

        int frameWidth = 24;
        int frameHeight = 25;