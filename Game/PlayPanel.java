
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.Graphics;

class PlayPanel extends JPanel
{
    final World world;
    final Player playah;
    final ArrayList<Enemy> enemies;
    final ArrayList<Bullet> bullets;
    final ArrayList<Explosion> explosions;
    Boolean pause;

    public PlayPanel(final ControllerPanel controllerPanel,
                     final World _world)
    {
        world = _world;
        playah = world.player;
        enemies = world.enemies;
        bullets = world.bullets;
        explosions = world.explosions;
        pause = world.pause;
        setOpaque(false);
    }

    public void paintComponent(Graphics g)
    {
        if (pause)
            {
                System.out.println("game is paused");
            }
        int playahX = playah.getXAlignment(getWidth()/world.columns);
        int playahY = playah.getYAlignment(getHeight()/world.rows);
        g.drawImage(playah.getImage(),
                    gridToPixelX(playah.x, getWidth()) + playahX,
                    gridToPixelY(playah.y, getHeight()) + playahY,
                    this);

         for (Enemy enemy : enemies)
            {
                int enemyX = enemy.getXAlignment(getWidth()/world.columns);
                int enemyY = enemy.getYAlignment(getHeight()/world.rows);
                g.drawImage(enemy.getImage(),
                            gridToPixelX(enemy.x, getWidth()) + enemyX,
                            gridToPixelY(enemy.y, getHeight()) + enemyY,
                            this);
            }

        for (Bullet bullet : bullets)
            { 
                int bulletX = bullet.getXAlignment(getWidth()/world.columns);
                int bulletY = bullet.getYAlignment(getHeight()/world.rows);
                g.drawImage(bullet.getImage(),
                            gridToPixelX(bullet.x, getWidth()) + bulletX,
                            gridToPixelY(bullet.y, getHeight()) + bulletY,
                            this);
            }

        for (Explosion explosion : explosions)
            {
                int explosionX 
                    = explosion.getXAlignment(getWidth()/world.columns);
                int explosionY
                    = explosion.getYAlignment(getHeight()/world.rows);
                g.drawImage(explosion.getImage(),
                            gridToPixelX(explosion.x, getWidth()) 
                            + explosionX,
                            gridToPixelY(explosion.y, getHeight()) 
                            + explosionY,
                            this);
            }
    }

    public Integer gridToPixelX(Integer gridX, Integer width)
    {
        return gridX * (width/world.columns);
    }

    public Integer gridToPixelY(Integer gridY, Integer height)
    {
        return gridY * (height/world.rows);
    }
}