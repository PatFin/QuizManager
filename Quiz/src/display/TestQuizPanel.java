package display;

@SuppressWarnings("serial")
public class TestQuizPanel extends QuizPanel {

	int goodAnswers;
	int nbQuestions;
	
	/**
	 * 
	 * @param o
	 */
	public TestQuizPanel(RequestToFrame o) {
		super(o);
	}

	/*
	 * (non-Javadoc)
	 * @see display.QuizPanel#showAnswer(boolean)
	 */
	@Override
	public void showAnswer(boolean goodAnswer) {
		if(goodAnswer){
			this.goodAnswers++;
		}
		nbQuestions++;
		
		if (current.noQuestionLeft()) {
			container.requestMessage("Congratulations, you have finished this deck! Score: "+goodAnswers+"/"+nbQuestions);
		} else {
			setQuestion(current.next());
		}
	}

}
