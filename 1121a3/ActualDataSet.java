/**
 * This class is used for representing an actual dataset, that is, a dataset
 * that holds a data matrix
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 * @author Mikaela Dobie, 300164161 - University of Ottawa
 */
public class ActualDataSet extends DataSet {
	/**
	 * The data matrix
	 */
	private String[][] matrix;

	/**
	 * The source identifier for the data. When the data source is a file, sourceId
	 * will be the name and location of the source file
	 */
	private String dataSourceId;

	/**
	 * Constructor for ActualDataSet. In addition to initializing dataSourceId,
	 * numAttributes, numRows and matrix, the constructor needs to create an array of
	 * attributes (instance of the Attribute class) and initialize the "attributes"
	 * instance variable of DataSet.
	 * 
	 * 
	 * @param reader is the DataReader instance to read data from.
	 */
	public ActualDataSet(DataReader reader) {
		// WRITE YOUR CODE HERE!
		this.matrix = reader.getData();
		this.dataSourceId = reader.getSourceId();
		numAttributes = reader.getNumberOfColumns();
		numRows = reader.getNumberOfDataRows();
		attributes = new Attribute[numAttributes];
		
		
		String name;
		AttributeType type;
		String[] unique;
		for (int i = 0; i<numAttributes; i++){
			name = reader.getAttributeNames()[i];
			unique = getUniqueValues(i);
			if (Util.isArrayNumeric(unique)){
				type = AttributeType.NUMERIC;
			}
			else{
				type = AttributeType.NOMINAL;
			}
			attributes[i] = new Attribute(name, i, type, unique);
		}

	}
	
		private String[] getUniqueValues(int column) {

		String temp = "";
		String temp2 = "";
		String[] unique;

		for (int i = 0; i < numRows; i++){
			if(matrix[i][column]==null)
				matrix[i][column]="";		
			if (temp.contains("'"+matrix[i][column]+"'") == false){
				temp = temp.concat("'"+matrix[i][column]+"'"+",");
				if (matrix[i][column].contains(",")){
					temp2 = temp2.concat("'"+matrix[i][column]+"'"+",");
				}
				else{
					temp2 = temp2.concat(matrix[i][column]+",");
				}
			}
		}
		
		unique = temp2.split(",(?=([^\']*\'[^\']*\')*[^\']*$)");
	    return unique;
	}

	/**
	 * Implementation of DataSet's abstract getValueAt method for an actual dataset
	 */
	public String getValueAt(int row, int attributeIndex) {
		// WRITE YOUR CODE HERE!
		if (attributeIndex >=0 && attributeIndex < numAttributes && row>=0 && row < numRows){
			return matrix[row][attributeIndex];
		}
		else{
			return null;
		}
		//Remove the following line when this method has been implemented

	}

	/**
	 * @return the sourceId of the dataset.
	 */
	public String getSourceId() {
		// WRITE YOUR CODE HERE!
		return dataSourceId;
		//Remove the following line when this method has been implemented

	}

	/**
	 * Returns a virtual dataset over this (actual) dataset
	 * 
	 * @return a virtual dataset spanning the entire data in this (actual) dataset
	 */
	public VirtualDataSet toVirtual() {
		// WRITE YOUR CODE HERE!
		int[] temp = new int[numRows];
		for (int i = 0; i<numRows; i++){
			temp[i] = i;
		}
		VirtualDataSet me = new VirtualDataSet(this, temp, attributes);
		return me;
		//Remove the following line when this method has been implemented

	}

	/**
	 * Override of toString() in DataSet
	 * 
	 * @return a string representation of this (actual) dataset.
	 */
	public String toString() {
		// WRITE YOUR CODE HERE!
		String str;
		str = "Actual dataset("+dataSourceId+") with "+Integer.toString(numAttributes)+" attribute(s) and "+Integer.toString(numRows)+" row(s)";
		str = str + System.lineSeparator() + super.toString();
		return str;
		//Remove the following line when this method has been implemented

	}
}