/**
 * This class enables calculating (weighted-average) entropy values for a set of
 * datasets
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 * @author Mikaela Dobie, 300164161 - University of Ottawa
 */
public class EntropyEvaluator {

	/**
	 * A static method that calculates the weighted-average entropy of a given set
	 * (array) of datasets. The assignment description provides a detailed
	 * explanation of this calculation. In particular, note that all logarithms are
	 * to base 2. For your convenience, we provide a log2 method. You can use this
	 * method wherever you need to take logarithms in this assignment.
	 * 
	 * @param partitions is the array of datasets to compute the entropy of
	 * @return Shannon's logarithmic entropy (to base 2) for the partitions
	 */
	public static double evaluate(DataSet[] partitions) {
		// WRITE YOUR CODE HERE!
		
		double temp0 = 0.0;
		double temp1 = 0.0;
		double[] ent = new double[partitions.length];
		int tempRow;
		double tempR;
		double tot;
		int total = 0;
		double sum = 0.0;
		// for (int k =0; k<partitions[0].getSource().getNumberOfDatapoints();k++)
			// if (partitions[0].getSource().getValueAt(j,partitions[0].getSource().getNumberOfAttributes()-1)==partitions[0].getSource().getUniqueValues(partitions[0].getNumberOfAttributes()-1)[0])
					// zero+=1.0;
				// else
					// one+=1.0;
		
		
		for (int i = 0; i<partitions.length;i++){
			tempRow = partitions[i].getNumberOfDatapoints();
			temp0 = 0.0;
			temp1 = 0.0;
			total+= tempRow;

			for (int j = 0; j<tempRow;j++){
				String o = partitions[i].getValueAt(j,partitions[i].getNumberOfAttributes()-1);
				String t = partitions[0].getUniqueAttributeValues(partitions[0].getNumberOfAttributes()-1)[0];
				if (o.equals(t)){
					
					temp0+=1.0;
				}
				else{
					
					temp1+=1.0;
				}
			}
			if (temp0!=0&&temp1!=0&&tempRow!=0)
				ent[i] = (-temp0/tempRow)*log2(temp0/tempRow)+(-temp1/tempRow)*log2(temp1/tempRow);
			else if (temp0==0&&temp1!=0&&tempRow!=0)
				ent[i] = (-temp1/tempRow)*log2(temp1/tempRow);
			else if (temp1==0&&temp0!=0&&tempRow!=0)
				ent[i] = (-temp0/tempRow)*log2(temp0/tempRow);
			else
				ent[i] = 0.0;
		}
		for (int k=0;k<ent.length;k++){
			tempR = partitions[k].getNumberOfDatapoints();
			tot = total;
			if(total!=0)
				ent[k] = ent[k]*((tempR/tot));
			sum += ent[k];
		}
		
		
		return sum;
		//Remove the following line when this method has been implemented
		
	}

	/**
	 * Calculate base-2 logarithm for a given number
	 * 
	 * @param x is the number to take the logarithm of
	 * @return base-2 logarithm for x
	 */
	public static double log2(double x) {
		return (Math.log(x) / Math.log(2));
	}
}
