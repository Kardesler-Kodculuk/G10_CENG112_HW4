package tree;

public interface ITreeIterator<T extends Comparable<? super T>> {
	/** 
	 * Checks if the iterator has a next element.
	 * @return True if it has a next element 
	 */
	public boolean hasNext();
	/**
	 * Return the next element, if there is any.
	 * @return
	 */
	public T next();
	/**
	 * Remove an element, may not be implementened.
	 * @return 
	 */
	public T remove();
}
