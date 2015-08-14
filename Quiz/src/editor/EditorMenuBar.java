package editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class EditorMenuBar extends JMenuBar {

	/**
	 * Menus contained in the this
	 */
	private JMenu fileMenu, editMenu, aboutMenu;
	/**
	 * Menu items contained in the menus.
	 */
	private JMenuItem newDeck, openDeck, saveDeck, insertQuestion, removeQuestion, creditsItem, userManualItem;
	
	private EditorListener listener;
	
	public EditorMenuBar (EditorListener l) {
		super();
		
		this.listener = l;
		
		// Create the file menu
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		this.add(fileMenu);

		newDeck = new JMenuItem("New Deck", KeyEvent.VK_N);
		newDeck.addActionListener(new MenuListener());
		fileMenu.add(newDeck);

		openDeck = new JMenuItem("Open Deck", KeyEvent.VK_O);
		openDeck.addActionListener(new MenuListener());
		fileMenu.add(openDeck);

		saveDeck = new JMenuItem("Save Deck", KeyEvent.VK_S);
		saveDeck.addActionListener(new MenuListener());
		fileMenu.add(saveDeck);

		// Create the edit menu
		editMenu = new JMenu("Edit");
		editMenu.setMnemonic(KeyEvent.VK_E);
		this.add(editMenu);

		insertQuestion = new JMenuItem("Insert Question", KeyEvent.VK_I);
		insertQuestion.addActionListener(new MenuListener());
		editMenu.add(insertQuestion);

		removeQuestion = new JMenuItem("Remove Question", KeyEvent.VK_R);
		removeQuestion.addActionListener(new MenuListener());
		editMenu.add(removeQuestion);
				
		//Creating the About menu
		aboutMenu = new JMenu("About");
		aboutMenu.setMnemonic(KeyEvent.VK_A);
		this.add(aboutMenu);
				
		creditsItem = new JMenuItem("Credits", KeyEvent.VK_C);
		creditsItem.getAccessibleContext().setAccessibleDescription("Read the credits");
		creditsItem.addActionListener(new MenuListener());
		aboutMenu.add(creditsItem);
				
		userManualItem = new JMenuItem("User Manual", KeyEvent.VK_M);
		userManualItem.getAccessibleContext().setAccessibleDescription("Open the user manual in your browser");
		userManualItem.addActionListener(new MenuListener());
		aboutMenu.add(userManualItem);
	}
	
	/**
	 * This class handles the MenuBar events and defines what to do with each
	 * JMenuItem. If any other button was being added, it should be added as
	 * ActionListener this private class, unless of course if that system was to
	 * change which is entirely possible !
	 * 
	 * @author Patrick
	 */
	private class MenuListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();

			// If newDeck was pressed
			if (o == newDeck) {
				listener.newDeck();
				return;
			}

			//If open deck was clicked
			if (o == openDeck) {
				listener.openDeck();
			}

			if (o == saveDeck) {
				listener.removeEmptyQuestionDialogAndSave();
			}

			// The current element gets pushed to the right and a new (and
			// empty) question is loaded
			if (o == insertQuestion) {
				listener.insertQuestion();
			}

			if (o == removeQuestion) {
				listener.removeQuestion();
			}
			
			if (o == creditsItem) {
				listener.openManual();
			}

			if (o == userManualItem) {
				listener.openManual();
			}
		}
	}
}