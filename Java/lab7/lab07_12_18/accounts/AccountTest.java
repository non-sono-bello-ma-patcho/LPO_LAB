package lab07_12_18.accounts;

public class AccountTest {

	public static void main(String[] args) {
		Client alice = new Person("Alice", "Ford");
		HistoryAccount aliceAccount = HistoryCreditAccount.newOfLimitBalance(-1000, 1000, alice);
		assert aliceAccount.withdraw(1000) == 0;
		assert aliceAccount.redo() == -1000;
		assert aliceAccount.undo() == 0;
		assert aliceAccount.deposit(2000) == 2000;
		assert aliceAccount.redo() == 4000;
		assert aliceAccount.redo() == 6000;
		assert aliceAccount.undo() == 4000;
		assert aliceAccount.getBalance() == 4000;
		assert aliceAccount.getLimit() == -1000;
	}

}
