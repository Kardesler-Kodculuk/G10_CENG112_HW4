/**
 * 
 */
package tree;

import internals.IMedia;

/**
 *
 */
public class IsmailTarator<T extends Comparable<? super T>> implements ITreeIterator<T>
{
	private IBinaryNode<IMedia> currentNode;
	
	/**
	 * @param currentNode
	 */
	public IsmailTarator(IBinaryNode<IMedia> currentNode)
	{
		this.currentNode = currentNode;
	}

	@Override
	public boolean hasNext()
	{
		if (currentNode.getNext() != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T next()
	{
		if (hasNext())
		{
			currentNode = currentNode.getNext();
			return (T) currentNode.getElement();
		}
		else
		{
			return null;
		}
	}

	@Override
	public T remove()
	{
		if (currentNode.getElement() != null)
		{
			@SuppressWarnings("unchecked")
			T result = (T) currentNode.getElement();
			currentNode.setElement(null);
			return result;
		}
		else
		{
			return null;
		}
	}

}
