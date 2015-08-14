package util;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.swing.JOptionPane;

import player.LearningQuizPanel;

public class ConfigWriter {

	public final static String success = "[Success]:=";
	public final static String fail = "[Failures]:=";
	
	public final static String path = "Preferences.cf";
	
	/**
	 * Allows to save the values of the LearningModeQuiz
	 * @param s number of success to pass a question,
	 * @param f number of failures to have the question added to difficult quiz.
	 */
	public static void savePreferences() {
		int s = LearningQuizPanel.successNeededForMearningMode;
		int f = LearningQuizPanel.failuresNeededForLearningMode;
		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	        new FileOutputStream(path), "utf-8"))) {
			writer.write(success + s+"\n"+fail + f);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog((Component) null, "Error",
			        "An error occured while saving your preferences. \nThis is mostly harmless but modified configuration might not be there \n next time you launch the software.", JOptionPane.WARNING_MESSAGE);
			return;
		}
		System.out.println("Preferences saved:");
		System.out.println("Success:"+s);
		System.out.println("Failures:"+f);
	}
	
	
	/**
	 * Allows to get the saved values for the configuration of the software.
	 * This is called in the constructor of the MainFrame to get the previous values 
	 * for the learning mode Quiz Panel.
	 */
	public static void loadPreferences () {
		BufferedReader reader;
		try {
			File f = new File(path);
			reader = new BufferedReader(new FileReader(f.getPath()));
			String line;
			while((line = reader.readLine()) != null) {
				if (line.startsWith(success)) {
						String toInteger = line.substring(success.length());
						LearningQuizPanel.successNeededForMearningMode = Integer.parseInt(toInteger);
						System.out.println("Loaded: "+success+LearningQuizPanel.successNeededForMearningMode);
				}
				
				if (line.startsWith(fail)) {
					String toInteger = line.substring(fail.length());
					LearningQuizPanel.failuresNeededForLearningMode = Integer.parseInt(toInteger);
					System.out.println("Loaded: "+fail+LearningQuizPanel.failuresNeededForLearningMode);
				}
				
			}
		} catch (Exception e) {
			System.out.println("Error encountered while reading file "+path);
			System.out.println("Creating a preferences file instead.");
			savePreferences();
		}
	}
}
