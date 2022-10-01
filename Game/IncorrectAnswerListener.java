import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class IncorrectAnswerListener implements ActionListener
{
    int questionIndex;

    public IncorrectAnswerListener(int _questionIndex)
    {
	questionIndex = _questionIndex;
    }
    public void actionPerformed(ActionEvent ae){}
}
