package editor;

import java.util.ArrayList;

import util.DeckEle;

public interface EditorListener {

	/**
	 * Clears all the fields in the Editor.
	 * Beware as unsaved content will be lost!
	 */
	public void newDeck ();
	
	/**
	 * Opens a dialog to open an existing deck in the file system.
	 * Beware that any unsaved content in the editor will be lost if a Deck is successfully loaded!
	 */
	public void openDeck();
	
	/**
	 * Opens a dialog to save the current content of the editor to a file.
	 */
	public void saveDeck();
	
	/**
	 * Gives an ArrauList of DeckEle whose question contain at least 1 empty field.
	 * If no such element is found, will return an empty ArrayList (size() method will return 0, isEmpty() method will return true).
	 * @return an ArrayList<DeckEle> containing the questions which have at least one empty field.
	 * @see DeckEle#hasEmptyFieldInQuestion()
	 */
	public ArrayList<DeckEle> checkEmptyQuestion();

	/**
	 * Stores the current content of the Editor to the current DeckEle.
	 */
	public void storeCurrentQuestion();
	
	/**
	 * Creates a new empty question where the editor currently is and pushes the current question and all the following to the right.
	 */
	public void insertQuestion();
	
	/**
	 * Deletes the current question and joins the question next to it.
	 */
	public void removeQuestion();
	
	/**
	 * Tries to open the UserManual in the user's browser.
	 */
	public void openManual();
	
	/**
	 * Checks the current content of the Editor for empty questions.
	 * If some are found, the user will be prompted with a dialog and asked if they want to remove those questions before saving.
	 * The deck will then be saved.
	 * @see #checkEmptyQuestion()
	 * @see #saveDeck()
	 */
	public void removeEmptyQuestionDialogAndSave();
	
	/**
	 * Checks if some questions in the Editor were modified but not saved. 
	 * This method is a safety net to be called before the content of the Editor is overwritten.
	 * Typically this method should be called before newDeck(), openDeck() and before shutting the Editor window.
	 * If some content was not saved, a dialog will show up and ask the user if they want to save the current deck, or not (and keep going) or cancel 
	 * @return 0 if no content was to be saved, 1 if user wants to save content, 2  if user doesn't want to save, 3 if cancel.
	 */
	public int checkForUnsavedContent();
}