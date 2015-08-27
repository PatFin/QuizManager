package util;

import java.util.prefs.Preferences;

import player.LearningQuizPanel;

public class ConfigWriter {

	public final static String success = "[Success]:";
	public final static String fail = "[Failures]:";
	public final static String skipExpl = "[SkipExplanation]:";
	
	public final static int defaultS = 3;
	public final static int defaultF = 3;
	public final static boolean defaultB = false;
	
	private static Preferences pref = Preferences.userNodeForPackage(player.LearningQuizPanel.class);
	
	/**
	 * Allows to save the values of the LearningModeQuiz
	 * If it is the first time the QuizPlayer is launched by the user, it will create a preference file on the system somewhere.
	 */
	public static void savePrefs () {
		int s = LearningQuizPanel.successNeededForMearningMode;
		int f = LearningQuizPanel.failuresNeededForLearningMode;
		boolean b = LearningQuizPanel.skipExplanationWhenCorrectAnswer;
		
		pref.putInt(success, s);
		pref.putInt(fail, f);
		pref.putBoolean(skipExpl, b);
	}
	
	/**
	 * Allows to get the saved values for the configuration of the software.
	 * This is called in the constructor of the MainFrame to get the previous (or the default) values for the learning mode Quiz Panel.
	 */
	public static void loadPrefs () {
		LearningQuizPanel.successNeededForMearningMode = pref.getInt(success, ConfigWriter.defaultS);
		LearningQuizPanel.failuresNeededForLearningMode = pref.getInt(fail, ConfigWriter.defaultF);
		LearningQuizPanel.skipExplanationWhenCorrectAnswer = pref.getBoolean(skipExpl, defaultB);
	}
}
