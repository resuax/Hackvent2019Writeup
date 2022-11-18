
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

class PausePanel extends JPanel
{
    public PausePanel(final ControllerPanel controllerPanel)
    {
        setBackground(Color.ORANGE);
        int rows = 4;
        int columns = 1;
        setLayout(new GridLayout(rows, columns));
        add(new JLabel("Game Paused!", SwingConstants.CENTER));
        add(new MainMenuButton(controllerPanel));
        add(new ResumeButton(controllerPanel));
        add(new ExitButton(controllerPanel));
    }
}