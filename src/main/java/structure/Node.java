package structure;

/**
 * Serves as the node class, containing a reference to the next LinearNode<T> in
 * the queue and a reference to the element stored in that node.
 *
 * @param <T> object of type T
 * 
 * @author sagesilberman
 * 
 */
public class Node<T> {
	/** reference to next node in list */
	private Node<T> next;

	/** element stores at this node */
	private T element;

	/**
	 * Creates an empty node
	 */
	public Node() {
		next = null;
		element = null;
	}

	/**
	 * Creates a node strong the specified element.
	 * 
	 * @param elem element to be stored
	 */

	public Node(T elem) {
		next = null;
		element = elem;
	}

	/**
	 * Returns the node that follows this one.
	 * 
	 * @return LinearNode<T> reference to next node
	 */
	public Node<T> getNext() {
		return next;
	}

	/**
	 * Sets the node that follows this one.
	 * 
	 * @param node node to follow this one
	 */
	public void setNext(Node<T> node) {
		next = node;
	}

	/**
	 * Returns the element stored in this node.
	 * 
	 * @return T element stored at this node
	 */
	public T getElement() {
		return element;
	}

	/**
	 * Sets the element stored in this node.
	 * 
	 * @param elem element to be stored at this node
	 */
	public void setElement(T ele) {
		element = ele;
	}

}
