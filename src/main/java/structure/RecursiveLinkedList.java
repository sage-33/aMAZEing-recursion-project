package structure;

/**
 * COMMENT
 * 
 * @author sagesilberman
 *
 * @param <T>
 */
public class RecursiveLinkedList<T> implements ListInterface<T> {

	private int count; // a count of the list
	private Node<T> head, tail; // creates a node

	public RecursiveLinkedList() {
		count = 0;
		head = null; // sets the head of the list
		tail = null; // sets the tail of the list

	}

	// COMMENT
	public Node<T> getNode(Node<T> currentNode, int currentIndex) {
		// base case if currentIndex is 0, return currentNode recursive case
		if (currentNode == null) {
			throw new NullPointerException("element is null");
		}
		if (currentIndex == 0) {
			return currentNode;
		} else {
			return getNode(currentNode.getNext(), currentIndex - 1);
		}

	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public ListInterface<T> insertFirst(T elem) {
		// like push!
		if (elem == null) {
			throw new NullPointerException("element is null");
		}

		// create a node
		Node<T> newNode = new Node<T>(elem);
		// set it's next to the current head
		newNode.setNext(head);
		// update head to point to our new node
		head = newNode;
		if (isEmpty()) {
			tail = newNode;
		}
		count++;
		// return our list
		return this;
	}

	@Override
	public ListInterface<T> insertLast(T elem) {
		if (elem == null) {
			throw new NullPointerException("element is null");
		}

		// enqueue :D
		Node<T> node = new Node<T>(elem);

		if (isEmpty()) {
			head = node;
		} else {
			tail.setNext(node);
		}

		tail = node;
		count++;

		// return the queue itself
		return this;
	}

	@Override
	public ListInterface<T> insertAt(int index, T elem) {
		// create new node with elem
		// get node at index-1 (using private getNode)
		// set new nodes next to node at index 1's next
		// set index-1's next to new node
		if (elem == null) {
			throw new NullPointerException("element is null");
		} else if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("out of bounds");
		}

		if (index == 0) {
			insertFirst(elem);
		} else if (index == size()) {
			insertLast(elem);
		} else {
			Node<T> newNode = new Node<T>(elem);
			Node<T> prevNode = getNode(head, index - 1);

			newNode.setNext(prevNode.getNext());
			prevNode.setNext(newNode);

			count++;
		}
		return this;
	}

	@Override
	public T removeFirst() {
		// dequeue :DD
		if (isEmpty()) {
			throw new IllegalStateException("it's empty so can't execute code");
		}
		T result = head.getElement();
		head = head.getNext();
		count--;

		if (isEmpty()) {
			tail = null;
		}

		return result;
	}

	@Override
	public T removeLast() {
		if (isEmpty()) {
			throw new IllegalStateException("it's empty so can't execute code");
		}
		return removeAt(count - 1);
	}

	@Override
	public T removeAt(int index) {
		// use contains and then call removeat at that thing

		// changing the node of the previous index to the index plus one so we basically
		// skip over the index we are removing
		if (index < 0 || index > count - 1) {
			throw new IndexOutOfBoundsException(" out of bounds");
		}
		T elem = getNode(head, index).getElement();
		if (index == 0) {
			head = head.getNext();
		} else if (index == size() - 1) {
			getNode(head, index - 1).setNext(null);

		} else {
			getNode(head, index - 1).setNext(getNode(head, index + 1));
		}
		count--;
		return elem;

	}

	@Override
	public T getFirst() {
		// peak!
		if (isEmpty()) {
			throw new IllegalStateException("it's empty so can't execute code");
		}
		return head.getElement();
	}

	@Override
	// get first but use tail!
	public T getLast() {
		if (isEmpty()) {
			throw new IllegalStateException("it's empty so can't execute code");
		}
		return tail.getElement();
	}

	@Override
	public T get(int index) {
		return getNode(head, index).getElement();

	}

	@Override
	public boolean remove(T elem) {
		if (elem == null) {
			throw new NullPointerException("element is null");
		}
		int index = contains(elem);
		if (index == -1) {
			return false;
		} else {
			removeAt(index);
			return true;
		}
	}

	@Override
	public int contains(T elem) {
		return contains(elem, head, 0);
	}

	// COMMENT
	private int contains(T toFind, Node<T> toCheck, int currentIndex) {
		// 2 base cases
		// base 1 - I've reached end of the list (return -1)
		// base 2 - I've found the node (toCheck.getElement().equals(toFind)) return
		// currentIndex

		// 1 recursive case
		// contains(toFind, toCheck.getNext(), currentIndex+1)

		if (currentIndex == count) {
			return -1;
		} else if (toCheck.getElement().equals(toFind)) {
			return currentIndex;
		} else {
			return contains(toFind, toCheck.getNext(), currentIndex + 1);
		}

	}

	@Override
	public boolean isEmpty() {
		if (count == 0) {
			return true;
		} else {
			return false;
		}
	}
}