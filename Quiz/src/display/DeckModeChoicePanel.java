package display;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import deck.Deck;

/**
 * This class is a menu for quiz mode. The two modes are: _Learning mode where
 * an explanation is provided after each answer and the question repeated until
 * the user reaches a certain number of good answers (can change this number in
 * Preferences Menu in class MainFrame) _Test mode where all the questions are
 * only showed once and the total score is given at the end of the quiz. TODO
 * Several improvments could be made. TestMode displays the explanations for
 * now, I would like a specific display by mode for the end of the quiz.
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
public class DeckModeChoicePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6552015897178278431L;

	private JButton learnButton = new JButton("Learning mode");
	private JButton testButton = new JButton("Test mode");
	private JLabel message = new JLabel("Choose a quiz mode");

	private Deck deck;

	RequestToFrame listener;

	public DeckModeChoicePanel(RequestToFrame l) {
		super(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridy = 0;
		this.add(message, constraints);

		constraints.gridy = 1;
		this.add(learnButton, constraints);
		this.add(testButton, constraints);

		this.listener = l;

		initButtonAction();
	}

	/**
	 * Allows to set the deck which is going to be used in the next quiz.
	 */
	public void setDeck(Deck d) {
		this.deck = d;
	}

	/**
	 * Initialises the actions to be performed for each button
	 */
	private void initButtonAction() {

		learnButton.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				listener.requestLearnModeQuiz(deck);
			}
		});

		testButton.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				listener.requestTestModeQuiz(deck);
			}
		});
	}
}