package player;

import util.Deck;

@SuppressWarnings("serial")
public class TestQuizPanel extends QuizPanel {

	int goodAnswers;
	int nbQuestions;
	
	/**
	 * Constructor for TestQuizPanel
	 * Requires only the container which has to implement RequestToFrame
	 * @param o the object that implements RequestToFrame.
	 */
	public TestQuizPanel(RequestToFrame o) {
		super(o);
	}

	@Override
	public void handleQuiz (Deck d) {
		super.handleQuiz(d);
		this.goodAnswers = 0;
		this.nbQuestions = 0;
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
