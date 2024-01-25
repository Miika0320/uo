// You are allowed to use LinkedList or other Collection classes in A2 and A3
import java.util.LinkedList;
import java.util.Arrays;

/**
 * This class is used for representing a virtual dataset, that is, a dataset
 * that is a view over an actual dataset. A virtual dataset has no data matrix
 * of its own.
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 * @author Mikaela Dobie, 300164161 - University of Ottawa
 */
public class VirtualDataSet extends DataSet {

	/**
	 * reference to the source dataset (instance of ActualDataSet)
	 */
	private ActualDataSet source;

	/**
	 * array of integers mapping the rows of this virtual dataset to the rows of its
	 * source (actual) dataset
	 */
	private int[] map;

	/**
	 * Constructor for VirtualDataSet. There are two important considerations here:
	 * (1) Make sure that you keep COPIES of the "rows" and "attributes" passed as
	 * formal parameters. Do not, for example, say this.map = rows. Instead, create
	 * a copy of rows before assigning that copy to this.map. (2) Prune the value
	 * sets of the attributes. Since a virtual dataset is only a subset of an actual
	 * dataset, it is likely that some or all of its attributes may have smaller
	 * value sets.
	 * 
	 * @param source     is the source dataset (always an instance of ActualDataSet)
	 * @param rows       is the set of rows from the source dataset that belong to
	 *                   this virtual dataset
	 * @param attributes is the set of attributes belonging to this virtual dataset.
	 *                   IMPORTANT: you need to recalculate the unique value sets
	 *                   for these attributes according to the rows. Why? Because
	 *                   this virtual set is only a subset of the source dataset and
	 *                   its attributes potentially have fewer unique values.
	 */
	public VirtualDataSet(ActualDataSet source, int[] rows, Attribute[] attributes) {
		// WRITE YOUR CODE HERE!
		this.source = source;
		this.map = rows.clone();
		this.attributes = attributes.clone();
		numRows = map.length;
		numAttributes = attributes.length;
		String[] unique;
		int a =0;
		
		for (int i = 0; i<attributes.length; i++){
			unique = getUniqueValues(i);
			if (this.attributes[i] != null)
				this.attributes[i].replaceValues(unique);
			else{
				this.attributes = new Attribute[numAttributes-1];
				numAttributes = this.attributes.length;
				for (int j=0;j<numAttributes; j++){
					if (attributes[a]!= null)
						this.attributes[j] = attributes.clone()[a];
					a++;
					
				}
			}
		}
		
	}
	/**
	 * Get unique values of certain rows.
	 */
	private String[] getUniqueValues(int column) {
	

		String temp = "";
		String temp2 = "";
		String[] unique;

		for (int i = 0; i < map.length; i++){
			if (temp.contains("'"+this.getValueAt(i,column)+"'") == false){
				temp = temp.concat("'"+this.getValueAt(i,column)+"'"+",");
				if (this.getValueAt(i,column).contains(",")){
					temp2 = temp2.concat("'"+this.getValueAt(i,column)+"'"+",");
				}
				else{
					temp2 = temp2.concat(this.getValueAt(i,column)+",");
				}
			}
		}
		
		unique = temp2.split(",(?=([^\']*\'[^\']*\')*[^\']*$)");
	    return unique;
	}

	/**
	 * String representation of the virtual dataset.
	 */
	public String toString() {
		// WRITE YOUR CODE HERE!
		String str;
		str = "Virtual dataset with "+Integer.toString(numAttributes)+" attribute(s) and "+Integer.toString(numRows)+" row(s)";
		str = str + System.lineSeparator() + "- Dataset is a view over " + source.getSourceId();
		str = str + System.lineSeparator() + "- Row indices in this dataset (w.r.t. its source dataset): "+Util.intArrayToString(map);
		str = str + System.lineSeparator() + super.toString();
		return str;
		//Remove the following line when this method has been implemented

	}
	

	/**
	 * Implementation of DataSet's getValueAt abstract method for virtual datasets.
	 * Hint: You need to call source.getValueAt(...). What you need to figure out is
	 * with what parameter values that method needs to be called.
	 */
	public String getValueAt(int row, int attributeIndex) {
		// WRITE YOUR CODE HERE!
		String value = source.getValueAt(map[row], attributes[attributeIndex].getAbsoluteIndex());
		return value;
		//Remove the following line when this method has been implemented
	}

	/**
	 * @return reference to source dataset
	 */
	public ActualDataSet getSourceDataSet() {
		// WRITE YOUR CODE HERE!
		return source;
		//Remove the following line when this method has been implemented

	}


	/**
	 * This method splits the virtual dataset over a nominal attribute. This process
	 * has been discussed and exemplified in detail in the assignment description.
	 * 
	 * @param attributeIndex is the index of the nominal attribute over which we
	 *                       want to split.
	 * @return a set (array) of partitions resulting from the split. The partitions
	 *         will no longer contain the attribute over which we performed the
	 *         split.
	 */
	public VirtualDataSet[] partitionByNominallAttribute(int attributeIndex) {
		// WRITE YOUR CODE HERE!
		String[] unique = getUniqueValues(attributeIndex);
		int m = 0;
		Attribute[] revise = new Attribute[attributes.length-1];
		for (int l = 0;l<attributes.length;l++){
			if (l!=attributeIndex){
				revise[m] = attributes[l];
				m++;
			}
		}
		VirtualDataSet[] partitions = new VirtualDataSet[unique.length];
		LinkedList<Integer> tempRow = new LinkedList<>();
		
		

		for (int i =0;i<unique.length;i++){
			for (int j = 0; j<map.length;j++){
				if (getValueAt(j,attributeIndex).contains(unique[i])) {
					tempRow.add(map[j]);
				}
			}
			int[] temp = new int[tempRow.size()];
			for (int k = 0; k<tempRow.size();k++){
				temp[k] = tempRow.get(k);
			}
			VirtualDataSet tempAmount = new VirtualDataSet(source, temp, revise);
			tempRow.clear();
			partitions[i] = tempAmount;
			
		}
		
		
		//this.attributes = revise;
		//numAttributes = revise.length;
		return partitions;
		//Remove the following line when this method has been implemented

	}


	/**
	 * This method splits the virtual dataset over a given numeric attribute at a
	 * specific value from the value set of that attribute. This process has been
	 * discussed and exemplified in detail in the assignment description.
	 * 
	 * @param attributeIndex is the index of the numeric attribute over which we
	 *                       want to split.
	 * @param valueIndex     is the index of the value (in the value set of the
	 *                       attribute of interest) to use for splitting
	 * @return a pair of partitions (VirtualDataSet array of length two) resulting
	 *         from the two-way split. Note that the partitions will retain the
	 *         attribute over which we perform the split. This is in contrast to
	 *         splitting over a nominal, where the split attribute disappears from
	 *         the partitions.
	 */
	public VirtualDataSet[] partitionByNumericAttribute(int attributeIndex, int valueIndex) {
		// WRITE YOUR CODE HERE!
		String[] unique = getUniqueValues(attributeIndex);
		VirtualDataSet[] partitions = new VirtualDataSet[2];
		LinkedList<Integer> tempRow = new LinkedList<>();
		LinkedList<Integer> tempRow2 = new LinkedList<>();

		String[] uni = new String[2];
		

		for (int m = 0; m<unique.length; m++){
			if (Double.parseDouble(unique[m])<= Double.parseDouble(unique[valueIndex]))
				uni[0] += unique[m]+',';
			else
				uni[1] += unique[m]+',';
			
		}			
		for (int i =0;i<map.length;i++){
			if (uni[0].contains(getValueAt(i,attributeIndex))) {
				tempRow.add(map[i]);
			}
			else{
				tempRow2.add(map[i]);
			}
		}
		
		int[] temp = new int[tempRow.size()];
		int[] temp2 = new int[tempRow2.size()];
		for (int k = 0; k<tempRow.size();k++){
			temp[k] = tempRow.get(k);
		}
		for (int t = 0; t<tempRow2.size();t++){
			temp2[t] = tempRow2.get(t);
		}
		VirtualDataSet tempAmount = new VirtualDataSet(source, temp, attributes);
		partitions[0] = tempAmount;
		VirtualDataSet tempAmount2 = new VirtualDataSet(source, temp2, attributes);
		partitions[1] = tempAmount2;

		return partitions;
		//Remove the following line when this method has been implemented
	}

	public static void main(String[] args) throws Exception {

		StudentInfo.display();

		System.out.println("============================================");
		System.out.println("THE WEATHER-NOMINAL DATASET:");
		System.out.println();

		ActualDataSet figure5Actual = new ActualDataSet(new CSVReader("weather-nominal.csv"));

		System.out.println(figure5Actual);

		VirtualDataSet figure5Virtual = figure5Actual.toVirtual();

		System.out.println("JAVA IMPLEMENTATION OF THE SPLIT IN FIGURE 5:");
		System.out.println();

		VirtualDataSet[] figure5Partitions = figure5Virtual
				.partitionByNominallAttribute(figure5Virtual.getAttributeIndex("outlook"));

		for (int i = 0; i < figure5Partitions.length; i++)
			System.out.println("Partition " + i + ": " + figure5Partitions[i]);

		System.out.println("============================================");
		System.out.println("THE WEATHER-NUMERIC DATASET:");
		System.out.println();

		ActualDataSet figure9Actual = new ActualDataSet(new CSVReader("weather-numeric.csv"));

		System.out.println(figure9Actual);

		VirtualDataSet figure9Virtual = figure9Actual.toVirtual();

		// Now let's figure out what is the index for humidity in figure9Virtual and
		// what is the index for "80" in the value set of humidity!

		int indexForHumidity = figure9Virtual.getAttributeIndex("humidity");

		Attribute humidity = figure9Virtual.getAttribute(indexForHumidity);

		String[] values = humidity.getValues();

		int indexFor80 = -1;

		for (int i = 0; i < values.length; i++) {
			if (values[i].equals("80")) {
				indexFor80 = i;
				break;
			}
		}

		if (indexFor80 == -1) {
			System.out.println("Houston, we have a problem!");
			return;
		}

		VirtualDataSet[] figure9Partitions = figure9Virtual.partitionByNumericAttribute(indexForHumidity, indexFor80);

		System.out.println("JAVA IMPLEMENTATION OF THE SPLIT IN FIGURE 9:");
		System.out.println();

		for (int i = 0; i < figure9Partitions.length; i++)
			System.out.println("Partition " + i + ": " + figure9Partitions[i]);

	}
}