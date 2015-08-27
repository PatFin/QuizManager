package util;

import java.util.ArrayList;

/**
 * This class consists in handling a set of questions with their answers in one
 * object called a Deck. It behaves as a linked list between elements called
 * DeckEle.
 * 
 * @see util.DeckEle
 * @see util.Question
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
public class Deck {

	DeckEle root;
	DeckEle last;

	/**
	 * Empty Constructor
	 */
	public Deck() {
		super();
		root = new DeckEle();
		last = root;
	}

	/**
	 * Indicates if there are questions left in the deck, that is if the next
	 * DeckEle is not null.
	 * 
	 * @return true if root is the last element, false otherwise.
	 */
	public boolean noQuestionLeft() {
		return !root.hasNext();
	}

	/**
	 * Indicates if the deck is empty, that is if the root/current element has no question.
	 * This happens if a Deck has been created but no Question were added to it yet.
	 * @return true if deck contains no question, false otherwise.
	 */
	public boolean isEmpty () {
		return this.getAllQuestions().size() == 0;
	}
	/**
	 * Adds the question given as parameter at the end of the list
	 * 
	 * @param q
	 *            the question to be added
	 */
	public void addQuestion(Question q) {
		if (root.hasQuestion()) {
			last.next = new DeckEle(q);
			last.next.prev = last;
			last = last.next;
		} else {
			root.question = q;
		}

	}
	
	/**
	 * Makes the root element go back in the prev elements until no such element exists.
	 * @return this after the method completed
	 */
	public Deck rewind (){
		while (root.hasPrev()) {
			root = root.prev;
		}
		return this;
	}

	/**
	 * Gives an ArrayList containing all the different questions contained by
	 * the deck. Multiple occurrences of a question in a deck will appear in
	 * the list returned. The list will not contain any null questions. 
	 * But a question might have been poorly initialised.
	 * @return the list of all questions contained in the deck.
	 */
	public ArrayList<Question> getAllQuestions() {
		ArrayList<Question> a = new ArrayList<Question>();
		this.rewind();
		DeckEle e = root;
		if (e != null) {
			while (e != null) {
				if (e.question != null) {
					a.add(e.question);
				}
				e = e.next;
			}
		}
		return a;
	}
	
	/**
	 * Gives an arrayList of all the questions the deck contains.
	 * @return ArrayList<DeckEle> of DeckEle contained by this.
	 */
	public ArrayList<DeckEle> getAllDeckEle () {
		ArrayList<DeckEle> a = new ArrayList<DeckEle>();
		this.rewind();
		DeckEle e = root;
		if (e != null) {
			while (e != null) {
				a.add(e);
				e = e.next;
			}
		}
		return a;
	}

	/**
	 * Moves the root to the next element. The current root is therefore lost
	 * after this operation.
	 * 
	 * @return the question of the next element (the next element being the new
	 *         root).
	 */
	public Question next() {
		root = root.next;
		return root.question;
	}

	/**
	 * Gives the question of the current (ie root) element of the list.
	 * @return the current Question of the deck.
	 */
	public Question getCurrentQuestion() {
		return root.question;
	}

	/**
	 * Sets all the success counter of all the questions in the deck to 0 If the
	 * root element is null, process stops and returns 1. If a null
	 * pointerExcpetion is encountered (poorly initialised question), returns 2.
	 * If no problem encountered, returns 0.
	 * 
	 * @return 0 if operation succeeded, 1 if deck is empty, 2 if a question is 'null' in a DeckEle of the Deck.
	 */
	public int resetSuccess() {
		// Checking the first element
		if (root.hasQuestion()) {
			root.question.success = 0;
			root.question.failed = 0;
		} else {
			System.out.println("Empty deck");
			return 1;
		}

		DeckEle cur = root;
		// Going through all the next elements
		while (cur.hasNext()) {
			cur = cur.next;
			try {
				cur.question.success = 0;
				cur.question.failed = 0;
			} catch (NullPointerException e) {
				System.out.println("Empty question");
				return 2;
			}
		}
		return 0;
	}

	/**
	 * Allows to set the root of the deck to a certain deckElement. It also
	 * updates the value of last to the last non null next DeckEle.
	 * 
	 * @param deckEle
	 *            the new root
	 */
	public void setRoot(DeckEle deckEle) {
		this.root = deckEle;
		DeckEle e = root;
		while (e.hasNext()) {
			e = e.next;
		}
		this.last = e;
	}

	/**
	 * Gives the root element of the deck
	 * 
	 * @return root (ie current DeckEle.
	 * @see #getCurrentQuestion() to get the current question directly
	 */
	public DeckEle getRoot() {
		return this.root;
	}
	
	/**
	 * Gives the number of DeckElement contained in the deck.
	 * If the deck is empty, will return 0.
	 * @return the number of DeckEle contained in the deck.
	 */
	public int size () {
		int c = 0;
		
		if(!this.isEmpty()) {
			this.rewind();
			
			while (root.hasNext()) {
				c++;
				this.next();
			}
		}
		
		return c;
	}

	/**
	 * Indicated if two decks are identical, that is if every single one of their questions are the same.
	 * Providing at least one null parameter will return false.
	 * @param a a deck
	 * @param b an other deck
	 * @return true if a and b contain the same questions, false otherwise or if either one or both of them are null
	 */
	public static boolean areIdentical(Deck a, Deck b) {
		try {
			a.rewind();
			b.rewind();
		} catch (NullPointerException e) {
			return false;
		}
		
		Object [] l1 = a.getAllQuestions().toArray();
		Object [] l2 = b.getAllQuestions().toArray();
		
		if (l1.length == l2.length ) {
			for (int i=0; i<l1.length; i++) {
				if (!Question.areIdentical((Question)l1[i],(Question) l2[i])) {
					return false;
				}
			}
		} else {
			return false;
		}
		
		return true;
	}
	
	public static void main (String [] args) {
		Question q1 = new Question("Q", "a", new String [] {"a","a","a"}, "Blof");
		Question q2 = new Question("Q", "a", new String [] {"a","a","a"}, "Blof");
		
		Deck d1 = new Deck();
		d1.addQuestion(q2);
		d1.addQuestion(q2);
		
		Deck d2 = new Deck();
		d2.addQuestion(q1);
		d2.addQuestion(q2);
		
		System.out.println(areIdentical(d1, d2));
	}
}
