package editor;

import java.util.ArrayList;

import util.DeckEle;

public interface EditorListener {

	public void newDeck ();
	
	public void openDeck();
	
	public void saveDeck();
	
	public ArrayList<DeckEle> checkEmptyQuestion();

	public void storeCurrentQuestion();
	
	public void insertQuestion();
	
	public void removeQuestion();
	
	public void openManual();
	
	public void removeEmptyQuestionDialogAndSave();
}