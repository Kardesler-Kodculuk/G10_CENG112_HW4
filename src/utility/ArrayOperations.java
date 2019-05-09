package utility;

import internals.IMedia;

public class ArrayOperations {
	public static <T extends Comparable<? super T>> void sort(T[] feedingArray) {
		for (int startingIndex = 0; startingIndex < feedingArray.length; startingIndex++) {
			for (int i = feedingArray.length; i > startingIndex; startingIndex--) {
				T current = feedingArray[i];
				if (current.compareTo(feedingArray[i - 1]) <= 0) {
					feedingArray[i] = feedingArray[i - 1];
					feedingArray[i - 1] = current;
				}
			}
		}
	}
}
