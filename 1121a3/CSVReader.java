import java.io.File;
import java.util.Scanner;

/**
 * This class provides an implementation of the DataReader interface for CSV
 * files
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 * @author Mikaela Dobie, 300164161 - University of Ottawa
 */
public class CSVReader implements DataReader {
	// WRITE YOUR CODE HERE!
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
	 * Instance variable for storing CSV file path
	 */
	private String filePath;
	
	/**
	 * Constructs a dataset by loading a CSV file
	 * 
	 * @param strFilename is the name of the file
	 */
	public CSVReader(String filePath) throws Exception {
		// WRITE YOUR CODE HERE!
		this.filePath = filePath;
		matrix = new String[numRows][numColumns];
		calculateDimensions(filePath);
		instantiateFromFile(filePath);
		
		
	}
	private void calculateDimensions(String filePath) throws Exception {
		Scanner scanner = new Scanner(new File(filePath));
		
		String[] temp = null;
		int lines = -1;

		
		while (scanner.hasNext()) {

			String str = scanner.nextLine();

			
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
	private void instantiateFromFile(String filePath) throws Exception {
		Scanner scanner = new Scanner(new File(filePath));

		int i = -1;
		String[] temp;
		matrix = new String[numRows][numColumns];
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			
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
						matrix[i][j] = temp[j].trim().replaceAll("'", "");
					}
					else{
						matrix[i][j] = "";
					}
				}
			}
			if (str.isEmpty() == false){
				i++;
			}
		}
		scanner.close();
	}


	public String[] getAttributeNames() {
		// WRITE YOUR CODE HERE!
		return attributeNames;
		//Remove the following line when this method has been implemented
	}

	public String[][] getData() {
		// WRITE YOUR CODE HERE!
		return matrix;
		//Remove the following line when this method has been implemented
	}

	public String getSourceId() {
		// WRITE YOUR CODE HERE!
		return filePath;
		//Remove the following line when this method has been implemented
	}

	public int getNumberOfColumns() {
		// WRITE YOUR CODE HERE!
		return numColumns;
		//Remove the following line when this method has been implemented
		
	}

	public int getNumberOfDataRows() {
		// WRITE YOUR CODE HERE!
		return numRows;
		//Remove the following line when this method has been implemented
		
	}
}