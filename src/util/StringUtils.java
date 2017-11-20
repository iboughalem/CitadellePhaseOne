package util;

public class StringUtils {

	/**
     * Check if the string is numeric.
     *
     * @param string the string to test
     * @return if the string is numeric
     */
    public static boolean isNumeric(final String string) {
        return string.matches("[0-9]+");
    }

    /**
     * Check if the string is alphabet.
     *
     * @param string the string to test
     * @return if the string is alphabet
     */
    public static boolean isAlphabet(final String string) {
        return string.matches("[a-z-A-Z]+");
    }
}
