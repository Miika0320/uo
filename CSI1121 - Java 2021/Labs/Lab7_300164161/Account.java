public class Account{
	private double balance;
	public Account(){
		balance = 0;
	}
	
	public double withdraw(double num)throws NotEnoughMoneyException{
		if (num> balance)
			throw new NotEnoughMoneyException(balance,num);
		else{
			balance -= num;
			return balance;
		}
	}
	
	public double deposit(double num)throws NotEnoughMoneyException{
		balance += num;
		return balance;
	}
	
	public double getBalance(){
		return balance;
	}
	
	
}