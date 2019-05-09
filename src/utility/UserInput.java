package utility;

import java.lang.reflect.Executable;
import java.util.Scanner;

import internals.*;
import tree.IBinarySearchTree;

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
	
	private static void printHigherPriced(int price, String higherLower, IMedia[] sortedArray) {
		boolean printFlag = false;
		IMedia checker = new Book("At the Mountains of Madness", price, 1936, "H.P. Lovecraft");
		switch (higherLower) {
		case "higher":
			for (IMedia media : sortedArray) {
				if (printFlag) {
					System.out.println(media);
				} else if (media.compareTo(checker) >= 0) {
					printFlag = true;
				}
			} //Not finished all cases.
		}
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

	}
	private static void userInput(IBinarySearchTree<IMedia> tree) {
		IMedia[] sortedArray = tree.toArray();
		minMaxInputs(sortedArray);
	}
}
