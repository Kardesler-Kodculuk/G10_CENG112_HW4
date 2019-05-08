/**
 * 
 */
package tree;


class NyanBinaryNode<T extends Comparable<? super T>>{
	private T element;
	private NyanBinaryNode<T> leftNode;
	private NyanBinaryNode<T> rightNode;
	
	private NyanBinaryNode() {
		this(null);
	}
	private NyanBinaryNode(T element) {
		this(element, null, null);
	}
	private NyanBinaryNode(T element, NyanBinaryNode<T> leftNode, NyanBinaryNode<T> rightNode) {
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
	public boolean setNode(String key, NyanBinaryNode node) {
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
	public int compareTo(NyanBinaryNode other) {
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
public class NyanSearchTree<T extends Comparable<? super T>> implements IBinarySearchTree<T> {

	private int height;
	private int nodeCount;
	private NyanBinaryNode<T> rootNode;
	
	@Override
	public T getRootData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfNodes() {
		// TODO Auto-generated method stub
		return 0;
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

	@Override
	public boolean contains(T entry) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T getEntry(T entry) {
		if (contains(entry)) {
			return entry;
		} else {
			return null;
		}
	}

	private void addEntry(NyanBinaryNode<T> entryNode, NyanBinaryNode<T> node) {
		if (node.isLeaf()) {
			if (entryNode)
		}
	}

	@Override
	public void addEntry(T entry) {
				
	}

	@Override
	public T remove(T entry) {
		// TODO Auto-generated method stub
		return null;
	}

}
