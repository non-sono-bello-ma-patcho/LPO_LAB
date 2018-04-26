package lab10;

public interface Scanner extends AutoCloseable {
	void next() throws ScannerException;

	boolean hasNext() throws ScannerException;

	String group();

	String group(int group);

	public void close() throws ScannerException;
}