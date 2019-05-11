package tree;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class NyanTreeIterator<T extends Comparable<? super T>> implements ITreeIterator<T> {

	private IBinarySearchTree<T> tree;
	private IBinaryNode<T> currentNode;
	
	
	public NyanTreeIterator(IBinarySearchTree<T> tree) {
		this.tree = tree;
		this.currentNode = tree.getRootNode();
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T remove() {
		throw new NotImplementedException();
	}
}
