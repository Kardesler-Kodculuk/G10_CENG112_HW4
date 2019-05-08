package utility;

public class ArrayOperations {
	public static <T extends Comparable<T>> void sort(T[] array) {
		for (int startingIndex = 0; startingIndex < array.length; startingIndex++) {
			for (int i = array.length; i > startingIndex; startingIndex--) {
				T current = array[i];
				if (current.compareTo(array[i - 1]) <= 0) {
					array[i] = array[i - 1];
					array[i - 1] = current;
				}
			}
		}
	}
}
