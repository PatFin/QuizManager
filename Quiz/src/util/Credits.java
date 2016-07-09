/**
 *  Created by Patrick Finnerty, this program allows for the creation of quiz for (but not restricted to) learning purposes.
    Copyright (C) 2015  Patrick Finnerty

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package util;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Window to show credits and the GNU general public license
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
public class Credits extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2333414264056722443L;

	private JTextArea text;
	private JScrollPane scrollPane;

	public Credits() {
		super("Credits");
		text = new JTextArea();
		scrollPane = new JScrollPane(text);

		this.add(scrollPane);

		text.setText("Created by Patrick Finnerty, this program allows for the creation \n"
				+ "of multiple choice quizzes for (but not restricted to) learning purposes.\n" 
				+ "Copyright (C) 2015  Patrick Finnerty\n"
				+ "If you happen to like this software and wish it could do other things or if you encounter a bug,\n"
				+ "please send me an e-mail at the adress mentioned just below. I will try to answer you as soon as possible.\n"
				+ "You can also ask for a copy of the source code by sending me an e-mail at finnertypatri@aol.fr \n" 
				+ "Just make sure that you use this code is allowed by the GNU general public license (see below). \n"

				+"\nThis program is free software: you can redistribute it and/or modify\n"
				+ "it under the terms of the GNU General Public License as published by\n"
				+ "the Free Software Foundation, either version 3 of the License, or\n"
				+ "(at your option) any later version.\n" +

		"This program is distributed in the hope that it will be useful,\n"
				+ "but WITHOUT ANY WARRANTY; without even the implied warranty of\n"
				+ "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n"
				+ "GNU General Public License for more details.\n" +

		"You should have received a copy of the GNU General Public License\n"
				+ "along with this program.  If not, see <http://www.gnu.org/licenses/>.");
		text.setFont(new Font(text.getFont().getName(), text.getFont().getStyle(), 15));
		text.setEditable(false);
		this.setSize(500, 600);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
}