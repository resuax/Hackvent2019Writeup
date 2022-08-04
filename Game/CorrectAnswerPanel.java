import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.CardLayout;
import java.util.ArrayList;

class CorrectAnswerPanel extends JPanel
{
    final CardLayout correctAnswersPanelCardLayout;
    final JPanel correctAnswersPanel;
    final ArrayList<QuestionAnswer> questionsAndAnswers;

    public CorrectAnswerPanel(final ControllerPanel controllerPanel,
                              ArrayList<QuestionAnswer> _questionsAndAnswers)
    {
        setLayout(new BorderLayout());
        questionsAndAnswers = _questionsAndAnswers;
        correctAnswersPanelCardLayout = new CardLayout();
        correctAnswersPanel = new JPanel(correctAnswersPanelCardLayout);

        for (int ii = 0; ii < questionsAndAnswers.size(); ii++)
            {

                QuestionAnswer qa = questionsAndAnswers.get(ii);
                JPanel correctAnswerPanel = new JPanel();
                correctAnswerPanel.setBackground(Color.green);
                int rows = 3;
                int columns = 1;
                correctAnswerPanel.setLayout(new GridLayout(rows, columns));
                correctAnswerPanel
                    .add(new JLabel("You are correct