import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.util.ArrayList;

class QuestionPanel extends JPanel
{
    final CardLayout questionsPanelCardLayout;
    final JPanel questionsPanel;
    final ArrayList<QuestionAnswer> questionsAndAnswers;

    public QuestionPanel(final ControllerPanel controllerPanel,
                         final ArrayList<QuestionAnswer> _questionsAndAnswers,
                         World world)
    {

        setLayout(new BorderLayout());
        questionsPanelCardLayout = new CardLayout();
        questionsPanel = new JPanel(questionsPan