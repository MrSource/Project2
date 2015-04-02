package adt;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SortedCircularDoublyLinkedList implements SortedList<E>
 * @author Martin Rivera
 *
 * @param <E> extends Comparable class
 */
public class SortedCircularDoublyLinkedList<E extends Comparable<E>> implements SortedList<E> {
	Node header;
	int currentSize;

	//Default Constructor
	public SortedCircularDoublyLinkedList(){
		header = new Node();
		header.setValue(null);
		header.setNext(header);
		header.setPrev(header);
		currentSize = 0;
	}

	//Iterator Method 
	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}

	/**Add method
	 * @param obj element to be added to the DLL
	 */
	@Override
	public boolean add(E obj) {
		//base case
		if(obj == null)
			throw new IllegalArgumentException("Argument cannot be null.");

		boolean aux = false; //helper bool variable to verify if the element is added. 

		for(Node temp = header.getNext();temp.getValue()!= null; temp=temp.getNext()){
			if(obj.compareTo(temp.getValue()) < 0){
				if(temp.getPrev().getValue() == (header.getValue())){
					Node temp2= new Node();
					temp2.setValue(obj);
					temp2.setNext(temp);
					temp2.setPrev(header);
					header.setNext(temp2);
					temp.setPrev(temp2);
					aux=true;
					currentSize++;
					break;
				}

				else{
					Node temp2= new Node();
					temp2.setValue(obj);
					temp2.setNext(temp);
					temp2.setPrev(temp.getPrev());
					temp.getPrev().setNext(temp2);
					temp.setPrev(temp2);
					aux=true;
					currentSize++;
					break;
				}
			}
		}

		if(aux == false){
			Node temp = new Node();
			temp.setValue(obj);
			temp.setNext(header);
			temp.setPrev(header.getPrev());
			header.getPrev().setNext(temp);
			header.setPrev(temp);
			aux=true;
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

	/** Remove method to remove an obj at its first instance
	 * @return if the obj was removed
	 * @param obj to be removed on the first appereance
	 */
	@Override
	public boolean remove(E obj) {
		if(obj == null)
			throw new IllegalArgumentException("Parameter cannot be null");

		for(Node temp = header.getNext(); temp.getValue() != null; temp = temp.getNext()){
			if(obj.compareTo(temp.getValue())==0){
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

	/** Remove an element at an specific index
	 * @param int index to be removed
	 * @return return boolean if value is removed or not
	 */
	@Override
	public boolean remove(int index) {
		if((index < 0) || (index > this.currentSize))
			throw new IndexOutOfBoundsException();

		int i = 0;
		for(Node temp = header.getNext(); temp.getValue()!=null; temp = temp.getNext(), i++){
			if(index == i){
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

	/**
	 * Remove al repetion of an specific object
	 * @param E obj to be remove
	 * @return int removed count
	 */
	@Override
	public int removeAll(E obj) {
		int counter = 0;
		while(this.remove(obj)){
			counter++;
		}
		return counter;
	}

	/**First element of de DLL
	 * @return E value of the first node of the DLL
	 */
	@Override
	public E first() {
		if(isEmpty())
			return null;
		return header.getNext().getValue();
	}
	/** Last element of the DLL
	 * @return E value of the last node of the DLL
	 */
	@Override
	public E last() {
		if(isEmpty())
			return null;
		return header.getPrev().getValue();
	}

	/**Get an specific node on the DLL
	 * @param index node to be returned
	 * @return E element at the specific node
	 */
	@Override
	public E get(int index) {
		if((index<0)||(index>this.currentSize)){
			throw new IndexOutOfBoundsException();
		}

		int counter = 0;
		for(Node temp = header.getNext(); temp.getValue()!=null; temp = temp.getNext(), counter++){
			if(counter == index)
				return temp.getValue();	
		}

		return null;
	}
	/**Clear the whole DLL
	 * 
	 */
	@Override
	public void clear() {
		while(!this.isEmpty()){
			this.remove(0);
		}
	}

	/** Verify if a element is on the DLL or not
	 * @param E element to compare
	 * @return if it is on the DLL or not
	 */
	@Override
	public boolean contains(E e) {
		for(Node temp = header.getNext(); temp.getValue()!=null; temp = temp.getNext()){
			if(temp.getValue().compareTo(e)==0){
				return true;
			}
		}

		return false;
	}

	/**Verify if the DLL is empty or not
	 * @return boolean size == 0
	 */
	@Override
	public boolean isEmpty() {
		if(currentSize == 0){
			return true;
		}
		else {
			return false;
		}
	}

	/**Index iterator
	 *@param int index to start interation
	 */

	@Override
	public Iterator<E> iterator(int index) {
		return new ListIterator(index);
	}

	/**fistIndex of the parameter at the DLL
	 * @param element to verify its first index
	 * return int the first index of the @param
	 */

	@Override
	public int firstIndex(E e) {
		int counter = 0;
		for(Node temp = header.getNext(); temp.getValue()!= null; temp = temp.getNext(),counter++){
			if(e.compareTo(temp.getValue())==0){
				return counter;
			}
		}

		return -1;
	}

	/**lastIndex of the paramter at the DLL
	 * @param element ot verify its last index on the 
	 */
	@Override
	public int lastIndex(E e) {
		int counter = currentSize-1;
		for(Node temp = header.getPrev(); temp.getValue()!= null; temp = temp.getPrev(),counter--){
			if(e.compareTo(temp.getValue())==0){
				return counter;
			}
		}

		return -1;
	}

	/**
	 * reverse iterator of the DLL
	 */
	@Override
	public ReverseIterator<E> reverseIterator() {
		return new ReverseListIterator();
	}
	
	/**
	 * ReverseIterator from an specific index
	 * @param int index for starting the iteration
	 */
	@Override
	public ReverseIterator<E> reverseIterator(int index) {
		return new ReverseListIterator(index);
	}	

	/**
	 * Private class for nodes
	 * @author Martin Rivera
	 *
	 */
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

	/**
	 * Private class for a FW iterator
	 * @author Martin Rivera
	 *
	 */
	private class ListIterator implements Iterator<E>{
		private Node nextNode;

		public ListIterator(){
			this.nextNode = header.getNext();
		}

		public ListIterator(int index){
			if((index < 0) || (index>currentSize))
				throw new IndexOutOfBoundsException();

			int counter = 0;
			Node temp;

			for(temp = header.getNext(); counter < index; temp = temp.getNext(), counter++);
			this.nextNode = temp;
		}

		@Override
		public boolean hasNext() {
			return nextNode.getValue() != null;
		}

		@Override
		public E next() {
			if (hasNext()){
				E result = this.nextNode.getValue();
				this.nextNode = this.nextNode.getNext();
				return result;
			}
			else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();

		}	
	}
	/**
	 * Private class for a BW iterator
	 * @author Martin Rivera
	 *
	 */
	private class ReverseListIterator implements ReverseIterator<E>{
		private Node prevNode;

		public ReverseListIterator(){
			this.prevNode = header.getPrev();
		}

		public ReverseListIterator(int index){
			int counter = currentSize;
			Node temp;

			for(temp = header.getPrev(); counter > currentSize-index; temp = temp.getPrev(), counter--);
			this.prevNode = temp;
		}

		@Override
		public boolean hasPrevious() {
			return prevNode != header;
		}
		@Override
		public E previous() {
			if (hasPrevious()){
				E result = prevNode.getValue();
				prevNode = prevNode.getPrev();
				return result;
			}
			else {
				throw new NoSuchElementException();

			}

		}

	}
}