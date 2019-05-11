/**
 * 
 */
package tree;

class BinaryNode<T>
{
	private T data;
	private BinaryNode<T> leftChild;
	private BinaryNode<T> rightChild;

	public BinaryNode(T data)
	{
		this.data = data;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	public BinaryNode()
	{
		this(null);
	}
	/**
	 * @return the data
	 */
	public T getData()
	{
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data)
	{
		this.data = data;
	}

	/**
	 * @return the leftChild
	 */
	public BinaryNode<T> getLeftChild()
	{
		return leftChild;
	}

	/**
	 * @return the rightChild
	 */
	public BinaryNode<T> getRightChild()
	{
		return rightChild;
	}
//	
//	public boolean hasNode(String key)
//	{
//		switch(key)
//		{
//		case "L":
//			if (this.leftChild != null)
//				{
//					return true;
//				}
//		case "R":
//			if (this.rightChild != null)
//			{
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	public boolean isLeaf()
//	{
//		if (this.hasNode("L") || this.hasNode("R"))
//		{
//			return false;
//		}
//		else
//		{
//			return true;
//		}
//	}

}

/**
 *
 */
public class BinalySearchTree<T extends Comparable<T>> implements IBinarySearchTree<T>
{
	BinaryNode<T> root;
	int height;
	int numberOfNodes;
	
	/**
	 * @param root
	 */
	public BinalySearchTree(BinaryNode<T> root)
	{
		this.root = root;
	}
	
	/**
	 * 
	 */
	public BinalySearchTree()
	{
		this(null);
	}

	@Override
	public T getRootData()
	{
		return this.root.getData();
	}
	
	@Override
	public int getHeight() // TODO
	{
		return height;
	}

	@Override
	public int getNumberOfNodes()
	{
		return numberOfNodes;
	}

	@Override
	public boolean isEmpty()
	{
		if (root.getData() == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void clear()
	{
		root = null;
	}

	/**
	 * Searches the tree
	 * 
	 * @param root - root of the tree
	 * @param entry - the data that searching
	 * @return if found, the node which has the given data. if not, null
	 */
	private BinaryNode<T> search(BinaryNode<T> root, T entry)
	{
		if (root.getData().equals(entry) || root == null)
		{
			return root;
		}
		else if (root.getData().compareTo(entry) > 0)
		{
			return search(root.getLeftChild(), entry);
		}
		else
		{
			return search(root.getRightChild(), entry);
		}
	}
	
	@Override
	public boolean contains(T entry)
	{
		if (this.search(root, entry) == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public T getEntry(T entry)
	{
		if (contains(entry))
		{
			return entry;
		}
		else
		{
			return null;
		}
	}

	private BinaryNode<T> findDuplicateSpace(BinaryNode<T> rightNode, T entry)
	{
		if (rightNode.getData() == null)
		{
			return rightNode;
		}
		else
		{
			return findDuplicateSpace(rightNode.getLeftChild(), entry);
		}
	}
	
	private BinaryNode<T> findSpace(BinaryNode<T> root, T entry)
	{
		if (root.getData() == null)
		{
			return root;
		}
		else if (root.getData().compareTo(entry) > 0)
		{
			return findSpace(root.getLeftChild(), entry);
		}
		else if (root.getData().compareTo(entry) < 0)
		{
			return findSpace(root.getRightChild(), entry);
		}
		else
		{
			BinaryNode<T> rightNode = root.getRightChild();
			return findDuplicateSpace(rightNode, entry);
		}
		
	}
	@Override
	public void addEntry(T entry)
	{
		findSpace(this.root, entry).setData(entry);
		numberOfNodes++;
	}

	@Override
	public T remove(T entry)
	{
		BinaryNode<T> searchedNode = search(root, entry);
		T RETURN_ME = searchedNode.getData();
		searchedNode.setData(null);
		return RETURN_ME;
	}
	private void appendToArray(T data, T[] array)
	{
		for (int i = 0; i < array.length; i++)
		{
			if (array[i] == null)
			{
				array[i] = data;
				break;
			}
		}
	}
	
	private void inOrderTraverse(BinaryNode<T> root, T[] array)
	{
		if (root != null)
		{
			inOrderTraverse(root.getLeftChild(), array);
			appendToArray(root.getData(), array);
			inOrderTraverse(root.getRightChild(), array);
		}
	}
	@Override
	public T[] toArray()
	{
		@SuppressWarnings("unchecked")
		T[] sortedArray = (T[]) new Comparable[numberOfNodes];
		inOrderTraverse(root, sortedArray);
		return sortedArray;
	}

}
