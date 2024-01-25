import java.io.File;
import java.util.Scanner;

/**
 * The class enables loading a dataset from a file (CSV format) and deriving
 * some important characteristics of the data
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 *	Mikaela Dobie 300164161
 *
 */
public class DataSet {

	/**
	 * The delimiter that separates attribute names and attribute values
	 */
	private static final char DELIMITER = ',';

	/**
	 * Character allowing escape sequences containing the delimiter
	 */
	private static final char QUOTE_MARK = '\'';

	/**
	 * Instance variable for storing the number of attributes (columns)
	 */
	private int numColumns;

	/**
	 * Instance variable for storing the number of datapoints (data rows)
	 */
	private int numRows;

	/**
	 * Instance variable for storing attribute names
	 */
	private String[] attributeNames;

	/**
	 * Instance variable for storing datapoints
	 */
	private String[][] matrix;

	/**
	 * Constructs a dataset by loading a CSV file
	 * 
	 * @param strFilename is the name of the file
	 */
	public DataSet(String strFilename) throws Exception {

		// WRITE YOUR CODE HERE!
		calculateDimensions(strFilename);
		instantiateFromFile(strFilename);
	}

	/**
	 * Returns the name of the attribute at a given column index
	 * 
	 * @param column is the column index
	 * @return attribute name at index (null if the index is out of range)
	 */
	public String getAttributeName(int column) {
		// WRITE YOUR CODE HERE!
		// Note: Remember to handle out-of-range values!
		
		// Remove the following null return after you implement this method
		if (column >=0 && column < attributeNames.length){
			return attributeNames[column];
		}
		else{
			return null;
		}
	}

	/**
	 * Returns the value of a given column for a given row (datapoint)
	 * 
	 * @param row    is the row (datapoint) index
	 * @param column is the column index
	 * @return the value of the attribute at column for the datapoint at row (null
	 *         if either row or column are out of range)
	 */
	public String getAttributeValue(int row, int column) {
		// WRITE YOUR CODE HERE!
		if (column >=0 && column < numColumns && row>=0 && row < numRows){
			return matrix[column][row];
		}
		else{
			return null;
		}
		// Note: Remember to handle out-of-range values!
		
		// Remove the following null return after you implement this method
	    

	}

	/**
	 * Returns the number of attributes
	 * 
	 * @return number of attributes
	 */
	public int getNumberOfAttributes() {
		return numColumns;
	}

	/**
	 * Returns the number of datapoints
	 * 
	 * @return number of datapoints
	 */
	public int getNumberOfDatapoints() {
		return numRows;
	}

	/**
	 * Returns a reference to an array containing the unique values that an
	 * attribute can assume in the dataset
	 * 
	 * @param attributeName is the name of the attribute whose unique values must be
	 *                      returned
	 * @return String[] reference to the unique values of the the attribute with the
	 *         given name
	 */
	public String[] getUniqueAttributeValues(String attributeName) {

		// WRITE YOUR CODE HERE!
		
		// Hint: You can first implement getUniqueAttributeValues(int column), below, 
		// and then make use of that private method here!
		
	    // Remove the following null return after you implement this method and
	    // return an appropriate array reference instead
	    int dex = -1;
		for (int i = 0; i < attributeNames.length; i++){
			if (attributeName.contains(attributeNames[i])){
				dex = i;
			}
		}
		if (dex==-1){
			return null;
		}
		else{
			return getUniqueAttributeValues(dex);
		}
	}

	/**
	 * Returns a reference to an array containing the unique values that the
	 * attribute at a certain column can assume in the dataset
	 * 
	 * @param column is the index (staring from zero) for the attribute whose unique
	 *               values must be returned
	 * @return String[] reference to the unique values of the attribute at the given
	 *         column
	 */
	private String[] getUniqueAttributeValues(int column) {
	
	    // WRITE YOUR CODE HERE!
		String temp = "";
		String temp2 = "";
		String[] unique;

		for (int i = 0; i < numRows; i++){
			if(matrix[column][i]==null)
				matrix[column][i]="";
			if (temp.contains("'"+matrix[column][i]+"'") == false){
				temp = temp.concat("'"+matrix[column][i]+"'"+",");
				if (matrix[column][i].contains(",")){
					temp2 = temp2.concat("'"+matrix[column][i]+"'"+",");
				}
				else{
					temp2 = temp2.concat(matrix[column][i]+",");
				}
			}
		}
		
		unique = temp2.split(",(?=([^\']*\'[^\']*\')*[^\']*$)");
	    // Remove the following null return after you implement this method and
	    // return an appropriate array reference instead
	    return unique;
	}

	/**
	 * Returns in the form of an explanatory string some important characteristics
	 * of the dataset. These characteristics are: the number of attributes, the
	 * number of datapoints and the unique values that each attribute can assume
	 * 
	 * @return String containing the characteristics (metadata)
	 */
	public String metadataToString() {

		// Hint: You can combine multiple lines by appending
		// a (platform-dependent) separator to the end of each line.
		// To obtain the (platform-dependent) separator, you can use 
		// the following command.
		String separator = System.getProperty("line.separator");

		// WRITE YOUR CODE HERE!
		String[] unique;
		String finale;
		finale = "Number of attributes: "+Integer.toString(numColumns)+separator+"Number of datapoints: "+Integer.toString(numRows)+separator+"* * * Attribute Value Sets * * *"+separator;
		//if x; final += getAttributeName(i)+" (numeric): "+unique2+separator, else: final += getAttributeName(i)+" (nominal): "+unique+separator
		//return final
		// System.out.println("Number of attributes: "+numColumns);
		// System.out.println("Number of datapoints: "+numRows);
		// System.out.println();
		// System.out.println("* * * Attribute Value Sets * * *");
		for (int i = 0; i < numColumns; i++){
			unique = getUniqueAttributeValues(i);
			
			if (Util.isArrayNumeric(unique)){
				// System.out.println(getAttributeName(i)+" (numeric): "+unique2);
				finale += getAttributeName(i)+" (numeric): "+Util.numericArrayToString(unique)+separator;
			}
			else{
				// System.out.println(getAttributeName(i)+" (nominal): "+unique);
				finale += getAttributeName(i)+" (nominal): "+Util.nominalArrayToString(unique)+separator;
			}
				
		}
		return finale;
		
		// Hint: You need to call getUniqueAttributeValues() for
		// each attribute (via either attribute name or attribute column) and
		// then concatenate the string representations of the arrays returned by
		// getUniqueAttributeValues(). To get the string representations for 
		// these arrays, you can use the methods provided in the Util class.
		// For nominal attributes use: Util.nominalArrayToString()
		// For numeric attributes use: Util.numericArrayToString()

		// Remove the following null return after you implement this method	

	}

	/**
	 * <b>main</b> of the application. The method first reads from the standard
	 * input the name of the CSV file to process. Next, it creates an instance of
	 * DataSet. Finally, it prints to the standard output the metadata of the
	 * instance of the DataSet just created.
	 * 
	 * @param args command lines parameters (not used in the body of the method)
	 */
	public static void main(String[] args) throws Exception {

        StudentInfo.display();

		System.out.print("Please enter the name of the CSV file to read: ");

		Scanner scanner = new Scanner(System.in);
		
		String strFilename = scanner.nextLine();

		DataSet dataset = new DataSet(strFilename);
		
		System.out.print(dataset.metadataToString());

	}

	/**
	 * This method should set the numColumns and numRows instance variables
	 * The method is incomplete; you need to complete it.
	 * @param strFilename is the name of the dataset file
	 */
	private void calculateDimensions(String strFilename) throws Exception {

		Scanner scanner = new Scanner(new File(strFilename));
		
		// YOU ARE ALLOWED TO DEFINE LOCAL VARIABLES
		String[] temp = null;
		int lines = -1;
		String[] temp2 = null;
		
		while (scanner.hasNext()) {
			// Read one full line from file
			
			// WRITE YOUR CODE HERE!
			String str = scanner.nextLine();
			//temp2 = str.split(",");
			
			temp2 = str.split(",");
			
			if (lines==-1 && (str.replaceAll(",","").length() != 0)){
				temp = str.split(",(?=([^\']*\'[^\']*\')*[^\']*$)");
			}
			
			if (str.isEmpty() == false)
				lines++;
		}
		
		if (temp != null)
		numColumns = temp.length;
		numRows = lines;
		
		

		scanner.close();
	}

	/**
	 * This method should load the attribute names into the attributeNames
	 * instance variable and load the datapoints into the matrix instance variable.
	 * The method is incomplete; you need to complete it.
	 * @param strFilename is the name of the file to read
	 */
	private void instantiateFromFile(String strFilename) throws Exception {
		Scanner scanner = new Scanner(new File(strFilename));
		
		// YOU ARE ALLOWED TO DEFINE LOCAL VARIABLES
		int i = -1;
		String[] temp;
		matrix = new String[numColumns][numRows];
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			
			// WRITE YOUR CODE HERE!
			if (i==-1){
				attributeNames = str.split(",(?=([^\']*\'[^\']*\')*[^\']*$)");
				for (int j = 0; j < attributeNames.length; j++){
					attributeNames[j] = attributeNames[j].trim().replaceAll("'", "");
				}
			}
			else{
				temp = str.split(",(?=([^\']*\'[^\']*\')*[^\']*$)");
				for (int j = 0; j < temp.length; j++){
					if (temp[j].length() != 0){					
						matrix[j][i] = temp[j].trim().replaceAll("'", "");
					}
					else{
						matrix[j][i] = "";
					}
				}
			}
			if (str.isEmpty() == false){
				i++;
			}
		}
		scanner.close();
	}
}