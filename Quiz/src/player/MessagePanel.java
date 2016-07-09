package player;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Allows to have a JPanel containing only a JLabel to display some text. TODO
 * allow automatic choice between a JTextArea and a JLabel depending on the size
 * of the text given?
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
public class MessagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1476122255366473534L;

	private JLabel message;

	/**
	 * Constructor Creates a JPanel which contains the String given as a
	 * parameter and of width indicated. The text font will automatically adapt
	 * to the size of the MessagePanel
	 * 
	 * @param text String to be displayed in the Panel
	 */
	public MessagePanel(String text) {
		super();
		this.message = new JLabel(text);
		this.add(message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Component#setSize(java.awt.Dimension)
	 */
	public void setSize(Dimension d) {
		super.setSize(d);
		this.message.setSize(d.width, d.height);
	}

	/**
	 * Method that changes the content of the message.
	 * 
	 * @param s
	 *            the new message of the messagePanel
	 */
	public void setText(String s) {
		this.message.setText(s);
	}
}
