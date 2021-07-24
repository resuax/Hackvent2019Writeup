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
            checker