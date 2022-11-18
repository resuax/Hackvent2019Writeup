
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;

class NumberOfErrorsPanel extends JPanel
{
    JLabel errorsLabel;
    World world;
    public NumberOfErrorsPanel(final World _world)
    {
        world = _world;
        int rows = 1;
        int columns = 1;
        setLayout(new GridLayout(rows, columns));
        errorsLabel
            = new JLabel(world.numberOfErrors + "/" + world.maxNumberOfErrors,
                         SwingConstants.CENTER);
        errorsLabel.setForeground(Color.BLACK);
        add(errorsLabel);
    }

    public void updateText()
    {
        errorsLabel.setText(world.numberOfErrors + "/"
                            + world.maxNumberOfErrors);
    }
}