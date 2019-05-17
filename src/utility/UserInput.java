package utility;

import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.Scanner;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import internals.*;
import tree.IBinarySearchTree;
import tree.ITreeIterator;
import tree.IsmailTarator;
import tree.NyanTreeIterator;

public class UserInput {
	
	private Scanner input;
	private IBinarySearchTree<IMedia> tree;
	
	private static ITreeIterator<IMedia> generateIterator(IBinarySearchTree<IMedia> tree) {
		ITreeIterator<IMedia> iterator = new IsmailTarator<IMedia>(tree);
		return iterator;
	}
	
	private String takeUserInput(String prompt) {
		System.out.println(prompt);
		String output = input.nextLine();
		return output;
	}
	
	/**
	 * Return if a given media type fits to the criteria
	 * @param mediaObject - Object to be tested
	 * @param max - Previous max value
	 * @param type - Type of the value we wish
	 * @return True if fits.
	 */
	private static boolean isMax(IMedia mediaObject, int max, String type) {
		if (type.equals("Media")) {
			if (mediaObject.mediaPrice() >= max) {
				return true;
			} else {
				return false;
			}
		} else {
			if (mediaObject.mediaType().equals(type) && mediaObject.mediaPrice()  >= max) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	private static boolean isCreator(IMedia current, String creator) {
		String creatorCheck;
		boolean check;
		switch (current.mediaType()) {
		case "Book":
			creatorCheck = ((Book) current).getAuthor();
			break;
		default:
			creatorCheck = ((Movie) current).getDirector();
			break;
		}
		check = creator.equals(creatorCheck);
		return check;
	}
	
	private static IMedia returnMediaWithHighest(IBinarySearchTree<IMedia> tree, String type, String creator) {
		ITreeIterator<IMedia> iterator = generateIterator(tree);
		int maxPrice = 0;
		IMedia maxMedia = null;
		IMedia current;
		while (iterator.hasNext()) {
			current = iterator.next();
			if (current == null) {
				System.out.println("NULL SHOULDN'T BE THERE M8");
				continue;
			}
			if (isMax(current, maxPrice, type)) {
				if (isCreator(current, creator)) {
					maxPrice = current.mediaPrice();
					maxMedia = current;
				}
			}
		}
		return maxMedia;	
	}
	
	private static IMedia returnMediaWithLowest(IBinarySearchTree<IMedia> tree, String type, String creator) {
		ITreeIterator<IMedia> iterator = generateIterator(tree);
		IMedia maxMedia = null;
		IMedia current;
		while (iterator.hasNext()) {
			current = iterator.next();
			if (current.mediaType().equals(type) && isCreator(current, creator)) {
				return current;
			}
		}
		return null;
	}
	
	private static String returnPriceLimited(IBinarySearchTree<IMedia> tree, int price, String key) {
		String output = "";
		ITreeIterator<IMedia> iterator = generateIterator(tree);
		boolean flag = false;
		boolean once = true;
		IMedia current;
		if (key.equals("lower")) {
			flag = true;
		}
		while(iterator.hasNext()) {
			current = iterator.next();
			if (current.mediaPrice() >= price && once) {
				flag = !flag;
				once = false;
			}
			if (flag) {
				output += current.toString() + " ";
			}
		}
		return output;
	}

	private static IMedia[] reverseMediaArray(IMedia[] array) {
		IMedia[] reversedArray = new IMedia[array.length];
		int lastIndex = array.length - 1;
		for (int i = 0; i < array.length; i++) {
			reversedArray[i] = array[lastIndex - i];
		}
		return reversedArray;
	}

	private static int getFrequencyOfMediaObject(IMedia[] array, String type) {
		int frequencyCount = 0;
		for (IMedia mediaObj : array) {
			if (mediaObj.mediaType().equals(type)) {
				frequencyCount++;
			}
		}
		return frequencyCount;
	}
	
	private static<K extends IMedia> IMedia[] filterArray(K keyObj, IMedia[] array) {
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
	};
	
	private void printDesAsc(IMedia[] array, boolean reverse, String type) {
		IMedia[] workingArray = Arrays.copyOf(array, array.length);
		String output;
		if (reverse) {
			workingArray = reverseMediaArray(workingArray);
		}
		if (type == null) {
			;
		} else if (type.equals("Book")) {
			Book keyObject = new Book("At the Mountains of Madness", 0, 1932, "Howard Phillips Lovecraft");
			workingArray = filterArray(keyObject, workingArray);
		} else {
			Movie keyObject = new Movie("Arrival", 0, 2016, "Denis Villeneuve", "Jeremy Renner", "Amy Adams");
			workingArray = filterArray(keyObject, workingArray);
		}
		output = ArrayOperations.convertToString(workingArray);
		System.out.println(output);
	}
	
	private void printLowHigh() {
		try {
			System.out.println(
					returnMediaWithHighest(tree,"Book", takeUserInput(
							"Enter author name for most expensive book.")).toString()); 
		} catch (NullPointerException e){
			System.out.println("Author you have entered isn't here.");
		} try {
			System.out.println(
					returnMediaWithLowest(tree, "Book", takeUserInput(
							"Enter the author name for the cheapest book")).toString());
		} catch (NullPointerException e) {
			System.out.println("Author you have entered isn't here.");
		} try {
			System.out.println(returnMediaWithHighest(tree, "Movie", takeUserInput(
					"Enter the director name for the most expensive movie")).toString());
		} catch (NullPointerException e) {
			System.out.println("Director you have entered doesn't exist.");
		}try {
			System.out.println(returnMediaWithLowest(tree, "Movie", takeUserInput(
					"Enter the director name for the cheapest movie")).toString());
		} catch (NullPointerException e) {
			System.out.println("Director you have entered doesn't exist.");
		}
	}
	
	private void printRanged() {
		System.out.println(
				returnPriceLimited(tree, Integer.parseInt(takeUserInput(
						"Input the price more expensive media will be printed")), "higher"));
		System.out.println(
				returnPriceLimited(tree, Integer.parseInt(takeUserInput(
						"Input the price cheaper media will be printed")), "lower"));
	}
	
	private void printArrays() {
		IMedia[] treeArray = new IMedia[tree.getNumberOfNodes()];
		ITreeIterator<IMedia> iterator = generateIterator(tree);
		int i = 0;
		while (iterator.hasNext()) {
			treeArray[i] = iterator.next();
			i++;
		}
		printDesAsc(treeArray, false, null);
		printDesAsc(treeArray, true, null);
		printDesAsc(treeArray, false, "Book");
		printDesAsc(treeArray, true, "Book");
		printDesAsc(treeArray, false, "Movie");
		printDesAsc(treeArray, true, "Movie");
	}

	public UserInput(IBinarySearchTree<IMedia> tree) {
		this.input = new Scanner(System.in);
		this.tree = tree;
		printLowHigh();
		printRanged();
		printArrays();
		input.close();
	}
}