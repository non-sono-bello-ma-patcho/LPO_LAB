import java.util.Random;

public class creditAccountClass{
	private long balance;
	private String owner;
	private long id;
	private int limit;

	public creditAccountClass(long bal, String own, long id, int limit){
		this.balance = bal;
		this.owner = own;
		this.id = id;
		this.limit = limit;
	}

	public long deposit(long amount){
		if(amount<0) throw new IllegalArgumentException();
		this.balance += amount;
		return balance;
	}

	public long withdrawn(long amount){
		if(amount<0 || this.balance-amount<this.limit) throw new IllegalArgumentException();
		this.balance-=amount;
		return balance;
	}

	public long getBalance(){
		return this.balance;
	}

	public String getOwner(){
		return this.owner;
	}

	public long getId(){
		return this.id;
	}

	public int getLimit(){
		return this.limit;
	}

	public void setLimit(int limit){
		if(limit>this.balance) throw new IllegalArgumentException();
		this.limit = limit;
	}
	// unuseful main:
	public static void main(String[] args){
		Random rand = new Random();
		creditAccountClass[] users = new creditAccountClass[4];
		users[0] = new creditAccountClass(0, "Andreo Storacio", 1, 0);
		users[1] = new creditAccountClass(0, "Danielo Fiorenzo", 2, 0);
		users[2] = new creditAccountClass(0, "John Doe", 3, 0);
		users[3] = new creditAccountClass(0, "Jane Doe", 4, 0);
		int u1, u2;
		long amount;
		for(int i=0; i<50; i++){
			u1 = rand.nextInt(4);
			amount = rand.nextInt(125);
			switch(rand.nextInt(3)) {
				case 0:
					users[u1].deposit(amount);
					System.out.println(users[u1].getId()+" [ "+users[u1].getOwner()+" ] has earned: "+amount);
					break;
				case 1:
					if(amount < users[u1].getBalance() - users[u1].getLimit()){
						users[u1].withdrawn(amount);
						System.out.println(users[u1].getId()+" [ "+users[u1].getOwner()+" ] has spent: "+amount);
					}
					else System.out.println(users[u1].getId()+" [ "+users[u1].getOwner()+" ] wanted to spend: "+amount+" but haven't such money..."); 
					break;
				case 2:
					u2 = rand.nextInt(4);
					while(u2==u1) u2 = rand.nextInt(4);
					if(amount < users[u1].getBalance() - users[u1].getLimit()){
						users[u1].withdrawn(amount);
						users[u2].deposit(amount);
						System.out.println(users[u1].getId()+" [ "+users[u1].getOwner()+" ] gave "+users[u2].getId()+" [ "+users[u2].getOwner()+"]: "+amount);
					}
					else System.out.println(users[u1].getId()+" [ "+users[u1].getOwner()+" ] wanted to give : "+amount+" to "+users[u2].getId()+" [ "+users[u2].getOwner()+"] but haven't such money..."); 
					break;
			}
		}
		for(int i=0; i<4; i++)	System.out.println(users[i].getId()+" [ "+users[i].getOwner()+" ] has "+users[i].getBalance());

	}
}