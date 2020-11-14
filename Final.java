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
    
    File 