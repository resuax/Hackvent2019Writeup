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

    public StartMenu(final ControllerPane