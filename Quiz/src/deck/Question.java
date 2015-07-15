package deck;

/**
 * Question class. This class contains all a question is made of, that is: _ a
 * question (String) _ a good answer (String) _ three wrong answers (String[]) _
 * an explanation (String usually on several lines) So you understand it
 * basically involves a lot of String. See class DeckFileHandler to see how
 * deckFiles (*.dk) are converted to a deck of question and how quesitons are
 * converted into Strings to store them as .dk files.
 * 
 * @see fileHandler.DeckWriter
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
public class Question {

	public String question; // The question
	public String answer; // The answer (not always 42)
	public String[] wrong; // Three wrong answers
	public String explanation; // Contains the grammar explanation linked to the
								// question

	public int success = 0; // The number of times a question has been answered
							// correctly. 0 when a question is created.

	/**
	 * Constructor
	 * 
	 * @param aQuestion
	 * @param anAnswer
	 * @param wrongAnswers
	 * @param anExplanation
	 */
	public Question(String aQuestion, String anAnswer, String[] wrongAnswers, String anExplanation) {
		question = aQuestion;
		answer = anAnswer;
		wrong = new String[3];
		for (int i = 0; i < wrong.length; i++) {
			try {
				wrong[i] = wrongAnswers[i];
			} catch (ArrayIndexOutOfBoundsException e) {
				wrong[i] = "<>";
			}
		}
		explanation = anExplanation;
	}

	public Question() {
		super();
		question = "";
		answer = "";
		wrong = new String[] { "", "", "" };
		explanation = "";
	}

	/**
	 * Displays the question only.
	 */
	public String toString() {
		return question;
	}
}
