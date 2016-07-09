package util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * Deck file filter (*.dk). File filter used in the various JFileChooser of the
 * program to filter all the files but the Deck file (extension *.dk). It
 * extends FileFilter and overrides the two methods getDescription and accept.
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
public class DeckFileFilter extends FileFilter {

	/*
	 * (non-Javadoc)
	 * @see javax.swing.filechooser.FileFilter#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Deck file (*.dk)";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File file) {
		if (file.isDirectory()) {
			return true;
		}
		return file.getName().endsWith(".dk");
	}
}
