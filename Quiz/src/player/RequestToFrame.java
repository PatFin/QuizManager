/**
 * 
 */
package player;

import util.Deck;

/**
 * This interface allows classes that handle stuff (like quizzes) to make
 * requests to their container (MainFrame in my case).
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
public interface RequestToFrame {

	/**
	 * Interface for objects to request a message to be displayed. This
	 * interface needs to be implemented by the MainFrame so that QuizPanel can
	 * ask for specific messages.
	 * 
	 * @param s
	 *            the message to be displayed
	 */
	public void requestMessage(String s);

	/**
	 * Requests to the frame to handle a new quiz in learning mode. By learning
	 * mode, we mean that the explanation is displayed after each question if
	 * the answer provided was wrong. The question is repeated a certain number
	 * of times as specified in the preferences of MainFrame.
	 * 
	 * @param d
	 *            the deck which is going to be used in the next quiz
	 */
	public void requestLearnModeQuiz(Deck d);

	/**
	 * Requests to the frame to handle a new quiz in test mode. By test mode we
	 * mean that every question of the deck is shown once and that no
	 * explanation is provided after an answer. The total score is given at the
	 * end of the quiz without any further detail.
	 * 
	 * @param d
	 *            the deck to be used in the next quiz
	 */
	public void requestTestModeQuiz(Deck d);
	
	public void requestLoadOtherQuiz ();
	
	/**
	 * Request to launch the same kind of quiz as before with deck d
	 * @param d the deck which is going to be the next quiz.
	 */
	public void requestQuizSameType(Deck d);
	/**
	 * Allows components to request the frame to display a End Quiz panel.
	 * @param total the total deck that can be played again,
	 * @param diff the deck which contains the questions the user had problems with.
	 */
	public void requestEndQuiz(Deck total, Deck diff, String message);
}
