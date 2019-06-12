package interpreterL.parser;

public interface Tokenizer extends AutoCloseable {

	TokenType next() throws TokenizerException;

	String tokenString();

	int intValue();

	String strValue();

	TokenType tokenType();

	boolean hasNext();

	public void close() throws TokenizerException;

	boolean boolValue();

}