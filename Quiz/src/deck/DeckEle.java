package deck;

/**
 * This class is the link from which the kind of linkeList class Deck @see Deck
 * aremade of. Each DeckEle contains: _ a question @see Question _ a previous
 * and a next DeckEle @see DeckEle which can be null
 * 
 * It contains method useful in setting next and previous elements, checking if
 * those elements exists. It contains mehtods to set and get the question
 * contained by this object.
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
public class DeckEle {

	Question question;
	DeckEle next;
	DeckEle prev;

	/**
	 * Constructor
	 */
	public DeckEle() {
		question = null;
		next = null;
	}

	/**
	 * Constructor
	 * 
	 * @param aQ
	 *            the question associated to that
	 */
	public DeckEle(Question aQ) {
		question = aQ;
		next = null;
	}

	/**
	 * Returns true if this element has a non null next element
	 * 
	 * @return true if next != null, false otherwise.
	 */
	public boolean hasNext() {
		return next != null;
	}

	/**
	 * Returns true if the previous element is not null, false otherwise.
	 * 
	 * @return true if field prev != null, false otherwise.
	 */
	public boolean hasPrev() {
		return prev != null;
	}

	/**
	 * Returns true if the DeckElement has a non null question
	 * 
	 * @return true if this has a non null question, false otherwise.
	 */
	public boolean hasQuestion() {
		return this.question != null;
	}

	/**
	 * Sets the question contained by the element to the question provided as
	 * argument.
	 * 
	 * @param q
	 *            the new question to be stored
	 */
	public void setQuestion(Question q) {
		this.question = q;
	}

	/**
	 * Gives the next deckElement. Be careful, it might return null!
	 * 
	 * @return the next DeckElement.
	 */
	public DeckEle getNext() {
		return this.next;
	}

	/**
	 * Sets the next deckElement. It also sets the previous element of the next
	 * one to this. If parameter is null, it will set the next to null without
	 * trying to change to this the next element (null)
	 * 
	 * @param deckEle
	 *            the next DeckElement
	 */
	public void setNext(DeckEle deckEle) {
		this.next = deckEle;
		if (deckEle != null) {
			this.next.prev = this;
		}
	}

	/**
	 * Sets the previous element. It also sets the next element of the previous
	 * one to this. If parameter is null, it will set the previous to null.
	 * 
	 * @param deckEle
	 *            the previous DeckElement
	 */
	public void setPrevious(DeckEle deckEle) {
		this.prev = deckEle;
		if (deckEle != null) {
			this.prev.next = this;
		}
	}

	/**
	 * Gives the question contained by the DeckElement
	 * 
	 * @return the question
	 */
	public Question getQuestion() {
		return this.question;
	}

	/**
	 * Gives the previous element. Careful, it will return null if no previous
	 * element was initialised.
	 * 
	 * @return
	 */
	public DeckEle getPrevious() {
		return this.prev;
	}
}