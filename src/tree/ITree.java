package tree;

public interface ITree<T> {
	/**
	 * Get the data at the root node.
	 * @return
	 */
	public T getRootData();
	/**
	 * get the height of the tree
	 * @return the height of the tree.
	 */
	public int getHeight();
	/**
	 * Get the number of nodes.
	 * @return number of nodes.
	 */
	public int getNumberOfNodes();
	/**
	 * If it is empty.
	 * @return true if it is empty.
	 */
	public boolean isEmpty();
	/**
	 * Clear the tree.
	 */
	public void clear();
}
