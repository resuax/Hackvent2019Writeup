import javax.swing.JLayeredPane;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

class PlayBase extends JLayeredPane
{
    PlayPanel playPanel;
    PausePanel pausePanel;
    NumberOfErrorsPanel errorsPanel;
    JPanel borderPanel;
    int background = 0;
    int base = 1;
    int foreground = 2;

    public PlayBase(final ControllerPanel controllerPanel, final  World world)
    {
        borderPanel = new BorderPanel();
        int