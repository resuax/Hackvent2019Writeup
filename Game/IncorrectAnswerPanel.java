import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.CardLayout;
import java.util.ArrayList;

class IncorrectAnswerPanel extends JPanel
{
    final CardLayout incorrectAnswersPanelCardLayout;
    final JPanel incorrectAnswersPanel;
    final ArrayList<QuestionAnswer> questionsAndAnswers;
    final ControllerPanel controllerPanel;

    public IncorrectAnswerPanel(final ControllerPanel _controllerPanel,
                                ArrayList<QuestionAnswer> _questionsAndAnswers)
    {
        setLayout(new BorderLayout());
        questionsAndAnswers = _questionsAndAnswers;
       