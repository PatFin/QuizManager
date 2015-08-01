/**
 * 
 */
package player;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import util.Question;

/**
 * This class is a cardLayout of 4 buttons that present the possible answers of
 * a question / the explanation to that question.
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
public class AnswerPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1993766780471621693L;

	JButton[] buttons; // The four buttons containing the possible answers to
						// the question
	JPanel answers; // The panel containing the buttons

	JTextArea expl;
	JPanel explanation; // The panel containing the explanation (grammar and
						// stuff)
	JScrollPane sP;

	String correctAnswer;

	AnswerProvided listener;

	final static String EXPLANATION = "EXP";
	final static String ANSWERS = "ANS";

	/**
	 * Constructor by default The buttons and explanation are created.
	 */
	public AnswerPanel() {
		super();
		this.setLayout(new CardLayout());

		// We create the answer panel
		answers = new JPanel();
		answers.setLayout(new GridLayout(2, 2));
		buttons = new JButton[4];
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton("Answer " + i);
			buttons[i].addActionListener(this);
			answers.add(buttons[i]);
		}

		// We also create the explanation Panel which is going to remain in
		// standby for the moment
		explanation = new JPanel();
		expl = new JTextArea(
				"This is a long explanation for a simple rule. \nHopefully you won't need it. \nBut hey, who knows ;)");
		sP = new JScrollPane(expl);
		explanation.add(sP); // Putting the textArea in the JPanel

		this.add(answers, ANSWERS);
		this.add(explanation, EXPLANATION);
		this.setMinimumSize(new Dimension(500, 500));
		this.setMaximumSize(new Dimension(500, 500));
	}

	/**
	 * Constructor This time the buttons will be initialised with the answers
	 * given by the question. The explanation will also be stored inside
	 * 
	 * @param q
	 *            the question which is going to be used right away
	 */
	public AnswerPanel(Question q) {
		this();
		this.setQuestion(q);
	}

	/**
	 * Allows to set the listener to AnswerProvided. In my program this is class
	 * QuizPanel
	 * 
	 * @param l
	 *            the object that implements AnswerProvided
	 * @see player.QuizPanel
	 */
	public void setListener(AnswerProvided l) {
		listener = l;
	}

	/**
	 * Allows to change the question contained by the AnswerPanel
	 * 
	 * @param q
	 *            the new question to be put inside
	 */
	public void setQuestion(Question q) {
		// First, we set the buttons according to the answers in the question.
		// We pick a random position for the answer (you don't want to always
		// have it on the first button)
		int r = (int) (3 * Math.random());
		int k = 0;
		for (int i = 0; i < buttons.length; i++) {
			if (r == i) {
				buttons[i].setText(q.answer);
			} else {
				buttons[i].setText(q.wrong[k]);
				k++;
			}
		}

		// We now store the explanation.
		this.expl.setText(q.explanation);

		// We store the correct answer to be able to determine if the right
		// button was clicked
		this.correctAnswer = q.answer;

		// And we choose to display the buttons with the answer buttons
		CardLayout cl = (CardLayout) (this.getLayout());
		cl.show(this, ANSWERS);
	}

	/**
	 * Shows the explanation of the question
	 * 
	 * @param goodAnswer
	 */
	public void showExplanation(boolean goodAnswer) {
		CardLayout cl = (CardLayout) (this.getLayout());
		cl.show(this, EXPLANATION);
	}

	/**
	 * Method called when the user presses one of the buttons
	 * 
	 * @param a
	 *            the event associated
	 */
	@Override
	public void actionPerformed(ActionEvent a) {
		listener.answered(((JButton) a.getSource()).getText() == this.correctAnswer);
	}
}
