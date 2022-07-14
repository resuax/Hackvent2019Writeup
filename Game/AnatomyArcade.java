/* Ananth Penghat
   July 26, 2014
   AnatomyArcade.java
*/
import java.util.ArrayList;
import java.awt.CardLayout;
import javax.swing.JFrame;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.util.Collections;
import java.awt.Dimension;
public class AnatomyArcade
{
    final ArrayList<QuestionAnswer> questionsAndAnswers
        = readQuestionsAndAnswers();

    public ArrayList<QuestionAnswer> readQuestionsAndAnswers()
    {
        ArrayList<QuestionAnswer> questionsAndAnswers
            = new ArrayList<QuestionAnswer>();
        Scanner questionScanner, answerScanner, answerLongScanner,
            incorrectAnswerScanner;
        questionScanner = answerScanner = answerLongScanner
            = incorrectAnswerScanner = null;
        try
            {
                questionScanner
                    = new Scanner(new File("questions.txt"));
                answerScanner
                    = new Scanner(new File("answers.txt"));
                answerLongScanner
                    = new Scanner(new File("answersLong.txt"));
                incorrectAnswerScanner
                    = new Scanner(new File("incorrectanswers.txt"));
            }
        catch (FileNotFoundException fnfe)
            {
                fnfe.printStackTrace();
                System.exit(-1);
            }

        while (questionScanner.hasNextLine()
               && answerScanner.hasNextLine()
               && answerLongScanner.hasNextLine()
               && incorrectAnswerScanner.hasNextLine())
            {
                ArrayList<Answer> answers = new ArrayList<Answer>();
                answers.add(new Answer(true, answerScanner.nextLine()));

                // Add two incorrect answers to ArrayList of answers.
                answers.add(new Answer(false,
                                       incorrectAnswerScanner.nextLine()));
                answers.add(new Answer(false,
                                       incorrectAnswerScanner.nextLine()));
                String answerLong = answerLongScanner.nextLine();
                Collections.shuffle(answers);
                questionsAndAnswers
                    .add(new QuestionAnswer(questionScanner.nextLine(),
                                            answers, answerLong));
            }
        return questionsAndAnswers;
    }

    public static void main (String[] args)
    {
        AnatomyArcade anatomyArcade = new AnatomyArcade();
        anatomyArcade.run();
    }

    public void run()
    {
        final Integer MAIN_WINDOW_WIDTH = 800, MAIN_WINDOW_HEIGHT = 600,
            MAIN_WINDOW_X = 180, MAIN_WINDOW_Y = 100;  
        JFrame mainFrame = new JFrame("ANATOMY ARCADE");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Boolean pause = false;
        int maxNumberOfErrors = 2;
        final World world  = new World(new ArrayList<Enemy>(),
                                       new ArrayList<Bullet>(),
                                       new ArrayList<Explosion>(),
                                       pause, maxNumberOfErrors,
                                       MAIN_WINDOW_HEIGHT, MAIN_WINDOW_WIDTH);
        ControllerPanel controllerPanel
            = new ControllerPanel(new CardLayout(),
                                  questionsAndAnswers,
                                  mainFrame,
                                  world);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controllerPanel.setPreferredSize(new Dimension(MAIN_WINDOW_WIDTH,
                                                       MAIN_WINDOW_HEIGHT));
        mainFrame.add(controllerPanel);
        mainFrame.pack();
        mainFrame.setFocusable(true);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }
}
