package player;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import util.Deck;

/*
 * This panel ask for the user if he wants to repeat the whole quiz he just did, or only repeat the questions he had problems with or go for an other deck.
 */
@SuppressWarnings("serial")
public class EndQuizPanel extends JPanel {
	
	private JTextArea message;
	private JButton repeatAll, repeatDiff, loadOther;
	
	/**
	 * Contains the decks that may (or may not) be chosen as the following one...
	 */
	private Deck full, diff;
	
	/**
	 * Container of this, this can make requests to that container.
	 */
	private RequestToFrame request;
	
	public EndQuizPanel (Deck fullDeck, Deck difficult, String aMessage, RequestToFrame r) {
		super();
		
		full = fullDeck;
		diff = difficult;
		request = r;
		Dimension d = new Dimension(100,40);
		
		this.setLayout (new GridLayout(2, 1));
		
		//Putting the message
		this.message = new JTextArea (aMessage +"\n"+"\n"+
				"You can now decide to repeat the whole deck once more.\n"+
				"Or you can choose to study only the questions you\n"+
				"struggled with. Or choose a different deck !\n"+
				"It's up to you!");
		this.message.setEditable(false);
		this.add(message);
		
		//Putting the buttons ...
		Box b = new Box(BoxLayout.LINE_AXIS);
		
		repeatAll = new JButton("All");
		repeatAll.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent a) {
				request.requestQuizSameType(full);
			}
		});
		repeatAll.setPreferredSize(d);
		b.add(repeatAll);
		
		repeatDiff = new JButton ("Difficult");
		repeatDiff.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent a) {
				request.requestQuizSameType(diff);
			}
		});
		repeatDiff.setEnabled(!diff.isEmpty());
		repeatDiff.setPreferredSize(d);
		b.add(repeatDiff);
		
		loadOther = new JButton("Other deck");
		loadOther.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent a) {
				request.requestLoadOtherQuiz();
			}
		});
		loadOther.setPreferredSize(d);
		b.add(loadOther);
		b.setAlignmentX(CENTER_ALIGNMENT);
		JPanel p = new JPanel ();
		p.add(b);
		
		this.add(p);
	}
}