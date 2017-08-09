package ru.github.vastap;

/**
 * Class with methods for experiments with Strings and bytecode
 */
public class Strings {

	/**
	 * Compile this class (for example: Build -> Recompile in Idea).<br>
	 * Look at bytecode (for example: View -> Bytecode in Idea).<br>
	 *
	 * @param a String for concatenation
	 * @param b String for concatenation
	 * @return Result of strings concatenation
	 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/String.html">String Java API</a>
	 * @see <a href="https://docs.oracle.com/javase/specs/jls/se8/html/jls-15.html#jls-15.18.1">JSE8 JLS</a>
	 */
	public String plusConcat(String a, String b) {
		String result = a + b;
		return result;
	}

	/**
	 * String concat method just creates new String object as ArrayCopy of char arrays of objects.
	 *
	 * @param a String for concatenation
	 * @param b String for concatenation
	 * @return Result of concatenation
	 */
	public String concat(String a, String b) {
		String result = a.concat(b);
		return result;
	}

}
