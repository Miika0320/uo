public class Arithmetic extends AbstractSeries {

    // instance variables
	private double sum = 0;
	private int count = 1;

    public double next() {

        // implement the method
		sum = sum + count;
		count++;
		return sum;
    }
}