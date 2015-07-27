package display;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import deck.Deck;
import deck.Question;

/**
 * class QuizPanel contains all the necessary graphical elements to display a
 * question and answer it properly. It is made of (for the graphical user
 * interface) _ a JLabel to display the question _ an AnswerPanel which contains
 * the 4 buttons containing the answers and the explanation @see AnswerPanel _ a
 * button to either move on to the next question or for the user to say 'I don't
 * know!'.
 * 
 * It contains a Deck, which contains the series of questions for the quiz. It
 * also contains a RequestToFrame listener to request for messages at the end of
 * the quiz.
 * 
 * @author Patrick Created by Patrick Finnerty, this program allows for the
 *         creation of quiz for (but not restricted to) learning purposes.
 *         Copyright (C) 2015 Patrick Finnerty
 *
 *         This program is free software: you can redistribute it and/or modify
 *         it under the terms of the GNU General Public License as published by
 *         the Free Software Foundation, either version 3 of the License, or (at
 *         your option) any later version.
 *
 *         This program is distributed in the hope that it will be useful, but
 *         WITHOUT ANY WARRANTY; without even the implied warranty of
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *         General Public License for more details.
 *
 *         You should have received a copy of the GNU General Public License
 *         along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
public abstract class QuizPanel extends javax.swing.JPanel implements AnswerProvided {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7364894838943006404L;

	protected JLabel question;
	protected AnswerPanel answers;
	protected JButton next;

	protected QuizState state;

	protected Deck current;
	protected int success;
	protected RequestToFrame container;

	/**
	 * Constructor Creates the various fields of the GUI.
	 * 
	 * @param o
	 *            the container that needs to implement RequestToFrame
	 */
	public QuizPanel(RequestToFrame o) {

		this.container = o;

		// Creating the elements and putting them into the window
		this.setLayout(new GridBagLayout());

		// The question label
		question = new JLabel("Question");
		// question.setPreferredSize(new Dimension(500,200));

		// The panel containing the answers and the explanation
		answers = new AnswerPanel();
		// answers.setPreferredSize(new Dimension(500,180));
		answers.setListener(this);

		// The button at the bottom to go to the next question
		next = new JButton("Next");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				switch (state) {
				case QUESTION:
					showAnswer(false);
					break;
				case ANSWER:
					if (current.noQuestionLeft()) {
						container.requestMessage("Congratulations, you have finished this deck!");
					} else {
						setQuestion(current.next());
					}
				}
			}
		});
		
		GridBagConstraints c = new GridBagConstraints();

		for (int i = 0; i < 3; i++) {
			c.gridy = i;
			switch (i) {
			case 0:
				this.add(question, c);
				break;
			case 1:
				this.add(answers, c);
				break;
			case 2:
				this.add(next, c);
			}
		}
	}

	/**
	 * Allows to display a question in the interface. This operation changes the
	 * 
	 * @param q
	 *            the question to be displayed
	 */
	public void setQuestion(Question q) {
		this.question.setText(q.question);
		this.answers.setQuestion(q);
		this.next.setText("I don't know.");
		this.state = QuizState.QUESTION;
	}

	/**
	 * Handles what is to be done when an answer has been provided
	 * 
	 * @param goodAnswer
	 */
	public abstract void showAnswer(boolean goodAnswer);

	/**
	 * Launches a new quiz with the deck given as parameter. All the cards in
	 * the deck will be seen. If the number of success to a question is lower
	 * than the one given as parameter, the question will be added to the end of
	 * the deck. Likewise, each question will be seen at least success times. If
	 * the question was present several times in the deck, this question will be
	 * seen at least the number of success + number of occurrences in the deck
	 * If the integer success value is less than 1, the deck will be gone
	 * through only once.
	 * @param d the deck which is going to be studied
	 * @param s the number of times a card needs to be answered correctly for it not to be added again at the end of the deck
	 */
	public void handleQuiz(Deck d, int s) {
		if (d.resetSuccess() == 0) {
			this.current = d;
			this.success = s;
			setQuestion(current.getCurrentQuestion());
		} else {
			container.requestMessage("Invalid deck provided");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Component#setSize(java.awt.Dimension)
	 */
	public void setSize(Dimension d) {
		super.setSize(d);
		this.answers.setSize(new Dimension(d.width - 10, 50));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see display.AnswerProvided#answered(boolean)
	 */
	@Override
	public void answered(boolean correct) {
		showAnswer(correct);
	}

	/**
	 * Descibes the current state of the quiz
	 * 
	 * @author Patrick possible values : QUESTION ANSWER.
	 */
	protected enum QuizState {
		QUESTION, ANSWER;
	}
}
