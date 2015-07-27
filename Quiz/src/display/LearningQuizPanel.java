package display;

import deck.Question;

@SuppressWarnings("serial")
public class LearningQuizPanel extends QuizPanel {

	/**
	 * Constructor
	 * This constructor only invoke that of the mother class QuizPanel. 
	 * It only creates the Java Swing components that are common to all the daughters of QuizPanel.
	 * @param o the container of the quizPanel
	 * @see QuizPanel
	 */
	public LearningQuizPanel(RequestToFrame o) {
		super(o);
	}
	
	/*
	 * (non-Javadoc)
	 * @see display.QuizPanel#showAnswer(boolean)
	 */
	@Override
	public void showAnswer(boolean goodAnswer) {
		if(goodAnswer){
			answers.expl.setText("Well Done!\n");
		}
		
		
		answers.showExplanation(goodAnswer);
		next.setText("Next Question ->");

		// We increment success of the question if goodAnswer
		Question q = current.getCurrentQuestion();
		if (goodAnswer) {
			q.success++;
		}

		// We add again the question at the end of the deck if it hasn't been succeeded enough yet.
		if (q.success < this.success) {
			current.addQuestion(q);
		}
		this.state = QuizState.ANSWER;
	}
}