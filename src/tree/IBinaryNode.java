package tree;

interface IBinaryNode<T extends Comparable<? super T>> {
	public IBinaryNode<T> getNode(String key);
	public IBinaryNode<T> getNext();
	public boolean setNode(String key, IBinaryNode<T> node);
	public T getElement();
	public void setElement(T value);
	public int compareTo(IBinaryNode<T> other);
	public boolean isLeaf();
}
