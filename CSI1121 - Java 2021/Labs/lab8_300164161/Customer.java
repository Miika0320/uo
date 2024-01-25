import java.util.Random;
public class Customer{
	private int arrival;
	private int itemsI;
	private int itemsR;
	private static final int MAX_NUM_ITEMS = 25;
	
	public Customer(int arrivalTime){
		arrival = arrivalTime;
		itemsI = numItems();
		itemsR = itemsI;
	}
	private static int numItems(){
		Random generator;  
		generator = new Random();  
 
		int numItems;  
		numItems = generator.nextInt(MAX_NUM_ITEMS-1)+1;
		return numItems;
	}
	
	public int getArrivalTime(){
		return arrival;
	}
	
	public int getNumberOfItems(){
		return itemsR;
	}
	
	public int getNumberOfServedItems(){
		return itemsI-itemsR;
	}
	
	public void serve(){
		itemsR--;
	}
	
}