package utility;

import internals.IMedia;

public class ArrayOperations {

	/**
	 * Written specifically to reverse the media array.
	 * @param array
	 * @return
	 */
	protected static IMedia[] reverseMediaArray(IMedia[] array) {
		IMedia[] reversedArray = new IMedia[array.length];
		int lastIndex = array.length - 1;
		for (int i = 0; i < array.length; i++) {
			reversedArray[i] = array[lastIndex - i];
		}
		return reversedArray;
	}

	/**
	 * Get the frequency of the book/movie objects inside media array.
	 * @param array IMedia array
	 * @param type Type, either book or movie
	 * @return Count of Movie/Book
	 */
	protected static int getFrequencyOfMediaObject(IMedia[] array, String type) {
		int frequencyCount = 0;
		for (IMedia mediaObj : array) {
			if (mediaObj.mediaType().equals(type)) {
				frequencyCount++;
			}
		}
		return frequencyCount;
	}
	
	/**
	 * Filter the IMedia array into a Book/Movie array.
	 * @param keyObj Object of movie or book.
	 * @param array IMedia array.
	 * @return
	 */
	protected static<K extends IMedia> IMedia[] filterArray(K keyObj, IMedia[] array) {
		String type = keyObj.mediaType();
		int frequencyOfKinIMeida = getFrequencyOfMediaObject(array, type);
		IMedia[] exportArray =  new IMedia[frequencyOfKinIMeida];
		int j = 0;
		for (IMedia mediaObj : array) {
			if (mediaObj.mediaType().equals(type)) {
				exportArray[j] = mediaObj;
				j++;
			}
		}
		return exportArray;
	}

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
