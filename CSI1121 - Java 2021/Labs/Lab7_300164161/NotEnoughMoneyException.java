public class NotEnoughMoneyException extends IllegalArgumentException{
	
	private double amount;
	private double balance;
	private double missing;
	
	public NotEnoughMoneyException(double balance, double amount){
		this.balance = balance;
		this.amount = amount;
		this.missing = amount - balance;
	}
	public double getAmount(){
		return amount;
	}
	
	public double getBalance(){
		return balance;
	}
	public double getMissingAmount(){
		return missing;
	}
	public String getMessage(){
		return "You do not have enough money to withdraw "+amount;
	}


}