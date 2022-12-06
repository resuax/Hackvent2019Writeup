
import java.util.ArrayList;

class QuestionAnswer
{
    public String question, answerLong;
    public ArrayList<Answer> answers;
    public QuestionAnswer(String _question, ArrayList<Answer> _answers,
                          String _answerLong)
    {
	question = _question;
	answers = _answers;
        answerLong = _answerLong;
    }
    public Answer correctAnswer()
    {
	for (Answer a: answers)
	    {
		if (a.correct)
		    {
			return a;
		    }
	    }
	return null;
    }
}