package models;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SortedCircularDoublyLinkedList
 * 
 * @author Martin Rivera
 *
 * @param <E>
 */
public class SortedCircularDoublyLinkedList<E extends Comparable<E>> implements SortedList<E> {
	Node header;
	int currentSize;

	// Default Constructor
	    SortedCircularDoublyLinkedList() {
		header = new Node();
		header.setValue(null);
		header.setNext(header);
		header.setPrev(header);
		currentSize = 0;
	}

	// Iterator Method
	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}

	@Override
	public boolean add(E obj) {
		// base case
		if (obj == null)
			throw new IllegalArgumentException("Argument cannot be null.");

		boolean aux = false; // helper bool variable to verify if the element is
								// added.

		for (Node temp = header.getNext(); temp.getValue() != null; temp = temp.getNext()) {
			if (obj.compareTo(temp.getValue()) < 0) {
				if (temp.getPrev().getValue() == (header.getValue())) {
					Node temp2 = new Node();
					temp2.setValue(obj);
					temp2.setNext(temp);
					temp2.setPrev(header);
					header.setNext(temp2);
					temp.setPrev(temp2);
					aux = true;
					currentSize++;
					break;
				}

				else {
					Node temp2 = new Node();
					temp2.setValue(obj);
					temp2.setNext(temp);
					temp2.setPrev(temp.getPrev());
					temp.getPrev().setNext(temp2);
					temp.setPrev(temp2);
					aux = true;
					currentSize++;
					break;
				}
			}
		}

		if (aux == false) {
			Node temp = new Node();
			temp.setValue(obj);
			temp.setNext(header);
			temp.setPrev(header.getPrev());
			header.getPrev().setNext(temp);
			header.setPrev(temp);
			aux = true;
			currentSize++;
		}
		return aux;
	}

	/**
	 * @return current size
	 */
	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public boolean remove(E obj) {
		if (obj == null)
			throw new IllegalArgumentException("Parameter cannot be null");

		for (Node temp = header.getNext(); temp.getValue() != null; temp = temp.getNext()) {
			if (obj.compareTo(temp.getValue()) == 0) {
				temp.getPrev().setNext(temp.getNext());
				temp.getNext().setPrev(temp.getPrev());
				temp.setNext(null);
				temp.setPrev(null);
				temp.setValue(null);
				currentSize--;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean remove(int index) {
		if ((index < 0) || (index > this.currentSize))
			throw new IndexOutOfBoundsException();

		int i = 0;
		for (Node temp = header.getNext(); temp.getValue() != null; temp = temp.getNext(), i++) {
			if (index == i) {
				temp.getPrev().setNext(temp.getNext());
				temp.getNext().setPrev(temp.getPrev());
				temp.setNext(null);
				temp.setPrev(null);
				temp.setValue(null);
				currentSize--;
				return true;
			}
		}
		return false;
	}

	@Override
	public int removeAll(E obj) {
		int counter = 0;
		while (this.remove(obj)) {
			counter++;
		}
		return counter;
	}

	@Override
	public E first() {
		if (isEmpty())
			return null;
		return header.getNext().getValue();
	}

	@Override
	public E last() {
		if (isEmpty())
			return null;
		return header.getPrev().getValue();
	}

	@Override
	public E get(int index) {
		if ((index < 0) || (index > this.currentSize)) {
			throw new IndexOutOfBoundsException();
		}

		int counter = 0;
		for (Node temp = header.getNext(); temp.getValue() != null; temp = temp.getNext(), counter++) {
			if (counter == index)
				return temp.getValue();
		}

		return null;
	}

	@Override
	public void clear() {
		while (!this.isEmpty()) {
			this.remove(0);
		}
	}

	@Override
	public boolean contains(E e) {
		for (Node temp = header.getNext(); temp.getValue() != null; temp = temp.getNext()) {
			if (temp.getValue().compareTo(e) == 0) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isEmpty() {
		if (currentSize == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Iterator<E> iterator(int index) {
		return new ListIterator(index);
	}

	@Override
	public int firstIndex(E e) {
		int counter = 0;
		for (Node temp = header.getNext(); temp.getValue() != null; temp = temp.getNext(), counter++) {
			if (e.compareTo(temp.getValue()) == 0) {
				return counter;
			}
		}

		return -1;
	}

	@Override
	public int lastIndex(E e) {
		int counter = currentSize - 1;
		for (Node temp = header.getPrev(); temp.getValue() != null; temp = temp.getPrev(), counter--) {
			if (e.compareTo(temp.getValue()) == 0) {
				return counter;
			}
		}

		return -1;
	}

	@Override
	public ReverseIterator<E> reverseIterator() {
		return new ReverseListIterator();
	}

	@Override
	public ReverseIterator<E> reverseIterator(int index) {
		return new ReverseListIterator(index);
	}

	private class Node {
		private E value;
		private Node next;
		private Node prev;

		public E getValue() {
			return value;
		}

		public void setValue(E value) {
			this.value = value;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public Node getPrev() {
			return prev;
		}

		public void setPrev(Node prev) {
			this.prev = prev;
		}
	}

	private class ListIterator implements Iterator<E> {
		private Node nextNode;

		public ListIterator() {
			this.nextNode = header.getNext();
		}

		public ListIterator(int index) {
			if ((index < 0) || (index > currentSize))
				throw new IndexOutOfBoundsException();

			int counter = 0;
			Node temp;

			for (temp = header.getNext(); counter < index; temp = temp.getNext(), counter++);
			this.nextNode = temp;
		}

		@Override
		public boolean hasNext() {
			return nextNode.getValue() != null;
		}

		@Override
		public E next() {
			if (hasNext()) {
				E result = this.nextNode.getValue();
				this.nextNode = this.nextNode.getNext();
				return result;
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();

		}
	}

	private class ReverseListIterator implements ReverseIterator<E> {
		private Node prevNode;

		public ReverseListIterator() {
			this.prevNode = header.getPrev();
		}

		public ReverseListIterator(int index) {
			int counter = currentSize;
			Node temp;

			for (temp = header.getPrev(); counter > currentSize - index; temp = temp.getPrev(), counter--);
			this.prevNode = temp;
		}

		@Override
		public boolean hasPrevious() {
			return prevNode != header;
		}

		@Override
		public E previous() {
			if (hasPrevious()) {
				E result = prevNode.getValue();
				prevNode = prevNode.getPrev();
				return result;
			} else {
				throw new NoSuchElementException();

			}

		}

	}
	
	private static SortedCircularDoublyLinkedList<Gem> singleton = new SortedCircularDoublyLinkedList<Gem>();
	
	public static SortedCircularDoublyLinkedList<Gem> getInstance(){
		return singleton;
	}
}
