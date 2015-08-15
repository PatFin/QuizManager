package util;

public class Util {

	/**
	 * Compares two Strings and returns true if they are identical.
	 * This method compares the two Strings given as parameter character by character.
	 * If the two Strings do not have the same length or if at least one of the characters of the String are different, this method will return false.
	 * Otherwise it will return true.
	 * @param a String to compare
	 * @param b other String to compare
	 * @return true if a and b are identical, false otherwise
	 */
	public static boolean compareString (String a, String b) {
		if (a.length() == b.length()) {
			for (int i = 0; i<a.length(); i++) {
				if (a.charAt(i) != b.charAt(i)) {
					return false;
				}
			}
		} else {
			return false;
		}
		
		return true;
	}
}
