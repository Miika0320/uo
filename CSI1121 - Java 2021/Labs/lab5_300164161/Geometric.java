public class Geometric extends AbstractSeries {

    // instance variables
	private double product = 0.0;
	private double count = 0.0;
    
    public double next() {

        // implement the method
		product  = product + (1.0/(Math.pow(2.0, count)));
		count++;
		return product;
    }
}