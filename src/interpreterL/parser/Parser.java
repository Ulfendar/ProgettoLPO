package interpreterL.parser;

import interpreterL.parser.ast.Prog;

public interface Parser {

	Prog parseProg() throws ParserException;

}