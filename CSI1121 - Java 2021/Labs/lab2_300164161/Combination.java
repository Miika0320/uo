public class Combination {

    // Instance variables.
    private int a;
	private int b;
	private int c;

    // Constructor
    public Combination( int first, int second, int third ) {
        // Your code here
		a = first;
		b = second;
		c = third;
    }

    // An instance method that compares this object
    // to other.
    // Always check that other is not null, i)
    // an error would occur if you tried to
    // access other.first if other was null, ii)
    // null is a valid argument, the method should
    // simply return false.

    public boolean equals( Combination other ) {
        // Put your code here and remove the line below
        if (other!=null && a == other.a && b == other.b && c == other.c){
			return true;
		}
		else{
			return false;
		}
    }

    // Returns a String representation of this Combination.

    public String toString() {
        // Put your code here and remove the line below
        return a+":"+b+":"+c;
    }

}