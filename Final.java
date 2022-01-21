/* Ananth Penghat
   April 14, 2014
   Final.java
   This program will create a space invaders type game
   based on the Immune System.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;
import java.io.*;           
import javax.imageio.*;
import java.awt.image.*;  

public class Final
{
    JFrame frame;               // Holds everything in game.
    CardLayout cards;           // CardLayout for main panels.
    CardLayout welcomeLayout;   // CardLayout for welcoming user to game.

    TitlePanel titlePanel;  // Contains main menu and instructions panels.
    Timer createGerms;      // Places germs on game board.
    Timer moveGerms;        // Moves germs down on board.
    
    boolean pauseBoolean = false;   // Determines whether to listen to keys or not when paused
    
    Font font;      // Font that will be used for welcoming panels.
    
    File instructionsFile;
    Scanner input;
    String instructionsString;
    
    public Final()
    {
        // Read in text that teaches user how to play.
        instructionsFile = new File("instructions.txt");
        try
        {
            input = new Scanner(instructionsFile);
        }
        catch (FileNotFoundException fnfe)
        {
            System.err.println("ERROR: Cannot open text file");
            System.exit(1);
        }
        while (input.hasNext())
        {
            instructionsString = input.nextLine();
        }
        input.close();

    }
    
    public static void main (String [] args)
    {
        Final f = new Final();
        f.run();
    }

    public void run()
    {
        // Initialize JFrame.
        frame = new JFrame("Final");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setSize(800, 600);    // 800x600 pixels.
        frame.setLocation(40, 40);  // JFrame appears here on screen.

        titlePanel = new TitlePanel();  // Initialize panel for main menu.

        cards = new CardLayout();   // Initialize main CardLayout.
        frame.setLayout(cards);

        // Add JPanels to frame.
        frame.getContentPane().add(titlePanel, "Title Panel");

        frame.setResizable(false);  // JFrame set at 800x600.
        frame.setVisible(true);     // JFrame is visible on screen.
        frame.setFocusable(true);
    }

    class TitlePanel extends JPanel
    {
        CheckerBoard menuPanel, infoPanel, immunePanel;  // Menu screen and instructions.
        WBCGamePanel wbc;

        public TitlePanel()
        {
            // Initialize CardLayout and set titleMenu to CardLayout.
            welcomeLayout = new CardLayout();   
            this.setLayout(welcomeLayout);  

            // Initialize JPanels and set Layout to 'null'.
            menuPanel = new CheckerBoard();     
            menuPanel.setLayout(null);
            
            infoPanel = new CheckerBoard();
            infoPanel.setLayout(null);
            
            immunePanel = new CheckerBoard();
            immunePanel.setLayout(null);
            
            wbc = new WBCGamePanel();
            wbc.setLayout(null);

            // Add JPanels to CardLayout 'welcomeLayout'.
            this.add(menuPanel, "Main Menu");
            this.add(infoPanel, "Instructions");
            this.add(immunePanel, "Immune System");
            this.add(wbc, "WBC Mini-Game");

            font = new Font("Arial", Font.BOLD, 20);
            
            // Game title JLabels.
            JLabel anatomyLabel = new JLabel("ANATOMY");
            JLabel arcadeLabel = new JLabel("ARCADE");
            anatomyLabel.setFont(new Font("Arial", Font.BOLD, 50));
            arcadeLabel.setFont(new Font("Arial", Font.BOLD, 50));
            anatomyLabel.setForeground(Color.white);
            arcadeLabel.setForeground(Color.white);
            anatomyLabel.setBounds(275, 35, 270, 50);
            arcadeLabel.setBounds(295, 60, 220, 105);
            menuPanel.add(anatomyLabel);
            menuPanel.add(arcadeLabel);
            
            // Goes to panel where user plays the game.
            JButton playButton = new JButton("Play!");
            playButton.setFont(font);
            playButton.addActionListener(new ButtonPlayListener());
            playButton.setBounds(305, 180, 188, 60);
            menuPanel.add(playButton);
            
            // Goes to panel on how to play.
            JButton infoButton = new JButton("How to Play!");
            infoButton.setFont(font);
            infoButton.addActionListener(new ButtonInfoListener());
            infoButton.setBounds(305, 260, 188, 60);
            menuPanel.add(infoButton);
            
            // Returns to Main Menu panel from Info panel.
            JButton infoReturnButton = new JButton("Return to Main Menu");
            infoReturnButton.setFont(font);
            infoReturnButton.addActionListener(new ButtonMainListener());
            infoReturnButton.setBounds(0, 0, 800, 30);
            infoPanel.add(infoReturnButton);

            JTextArea helpTextArea = new JTextArea(instructionsString);
            helpTextArea.setEditable(false);
            helpTextArea.setLineWrap(true);
            helpTextArea.setWrapStyleWord(true);
            helpTextArea.setFont(new Font("Arial", Font.BOLD, 20));
            helpTextArea.setForeground(Color.white);    // Red default.
            helpTextArea.setOpaque(false);
            helpTextArea.setBounds(200, 35, 600, 75);
            infoPanel.add(helpTextArea);
            
            // Goes to panel about the Immune System
            JButton immuneSystemButton = new JButton("Immune System!");
            immuneSystemButton.setFont(new Font("Arial", Font.BOLD, 18));
            immuneSystemButton.addActionListener(new ButtonImmuneListener());
            immuneSystemButton.setBounds(305, 340, 188, 60);
            menuPanel.add(immuneSystemButton);
            
            // Returns to Main Menu panel from Immune System Panel.
            JButton immuneReturnButton = new JButton("Return to Main Menu");
            immuneReturnButton.setFont(font);
            immuneReturnButton.addActionListener(new ButtonMainListener());
            immuneReturnButton.setBounds(0, 0, 800, 30);
            immunePanel.add(immuneReturnButton);

            // Closes the game when clicked.
            JButton quitButton = new JButton("Exit!");
            quitButton.setFont(font);
            quitButton.addActionListener(new ButtonQuitListener());
            quitButton.setBounds(305, 420, 188, 60);
            menuPanel.add(quitButton);
        }
    }

    class CheckerBoard extends JPanel
    {       
        int ii, jj;         // Used in 'for loop' to draw checkerboard.
        int x, y;           // Coordinates to draw checkerboard.
        int colorCounter;   // Determines which color to use for checkerboard.
        Color red, myRed;   // Colors used to draw checkerboard.

        // Animates the checkerboard design (alternates colors).
        Animate animate;    
        Timer checkerboardTimer;

        private class Animate implements ActionListener  // Animates checkerboard.
        {
            public void actionPerformed(ActionEvent e)
            {   
                // Switch colors depending on 'colorCounter' value.
                if (colorCounter % 2 == 0)
                {
                    red = Color.red;
                    myRed = red.darker();
                }
                else
                {
                    myRed = Color.red;
                    red = myRed.darker();
                }
                // Increment and repaint checkerboard.
                colorCounter++;
                repaint();
            }
        }

        public CheckerBoard()
        {
            colorCounter = 0;   
            // Initialize and start animation of checkerboard.
            animate = new Animate();
            checkerboardTimer = new Timer(500, animate);
            checkerboardTimer.start();
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            // Draw checkerboard pattern.
            for (ii = 0; ii < 8; ii++)
            {
                for (jj = 0; jj < 8; jj++)
                {
                    x = ii * 100;   // Convert xCord to pixels.
                    y = jj * 73;    // Convert yCord to pixels.

                    // Set the colors of the checkerboard.
                    if ((ii % 2) == (jj % 2))
                    {
                        g.setColor(myRed);
                    }
                    else
                    {
                        g.setColor(red);
                    }
                    g.fillRect(x, y, 100, 73);  // Draw rectangle.
                }
            }
            ii = jj = 0;    // Reset values so 'for loop' can rerun.
        }
    }

    class WBCGamePanel extends JPanel implements KeyListener
    {
        ArrayList<GameObject> objectCells;
        ArrayList<GameObject> objectOrangeGerms;
        ArrayList<GameObject> objectPurpleGerms;
        ArrayList<GameObject> objectBlackGerms;
        ArrayList<GameObject> objectBullets;
        ArrayList<GameObject> objectExplosion;
        
        // Images for blood cell sprite sheets and instances of cell
        BufferedImage cellSprite, drawing;
        // Images for germ sprite sheets and different colored germs.
        BufferedImage germSprite, orangeGerm, blackGerm, purpleGerm;
        // Instances of the germs.
        BufferedImage purpleDrawing, orangeDrawing, blackDrawing;
        // Germ that will be drawn.
        BufferedImage germToBeDrawn;
        // Images of explosion sprite sheet and instances of cell.
        BufferedImage explosionSprite, kaboom, explosionDrawing;
        // Images of bullet sprite sheet and instances of bullet.                              
        BufferedImage bulletSprite, ammo, ammo2, ammo3, ammo4, bulletDrawing;
        // Image for background
        BufferedImage backgroundSprite; 

        // Animates the white blood cells, germs, and background.
        SpriteAnimation sa;
        Timer generalAnimations; 

        CreateGerms cg; // Creates and places germs on board.
        MoveGerms mg;   // Moves germs down on board.

        // Animates  explosion.
        ExplosionAnimation ea;  
        Timer explosionTimer;

        int counter;        // Determines which subimage to draw
        int xStart, yStart, backgroundHeight, backgroundWidth; // Where to draw background.

        // Used to position the cell on the game board.
        ArrayList<Point> cells;
        Point cellPoint;
        int cellPixelX, cellPixelY;

        // Used to place and position germs on game board.
        ArrayList<Point> germs;
        Point germPoint;
        int germPixelX, germPixelY;

        // Used to place bullets on game board.
        ArrayList<GameObject> bullets;
        int whenToDrawBullets; // Bullets only get drawn when spacebar is pressed.
	int bulletWidth = 45;
	int bulletHeight = 60;

        int timeToDrawCounter;  // Determines how long germs should spawn.
        int explosionCounter;   // Determines which explosion subimage should be drawn.
        int pauseCounter; // Determines if timers should be started or stopped when 'p' is pressed.

        JButton howToPlayButton, immunePauseButton, quitPauseButton;
        JLabel pauseMessage;

        // Class which animates white blood cells, germs, and backgrounds.
        private class SpriteAnimation implements ActionListener
        {
            public void actionPerformed (ActionEvent e)
            {
                if (counter > 4)
                {
                    counter = 1;
                }
                drawing = objectCells.get(counter-1).getDrawing();
                orangeDrawing = objectOrangeGerms.get(counter-1).getDrawing();
                purpleDrawing = objectPurpleGerms.get(counter-1).getDrawing();
                blackDrawing = objectBlackGerms.get(counter-1).getDrawing();
                bulletDrawing = objectBullets.get(counter-1).getDrawing();
                backgroundHeight = backgroundWidth = (counter * 100) + 800;
                counter++;
                repaint();
            }
        }

        // Class which creates and places germs on game board.
        private class CreateGerms implements ActionListener
        {
            public void actionPerformed (ActionEvent ae)
            {   
                generateEnemies(); // Create germs.

                // Stop generating germs after some time.
                if (timeToDrawCounter == 19)    // Make 20 germs.
                {
                    cre