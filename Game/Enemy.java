import java.awt.image.BufferedImage;
import java.util.ArrayList;

class Enemy extends GameObject
{
    static int speciesCount = 6;

    public Enemy(int column)
    {
        super("germs.gif", column, 0);

        int fram