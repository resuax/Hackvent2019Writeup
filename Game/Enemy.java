import java.awt.image.BufferedImage;
import java.util.ArrayList;

class Enemy extends GameObject
{
    static int speciesCount = 6;

    public Enemy(int column)
    {
        super("germs.gif", column, 0);

        int frameWidth = 40;
        int frameHeight = 60;
        int xOffset = 80;
        int colorPicker = (int)(Math.r