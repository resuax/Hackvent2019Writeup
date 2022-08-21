import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

class ExitButton extends JButton
{
    public ExitButton(final ControllerPanel controllerPanel)
    {
        super("Exit!");
	addActionListener(new ActionListener()
	    {
		public void actionPerformed(ActionEvent ae)
		{
		    System.exit(0);
		}
	    }); 
    }
}
