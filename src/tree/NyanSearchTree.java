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
	 * 
	 * @param key
	 * @return
	 */
	private NyanBinaryNode<T> getNode(String key) {
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
	private boolean setNode(String key, NyanBinaryNode node) {
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
	
	private T getElement() {
		return element;
	}
	
	private void setElement(T element) {
		this.element = element;
	}
	
	private void compareTo(NyanBinaryNode other) {}
}


/**
 * Binary search tree implementation.
 */
public class NyanSearchTree<T extends Comparable<? super T>> implements IBinarySearchTree<T> {

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(T entry) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T getEntry(T entry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addEntry(T entry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T remove(T entry) {
		// TODO Auto-generated method stub
		return null;
	}

}
