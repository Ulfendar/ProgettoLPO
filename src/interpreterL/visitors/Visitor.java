package interpreterL.visitors;

import interpreterL.parser.ast.*;

import java.util.Set;

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

	T visitSet(ExpSeq res);

	T visitExpList(Set<Exp> exps);

	T visitUnion(Exp set1, Exp set2);

	T visitIntersection(Exp set1, Exp set2);

	T visitSize(Exp exp);

	T visitStringLiteral(String value);

	T visitCat(Exp left, Exp right);

	T visitIn(Exp left, Exp right);

	T visitWhileStmt(Exp exp, Block block);

}
