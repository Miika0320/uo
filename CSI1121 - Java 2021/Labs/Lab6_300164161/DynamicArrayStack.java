public class DynamicArrayStack<E> implements Stack<E> {

    // Instance variables

    private E[] elems;  // Used to store the elements of this ArrayStack
    private int top;    // Designates the first free cell
    private static final int DEFAULT_INC = 25;   //Used to store default increment / decrement

    @SuppressWarnings( "unchecked" )

    // Constructor
    public DynamicArrayStack( int capacity ) {
        // Your code here.
		if (capacity >= DEFAULT_INC)
			elems = (E[]) new Object [ capacity ];
		else
			elems = (E[]) new Object [ DEFAULT_INC ];
		top = 0;
    }

    // Gets current capacity of the array
    public int getCapacity() {
        return elems.length;
    }

    // Returns true if this DynamicArrayStack is empty
    public boolean isEmpty() {
        return ( top == 0 );
    }

    // Returns the top element of this ArrayStack without removing it
    public E peek() {
        return elems[ top-1 ];
    }

    @SuppressWarnings( "unchecked" )

    // Removes and returns the top element of this stack
    public E pop() {
        // Your code here.
		E popped = elems[ --top ];

        elems[ top ] = null;
		if (top+25<= elems.length){
			E[] temp = (E[]) new Object [ elems.length-25 ];
			for (int i = 0;i<temp.length; i++){
				temp[i] = elems[i];
			}
			elems = temp;
		}
        return popped;
    }

    @SuppressWarnings( "unchecked" )

    // Puts the element onto the top of this stack.
    public void push( E element ) {
        // Your code here.
		if (top <= DEFAULT_INC-1){
			elems[ top++ ] = element;
		}
		else{
			E[] temp = (E[]) new Object [ elems.length+25 ];
			for (int i = 0;i<elems.length; i++){
				temp[i] = elems[i];
			}
			elems = temp;
			elems[ top++ ] = element;
		}
    }

    @SuppressWarnings( "unchecked" )

    public void clear() {
        // Your code here.
		for (int i = 0; i < elems.length; i++){
			elems[i] = null;
		}
		elems = (E[]) new Object [ DEFAULT_INC ];
		top = 0;
    }

}