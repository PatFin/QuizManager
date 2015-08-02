package editor;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import util.*;

/**
 * This class is essentially a tool to edit decks. It allows the user to easily
 * open existing decks and change/insert/remove questions and to store them as a
 * Deck file (*.dk). It is not totally safe yet, for instance there are no
 * warning messages when the user is about to lose the current content if he
 * opens another deck. This is yet to be done.
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
public class DeckEditor extends JFrame {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -6703163134409694786L;

	private URL manualURL;
	public final String editorManual = "http://patfin.github.io/QuizManager/QuizManager_Manual_v1.0.html#EDITOR";
	
	private Deck deck;
	private DeckEle currentDeckEle;

	private int currentCard;

	private JLabel title = new JLabel();

	private JLabel queL = new JLabel("Question:");
	private JLabel ansL = new JLabel("Answer:");
	private JLabel wroL1 = new JLabel("Wrong answer:");
	private JLabel wroL2 = new JLabel("Wrong answer:");
	private JLabel wroL3 = new JLabel("Wrong answer:");
	private JLabel expL = new JLabel(" Explanation:");

	private JTextField queT = new JTextField(25);
	private JTextField ansT = new JTextField(25);
	private JTextField wro1T = new JTextField(25);
	private JTextField wro2T = new JTextField(25);
	private JTextField wro3T = new JTextField(25);

	private JTextArea explT = new JTextArea(3, 25);
	private JScrollPane scrollPaneExplanation = new JScrollPane(explT);

	private JButton prev = new JButton("Prev");
	private JButton next = new JButton("Next");

	private JPanel p1 = new JPanel(new GridLayout(5, 2));
	private JPanel p2 = new JPanel(new GridLayout(1, 2));
	private JPanel p3 = new JPanel(new GridLayout(1, 3));

	private JPanel total = new JPanel();

	private JMenuBar menuBar;
	private JMenu fileMenu, editMenu, aboutMenu;
	private JMenuItem newDeck, openDeck, saveDeck, insertQuestion, removeQuestion, creditsItem, userManualItem;

	/**
	 * Constructor No parameter needed.
	 */
	public DeckEditor() {
		super("Deck Editor");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());

		try {
			manualURL = new URL(editorManual);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		// Creating the menuBar
		menuBarInit();

		// Putting the elements into the frame
		total.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Title at the top
		c.gridy = 0;
		total.add(title, c);

		// Fields for question and answers on a second row
		c.gridy = 1;

		p1.add(queL);
		p1.add(queT);
		p1.add(ansL);
		p1.add(ansT);
		p1.add(wroL1);
		p1.add(wro1T);
		p1.add(wroL2);
		p1.add(wro2T);
		p1.add(wroL3);
		p1.add(wro3T);
		total.add(p1, c);

		// Field for explanation
		c.gridy = 2;
		p2.add(expL);
		p2.add(scrollPaneExplanation);
		total.add(p2, c);

		// Field for buttons at the bottom
		c.gridy = 3;
		p3.add(prev);
		p3.add(next);
		total.add(p3, c);

		this.add(total, BorderLayout.CENTER);

		// Addding the action listeners
		prev.addActionListener(new ButtonPressed());
		next.addActionListener(new ButtonPressed());

		// Create a new deck
		newDeck();

		// Disabling the prev button to avoid crash
		prev.setEnabled(false);

		this.pack();
		this.setMinimumSize(this.getSize());
	}

	/**
	 * Creates the MenuBar. Method called only in the constructor.
	 */
	private void menuBarInit() {
		// Create the menubar
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		// Create the file menu
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(fileMenu);

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
		menuBar.add(editMenu);

		insertQuestion = new JMenuItem("Insert Question", KeyEvent.VK_I);
		insertQuestion.addActionListener(new MenuListener());
		editMenu.add(insertQuestion);

		removeQuestion = new JMenuItem("Remove Question", KeyEvent.VK_R);
		removeQuestion.addActionListener(new MenuListener());
		editMenu.add(removeQuestion);
		
		//Creating the About menu
		aboutMenu = new JMenu("About");
		aboutMenu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(aboutMenu);
		
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
	 * Clears all the questions and sets a new one.
	 */
	public void newDeck() {
		this.currentDeckEle = new DeckEle(new Question());
		loadDeckElement(currentDeckEle);
		currentCard = 1;
		prev.setEnabled(false);
		updateTitle();
	}

	/**
	 * Updates the field values of this window to that of the question in the
	 * DeckEle. It also sets the current DeckElement to the one provided as
	 * parameter.
	 * 
	 * @param e
	 *            the DeckElement we are going to work with
	 */
	private void loadDeckElement(DeckEle e) {
		this.currentDeckEle = e;

		Question q = e.getQuestion();
		this.queT.setText(q.question);
		this.ansT.setText(q.answer);
		this.wro1T.setText(q.wrong[0]);
		this.wro2T.setText(q.wrong[1]);
		this.wro3T.setText(q.wrong[2]);
		this.explT.setText(q.explanation);
	}

	/**
	 * Opens a deck in the editor from a file which will be chosen by the user
	 * via a JFileChooser
	 */
	public void openDeck() {
		JFileChooser fs = new JFileChooser(new File("user"));
		fs.setDialogTitle("Choose a deck");
		fs.setFileFilter(new DeckFileFilter());

		if (fs.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			String totalFile = "";
			BufferedReader buffReader;
			try {
				File file = fs.getSelectedFile();
				buffReader = new BufferedReader(new FileReader(file.getPath()));
				String line = "";
				while ((line = buffReader.readLine()) != null) {
					totalFile += "\n" + line;
				}
				buffReader.close();
			} catch (Exception error) {
				System.out.println("An error occured while reading the file. It may be corrupted."); // 
				JOptionPane.showMessageDialog(null,
						"An error occured while reading the file.\n"
								+ "It may be corrupted.",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			Deck d = DeckWriter.stringToDeck(totalFile);
			newDeck();
			loadDeckElement(currentDeckEle = d.getRoot());
		}
	}

	/**
	 * Updates the index of the card shown at the top of the editor. To be
	 * called whenever the value of currentCard changes. TODO find a better
	 * 'cleaner' way to update the text -> adding an index to class DeckEle ?
	 */
	private void updateTitle() {
		title.setText("Card " + currentCard);
	}
	
	/**
	 * Checks the current content of the editor for empty fields in some questions.
	 * @return an array containing the card numbers where there is a problem.
	 */
	private ArrayList<DeckEle> checkEmptyQuestion () {
		ArrayList<DeckEle> issues = new  ArrayList <DeckEle> ();
		
		// First we create the deck object.
		deck = new Deck();
		// We go back to the first deckElement
		DeckEle buff = currentDeckEle;
		while (buff.hasPrev()) {
			buff = buff.getPrevious();
		}
		// We set the root to be the first DeckElement!
		deck.setRoot(buff);
		
		//We go through all the elements checking if there are emtpy fields, if so we add them to the ArrayList.
		while (buff.hasNext()) {
			if (buff.hasEmptyFieldInQuestion()) {
				issues.add(buff);
			}
			buff = buff.getNext();
		}
		if(buff.hasEmptyFieldInQuestion()) {
			issues.add(buff);
		}
		
		return issues;
	}
	
	
	/**
	 * This method saves the current deck to a file chosen by the user thanks to the JFileChooser.
	 * It is recommended to check the current deck for empty questions beforehand to avoid saving useless stuff.
	 * @see #checkEmptyQuestions
	 */
	private void saveDeck () {
		// Now the JFileChooser
		JFileChooser fs = new JFileChooser(new File("c:\\user"));
		fs.setDialogTitle("Save current Deck");
		fs.setFileFilter(new DeckFileFilter());

		if (fs.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			String s = DeckWriter.deckToString(deck);
			File file = fs.getSelectedFile();
			try {
				FileWriter fw = new FileWriter(file.getPath());
				fw.write(s);
				fw.flush();
				fw.close();
				JOptionPane.showMessageDialog((Component) null, "Deck "+file.getName()+" saved successfully",
				        "Save complete", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception error) {
				error.printStackTrace();
				JOptionPane.showMessageDialog((Component) null, "Save - Error",
				        "An error occured while saving "+file.getName()+".", JOptionPane.WARNING_MESSAGE);
			}
		}
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
				newDeck();
				return;
			}

			//If open deck was clicked
			if (o == openDeck) {
				openDeck();
				return;
			}

			if (o == saveDeck) {
				// We store the last element created!
				currentDeckEle.setQuestion(new Question(queT.getText(), ansT.getText(),
						new String[] { wro1T.getText(), wro2T.getText(), wro3T.getText() }, explT.getText()));

				//We check the current deck for empty questions and ask the user if he wants to remove those, keep them or cancel.
				ArrayList <DeckEle> issues = checkEmptyQuestion();
				switch (issues.size()) {
				case 0:
					saveDeck();
					return;
				default:
					//Propose to remove those questions, or save anyway or cancel.
					System.out.println(issues.size() + " Questions have empty fields in the current DeckEditor session.");
					
					//TODO open a dialog, switch on the result.
					int result = JOptionPane.showConfirmDialog((Component) null, "Some questions have empt fields. \nWould you like to remove those before saving?","Warning - Empty fields", JOptionPane.YES_NO_CANCEL_OPTION);
					System.out.println(result);
					switch (result) {
					case 0: //remove questions + save
						DeckEle d = deck.getRoot();
						while (issues.contains(d) && d != null) {
							d=d.getNext();
						}
						if (d == null) {
							return; //Display error no questions to save ..?
						}
						d.setPrevious(null);
						deck.setRoot(d);
						
						DeckEle b = d;
						while (b.hasNext()){
							b = b.getNext();
							if (!issues.contains(b)) {
								d.setNext(b);
								d=b;
							}
						}
					case 1: //save directly
						deck.rewind();
						saveDeck();
						break;
					default:	//Cancel 
						return;
					}
				}
			}

			// The current element gets pushed to the right and a new (and
			// empty) question is loaded
			if (o == insertQuestion) {
				DeckEle insert = new DeckEle(new Question());

				if (currentDeckEle.hasPrev()) {
					insert.setPrevious(currentDeckEle.getPrevious());
				}
				insert.setNext(currentDeckEle);

				loadDeckElement(currentDeckEle = insert);
				return;
			}

			if (o == removeQuestion) {
				if (currentDeckEle.hasNext()) {
					if (currentDeckEle.hasPrev()) {
						currentDeckEle.getPrevious().setNext(currentDeckEle.getNext());
						currentDeckEle = currentDeckEle.getNext();
					} else {
						currentDeckEle.getNext().setPrevious(null);
						currentDeckEle = currentDeckEle.getNext();
					}
				} else {
					if (currentDeckEle.hasPrev()) {
						currentDeckEle = currentDeckEle.getPrevious();
						currentDeckEle.setNext(null);
						currentCard--;
						if (!currentDeckEle.hasPrev()) {
							prev.setEnabled(false);
						}
					} else {
						currentDeckEle = new DeckEle(new Question());
					}
				}
				updateTitle();
				loadDeckElement(currentDeckEle);
			}
			
			if (o == creditsItem) {
				new Credits();
			}

			if (o == userManualItem) {
				Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
			    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			        try {
			            desktop.browse(manualURL.toURI());
			        } catch (Exception exc) {
			            exc.printStackTrace();
			        }
			    }
			}
		}
	}

	/**
	 * This private class handles the behaviour of the two buttons contained by
	 * the window. There are two buttons in the interface (text being Prev and
	 * Next). They allow the user to navigate from a question to another and
	 * that's what's written in this class.
	 * 
	 * @author Patrick
	 */
	private class ButtonPressed implements ActionListener {

		/**
		 * Whichever button is pressed, the content of the fields are stored to
		 * a question in the deck Then depending on whether next or previous
		 * button was pressed, we go and get the corresponding question.
		 * 
		 * @param e
		 *            ActionEvent from the button which has been pressed.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			currentDeckEle.setQuestion(new Question(queT.getText(), ansT.getText(),
					new String[] { wro1T.getText(), wro2T.getText(), wro3T.getText() }, explT.getText()));

			Object o = e.getSource();
			// If next button was pressed
			if (o == next) {
				// We check if a next element was created, if not we create it.
				if (!currentDeckEle.hasNext()) {
					currentDeckEle.setNext(new DeckEle(new Question()));
				}
				loadDeckElement(currentDeckEle.getNext());
				prev.setEnabled(true); // There exists a previous element so we
										// set it enable. It might already be
										// enabled but I believe it is quicker
										// to just set it true than testing if
										// it is false and changing. Feel free
										// to correct me if I'm wrong !
				currentCard++;
				updateTitle();
			}

			// If the previous button was pressed
			if (o == prev) {
				loadDeckElement(currentDeckEle.getPrevious());

				// If we ended up at the first question, there are no previous
				// question -> we need to disable the prev button to avoid
				// errors.
				if (!currentDeckEle.hasPrev()) {
					prev.setEnabled(false);
				}
				currentCard--;
				updateTitle();
			}
		}
	}

	/**
	 * Launches a new DeckEditor
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new DeckEditor().setVisible(true);
		;
	}

}