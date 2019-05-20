package tree;

import stack.IStack;
import stack.LinkedStack;

public class NyanTreeIterator<T extends Comparable<? super T>> implements ITreeIterator<T> {

	private IBinarySearchTree<T> tree;
	private IBinaryNode<T> active;
	private IStack<IBinaryNode<T>> traversalStack;
	
	
	public NyanTreeIterator(IBinarySearchTree<T> tree) {
		this.tree = tree;
		this.active = tree.getRootNode();
		traversalStack = new LinkedStack<IBinaryNode<T>>(tree.getRootNode());
		traverseToEnd();
	}
	
	@Override
	public boolean hasNext() {
		return !traversalStack.isEmpty();
	}

	private void traverseToEnd() {
		while (active != null) {
			if (traversalStack.peek() != active) {
				traversalStack.push(active);
			}
			active = active.getNode("L");
		}
	}
	@Override
	public T next() {
		if (hasNext()) {
			IBinaryNode<T> current = traversalStack.pop();
			this.active = current.getNode("R");
			traverseToEnd();
			return current.getElement();
		}
		return null;
	}

	@Override
	public T remove() {
		return null;
	}
}
