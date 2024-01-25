public abstract class AbstractSeries implements Series {

    public double[] take(int k) {

        // implement the method
		double[] take = new double[k];
		for (int i = 0; i<k; i++){
			take[i] = this.next();
		}
        return take;
    }
	
	

}