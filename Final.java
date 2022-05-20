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
                    createGerms.stop(); // Stop after 20th germ.
                }
                timeToDrawCounter++;
                repaint();
            }
        }

        // Class which moves the germs one down.
        private class MoveGerms implements ActionListener
        {
            public void actionPerformed(ActionEvent ae)
            {
                for (Point germPoint : germs)
                {
                    germPoint.y += 1;
                    repaint();
                }
            }
        }

        // Class which animates the explosion.
        private class ExplosionAnimation implements ActionListener
        {
            public void actionPerformed (ActionEvent ae)
            {
                if (explosionCounter > 13)
                {
                    explosionCounter = 1;
                }
                explosionDrawing = objectExplosion.get(explosionCounter-1).getDrawing();
                explosionCounter++;
                repaint();
            }
        }

        public WBCGamePanel()
        {   
            frame.addKeyListener(this);
            
            // Components that appear when game is paused.
            howToPlayButton = new JButton("How to Play!");
            immunePauseButton = new JButton("Immune System!");
            quitPauseButton = new JButton("Quit!");
            pauseMessage = new JLabel("GAME PAUSED - 'P' TO RESUME");

            // Creates random location of cell and adds to arraylist. 
            cells = new ArrayList<Point>();
            cellPoint = randomPointCell();
            cells.add(cellPoint);
            
            objectCells = new ArrayList<GameObject>();
            objectOrangeGerms = new ArrayList<GameObject>();
            objectPurpleGerms = new ArrayList<GameObject>();
            objectBlackGerms = new ArrayList<GameObject>();
            objectBullets = new ArrayList<GameObject>();
            objectExplosion = new ArrayList<GameObject>();

            germs = new ArrayList<Point>();    // Contains the germ locations.
            bullets = new ArrayList<GameObject>(); // Contains bullets.
            whenToDrawBullets = 0;  // Draw bullet after values set.

            backgroundWidth = backgroundHeight = 800;      // Used to draw background.

            try  // Try to read in sprite sheets.
            {
                cellSprite = ImageIO.read(new File("wbloodcells.png"));
                backgroundSprite = ImageIO.read(new File("redpixel.jpg"));
                germSprite = ImageIO.read(new File("germs.gif"));
                explosionSprite = ImageIO.read(new File("explosion.png"));
                bulletSprite = ImageIO.read(new File("bullets.png"));
            }
            catch (IOException ioe) // Print error message if error
            {
                System.out.println("Image error.");
            }
            
            // Get subimages of white blood cell sprite sheet.
            for (int ii = 0; ii <  4; ii++)
            {
                BufferedImage image = cellSprite.getSubimage(ii * 90, 0, 94, 93);
                objectCells.add(new GameObject(image, 94, 93, ii * 90, 0));
            }
            
            for (int ii = 80; ii <= 200; ii += 40)
            {
                BufferedImage orangeImage =
            germSprite.getSubimage(ii, 60, 39, 60);
                objectOrangeGerms.add(new GameObject(orangeImage, 39, 60, ii, 60));
                
                BufferedImage purpleImage = 
            germSprite.getSubimage(ii, 240, 39, 60);
                objectPurpleGerms.add(new GameObject(purpleImage, 39, 60, ii, 240));
                
                BufferedImage blackImage = 
            germSprite.getSubimage(ii, 180, 39, 60);
                objectBlackGerms.add(new GameObject(blackImage, 39, 60, ii, 180));
            }

            // Get subimages of bullet from bullet sprite sheet.
            for (int ii = 0; ii <= 69; ii += 23)
            {
                BufferedImage bulletImage = 
            bulletSprite.getSubimage(ii, 0, 23, 25);
                objectBullets.add(new GameObject(bulletImage, 23, 25, ii, 0));
            }

            int kaboomWidth = 39;
            int kaboomHeight = 39;
            for (int ii = 0; ii < 13; ii++)
            {    
            // Get subimages of explosion from explosion sprite sheet.
                kaboom = explosionSprite.getSubimage(ii * kaboomWidth,
                                 157 - kaboomHeight,
                                 kaboomWidth, kaboomHeight);
                objectExplosion.add(new GameObject(kaboom, 
                               kaboomWidth, kaboomHeight, 
                               100, 100));
            }

            // Animate the cell, germs, and background.
            sa = new SpriteAnimation();
            generalAnimations = new Timer(200, sa);

            // Create the germs and place them on the game board.
            cg = new CreateGerms();
            createGerms = new Timer(500, cg);

            // Move the germs around on the board.
            mg = new MoveGerms();
            moveGerms = new Timer(1000, mg);
        
            // Animate the explosion.
            ea = new ExplosionAnimation();
            explosionTimer = new Timer(60, ea);

            timeToDrawCounter = 0; // Maintains limit of objects to be drawn.
            pauseCounter = 0; // Whether to stop/start timers.
            explosionCounter = counter = 1;    // Counter for animations.

            generalAnimations.start();
           // explosionTimer.start();
        }

        public void generateEnemies()  // Generates germs in easy mode.
        {
            germPoint = randomPointGerm();
            while (germs.contains(germPoint))
            {
                germPoint = randomPointGerm();
            }
            germs.add(germPoint);
        }   

        public void moveBullets()
        {
	    ArrayList<GameObject> newBullets = new ArrayList<GameObject>();
	    for (GameObject bullet : bullets)
	    {
		GameObject newBullet = new GameObject(bullet.getDrawing(), bullet.getWidth(), bullet.getHeight(),bullet.getX(), bullet.getY() - 1);
		newBullets.add(newBullet);
	    }
	    bullets = newBullets;
        }

        public Point randomPointCell()  // Random point for cell.
        {
            return new Point((int)(Math.random() * 10), 9);
        }

        public Point randomPointGerm()  // Random Point for germ.
        {
            return new Point((int)(Math.random() * 10), 0);
        }

        public int getPixelX(int generatedNumber)   // Generate xCord from random point.
        {
            return (generatedNumber * 80);
        }

        public int getPixelY(int generatedNumber)   // Generate yCord from random point.
        {
            return (generatedNumber * 58);
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            // Draw background.
            g.drawImage(backgroundSprite, 0, 0, 
            backgroundWidth, backgroundHeight, this); 
            g.drawImage(explosionDrawing, 100, 100, 80, 50, this); // Draw explosions.
           
            // Get valid pixel locations for white blood cell and draw.
            for (Point whiteBloodCellPoint : cells)
            {
                cellPixelX = getPixelX(whiteBloodCellPoint.x);
                cellPixelY = getPixelY(whiteBloodCellPoint.y);
                // Print message if germ hits white blood cell.
                if (germs.contains(whiteBloodCellPoint))
                {
                    System.out.println("Hit by germ!");
                }
                g.drawImage(drawing, cellPixelX, cellPixelY, 60, 70, this); 
            }

            // Get valid pixels locations for germs and draw.
            for (Point germEnemyPoint : germs)
            {
                germPixelX = getPixelX(germEnemyPoint.x);
                germPixelY = getPixelY(germEnemyPoint.y);
                germToBeDrawn = purpleDrawing;  // Use purple germ sprite.
                g.drawImage(germToBeDrawn, germPixelX, germPixelY - 10, 
                50, 60, this);       
            }

            // Draw bullets after values are set.
	    for (GameObject bullet : bullets)
	    {
		g.drawImage(bullet.getDrawing(),
			    getPixelX(bullet.getX()), 
			    getPixelY(bullet.getY()),
			    bullet.getWidth(), bullet.getHeight(), this);
	    }

            if (pauseBoolean != true)   // If game is in play.
            {   
               moveBullets(); // Move the bullets across the board.
            }

            if (pauseBoolean != false)  // If game is paused.
            {
                // Black rectangle shown when game is paused; for visibility.
                g.setColor(Color.black);
                g.fillRect(70, 25, 650, 50);

            }
        }   

        public void keyPressed(KeyEvent e)
        {
            char c = e.getKeyChar();
            if (c == 'p') // Pause game
            {
                pauseCounter++;
                if (pauseCounter % 2 == 0) // Start game again
                {
                    pauseBoolean = false;
                    generalAnimations.start();
                    //explosionTimer.start();
                    createGerms.start();
                    moveGerms.start();

                    // Remove JComponents if game is not paused.
                    remove(howToPlayButton);
                    remove(immunePauseButton);
                    remove(quitPauseButton);
                    remove(pauseMessage);
                }
                
                else // Stop everything
                {
                    pauseBoolean = true;
                    generalAnimations.stop();
                    explosionTimer.stop();
                    createGerms.stop();
                    moveGerms.stop();

                    // JButton to return to titlescreen.
                    howToPlayButton.setFont(font);
                    howToPlayButton.addActionListener(new ButtonInfoListener());
                    howToPlayButton.setBounds(305, 180, 188, 60);
                    add(howToPlayButton);
                    
                    // JButton to show immune system info.
                   immunePauseButton.setFont(new Font("Arial", Font.BOLD, 18));
                   immunePauseButton.addActionListener(new ButtonImmuneListener());
                   immunePauseButton.setBounds(305, 260, 188, 60);
                   add(immunePauseButton);
                   
                   // JButton that returns to main menu.
                   quitPauseButton.setFont(font);
                   quitPauseButton.addActionListener(new ButtonMainListener());
                   quitPauseButton.setBounds(305, 340, 188, 60);
                   add(quitPauseButton);
                   
                   // Message that instructs user how to resume game.
                   pauseMessage.setFont(new Font("Arial", Font.BOLD, 40));
                   pauseMessage.setForeground(Color.white);
                   pauseMessage.setBounds(80, 25, 680, 50);
                   add(pauseMessage);
                }
            }
            
            if (pauseBoolean != true)   // Only move when game is not paused.
            {
                if (c == 'a') // Move left
                {
                    cellPoint.x -= 1;
                    if (cellPoint.x < 0)
                    {
                        cellPoint.x = 9;
                    }
                }

                if (c == 'd') // Move right
                {
                    cellPoint.x += 1;
                    if (cellPoint.x > 9)
                    {
                        cellPoint.x = 0;
                    }
                }
                    
                if (e.getKeyCode() == KeyEvent.VK_SPACE)    // Spacebar pressed.
                {
                    // Create bullet, add to arraylist, and get pixel values.
		    BufferedImage bulletImage = 
			bulletSprite.getSubimage(0, 0, 23, 25);
                 