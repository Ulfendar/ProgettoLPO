package interpreterL.parser.ast;

import interpreterL.visitors.Visitor;

public interface AST {
	<T> T accept(Visitor<T> visitor);

}
