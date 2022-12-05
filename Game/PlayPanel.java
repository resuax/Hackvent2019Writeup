
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