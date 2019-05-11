package utility;

import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.Scanner;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import internals.*;
import tree.IBinarySearchTree;
import tree.ITreeIterator;
import tree.NyanTreeIterator;

public class UserInput {
	private static String getCreator(IMedia media) {
		String creator;
		if (media.mediaType() == "Movie") {
			creator = ((Movie) media).getDirector();
		} else {
			creator = ((Book) media).getAuthor();
		}

		return creator;
	}

	private static IMedia returnByPrice(String maxmin, String type, String creator, IMedia[] sortedArray) {
		switch (maxmin) {
		case "max":
			for (int i = (sortedArray.length - 1); i >= 0; i--) {
				if (sortedArray[i].mediaType().equals(type) && getCreator(sortedArray[i]).equals(creator)) {
					return sortedArray[i];
				}
			}
		case "min":
			for (IMedia media : sortedArray) {
				if (media.mediaType().equals(type) &&  getCreator(media).equals(creator)) {
					return media;
				}
			}
		default:
			return null;
		}
	}
	
	private static void printRangePriced(int price, String higherLower, IMedia[] sortedArray) {
		boolean printFlag = (higherLower.equals("higher")) ? false : true;
		IMedia checker = new Book("At the Mountains of Madness", price, 1936, "H.P. Lovecraft");
		for (IMedia media : sortedArray) {
			if (printFlag) {
				System.out.println(media);
			} else if (media.compareTo(checker) >= 0) {
				printFlag = !printFlag;
			}
		}
	}

	private static void printArray(IMedia keyObject, IMedia[] sortedArray, boolean reverse) {
		IMedia[] filteredArray;
		if (keyObject != null) {
			filteredArray = ArrayOperations.filter(keyObject, sortedArray);
		} else {
			filteredArray = sortedArray;
		}
		if (reverse) {
			filteredArray = ArrayOperations.reverse(filteredArray);
		}
		System.out.println(ArrayOperations.convertToString(filteredArray));
	}
	
	private static String takeUserInput(String prompt) {
		Scanner userInput = new Scanner(System.in);
		System.out.println(prompt);
		String input = userInput.next();
		userInput.close();
		return input;
	}

	private static void minMaxInputs(IMedia[] sortedArray) {
		String authorMin = takeUserInput("Enter the name of the author whose lowest priced book you want to see: ");
		System.out.println(returnByPrice("min", "Book", authorMin, sortedArray));
		String authorMax = takeUserInput("Enter the name of the author whose highest priced book you want to see: ");
		System.out.println(returnByPrice("max", "Book", authorMax, sortedArray));
		String directorMin = takeUserInput("Enter the name of the director whose lowest priced movie you want to see: ");
		System.out.println(returnByPrice("min", "Movie", directorMin, sortedArray));
		String directorMax = takeUserInput("Enter the name of the director whose highest priced movie you want to see: ");
		System.out.println(returnByPrice("max", "Movie", directorMax, sortedArray));
	}
	
	private static void rangeInputs(IMedia[] sortedArray) {
		int rangeGreater = Integer.parseInt(takeUserInput("Enter the price of which the more expensive media will be printed: "));
		printRangePriced(rangeGreater, "higher", sortedArray);
		int rangeLower = Integer.parseInt(takeUserInput("Enter the prive of which the cheaper media will be printed: "));
		printRangePriced(rangeLower, "lower", sortedArray);
	}
	
	private static void sortInputs(IMedia[] sortedArray) {
		Book bookExample = new Book("At the Mountains of Madness", 12, 1936, "H.P. Lovecraft");
		Movie movieExample = new Movie("Arrival", 26, 2016, "Denis Villeneuve", "Jeremy Renner", "Amy Adams");
		printArray(null, sortedArray, true);
		printArray(null, sortedArray, false);
		printArray(bookExample, sortedArray, true);
		printArray(bookExample, sortedArray, false);
		printArray(movieExample, sortedArray, true);
		printArray(movieExample, sortedArray, false);
	}

	public static void userInput(IBinarySearchTree<IMedia> tree) {
		ITreeIterator<IMedia> ismailTarator = new NyanTreeIterator<IMedia>(tree);
		IMedia[] sortedArray = new IMedia[tree.getNumberOfNodes()];
		int i = 0;
		while (ismailTarator.hasNext()) {
			System.out.println(smailTarator.next());
		}
		System.out.println(Arrays.toString(sortedArray));
		minMaxInputs(sortedArray);
		rangeInputs(sortedArray);
		sortInputs(sortedArray);
	}
}
