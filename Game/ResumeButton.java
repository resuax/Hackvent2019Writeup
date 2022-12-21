
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

class ResumeButton extends JButton // Creates JButtons to play game.
{
    public ResumeButton(final ControllerPanel controllerPanel)
    {
	super("Resume Game!");
	setFocusable(false);
	addActionListener(new ActionListener()
	    {
		public void actionPerformed(ActionEvent ae)
		{
		    controllerPanel.resumeGame();
		}
	    });
    }
}