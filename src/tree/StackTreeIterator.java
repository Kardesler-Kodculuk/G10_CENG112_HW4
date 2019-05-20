package tree;

import stack.IStack;
import stack.LinkedStack;

public class StackTreeIterator<T extends Comparable<? super T>> implements ITreeIterator<T> {

	private IBinaryNode<T> active;
	private IStack<IBinaryNode<T>> traversalStack;
	
	
	public StackTreeIterator(IBinarySearchTree<T> tree) {
		this.active = tree.getRootNode();
		traversalStack = new LinkedStack<IBinaryNode<T>>(tree.getRootNode());
		traverseToEnd();
	}
	
	@Override
	public boolean hasNext() {
		return !traversalStack.isEmpty();
	}

	/**
	 * Traverses until it finds the leftmost node.
	 */
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
