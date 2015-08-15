package util;

/**
 * Question class. This class contains all a question is made of, that is: _ a
 * question (String) _ a good answer (String) _ three wrong answers (String[]) _
 * an explanation (String usually on several lines) So you understand it
 * basically involves a lot of String. See class DeckFileHandler to see how
 * deckFiles (*.dk) are converted to a deck of question and how quesitons are
 * converted into Strings to store them as .dk files.
 * 
 * @see util.DeckWriter
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

	public int success = 0; // The number of times a question has been answered correctly. 0 when a question is created.
	public int failed = 0;
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

	/**
	 * Returns true if all the fields of the two questions are identical.
	 * This is not a a == b check, it is a real parameter by parameter check.
	 * If either one of the parameters is null, this method will return false.
	 * @param a a question
	 * @param b	an other question
	 * @return true if the questions contain the same Strings, false otherwise.
	 * @see Util#compareString(String, String)
	 */
	public static boolean areIdentical(Question a, Question b) {
		if (a!= null && b != null) {
			return Util.compareString(a.question, b.question) && 
					Util.compareString(a.answer, b.answer) && 
					Util.compareString(a.explanation, b.explanation) &&
					Util.compareString(a.wrong[0], b.wrong[0]) &&
					Util.compareString(a.wrong[1], b.wrong[1]) &&
					Util.compareString(a.wrong[2], b.wrong[2]);
		} else {
			return false;
		}
	}
	
	/**
	 * Main method for testing ... :D
	 * @param args nothing needed!
	 */
	public static void main (String [] args) {
		Question q1 = new Question("Q", "a", new String [] {"a","a","a"}, "Blof");
		
		Question q2 = new Question("Q", "a", new String [] {"a","a","a"}, "Blof");
		q1 = null;
		q2 = null;
		
		
		System.out.println(areIdentical(q1, q2));
	}
}
