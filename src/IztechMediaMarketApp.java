import internals.*;
import tree.*;
import utility.*;

public class IztechMediaMarketApp {

	public static void main(String[] args) {
		CSVParser fileReader = new CSVParser("CENG112_HW4_Media.txt");
		IBinarySearchTree<IMedia> tree = fileReader.getTree();
		UserInput userInput = new UserInput(tree); //Not sure how GC will act if I remove the assign.
	}
}

