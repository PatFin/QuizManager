package player;

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
		} else {
			difficult.addQuestion(currentDeck.getCurrentQuestion());
		}
		nbQuestions++;
		
		if (currentDeck.noQuestionLeft()) {
			endQuiz();
		} else {
			setQuestion(currentDeck.next());
		}
	}

	@Override
	public void endQuiz() {
		container.requestEndQuiz(originalDeck, difficult, "Congratulations, you have finished this deck! Score: "+goodAnswers+"/"+nbQuestions);

	}

}
