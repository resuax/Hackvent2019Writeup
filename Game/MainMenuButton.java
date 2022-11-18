
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MainMenuButton extends JButton // Creates JButtons to return to Main Menu.
{
    public MainMenuButton(final ControllerPanel controllerPanel)
    {
	super("Main Menu!");
	addActionListener(new ActionListener()
	    {
		public void actionPerformed(ActionEvent ae)
		{
		    controllerPanel.switchToMainMenu();
		}
	    });
    }
}