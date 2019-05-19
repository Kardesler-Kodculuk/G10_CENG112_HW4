package utility;

public class ArrayOperations {
	
	/**
	 * Convert the array to its String representation.
	 * @param array
	 * @return
	 */
	public static <T> String convertToString(T[] array) {
		String outputString = "[";
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null) {
				continue;
			}
			outputString += array[i].toString();
			if (i < array.length - 1) {
				outputString += ", ";
			} else {
				outputString += "]";
			}
		}
		return outputString;
	}
}
