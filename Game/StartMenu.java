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
        howToPlayButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    controllerPanel.switchToHowToPlayPanel();
                }
            });

        immuneSystemFactsButton = new JButton("The Immune System!");
        immuneSystemFactsButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    controllerPanel.switchToImmunePanel();
                }
            });

        questionAnswerButton = new JButton("Get Question!");
        questionAnswerButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    controllerPanel.switchToQuestionsPanel();
                }
            });

        exitButton = new JButton("Exit!");
        exitButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    System.exit(0);
                }
            });

        add(titleBar, BorderLayout.NORTH);
        allTheButtons.add(playButton);
        allTheButtons.add(howToPlayButton);
        allTheButtons.add(immuneSystemFactsButton);
        allTheButtons.add(questionAnswerButton);
        allTheButtons.add(exitButton);
        add(allTheButtons, BorderLayout.CENTER);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int width = getWidth(); //windowWidth
        int height = getHeight(); //windowHeight
        final int ROWS = 8;
        final int COLUMNS = 8;

        for (int row = 0; row < ROWS; row++)
            {
                for (int column = 0; column < COLUMNS; column++)
                    {
                        // Makes a checkerboard pattern
                        if ((row % 2) == (column % 2))
                            {
                                g.setColor(color0);
                            }
                        else
                            {
                                g.setColor(color1);
                            }
                        //handles white gaps left by rounding on startmenu
                        int upperLeftCurrentX =  column * width/COLUMNS;
                        int upperLeftNextX = (column+1) * width/COLUMNS;
                        int upperLeftCurrentY = row * height / ROWS;
                        int upperLeftNextY = (row+1) * height / ROWS;
                        int rectangleWidth
                            = upperLeftNextX - upperLeftCurrentX;
                        int rectangleHeight
                            = upperLeftNextY - upperLeftCurrentY;

                        g.fillRect(column * width / COLUMNS,
                                   row * height / ROWS,
                                   rectangleWidth,
                                   rectangleHeight);
                    }
            }
    }

    public void switchColors()
    {
        Color temp = color0;
        color0 = color1;
        color1 = temp;
    }
}
