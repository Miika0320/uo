/**
 * This class enables the calculation and sorting of information gain values
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 * @author Mikaela Dobie, 300164161 - University of Ottawa
 */
public class InformationGainCalculator {

	/**
	 * @param dataset is the dataset whose attributes we want to analyze and sort
	 *                according to information gain
	 * @return an array of GainInfoItem instances sorted in descending order of gain
	 *         value
	 */
	 
	 //GainInfoItem(String attributeName, AttributeType attributeType, double gainValue, String splitAt)
	public static GainInfoItem[] calculateAndSortInformationGains(VirtualDataSet dataset) {
		// WRITE YOUR CODE HERE!
		double gainTemp;
		int num = dataset.getNumberOfDatapoints()/2;
		String name;
		String split;
		AttributeType type;
		VirtualDataSet[] partitions;
		String[] unique;
		Attribute temp;
		GainInfoItem[] hiya = new GainInfoItem[dataset.getNumberOfAttributes()-1];
		double ent = 0;
		DataSet[] dArray = new DataSet[1];
		dArray[0] = dataset;
		
		
		for (int i =0; i<dataset.getNumberOfAttributes()-1;i++){
			temp = dataset.getAttribute(i);
			name = temp.getName();
			type = temp.getType();
			if (type == AttributeType.NOMINAL){
				partitions = dataset.partitionByNominallAttribute(i);
				ent = EntropyEvaluator.evaluate(partitions);
				
				split = name;
			}
			else{
				unique = dataset.getUniqueAttributeValues(i);
				num = checkGain(dataset, i);
				split = unique[num];
				partitions = dataset.partitionByNumericAttribute(i,num);
				
				ent = EntropyEvaluator.evaluate(partitions);
				
				
			}
			gainTemp = (EntropyEvaluator.evaluate(dArray)-ent);
			hiya[i] = new GainInfoItem(name,type,gainTemp,split);
			
			
		}
		GainInfoItem.reverseSort(hiya);
		//Remove the following line when this method has been implemented
		return hiya;
	}
	
	
	private static int checkGain(VirtualDataSet y, int index){
		
		
		String[] unique = y.getUniqueAttributeValues(index);
		VirtualDataSet[] parts;
		double[] x = new double[unique.length];
		double temp1 = 0.0;
		int temp2 = 0;
		DataSet[] temp = new DataSet[1];
		temp[0] = y;
		for (int k = 0; k<unique.length; k++){
			parts = y.partitionByNumericAttribute(index,k);
			x[k] = EntropyEvaluator.evaluate(temp)-EntropyEvaluator.evaluate(parts);
			
		}
		for (int i = 0; i<x.length-1;i++)
			if(x[i]>temp1){
				temp1 = x[i];
				temp2 = i;
			}
		return temp2;
			
		
		
	}

	public static void main(String[] args) throws Exception {

		StudentInfo.display();

		if (args == null || args.length == 0) {
			System.out.println("Expected a file name as argument!");
			System.out.println("Usage: java InformationGainCalculator <file name>");
			return;
		}

		String strFilename = args[0];

		ActualDataSet actual = new ActualDataSet(new CSVReader(strFilename));

		// System.out.println(actual);

		VirtualDataSet virtual = actual.toVirtual();

		// System.out.println(virtual);

		GainInfoItem[] items = calculateAndSortInformationGains(virtual);

		// Print out the output
		System.out.println(
				" *** items represent (attribute name, information gain) in descending order of gain value ***");
		System.out.println();

		for (int i = 0; i < items.length; i++) {
			System.out.println(items[i]);
		}
	}
}
