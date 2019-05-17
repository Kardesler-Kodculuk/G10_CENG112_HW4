package utility;

import internals.IMedia;

public class ArrayOperations {
	public static <T extends Comparable<? super T>> void sort(T[] feedingArray) {
		for (int startingIndex = 0; startingIndex < feedingArray.length; startingIndex++) {
			for (int i = (feedingArray.length - 1); i > startingIndex; i--) {
				T current = feedingArray[i];
				if (current.compareTo(feedingArray[i - 1]) <= 0) {
					feedingArray[i] = feedingArray[i - 1];
					feedingArray[i - 1] = current;
				}
			}
		}
	}
	
	public static <T extends Comparable<? super T>> T[] reverse(T[] sortedArray) {
		T[] reversedArray = (T[]) new Comparable[sortedArray.length];
		int mirrorIndex;
		for (int i = 0; i < sortedArray.length; i++) {
			mirrorIndex = (sortedArray.length - 1) - i;
			reversedArray[mirrorIndex] = sortedArray[i];
		}
		return reversedArray;
	}
	
	public static <T, K extends T> int countFrequency(K keyObject, T[] sortedArray) {
		int frequency = 0;
		for (T item : sortedArray) {
			if (item.getClass().equals(keyObject.getClass())) {
				frequency++;
			}
		}
		return frequency;
	}
	
	public static <T, K extends T> K[] filter(K keyObject, T[] sortedArray) {
		int frequencyOfK = countFrequency(keyObject, sortedArray);
		K[] filteredArray = (K[]) new Object[frequencyOfK];
		int i = 0;
		for (T item : sortedArray) {
			if (item.getClass().equals(keyObject.getClass())) {
				filteredArray[i] = (K) item;
				i++;
			}
		}
		return filteredArray;
	}
	
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
