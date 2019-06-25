package interpreterL.visitors.typechecking;

import interpreterL.environments.EnvironmentException;
import interpreterL.environments.GenEnvironment;
import interpreterL.parser.ast.*;
import interpreterL.visitors.Visitor;

import java.util.Iterator;
import java.util.Set;

import static interpreterL.visitors.typechecking.PrimtType.*;

public class TypeCheck implements Visitor<Type> {

	private final GenEnvironment<Type> env = new GenEnvironment<>();

	private void checkBinOp(Exp left, Exp right, Type type) {
		type.checkEqual(left.accept(this));
		type.checkEqual(right.accept(this));
	}

	// static semantics for programs; no value returned by the visitor

	@Override
	public Type visitProg(StmtSeq stmtSeq) {
		try {
			stmtSeq.accept(this);
		} catch (EnvironmentException e) { // undefined variable
			throw new TypecheckerException(e);
		}
		return null;
	}

	// static semantics for statements; no value returned by the visitor

	@Override
	public Type visitAssignStmt(Ident ident, Exp exp) {
		Type found = env.lookup(ident);
		found.checkEqual(exp.accept(this));
		return null;
	}

	@Override
	public Type visitPrintStmt(Exp exp) {
		exp.accept(this);
		return null;
	}

	@Override
	public Type visitDecStmt(Ident ident, Exp exp) {
		env.dec(ident, exp.accept(this));
		return null;
	}

	@Override
	public Type visitIfStmt(Exp exp, Block thenBlock, Block elseBlock) {
		BOOL.checkEqual(exp.accept(this));
		thenBlock.accept(this);
		if (elseBlock == null)
			return null;
		elseBlock.accept(this);
		return null;
	}

	@Override
	public Type visitBlock(StmtSeq stmtSeq) {
		env.enterScope();
		stmtSeq.accept(this);
		env.exitScope();
		return null;
	}

	@Override
	public Type visitWhileStmt(Exp exp, Block block) {
		BOOL.checkEqual(exp.accept(this));
		block.accept(this);
		return null;
	}

	// static semantics for sequences of statements
	// no value returned by the visitor

	@Override
	public Type visitSingleStmt(Stmt stmt) {
		stmt.accept(this);
		return null;
	}

	@Override
	public Type visitMoreStmt(Stmt first, StmtSeq rest) {
		first.accept(this);
		rest.accept(this);
		return null;
	}

	// static semantics of expressions; a type is returned by the visitor

	@Override
	public Type visitAdd(Exp left, Exp right) {
		checkBinOp(left, right, INT);
		return INT;
	}

	@Override
	public Type visitIntLiteral(int value) {
		return INT;
	}

	@Override
	public Type visitMul(Exp left, Exp right) {
		checkBinOp(left, right, INT);
		return INT;
	}

	@Override
	public Type visitSign(Exp exp) {
		return INT.checkEqual(exp.accept(this));
	}

	@Override
	public Type visitIdent(Ident id) {
		return env.lookup(id);
	}

	@Override
	public Type visitNot(Exp exp) {
		return BOOL.checkEqual(exp.accept(this));
	}

	@Override
	public Type visitAnd(Exp left, Exp right) {
		checkBinOp(left, right, BOOL);
		return BOOL;
	}

	@Override
	public Type visitBoolLiteral(boolean value) {
		return BOOL;
	}

	@Override
	public Type visitEq(Exp left, Exp right) {
		left.accept(this).checkEqual(right.accept(this));
		return BOOL;
	}

	@Override
	public Type visitPairLit(Exp left, Exp right) {
		return new PairType(left.accept(this), right.accept(this));
	}

	@Override
	public Type visitFst(Exp exp) {
		return exp.accept(this).getFstPairType();
	}

	@Override
	public Type visitSnd(Exp exp) {
		return exp.accept(this).getSndPairType();
	}


	public Type visitExpList(Set<Exp> exps){
		Iterator<Exp> iter = exps.iterator();
		Type type = null;
		if(iter.hasNext())
			type = iter.next().accept(this);
		while (iter.hasNext())
			type.checkEqual(iter.next().accept(this));
		return type;
	}

	@Override
	public Type visitSet(ExpSeq elem){
		return new SetType(elem.accept(this));
	}

	@Override
	public Type visitUnion(Exp left, Exp right){
		Type l = left.accept(this);
		l.checkIsSetType();
		Type r = right.accept(this);
		r.checkIsSetType();
		l.checkEqual(r);
		return l;

	}

	public Type visitIntersection(Exp left, Exp right){
		Type r = right.accept(this);
		r.checkIsSetType();
		Type l = left.accept(this);
		l.checkIsSetType();
		l.checkEqual(r);
		return l;

	}

	public Type visitSize(Exp exp){
		 Type type = exp.accept(this);

		 type.checkEquals();

		 return INT;
	}

	@Override
	public Type visitIn(Exp elem, Exp set) {
		Type typeS = set.accept(this);
		Type typeE = elem.accept(this);
		typeS.checkIsSetTypeOf(typeE);
		return BOOL;
	}

	@Override
	public Type visitStringLiteral(String value) { return STRING;}

	@Override
	public Type visitCat(Exp left, Exp right) {
		checkBinOp(left, right, STRING);
		return STRING;
	}

}
