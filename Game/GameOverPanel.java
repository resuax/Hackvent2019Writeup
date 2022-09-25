import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.GridLayout;

class GameOverPanel extends JPanel
{
    public GameOverPanel(final ControllerPanel controllerPanel)
    {
        setBackground(Color.BLUE);
        int rows = 4;
        int columns = 1;
        setLayout(new GridLayout(rows, columns));
        add(new JLabel("Game Over!", SwingConstants.CENTER));
        add(new MainMenuButton(controllerPanel));
        add(new PlayButton(controllerPanel));
        add(new ExitButton(controllerPanel));
    }
}
