import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JPanel;
import java.io.File;
import javax.swing.JLabel;

class InformationPanel extends JPanel
{
    String content;
    public InformationPanel(final ControllerPanel controllerPanel,
                            String textFileName, String panelName)
    {
        