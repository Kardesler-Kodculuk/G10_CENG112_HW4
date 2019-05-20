package utility;

import java.util.Arrays;
import java.util.Scanner;
import internals.*;
import tree.IBinarySearchTree;
import tree.ITreeIterator;
import tree.IsmailTarator;
import tree.NyanTreeIterator;

/**
 * The class that handles input-output events between the user and the program.
 *
 */
public class UserInput {
	
	private Scanner input;
	private IBinarySearchTree<IMedia> tree;
	
	/**
	 * Generate an iterator for the tree
	 * @param tree
	 * @return
	 */
	private static ITreeIterator<IMedia> generateIterator(IBinarySearchTree<IMedia> tree) {
		ITreeIterator<IMedia> iterator = new NyanTreeIterator<IMedia>(tree);
		return iterator;
	}
	
	/**
	 * Take input from the user. This is basically analogous to Python's input built-in function.
	 * @param prompt
	 * @return
	 */
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
	
	/**
	 * Checks if media's creator (author/director) equals to the given creator.
	 * @param current Media to be checked
	 * @param creator Creator name to be checked
	 * @return
	 */
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
	
	/**
	 * Return the media with highest price of the class type with the creator name creator.
	 * @param tree - Tree
	 * @param type - Type of the IMedia, must be an implementation of.
	 * @param creator - Name of the director/author.
	 * @return
	 */
	private static IMedia returnMediaWithHighest(IBinarySearchTree<IMedia> tree, String type, String creator) {
		ITreeIterator<IMedia> iterator = generateIterator(tree);
		int maxPrice = 0;
		IMedia maxMedia = null;
		IMedia current;
		while (iterator.hasNext()) {
			current = iterator.next();
			if (current == null) {
				System.out.println("Null error.");
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
	
	/**
	 * Same as returnMediaWithHighest but instead the lowest method.
	 * @param tree - Tree
	 * @param type - Type of the IMedia
	 * @param creator - Creator name Director/Author
	 * @return
	 */
	private static IMedia returnMediaWithLowest(IBinarySearchTree<IMedia> tree, String type, String creator) {
		ITreeIterator<IMedia> iterator = generateIterator(tree);
		IMedia current;
		while (iterator.hasNext()) {
			current = iterator.next();
			if (current.mediaType().equals(type) && isCreator(current, creator)) {
				return current;
			}
		}
		return null;
	}
	
	/**
	 * Return a String consisting of the IMedia objects limited by a price range.
	 * @param tree Tree
	 * @param price Price range floor or ceiling
	 * @param key Whether you want lower or higher prices.
	 * @return
	 */
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
	
	/**
	 * Print the arrays in decreasing or increasing order.
	 * @param array IMedia array to be printed
	 * @param reverse Reverse the array, boolean.
	 * @param type Type of the printed output, IMedia, Book, Movie.
	 */
	private void printDesAsc(IMedia[] array, boolean reverse, String type) {
		IMedia[] workingArray = Arrays.copyOf(array, array.length);
		String output;
		if (reverse) {
			workingArray = ArrayOperations.reverseMediaArray(workingArray);
		}
		if (type == null) {
			;
		} else if (type.equals("Book")) {
			Book keyObject = new Book("At the Mountains of Madness", 0, 1932, "Howard Phillips Lovecraft");
			workingArray = ArrayOperations.filterArray(keyObject, workingArray);
		} else {
			Movie keyObject = new Movie("Arrival", 0, 2016, "Denis Villeneuve", "Jeremy Renner", "Amy Adams");
			workingArray = ArrayOperations.filterArray(keyObject, workingArray);
		}
		output = ArrayOperations.convertToString(workingArray);
		System.out.println(output);
	}
	
	/**
	 * Print lowest highest members of Book/Movie
	 */
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
	
	/**
	 * Print the outputs limited by a price range.
	 */
	private void printRanged() {
		System.out.println(
				returnPriceLimited(tree, Integer.parseInt(takeUserInput(
						"Input the price more expensive media will be printed")), "higher"));
		System.out.println(
				returnPriceLimited(tree, Integer.parseInt(takeUserInput(
						"Input the price cheaper media will be printed")), "lower"));
	}
	
	/**
	 * Print the array increasing decreasing.
	 */
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