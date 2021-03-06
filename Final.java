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
            
            // Goes to panel 