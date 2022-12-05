import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

class PlayButton extends JButton // Creates JButtons to play game.
{
    public PlayButton(final ControllerPanel controllerPanel)
    {
	super("Play!");
	addActionListener(new ActionListener()
	    {
		public void actionPerformed(ActionEvent ae)
		{
		    controllerPanel.switchToPlayPanel();
		}
	    });
    }
}
