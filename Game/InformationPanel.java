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
        setLayout(new BorderLayout());
        add(new JLabel(panelName), BorderLayout.PAGE_START);
        add(new MainMenuButton(controllerPanel), BorderLayout.LINE_START);

        try
            {
                content = new Scanner(new File(textFileName),
       