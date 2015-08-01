package player;

import util.Deck;

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

	public void handleQuiz (Deck d, int s) {
		this.goodAnswers = 0;
		this.nbQuestions = 0;
		
		super.handleQuiz(d, s);
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
			difficult.addQuestion(current.getCurrentQuestion());
		}
		nbQuestions++;
		
		if (current.noQuestionLeft()) {
			container.requestEndQuiz(current, difficult, "Congratulations, you have finished this deck! Score: "+goodAnswers+"/"+nbQuestions);
		} else {
			setQuestion(current.next());
		}
	}

}
