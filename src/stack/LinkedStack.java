/**
 * 
 */
package stack;

class Node<T> {
	private T element;
	private Node<T> next;

	/**
	 * @return the element
	 */
	public T getElement() {
		return element;
	}

	/**
	 * @param element the element to set
	 */
	public void setElement(T element) {
		this.element = element;
	}

	/**
	 * @return the next
	 */
	public Node<T> getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	public Node() {
		this(null);
	}

	public Node(T element) {
		this(element, null);
	}
	
	public Node(T element, Node<T> next) {
		this.element = element;
		this.next = next;
	}
	
	
}

public class LinkedStack<T> implements IStack<T> {

	private Node<T> top;
	private int nodeCount;

	public LinkedStack() {
		this.top = null;
		this.nodeCount = 0;
	} public LinkedStack(T element) {
		if (element != null) {
			this.top = new Node<T>(element);
			this.nodeCount = 1;
		} else {
			this.top = null;
			this.nodeCount = 1;
		}
	} 

	@Override
	public T pop() {
		if (isEmpty()) {
			return null;
		} else {
			Node<T> poppedNode = top;
			this.top = poppedNode.getNext();
			T element = poppedNode.getElement();
			poppedNode.setNext(null);
			return element;
		}
	}

	@Override
	public void push(T newElement) {
		Node<T> nodusTemporalis = new Node<T>(newElement, top);
		this.top = nodusTemporalis;
		nodeCount++;
	}

	@Override
	public T peek() {
		if (top != null) {
			return top.getElement();
		} else {
			return null;
		}
	}

	@Override
	public void clean() {
		this.top = null;
	}

	@Override
	public boolean isEmpty() {
		return top == null;
	}

}
