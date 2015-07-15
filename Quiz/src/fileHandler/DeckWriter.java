package fileHandler;

import java.util.Scanner;

import deck.*;

/**
 * This class handles conversion from String to Deck object and from Deck to
 * String It is used when storing Deck as .dk files.
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
public class DeckWriter {

	/**
	 * Stores a deck in a shape of a .dk file
	 * 
	 * @param name
	 *            the name of the deck to be written
	 * @param d
	 *            the deck itself
	 */
	public static String deckToString(Deck d) {
		String s = "";

		for (Question q : d.getAllQuestions()) {
			s = s + "\n<\n" + q.question + "\n" + q.answer + "\n"; // < then
																	// question
																	// then
																	// answer
			for (int i = 0; i < q.wrong.length; i++) {
				s = s + q.wrong[i] + "\n"; // then each wrong answer
			}
			s = s + q.explanation + "\n>"; // then explanation then ">
		}

		return s;
	}

	/**
	 * Returns a Deck exploitable by the program from a String. This method is
	 * used when loading a deck from a .dk file where all the questions are
	 * stored as String data.
	 * 
	 * @param s
	 * @return
	 */
	public static Deck stringToDeck(String s) {
		Deck d = new Deck();

		Scanner scan = new Scanner(s);

		while (scan.hasNextLine()) {
			String line;
			do {
				line = scan.nextLine();
			} while (scan.hasNextLine() && !line.startsWith("<"));

			String question = scan.nextLine();
			String ans = scan.nextLine();
			String[] w = new String[] { scan.nextLine(), scan.nextLine(), scan.nextLine() };
			String expl = "";
			String buf;
			while (!(buf = scan.nextLine()).startsWith(">")) {
				expl = expl + buf + "\n";
			}
			d.addQuestion(new Question(question, ans, w, expl));
		}
		scan.close();

		return d;
	}
}
