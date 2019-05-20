/**
 * 
 */
package tree;


class NyanBinaryNode<T extends Comparable<? super T>> implements IBinaryNode<T>, Comparable<IBinaryNode<T>>{
	private T element;
	private IBinaryNode<T> leftNode;
	private IBinaryNode<T> rightNode;
	
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
	@Override
	public IBinaryNode<T> getNode(String key) {
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
	@Override
	public boolean setNode(String key, IBinaryNode<T> node) {
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
	@Override
	public T getElement() {
		return element;
	}
	
	/**
	 * Set the element
	 * @param element to set.
	 */
	@Override
	public void setElement(T element) {
		this.element = element;
	}

	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!obj.getClass().equals(obj.getClass())) return false;
		else {
			@SuppressWarnings("unchecked")
			NyanBinaryNode<T> compareNode = (NyanBinaryNode<T>) obj;
			return element.equals(compareNode.element);
		}
	}

	/**
	 * Compare nodes.
	 * @param other other node.
	 * @return if bigger 1, if smaller -1, if equals 0.
	 */
	public int compareTo(IBinaryNode<T> other) {
		if (! equals(other) && other.getElement() != null) {
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
	@Override
	public IBinaryNode<T> getNext() {
		return getNode("R");
	}
}


/**
 * Binary search tree implementation.
 */
public class LinkedBinarySearchTree<T extends Comparable<T>> implements IBinarySearchTree<T> {

	private int height;
	private int nodeCount;
	private NyanBinaryNode<T> rootNode;
	
	public LinkedBinarySearchTree() {
		this((T) null);
	}
	
	public LinkedBinarySearchTree(T element) {
		this(new NyanBinaryNode<T>(element));
	}
	
	public LinkedBinarySearchTree(NyanBinaryNode<T> rootNode) {
		if (rootNode.getElement() == null) {
			this.height = 0;
			this.nodeCount = 0;
			this.rootNode = null;
		} else {
			this.height = this.nodeCount = 1;
			this.rootNode = rootNode;
		}
	}

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
	private IBinaryNode<T> search(IBinaryNode<T> searchNode, IBinaryNode<T> node) {
		if (node.equals(searchNode)) {
			return node;
		} else if (node.isLeaf() && ! node.equals(searchNode)) {
			return null;
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
		if (search(searchNode, rootNode) == null) {
			return false;
		} else {
			return true;
		}
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
	private void addEntry(IBinaryNode<T> entryNode, IBinaryNode<T> node, int depth) {
		depth++;
		if (entryNode.compareTo(node) >= 0) {
			if (node.getNode("R") == null) {
				node.setNode("R", entryNode);
			} else {
				addEntry(entryNode, node.getNode("R"), depth + 1);
			}
		} else {
			if (node.getNode("L") == null) {
				node.setNode("L", entryNode);
			} else {
				addEntry(entryNode, node.getNode("L"), depth + 1);
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
		IBinaryNode<T> nodusTerminalis = new NyanBinaryNode<T>(entry);
		IBinaryNode<T> originalNode = search(nodusTerminalis, rootNode);
		IBinaryNode<T> rightmostNode = findRightmostNode(originalNode);
		if (!originalNode.equals(rootNode)) {
			IBinaryNode<T> originalProgenitor = findProgenitor(rootNode, originalNode);
			String originalSide = findProgenitorSide(originalProgenitor, originalNode);
			rightmostNode.setNode("R", originalNode.getNode("R"));
			originalProgenitor.setNode(originalSide, originalNode.getNode("L"));
		} else {
			rightmostNode.setNode("R", originalNode.getNode("R"));
			this.rootNode = (NyanBinaryNode<T>) originalNode.getNode("L");
		}		
		return entry;
	}
	
	private IBinaryNode<T> findProgenitor(IBinaryNode<T> rootNode, IBinaryNode<T> searchNode) {
		if (rootNode.isLeaf()) {
			return null;
		} else {
			IBinaryNode<T> rightNode = rootNode.getNode("R");
			IBinaryNode<T> leftNode = rootNode.getNode("L");
			if (rightNode != null) {
				if (rightNode.equals(searchNode)) {
					return rootNode;
				}
			}
			
			if (leftNode != null) {
				if (leftNode.equals(searchNode)) {
					return rootNode;
				}
			}
			
			IBinaryNode<T> answerFromRight = findProgenitor(rightNode, searchNode);
			IBinaryNode<T> answerFromLeft = findProgenitor(leftNode, searchNode);
			if (answerFromRight != null) {
				return answerFromRight;
			} else if (answerFromLeft != null) {
				return answerFromLeft;
			} else {
				return null;
			}
		}
	}
	
	private String findProgenitorSide(IBinaryNode<T> progenitorNode, IBinaryNode<T> searchNode) {
		IBinaryNode<T> right = progenitorNode.getNode("R");
		if (right != null && right.equals(searchNode)) {
			return "R";
		} else {
			return "L";
		}
	}
	
	private IBinaryNode<T> findRightmostNode(IBinaryNode<T> progenitorNode) {
		IBinaryNode<T> nextRightNode = progenitorNode.getNext();
		if (nextRightNode != null) {
			return findRightmostNode(nextRightNode);
		} else {
			return progenitorNode;
		}
	}
	
	@Override
	public IBinaryNode<T> getRootNode() {
		return this.rootNode;
	}
}
