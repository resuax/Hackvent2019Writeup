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
	label.setBackground(Color.RED);
	label.setForeground(Color.BLACK);
	label.setBounds(100, 100, 100, 100);
        add(label, new Integer(0));
	JLabel label2 = new JLabel("World");
	label2.setOpaque(true);
	label2.setBounds(150, 150, 100, 100);
	label2.setBackground(Color.BLUE);
	label2.setForeground(Col