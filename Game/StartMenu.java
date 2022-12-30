import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

class StartMenu extends JPanel
{
    JLabel titleBar;
    JButton playButton;
    JButton howToPlayButton;
    JButton immuneSystemFactsButton;
    JButton questionAnswerButton;
    JButton exitButton;
    Color color0, color1;

    public StartMenu(final ControllerPanel controllerPanel)
    {
        setLayout(new BorderLayout());
        int startMenuTop, startMenuLeft, startMenuBottom, startMenuRight;
        startMenuTop = 60;
        startMenuLeft = startMenuRight = 30;
        startMenuBottom = 50;
        setBorder(new EmptyBorder(startMenuTop, startMenuLeft,
                                  startMenuBottom, startMenuRight));
        color0 = Color.BLUE;
        color1 = Color.BLUE.darker();

        JPanel allTheButtons = new JPanel();
        int rows = 5;
        int columns = 1;
        int horizontalGap = 0;
        int verticalGap = 30;
        GridLayout startMenuGridLayout
            = new GridLayout(rows, columns, horizontalGap, verticalGap);
        allTheButtons.setLayout(startMenuGridLayout);
        allTheButtons.setOpaque(false);
        int buttonTop, buttonLeft, buttonBottom, buttonRight;
        buttonTop = buttonBottom = 30;
        buttonLeft = buttonRight = 100;
        allTheButtons.setBorder(new EmptyBorder(buttonTop, buttonLeft,
                                                buttonBottom, buttonRight));

        titleBar = new JLabel("ANATOMY ARCADE", SwingConstants.CENTER);
        int fontSize = 50;
        titleBar.setFont(new Font("Arial", Font.BOLD, fontSize));
        titleBar.setForeground(Color.white);

        playButton = new JButton("Play!");
        playButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    controllerPanel.switchToPlayPanel();
                }
            });

        howToPlayButton = new JButton("How to Play!");
        howToPlayButton.addActionListener(new ActionListen