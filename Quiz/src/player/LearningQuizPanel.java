package player;

import util.Question;

@SuppressWarnings("serial")
public class LearningQuizPanel extends QuizPanel {

	/**
	 * Parameter for the learning mode.
	 * success is the number of times you need to answer a question correctly so that you won't see it again.
	 */
	public static int successNeededForMearningMode;
	/**
	 * Parameter for the learning mode.
	 * failures is the number of times you need to fail a question so that it will be added to the difficult questions deck.
	 */
	public static int failuresNeededForLearningMode;
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
		Question q = currentDeck.getCurrentQuestion();
		if (goodAnswer) {
			q.success++;
		} else {
			q.failed++;
			
			/**If the question was failed a certain number of times, we add it to the difficult question list.
			 * Not that a failed>x would not work because the question will be added multiple times!
			 */
			if(q.failed == failuresNeededForLearningMode) {
				difficult.addQuestion(q);
			}
		}
		
		// We add again the question at the end of the deck if it hasn't been succeeded enough yet.
		if (q.success < LearningQuizPanel.successNeededForMearningMode) {
			currentDeck.addQuestion(q);
		}
		this.state = QuizState.ANSWER;
	}

	@Override
	public void endQuiz() {
		container.requestEndQuiz(currentDeck, difficult, "Congratulations, you have finished this deck!");
	}
}