import java.util.NoSuchElementException;

public class OrderedList implements OrderedStructure {

    // Implementation of the doubly linked nodes (nested-class)

    private static class Node {

      	private Comparable value;
      	private Node previous;
      	private Node next;

      	private Node ( Comparable value, Node previous, Node next ) {
      	    this.value = value;
      	    this.previous = previous;
      	    this.next = next;
      	}
    }

    // Instance variables

    private Node head;
	

    // Representation of the empty list.

    public OrderedList() {
        // Your code here.
        head = new Node(null,null,null);
    }

    // Calculates the size of the list

    public int size() {
      	// Remove line below and add your implementation.
		Node current = head.next;
		int count = 0;
		while (current!=null){
			count++;
			current = current.next;
			
		}
		return count;
    }


    public Object get( int pos ) throws IndexOutOfBoundsException{
        // Remove line below and add your implementation.
		Node current = head.next;
		int count = 0;
		while (count!= pos && current!= null){
			count++;
			current = current.next;
		}
		if (count == pos) 
			return (Object) current.value;
		else
			throw new IndexOutOfBoundsException();
    }

    // Adding an element while preserving the order

    public boolean add( Comparable o ) throws IllegalArgumentException {
        // Remove line below and add your implementation.
		Node current = head.next;
		Node temp;
		if (o == null)
			throw new IllegalArgumentException();
		if (current == null){
			temp = new Node(o, head, null);
			head.next = temp;
			return true;
		}
		while (current!= null){
			if (this.size()==1) {
				if (current.value.compareTo(o)>0) {
					temp = new Node (o, current.previous, current);
					head.next = temp;
					current.previous = temp;
					return true;
				}
				else {
					temp = new Node (o, current, null);
					current.next = temp;
					return true;
				}
			
			}
			if (current.value.compareTo(o)>0 && current.previous==null||current.value.compareTo(o)>0 && current.previous.value.compareTo(o)<0){
				temp = new Node (o, current.previous, current);
				current.previous.next = temp;
				current.previous = temp;
				return true;
			}
			else if (current.value.compareTo(o)<=0 && current.next==null||current.value.compareTo(o)<=0 && current.next.value.compareTo(o)>=0){
				temp = new Node (o, current, current.next);
				current.next = temp;
				current.next.previous = temp;
				return true;
			}
			else if (current.value.compareTo(o)>0){
				current = current.previous;
				if (current == null){
					temp = new Node(o, head, head.next);
					return true;
				}
			}
			else{
				current = current.next;
				if (current == null){
					temp = new Node(o, current.previous, null);
					return true;
				}
			}
				
		}
		return false;

    }

    //Removes one item from the position pos.

    public void remove( int pos ) throws IndexOutOfBoundsException{
      // Remove line below and add your implementation.
	  Node current = head.next;
		int count = 0;
		while (count!= pos && current!= null){
			count++;
			current = current.next;
		}
		if (count == pos) {
			if (current.previous == null)
				if (current.next == null)
					head.next=null;
				else
					current.next.previous = head;
			else if (current.next ==null){
				current.previous.next = null;
  			}
			else{
				current.previous.next = current.next;
				current.next.previous = current.previous;
			}
			current = null;
		}
		else
			throw new IndexOutOfBoundsException();
    }

    // Knowing that both lists store their elements in increasing
    // order, both lists can be traversed simultaneously.

    public void merge( OrderedList other ) {
      // Remove line below and add your implementation.

		Comparable bcurrent= (Comparable) other.get(0);
		this.add(bcurrent);
		int count = 1;
		while (count<other.size()){
			bcurrent = (Comparable) other.get(count);
			this.add(bcurrent);
			count++;
		}
	}
}