package lab07_12_18.accounts;

public class CreditAccount implements Account {
	private static long availableId;
	private static final int default_limit = -1000_00;

	private int limit;
	private long balance; // in cents
	private final Client owner;
	private final long id;

	/*
	 * invariant owner!=null && balance>=limit && (c1.id==c2.id)==(c1==c2)
	 */
	private static void checkPositive(long amount) {
		if (amount <= 0)
			throw new IllegalArgumentException("Argument must be positive");
	}

	private static void checkLimit(long balance, long limit) {
		if (balance < limit)
			throw new IllegalArgumentException("Balance below limit");
	}

	protected CreditAccount(int limit, long balance, Client owner) {
		if (owner == null)
			throw new NullPointerException();
		CreditAccount.checkPositive(balance);
		CreditAccount.checkLimit(balance, limit);
		this.limit = limit;
		this.balance = balance;
		this.owner = owner;
		this.id = availableId++;
	}

	protected CreditAccount(long balance, Client owner) {
		this(CreditAccount.default_limit, balance, owner);
	}

	public static CreditAccount ofLimitBalance(int limit, long balance, Client owner) {
		return new CreditAccount(limit, balance, owner);
	}

	public static CreditAccount ofBalance(long balance, Client owner) {
		return new CreditAccount(balance, owner);
	}

	public long deposit(long amount) {
		CreditAccount.checkPositive(amount);
		return this.balance += amount;
	}

	public long withdraw(long amount) {
		CreditAccount.checkPositive(amount);
		long newBalance = this.balance - amount;
		CreditAccount.checkLimit(newBalance, this.limit);
		return this.balance = newBalance;
	}

	public long getBalance() {
		return this.balance;
	}

	public Client getOwner() {
		return this.owner;
	}

	public long getId() {
		return this.id;
	}

	public int getLimit() {
		return this.limit;
	}

	public void setLimit(int limit) {
		CreditAccount.checkLimit(this.balance, limit);
		this.limit = limit;
	}
}
