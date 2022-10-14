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
        controllerPanel = _controllerPanel;
        incorrectAnswersPanelCardLayout = new CardLayout();
        incorrectAnswersPanel = new JPanel(incorrectAnswersPanelCardLayout);

        for (int ii = 0; ii < questionsAndAnswers.size(); ii++)
            {
                QuestionAnswer qa = questionsAndAnswers.get(ii);
                JPanel incorrectAnswerPanel = new JPanel();
                incorrectAnswerPanel.setBackground(Color.red);
                int rows = 3;
                int columns = 1;
                incorrectAnswerPanel.setLayout(new GridLayout(rows, columns));
                incorrectAnswerPanel
                    .add(new JLabel("You are incorrect!", SwingConstants.CENTER));
                if (qa.correctAnswer() == null)
                    {
                        Syst