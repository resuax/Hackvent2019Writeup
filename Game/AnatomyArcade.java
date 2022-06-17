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
        catch (Fi