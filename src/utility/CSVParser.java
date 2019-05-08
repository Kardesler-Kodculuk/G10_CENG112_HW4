package utility;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class CSVParser {

	private Object[] medias;
	private int lineCount;
	
	private static Object generateMedia(String mediaLine) {return null;};
	private static Object generateSearchTree(Object[] feedingArray) {return null;};
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
	
	private static int countLines(String fileName) {
		Scanner fileScanner = generateScanner(fileName);
		String temp = null;
		int lineCount = 0;
		while (fileScanner.hasNext()) {
			temp = fileScanner.nextLine();
			lineCount++;
		}
		temp = null;
		return lineCount;
	}
	
	private void populateMedias(String fileName) {
		Scanner fileReader = generateScanner(fileName);
		int index = 0;
		String currentLine;
		while (fileReader.hasNextLine()) {
			currentLine = fileReader.nextLine(); 
			medias[index] = generateMedia(currentLine);
			index++;
		}
	}
	
	public CSVParser(String fileName) {
		lineCount = countLines(fileName);
		medias = new Object[lineCount];
		populateMedias(fileName);
//		ArrayOperations.sort(medias);
	}
	
}
