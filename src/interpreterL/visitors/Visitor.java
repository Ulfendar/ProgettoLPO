package interpreterL.visitors;

import interpreterL.parser.ast.Block;
import interpreterL.parser.ast.Exp;
import interpreterL.parser.ast.Ident;
import interpreterL.parser.ast.Stmt;
import interpreterL.parser.ast.StmtSeq;

public interface Visitor<T> {
	T visitAdd(Exp left, Exp right);

	T visitAssignStmt(Ident ident, Exp exp);

	T visitIntLiteral(int value);
	
	T visitEq(Exp left, Exp right);

	T visitMoreStmt(Stmt first, StmtSeq rest);

	T visitMul(Exp left, Exp right);

	T visitPrintStmt(Exp exp);

	T visitProg(StmtSeq stmtSeq);

	T visitSign(Exp exp);

	T visitIdent(Ident id); // the only corner case ...

	T visitSingleStmt(Stmt stmt);

	T visitDecStmt(Ident ident, Exp exp);

	T visitNot(Exp exp);

	T visitAnd(Exp left, Exp right);

	T visitBoolLiteral(boolean value);

	T visitIfStmt(Exp exp, Block thenBlock, Block elseBlock);

	T visitBlock(StmtSeq stmtSeq);

	T visitPairLit(Exp left, Exp right);

	T visitFst(Exp exp);

	T visitSnd(Exp exp);

	T visitStringLiteral(String value);

	T visitCat(Exp left, Exp right);
}
