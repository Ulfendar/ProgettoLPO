package interpreterL.visitors.evaluation;

import interpreterL.environments.EnvironmentException;
import interpreterL.environments.GenEnvironment;
import interpreterL.parser.ast.*;
import interpreterL.visitors.Visitor;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class Eval implements Visitor<Value> {

	private final GenEnvironment<Value> env = new GenEnvironment<>();
	private final PrintWriter printWriter;

	public Eval() {
		printWriter = new PrintWriter(System.out, true);
	}

	public Eval(PrintWriter printWriter) {
		this.printWriter = requireNonNull(printWriter);
	}

	// dynamic semantics for programs; no value returned by the visitor

	@Override
	public Value visitProg(StmtSeq stmtSeq) {
		try {
			stmtSeq.accept(this);
			// possible runtime errors
			// EnvironmentException: undefined variable
		} catch (EnvironmentException e) {
			throw new EvaluatorException(e);
		}
		return null;
	}

	// dynamic semantics for statements; no value returned by the visitor

	@Override
	public Value visitAssignStmt(Ident ident, Exp exp) {
		env.update(ident, exp.accept(this));
		return null;
	}

	@Override
	public Value visitPrintStmt(Exp exp) {
		printWriter.println(exp.accept(this));
		return null;
	}

	@Override
	public Value visitDecStmt(Ident ident, Exp exp) {
		env.dec(ident, exp.accept(this));
		return null;
	}

	@Override
	public Value visitIfStmt(Exp exp, Block thenBlock, Block elseBlock) {
		if (exp.accept(this).asBool())
			thenBlock.accept(this);
		else if (elseBlock != null)
			elseBlock.accept(this);
		return null;
	}

	@Override
	public Value visitBlock(StmtSeq stmtSeq) {
		env.enterScope();
		stmtSeq.accept(this);
		env.exitScope();
		return null;
	}

	// dynamic semantics for sequences of statements
	// no value returned by the visitor

	@Override
	public Value visitSingleStmt(Stmt stmt) {
		stmt.accept(this);
		return null;
	}

	@Override
	public Value visitMoreStmt(Stmt first, StmtSeq rest) {
		first.accept(this);
		rest.accept(this);
		return null;
	}

	@Override
	public Value visitWhileStmt(Exp exp, Block block) {
		while (exp.accept(this).asBool())
			block.accept(this);
		return null;
	}

	// dynamic semantics of expressions; a value is returned by the visitor

	public Value visitExpList(Set<Exp> exps) {
		Set<Value> ret = new HashSet<>();
		for (Exp cur : exps)
			ret.add(cur.accept(this));
		return new SetValue(ret);
	}


	@Override
	public Value visitUnion(Exp first, Exp sec) {
        return (first.accept(this)).asSet().Union(sec.accept(this).asSet());
	}

	public Value visitIntersection(Exp first, Exp sec) {
        return (first.accept(this)).asSet().Intersection(sec.accept(this).asSet());
	}

	public Value visitSize(Exp exp) {
		try {
            return new IntValue(exp.accept(this).asSet().Size());
		} catch (EvaluatorException e) {
			return new IntValue(exp.accept(this).asString().length());
		}
	}

	public Value visitIn(Exp first, Exp sec) {
		return new BoolValue(sec.accept(this).asSet().isIn(first.accept(this)));
	}

	@Override
	public Value visitAdd(Exp left, Exp right) {
		return new IntValue(left.accept(this).asInt() + right.accept(this).asInt());
	}

	@Override
	public Value visitIntLiteral(int value) {
		return new IntValue(value);
	}

	@Override
	public Value visitMul(Exp left, Exp right) {
		return new IntValue(left.accept(this).asInt() * right.accept(this).asInt());
	}

	@Override
	public Value visitSign(Exp exp) {
		return new IntValue(-exp.accept(this).asInt());
	}

	@Override
	public Value visitIdent(Ident id) {
		return env.lookup(id);
	}

	@Override
	public Value visitNot(Exp exp) {
		return new BoolValue(!exp.accept(this).asBool());
	}

	@Override
	public Value visitAnd(Exp left, Exp right) {
		return new BoolValue(left.accept(this).asBool() && right.accept(this).asBool());
	}

	@Override
	public Value visitBoolLiteral(boolean value) {
		return new BoolValue(value);
	}

	@Override
	public Value visitEq(Exp left, Exp right) {
		return new BoolValue(left.accept(this).equals(right.accept(this)));
	}

	@Override
	public Value visitPairLit(Exp left, Exp right) {
		return new PairValue(left.accept(this), right.accept(this));
	}

	@Override
	public Value visitFst(Exp exp) {
		return exp.accept(this).asPair().getFstVal();
	}

	@Override
	public Value visitSnd(Exp exp) {
		return exp.accept(this).asPair().getSndVal();
	}

	@Override
	public Value visitSet(ExpSeq set) {

        return set.accept(this);
	}

	@Override
	public Value visitStringLiteral(String value) {
		return new StringValue(value);
	}

	@Override
	public Value visitCat(Exp left, Exp right) {
		return new StringValue(left.accept(this).asString() + right.accept(this).asString());
	}

}