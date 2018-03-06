package lab07_12_18.accounts;

public class HistoryCreditAccount extends CreditAccount implements HistoryAccount {
    /* completare */
	private long History; //for the last operations. For default it should be 0
	
	protected HistoryCreditAccount(int limit, long balance, Client owner){
		super(limit,balance,owner);
	}
	
	public static  HistoryCreditAccount newOfLimitBalance(int limit, long balance, Client owner){
		return new HistoryCreditAccount(limit,balance,owner);
	}
	
	public long undo(){
		if(this.History == 0)
			throw new IllegalStateException("cannot do the undo operation again or maybe there was no preavious operations");
		else if(this.History < 0){
			long temp = this.deposit(this.History);
			this.History = 0;
			return temp;
		}
		else {
			long temp = this.withdraw(this.History);
			this.History = 0;
			return temp;
		}
	}
	
	public long redo(){
		if(this.History == 0)
			throw new IllegalStateException("you can not redo after an undo");
		else if(this.History > 0)
			return this.deposit(this.History);
		else return this.withdraw(this.History);
	}
	
	
	
	@Override
	public long withdraw(long amount){
		super.withdraw(amount);
		History = -amount;
		return this.getBalance();
	}
	@Override
	public long deposit(long amount){
		super.deposit(amount);
		History = amount;
		return this.getBalance();
	}
	
}
