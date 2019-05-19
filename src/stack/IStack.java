package stack;

public interface IStack<T> {
	/**
	 * Pop the top element of the stack.
	 * @return Topmost element.
	 */
	public T pop();
	/**
	 * Push an element into the stack.
	 * @param newElement Element to be pushed.
	 */
	public void push(T newElement);
	/**
	 * Return the topmost element without changing it.
	 * @return
	 */
	public T peek();
	/**
	 * Clean the stack.
	 */
	public void clean();
	/**
	 * Return if the stack is empty.
	 * @return If the stack is empty.
	 */
	public boolean isEmpty();
	
}
