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

    public CorrectAnswerPanel(final ControllerPan