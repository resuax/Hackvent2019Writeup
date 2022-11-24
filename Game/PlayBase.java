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
        int bottom, right, left, top;
        bottom = right = left = top = 40;
        borderPanel.setBorder(new EmptyBorder(top,left,bottom,right));
        borderPanel.setLayout(new BorderLayout());
        playPanel = new PlayPanel(controllerPanel, world);
        borderPanel.add(playPanel, BorderLayout.CENTER);
        int playX, playY;
        playX = playY = 0;
        borderPanel.setBounds(playX, playY, 
                              world.pixelWidth, world.pixelHeight);
        add(borderPanel, new Integer(base));

        pausePanel = new PausePanel(controllerPanel);
        int pauseX, pauseY, pauseWid