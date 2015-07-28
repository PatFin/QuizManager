/**
 * 
 */
package display;

/**
 * This interface allows for some classes to warn their container that the user
 * has given an answer and that stuff needs to be performed. In my program, this
 * interface is implemented by class QuizPanel which listens to AnswerPanel.
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
 * @see display.QuizPanel
 * @see display.AnswerPanel
 */
public interface AnswerProvided {

	/**
	 * Gives the signal to the listener that an answer has been provided
	 * 
	 * @param correct
	 *            true if it is a correct answer, false otherwise.
	 */
	public void answered(boolean correct);
}