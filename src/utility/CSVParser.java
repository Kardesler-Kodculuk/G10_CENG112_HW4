package utility;

import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import internals.*;
import tree.*;

public class CSVParser {

	private IMedia[] medias;
	private int lineCount;
	private IBinarySearchTree<IMedia> tree;
	
	
	/**
	 * Convert the given csv line to its media counterpart
	 * @param mediaLine line containing the info
	 * @return media object.
	 */
	private static IMedia generateMedia(String mediaLine) {
		String info[] = mediaLine.split(",");
		IMedia media = null;
		switch (info[0]) {
		case "Movie":
			media = new Movie(info[1], Integer.parseInt(info[2]), Integer.parseInt(info[3]), info[4], info[5], (info.length == 7) ? info[6]: "None.");
			break;
		case "Book":
			media = new Book(info[1], Integer.parseInt(info[2]), Integer.parseInt(info[3]), info[4]);
			break;
		default:
			break;
		}
		
		return media;
	}
	
	/**
	 * Create a balanced binary search tree from the elements of an array.
	 * @param feedingArray Array elements are stored in.
	 * @return the tree object.
	 */
	private static IBinarySearchTree<IMedia> generateSearchTree(IMedia[] feedingArray) {
	IBinarySearchTree<IMedia> tree = new NyanSearchTree<IMedia>();
	for (IMedia media : feedingArray) {
		tree.addEntry(media);
	}
	return tree;
	}
	
	/**
	 * Generate a scanner to read a specific file.
	 * @param fileName name of the file to be read.
	 * @return the scanner object
	 */
	private static Scanner generateScanner(String fileName) {
		File targetFile = new File(fileName);
		Scanner fileReader = null;
		try {
			fileReader = new Scanner(targetFile);
		} catch (FileNotFoundException e) {
			System.err.println("File not found.");
		}
		return fileReader;
	}
	
	/**
	 * Count the lines in the given file
	 * @param fileName name of the file lines shall be counted at, it shall count 'til the line count, line count
	 * i is the number thou shall count to, and number of counting shall be i.
	 * @return the count of lines.
	 */
	private static int countLines(String fileName) {
		Scanner fileScanner = generateScanner(fileName);
		String temp = null;
		int lineCount = 0;
		while (fileScanner.hasNextLine()) {
			temp = fileScanner.nextLine();
			if (temp == null || temp.equals("")) {
				continue;
			} else {
				lineCount++;
			}
		}
		temp = null;
		fileScanner.close();
		return lineCount;
	}
	
	/**
	 * Populate the medias array by creating mass media.
	 * @param fileName Name of the file containing the name of the medias.
	 */
	private void populateMedias(String fileName) {
		Scanner fileReader = generateScanner(fileName);
		int index = 0;
		String currentLine;
		while (fileReader.hasNextLine()) {
			currentLine = fileReader.nextLine();
			if (currentLine == null || currentLine.equals("")) {
				continue;
			} else {
				medias[index] = generateMedia(currentLine);
				index++;
			}
		}
		fileReader.close();
	}
	
	public CSVParser(String fileName) {
		lineCount = countLines(fileName);
		medias = new IMedia[lineCount];
		populateMedias(fileName);
		this.tree = generateSearchTree(medias); 
	}
	
	public IBinarySearchTree<IMedia> getTree() {
		return tree;
	}
}
