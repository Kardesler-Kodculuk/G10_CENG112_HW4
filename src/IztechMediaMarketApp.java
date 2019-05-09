import java.lang.reflect.Executable;
import java.util.Arrays;

import internals.IMedia;
import tree.IBinarySearchTree;
import tree.NyanSearchTree;
import utility.ArrayOperations;
import utility.CSVParser;
import utility.UserInput;

public class IztechMediaMarketApp {

	public static void main(String[] args) {
		CSVParser fileReader = new CSVParser("CENG112_HW4_Media.txt");
		IBinarySearchTree<IMedia> tree = fileReader.getTree();
		UserInput.userInput(tree);
	}
}
