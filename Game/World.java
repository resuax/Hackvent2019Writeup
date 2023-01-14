
import java.util.ArrayList;

class World
{
    public final ArrayList<Enemy> enemies;
    public final ArrayList<Bullet> bullets;
    public final ArrayList<Explosion> explosions;
    public final Player player;
    public Boolean pause;
    public int numberOfErrors;
    public int maxNumberOfErrors;
    public int columns, rows;
    public int pixelHeight, pixelWidth;

    public World (ArrayList<Enemy> _enemies,
                  ArrayList<Bullet> _bullets,
                  ArrayList<Explosion> _explosions,
                  Boolean _pause,
                  int _maxNumberOfErrors,
                  int _pixelHeight,
                  int _pixelWidth)
    {
        enemies = _enemies;
        bullets = _bullets;
        explosions = _explosions;
        columns = rows = 10;
        player = new Player(this);