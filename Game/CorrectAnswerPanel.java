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
                    .add(new JLabel("You are correct!", SwingConstants.CENTER));
                if (qa.correctAnswer() == null)
                    {
                        System.out.println("The correct answer is null.");
                        Thread.dumpStack();
                        System.exit(-1);
                    }
                JTextArea answer = new JTextArea(qa.answerLong);
                answer.setLineWrap(true);
                answer.setOpaque(false);
                answer.setWrapStyleWord(true);
                correctAnswerPanel.add(answer);
                correctAnswerPanel.add(new PlayButton(controllerPanel));
                correctAnswersPanel.add(correctAnswerPanel,
                                        "Correct Answer " + ii);
            }
        add(correctAnswersPanel, BorderLayout.CENTER);
        add(new MainMenuButton(controllerPanel), BorderLayout.LINE_START);
    }

    public void switchToCorrectPanel(final int questionNumber)
    {
        correctAnswersPanelCardLayout.show(correctAnswersPanel,
                                           "Correct Answer " + questionNumber);
    }
}
