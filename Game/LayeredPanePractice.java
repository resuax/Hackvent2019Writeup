import javax.swing.JLayeredPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Color;

public class LayeredPanePractice extends JLayeredPane
{
    public LayeredPanePractice()
    {
	setBackground(Color.ORANGE);
        setPreferredSize(new Dimension(400, 400));
        JLabel label = new JLabel("Hello");
	label.setOpaque(true);
	label.setBackground(Color.RED