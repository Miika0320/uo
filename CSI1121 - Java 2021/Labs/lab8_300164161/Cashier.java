public class Cashier{
	
	private int customersServed;
	private int itemsServed;
	private ArrayQueue<Customer> queue;
	private int time;
	private Customer current;
	private int wait;
	
	public Cashier(){
		itemsServed = 0;
		customersServed = 0;
		queue = new ArrayQueue<Customer>();
		time = 0;
		if (!queue.isEmpty())
			current = queue.dequeue();
		wait = 0;
	}
	
	public void addCustomer(Customer c){
		queue.enqueue(c);
	}
	
	public int getQueueSize(){
		return queue.size();
	}
	public void serveCustomers(int currentTime){
		
		if(current==null&&!queue.isEmpty()){
			
			current = queue.dequeue();
			
			if (current!=null&&current.getNumberOfItems() == 1){
				current.serve();
				itemsServed += current.getNumberOfServedItems();
				customersServed++;
				time+=currentTime-current.getArrivalTime();
				current = null;
			}
			else
				current.serve();
					
				
		}
		
		else{
			if (current!=null&&current.getNumberOfItems() == 1){
				current.serve();
				itemsServed += current.getNumberOfServedItems();
				customersServed++;
				time+=currentTime-current.getArrivalTime();
				current = null;
				
			}
			else if (current!=null)
				current.serve();
		}
			
		
		
	}
	
	public int getTotalCustomerWaitTime(){
		return time;
	}
	
	public int getTotalCustomersServed(){
		return customersServed;
	}
	
	public int getTotalItemsServed(){
		return itemsServed;
	}
	
	public String toString(){
		String str;
		if (customersServed!=0){
			str = "The total number of customers served is "+Integer.toString(customersServed)+System.lineSeparator();
			str += "The average number of items per customers was "+Integer.toString(itemsServed/customersServed)+System.lineSeparator();
			str += "The average waiting time (in seconds) was "+Integer.toString(time/customersServed);
		}else{
			str = "The total number of customers served is 0"+System.lineSeparator();
			str += "The average number of items per customers was 0"+System.lineSeparator();
			str += "The average waiting time (in seconds) was 0";
		}
		return str;
	}
	

}