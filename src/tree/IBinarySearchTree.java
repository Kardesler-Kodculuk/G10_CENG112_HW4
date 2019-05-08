package tree;

public interface IBinarySearchTree<T extends Comparable<? super T>> extends ITree<T> {
	/**
	 * Check if the Binary Search Tree contains a specific entry.
	 * @param entry to check
	 * @return true if contains, false otherwise.
	 */
	public boolean contains(T entry);
	/**
	 * Get an entry if it exists
	 * @param entry
	 * @return entry, if it is there, null otherwise.
	 */
	public T getEntry(T entry);
	/**
	 * Add an entry.
	 * @param entry to add.
	 */
	public void addEntry(T entry);
	/**
	 * Remove an entry
	 * @param entry
	 * @return the entry removed or null if no such entry exists.
	 */
	public T remove(T entry);
	/**
	 * Return an entry consisting of elements of the tree, ordered.
	 * @return the array.
	 */
	public T[] toArray();
}
