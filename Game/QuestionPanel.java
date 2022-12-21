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
        questionsPanel = new JPanel(questionsPanelCardLayout);
        questionsAndAnswers = _questionsAndAnswers;

        final ButtonGroup answerGroup = new ButtonGroup();
        for (int ii = 0; ii < questionsAndAnswers.size(); ii++)
            {
                final Integer iii = ii;
                QuestionAnswer qa = questionsAndAnswers.get(ii);
                JPanel questionPanel = new JPanel();
                // 2 is for the first two rows containing the question and title
                int rows = 2 + qa.answers.size();
                int columns = 1;
                questionPanel.setLayout(new GridLayout(rows, columns));
                questionPanel
                    .add(new JLabel("Question Panel", SwingConstants.CENTER));
                JTextArea questionArea = new JTextArea(qa.question);
                questionArea.setLineWrap(true);
                questionArea.setWrapStyleWord(true);
                questionPanel.add(questionArea);

                for (final Answer answer : qa.answers)
                    {
                        JRadioButton optionButton
                            = new JRadioButton(answer.text);
                        optionButton.addActionListener(new ActionListener()
                            {
                                public void actionPerformed(ActionEvent ae)
                                {
                                    if (answer.correct)
                                        {
                                            controllerPanel.switchToCorrectAnswerPanel(iii);
                                        }
                                    else
                                        {
                                            controllerPanel.handleIncorrectAnswer(iii);
                                        }
                                    answerGroup.clearSelection();
                                }
                            });
                        answerGroup.add(optionButton);
                        questionPanel.add(optionButton);
                    }
                questionsPanel.add(questionPanel, "Question " + ii);
            }

        add(questionsPanel, BorderLayout.CENTER);
        add(new MainMenuButton(controllerPanel),  BorderLayout.LINE_START);
    }

    public void showRandomQuestion()
    {
        int questionToShow = (int)(Math.random() * questionsAndAnswers.size());
        questionsPanelCardLayout.show(questionsPanel,
                                      "Question " + questionToShow);
    }
}
