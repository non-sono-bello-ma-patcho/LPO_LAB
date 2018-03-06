package lab07_12_18.accounts;

public interface HistoryAccount extends Account {
	/*
	 * undo the previous withdraw/deposit operation
	 * 
	 * throws IllegalStateException if
	 * 
	 * there was no previous operation or
	 * 
	 * the previous operation has been already undone
	 */
	long undo();

	/*
	 * redo the previous withdraw/deposit operation
	 * 
	 * throws IllegalStateException if
	 * 
	 * there was no previous operation or
	 * 
	 * the previous operation has been undone
	 */
	
	long redo();
}
