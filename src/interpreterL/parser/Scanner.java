package interpreterL.parser;

public interface Scanner extends AutoCloseable {
	void next() throws ScannerException;

	boolean hasNext() throws ScannerException;

	String group();

	String group(int group);

	void close() throws ScannerException;
}