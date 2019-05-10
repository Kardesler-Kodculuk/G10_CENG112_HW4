/**
 * 
 */
package tree;


class NyanBinaryNode<T extends Comparable<T>>{
	private T element;
	private NyanBinaryNode<T> leftNode;
	private NyanBinaryNode<T> rightNode;
	
	public NyanBinaryNode() {
		this(null);
	}
	public NyanBinaryNode(T element) {
		this(element, null, null);
	}
	public NyanBinaryNode(T element, NyanBinaryNode<T> leftNode, NyanBinaryNode<T> rightNode) {
		this.element = element;
		this.leftNode = leftNode;
		this.rightNode = rightNode;
	}
	
	/**
	 * Return a node.
	 * @param key Left or Right
	 * @return the node or null if wrong key or maybe if node doesn't exist.
	 */
	public NyanBinaryNode<T> getNode(String key) {
		switch (key) {
		case "L":
			return leftNode;
		case "R":
			return rightNode;
		default:
			return null;
		}
	}
	
	/**
	 * Set one of the nodes.
	 * @param key l for left, r for right.
	 * @param node node to set.
	 * @return true if right, false otherwise.
	 */
	public boolean setNode(String key, NyanBinaryNode<T> node) {
		switch (key) {
		case "L":
			this.leftNode = node;
			break;
		case "R":
			this.rightNode = node;
			break;
		default:
			return false;
		}
		return true;
	}
	
	/**
	 * Get the element.
	 * @return the element.
	 */
	public T getElement() {
		return element;
	}
	
	/**
	 * Set the element
	 * @param element to set.
	 */
	public void setElement(T element) {
		this.element = element;
	}

	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!obj.getClass().equals(obj.getClass())) return false;
		else {
			NyanBinaryNode<T> compareNode = (NyanBinaryNode<T>) obj;
			return element.equals(compareNode.element);
		}
	}

	/**
	 * Compare nodes.
	 * @param other other node.
	 * @return if bigger 1, if smaller -1, if equals 0.
	 */
	public int compareTo(NyanBinaryNode<T> other) {
		if (! equals(other)) {
			return this.element.compareTo((T) other.getElement());
		} else {
			return 0;
		}
	}
	
	/**
	 * Check if the node is leaf.
	 * @return True if leaf, false otherwise.
	 */
	public boolean isLeaf() {
		return (leftNode == null && rightNode == null);
	}
}


/**
 * Binary search tree implementation.
 */
public class NyanSearchTree<T extends Comparable<T>> implements IBinarySearchTree<T> {

	private int height;
	private int nodeCount;
	private NyanBinaryNode<T> rootNode;
	
	@Override
	public T getRootData() {
		if (!isEmpty()) {
			return rootNode.getElement();
		} else {
			return null;
		}
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getNumberOfNodes() {
		return nodeCount;
	}

	@Override
	public boolean isEmpty() {
		return (rootNode == null);
	}

	@Override
	public void clear() {
		this.rootNode = null;
		this.height = 0;
		this.nodeCount = 0;
	}

	/**
	 * Search the tree for a trinket, for a sign, for an element.
	 * @param searchNode Node that contains the element to be searched.
	 * @param node
	 * @return
	 */
	private boolean search(NyanBinaryNode<T> searchNode, NyanBinaryNode<T> node) {
		if (node.equals(searchNode)) {
			return true;
		} else if (node.isLeaf() && ! node.equals(searchNode)) {
			return false;
		} else {
			if (searchNode.compareTo(node) > 0) {
				return search(searchNode, node.getNode("R"));
			} else {
				return search(searchNode, node.getNode("L"));
			}
		}
	}

	@Override
	public boolean contains(T entry) {
		if (isEmpty()) return false;
		NyanBinaryNode<T> searchNode = new NyanBinaryNode<T>(entry);
		return search(searchNode, rootNode);
	}

	@Override
	public T getEntry(T entry) {
		if (contains(entry)) {
			return entry;
		} else {
			return null;
		}
	}

	/**
	 * Perhaps the height has to change, perhaps the new node is in a different height.
	 * Fear not, for that, we have the candidate height! Why? Because the addEntry function
	 * has a lot of if-elses, which raises its complexity, therefore I will hide this if-else
	 * Chain into this method!
	 * @param candidateHeight - A candidate to become the new this.height.
	 */
	private void updateHeight(int candidateHeight) {
		if (candidateHeight > height) {
			this.height = candidateHeight;
		}
	}

	/**
	 * A private recursive method to add an entry
	 * @param entryNode The new element put in a node.
	 * @param node the current node in the recursion.
	 * @param depth depth we are searching in, hopefully not too deep, because if we are,
	 * don't forget that Ph'nglui mglw'nafh Cthulhu R'lyeh wgah'nagl fhtagn.
	 */
	private void addEntry(NyanBinaryNode<T> entryNode, NyanBinaryNode<T> node, int depth) {
		depth++;
		if (entryNode.compareTo(node) >= 0) {
			if (node.getNode("R") == null) {
				node.setNode("R", entryNode);
			} else {
				addEntry(entryNode, node.getNode("R"), depth);
			}
		} else {
			if (node.getNode("L") == null) {
				node.setNode("L", entryNode);
			} else {
				addEntry(entryNode, node.getNode("L"), depth);
			}
		}
	}

	@Override
	public void addEntry(T entry) {
		NyanBinaryNode<T> newElementNode = new NyanBinaryNode<T>(entry);
		if (rootNode == null) {
			this.rootNode = newElementNode;
			updateHeight(1);
		} else {
			addEntry(newElementNode, rootNode, 0);
		}
		nodeCount++;
	}

	@Override
	public T remove(T entry) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Traverse the list inorder
	 * @param rootNode Current node.
	 * @param traversalArray array the nodes will be added to.
	 * @param indexContainer index that can be shared between recursion.
	 */
	private void inorderTraverse(NyanBinaryNode<T> rootNode, T[] traversalArray, int[] indexContainer) {
		if (rootNode == null) {
			;
		} else if (rootNode.isLeaf()) {
			traversalArray[indexContainer[0]] = rootNode.getElement();
			indexContainer[0]++;
		} else {
			inorderTraverse(rootNode.getNode("L"), traversalArray, indexContainer);
			traversalArray[indexContainer[0]] = rootNode.getElement();
			indexContainer[0]++;
			inorderTraverse(rootNode.getNode("R"), traversalArray, indexContainer);
		}
	}
	
	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] nodeArray = (T[]) new Comparable[nodeCount];
		int[] indexContainer = new int[1];
		indexContainer[0] = 0;
		inorderTraverse(rootNode, nodeArray, indexContainer);
		return nodeArray;
	}

}
