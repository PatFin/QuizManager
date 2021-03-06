package player;

import java.awt.CardLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import util.ConfigWriter;
import util.Credits;
import util.Deck;
import util.DeckFileFilter;
import util.DeckWriter;

/**
 * This class contains and handles quiz. It allows to open quiz files (*.dk) and
 * to play the quiz. Options can be changed such as the number of good answers
 * required to pass a question. It can also open a Editor window to edit decks
 * or create new one.
 * 
 * @see editor.DeckEditor
 * @author Patrick 
 * Created by Patrick Finnerty, this program allows for the
 * creation of quiz for (but not restricted to) learning purposes.
 * Copyright (C) 2015 Patrick Finnerty
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
public class MainFrame extends JFrame implements RequestToFrame {

	
	private URL manualURL;
	public final String QMmanual = "http://patfin.github.io/QuizManager/QuizManager-v1.1_index.html";
	
	private static final long serialVersionUID = -5564000254022541818L;

	private JMenuBar menuBar;
	private JMenu fileMenu, preferencesMenu, aboutMenu;
	private JMenuItem loadDeckItem, optionItem, creditsItem, userManualItem;

	private QuizPanel quizPanel;
	private MessagePanel message;
	private DeckModeChoicePanel gameModeChoice;
	private EndQuizPanel endQuiz;

	private JPanel clPanel;
	final static String MESSAGE = "mess";
	final static String QUIZ = "quiz";
	final static String MODE = "mode";
	final static String ENDQUIZ = "end";

	private final SettingsWindow settings;
	/**
	 * Constructor No parameters needed. Will lead to a blank window with only a
	 * welcome message
	 */
	public MainFrame() {
		super("Quiz Player 1.1");
		this.setResizable(true);
		this.setPreferredSize(new Dimension(500, 600));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		try {
			manualURL = new URL(QMmanual);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		jMenuBarInit();

		clPanel = new JPanel();
		clPanel.setLayout(new CardLayout(1, 1));
		
		ConfigWriter.loadPrefs();
		
		this.quizPanel = new LearningQuizPanel(this);
		this.message = new MessagePanel("");
		this.gameModeChoice = new DeckModeChoicePanel(this);
		clPanel.add(message, MESSAGE);
		clPanel.add(quizPanel, QUIZ);
		clPanel.add(gameModeChoice, MODE);

		this.add(clPanel);
		this.requestMessage("Welcome to Quiz Player");
		this.pack();
		
		Dimension d = this.getContentPane().getSize();
		message.setSize(d);
		quizPanel.setSize(d);
		gameModeChoice.setSize(d);
		
		settings = new SettingsWindow();
	}

	/**
	 * Creates the menu bar. To be called in the constructor
	 */
	private void jMenuBarInit() {
		// Creating the JMenuBar
		menuBar = new JMenuBar();

		// Creating File Menu
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.getAccessibleContext().setAccessibleDescription("Menu for deck loading options");

		loadDeckItem = new JMenuItem("Load Deck...", KeyEvent.VK_L);
		loadDeckItem.getAccessibleContext().setAccessibleDescription("Load a deck from an existing file.");
		loadDeckItem.addActionListener(new MenuListener());
		fileMenu.add(loadDeckItem);

		// Creating Preferences menu
		preferencesMenu = new JMenu("Preferences");
		preferencesMenu.setMnemonic(KeyEvent.VK_P);
		preferencesMenu.getAccessibleContext().setAccessibleDescription("For settings regarding QuizManager");
		preferencesMenu.addActionListener(new MenuListener());

		optionItem = new JMenuItem("Options", KeyEvent.VK_O);
		optionItem.getAccessibleContext().setAccessibleDescription(
				"Let you choose after how many successful answers you won't see a card again.");
		optionItem.addActionListener(new MenuListener());
		preferencesMenu.add(optionItem);

		// Creating the about menu
		aboutMenu = new JMenu("About");
		aboutMenu.setMnemonic(KeyEvent.VK_H);
		aboutMenu.getAccessibleContext().setAccessibleDescription("When what you are looking for isn't in the other categories.");

		creditsItem = new JMenuItem("Credits", KeyEvent.VK_C);
		creditsItem.getAccessibleContext().setAccessibleDescription("Read the credits");
		creditsItem.addActionListener(new MenuListener());
		
		userManualItem = new JMenuItem("User Manual", KeyEvent.VK_M);
		userManualItem.getAccessibleContext().setAccessibleDescription("Open the user manual in your browser");
		userManualItem.addActionListener(new MenuListener());
		
		aboutMenu.add(creditsItem);
		aboutMenu.add(userManualItem);
		
		menuBar.add(fileMenu);
		menuBar.add(preferencesMenu);
		menuBar.add(aboutMenu);
		this.setJMenuBar(menuBar);
	}

	/**
	 * This method asks the user to open a deck file.
	 * If the operation was successful, it launches the quiz mode choice menu in which the user can choose for a game mode.
	 * The quiz will then launch accordingly.
	 */
	public void loadDeckFromFile() {
		JFileChooser fs = new JFileChooser(new File("c:\\user"));
		fs.setDialogTitle("Choose a Deck");
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
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "An error occured when reading the file "
						+ fs.getSelectedFile().getName() + "\n" + "It may be corrupted.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			// We get in the game mode choice panel
			gameModeChoice.setDeck(DeckWriter.stringToDeck(totalFile));
			this.show(MainFrame.MODE);
		}
	}

	/**
	 * Launches a new quiz
	 * @param d the quiz to be launched
	 * @param i the number of times the card needs to be answered properly before it is not added anymore at the end of the deck
	 */
	private void handleQuiz(Deck d) {
		this.quizPanel.handleQuiz(d);
		this.show(MainFrame.QUIZ);
	}

	/**
	 * This class handles what is to be done when the user presses an Item in
	 * the menuBar
	 * 
	 * @author Patrick Items handled by this class include: loadDeckItem
	 *         createDeckItem editDeckItem optionItem creditsItem
	 */
	private class MenuListener implements ActionListener {

		/**
		 * Decides what to do as a function of the event source.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			/**
			 * Open a JFileChooser to load a deck (*.dk) file and launch the
			 * quiz.
			 */
			if (source == loadDeckItem) {
				loadDeckFromFile();
				return;
			}
			
			/**
			 * Launches a the settings window.
			 */
			if (source == optionItem) {
				settings.refreshValues();
				settings.setVisible(true);
			}

			/**
			 * Launches a new Credits window
			 */
			if (source == creditsItem) {
				new Credits();
			}

			/**
			 * Attempts to launch a new tab in the browser, displayiing the user manual page.
			 */
			if (source == userManualItem) {
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

	/*
	 * (non-Javadoc)
	 * @see display.RequestToFrame#requestMessage(java.lang.String)
	 */
	@Override
	public void requestMessage(String s) {
		this.message.setText(s);
		this.show(MainFrame.MESSAGE);
	}

	/*
	 * (non-Javadoc)
	 * @see display.RequestToFrame#requestLearnModeQuiz(deck.Deck)
	 */
	@Override
	public void requestLearnModeQuiz(Deck d) {
		clPanel.remove(quizPanel);
		this.quizPanel = new LearningQuizPanel(this);
		clPanel.add(quizPanel, QUIZ);
		handleQuiz(d);
	}

	/*
	 * (non-Javadoc)
	 * @see display.RequestToFrame#requestTestModeQuiz(deck.Deck)
	 */
	@Override
	public void requestTestModeQuiz(Deck d) {
		clPanel.remove(quizPanel);
		this.quizPanel = new TestQuizPanel(this);
		clPanel.add(quizPanel, QUIZ);
		handleQuiz(d);
	}

	/**
	 * Make the card layout of the mainFrame display the corresponding card. As
	 * parameter, the String field should be MESSAGE or QUIZ which are static
	 * attributes
	 * @param param
	 */
	private void show(String param) {
		CardLayout cl = (CardLayout) clPanel.getLayout();
		cl.show(clPanel, param);
	}

	/**
	 * Launches a new Quiz Window
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new MainFrame().setVisible(true);
		;
	}

	/**
	 * Permet l'affichage de panel de fin de quiz dans le MainFrame.
	 */
	@Override
	public void requestEndQuiz(Deck total, Deck diff, String message) {
		try {
			clPanel.remove(endQuiz);
		} catch (Exception e) {
			//System.out.println("There were no endQuiz panel to be removed");
		}
		this.endQuiz = new EndQuizPanel (total, diff, message, this);
		clPanel.add(endQuiz,ENDQUIZ);
		show(ENDQUIZ);
	}

	/*
	 * (non-Javadoc)
	 * @see player.RequestToFrame#requestLoadOtherQuiz()
	 */
	@Override
	public void requestLoadOtherQuiz() {
		this.loadDeckFromFile();
	}

	/*
	 * (non-Javadoc)
	 * @see player.RequestToFrame#requestQuizSameType(util.Deck)
	 */
	@Override
	public void requestQuizSameType(Deck d) {
		handleQuiz(d);
	}
}