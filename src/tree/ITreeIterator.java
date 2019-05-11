package tree;

public interface ITreeIterator<T extends Comparable<? super T>> {
	public boolean hasNext();
	public T next();
	public T remove();
}
