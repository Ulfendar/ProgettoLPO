package interpreterL.parser.ast;

import interpreterL.visitors.Visitor;

public class AssignStmt extends AbstractAssignStmt {

	public AssignStmt(Ident ident, Exp exp) {
		super(ident, exp);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitAssignStmt(ident, exp);
	}
}
