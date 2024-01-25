public class Rational {

    private int numerator;
    private int denominator;

    // constructors

    public Rational(int numerator) {
	     // Your code here
		 this.numerator = numerator;
		 this.denominator = 1;
    }

    public Rational(int numerator, int denominator) {
	     // Your code here
		 this.numerator = numerator/gcd(numerator,denominator);
		 this.denominator = denominator/gcd(numerator,denominator);
		 
		 
    }

    // getters

    public int getNumerator() {
	     return numerator;
    }

    public int getDenominator() {
	     return denominator;
    }

    // instance methods

    public Rational plus(Rational other) {
	     // Your code here
		 Rational c = new Rational(1,1);
		 if (denominator != other.getDenominator()){
			 c.numerator = numerator*other.denominator + other.numerator*denominator;
			 c.denominator = denominator*other.denominator;

		 }
		 else{
			 c.numerator = numerator + other.numerator;
			 c.denominator = denominator;
		 }
		 c.reduce();
		 return c;
    }

    public static Rational plus(Rational a, Rational b) {
    	// Your code here
		Rational c;
		c = a.plus(b);
		return c;
    }

    // Transforms this number into its reduced form

    private void reduce() {
      // Your code here
	  int gcd = gcd(numerator,denominator);
	  numerator = numerator/(gcd);
	  denominator = denominator/(gcd);
    }

    // Euclid's algorithm for calculating the greatest common divisor
    private int gcd(int a, int b) {
      // Note that the loop below, as-is, will time out on negative inputs.
      // The gcd should always be a positive number.
      // Add code here to pre-process the inputs so this doesn't happen.
		int olda = a;
		int oldb = b;
    	while (a != b && a>0 &&b>0)
    	    if (a > b)
    		     a = a - b;
    	    else
    		     b = b - a;
		if (olda == a && oldb%a != 0)
			return 1;
		else
			return a;
    }

    public int compareTo(Rational other) {
      // Your code here
	  int difference;
	  difference = numerator*other.denominator - other.numerator*denominator;
	  if (difference<0)
		  return -1;
	  else if (difference==0)
		  return 0;
	  else
		  return 1;
	}

    public boolean equals(Rational other) {
      // Your code here
	  return (numerator/denominator)==(other.numerator/other.denominator);
    }

    public String toString() {
    	String result;
    	if (denominator == 1) {
    	    // Your code here
			result = String.valueOf(numerator);
    	} else {
    	    // Your code here
			result = String.valueOf(numerator)+"/"+String.valueOf(denominator);
    	}
    	return result;
    }

}