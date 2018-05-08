package lab11.visitors.typechecking;

import lab11.environments.EnvironmentException;
import lab11.environments.GenEnvironment;
import lab11.parser.ast.Exp;
import lab11.parser.ast.ExpSeq;
import lab11.parser.ast.Ident;
import lab11.parser.ast.Stmt;
import lab11.parser.ast.StmtSeq;
import lab11.visitors.Visitor;

import static lab11.visitors.typechecking.PrimtType.INT;

public class TypeCheck implements Visitor<Type> {

	private final GenEnvironment<Type> env = new GenEnvironment<>();

	private void checkBinOp(Exp left, Exp right, Type type) {
		left.accept(this).checkEqual(type);
		right.accept(this).checkEqual(type);
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
		// to be modified/completed

		/*env.enterLevel(); da capire ancora bene... secondo me bisogna comunque creare una cella nuova sulla lista */

		return null;
	}

	@Override
	public Type visitPrintStmt(Exp exp) {
		// to be modified/completed
		exp.accept(this);
		return null;
	}

	@Override
	public Type visitForEachStmt(Ident ident, Exp exp, StmtSeq block) {
		// to be modified/completed
		Type ty = exp.accept(this).getListElemType();
		env.enterLevel();
		env.dec(ident,ty);
		block.accept(this);
		env.exitLevel();

		return null;
	}

	@Override
	public Type visitVarStmt(Ident ident, Exp exp) {
		// to be modified/completed
		return null;
	}

	// static semantics for sequences of statements
	// no value returned by the visitor

	@Override
	public Type visitSingleStmt(Stmt stmt) {
		// to be modified/completed
		return null;
	}

	@Override
	public Type visitMoreStmt(Stmt first, StmtSeq rest) {
		// to be modified/completed
		return null;
	}

	// static semantics of expressions; a type is returned by the visitor

	@Override
	public Type visitAdd(Exp left, Exp right) {
		// to be modified/completed
		return null;
	}

	@Override
	public Type visitIntLiteral(int value) {
		// to be modified/completed
		return null;
	}

	@Override
	public Type visitListLiteral(ExpSeq exps) {
		// to be modified/completed
		return new ListType(exps.accept(this));
	}

	@Override
	public Type visitMul(Exp left, Exp right) {
		// to be modified/completed
		checkBinOp(left,right,INT);
		return INT;
	}

	@Override
	public Type visitPrefix(Exp left, Exp right) {
		// to be modified/completed
		Type elem = left.accept(this);
		return new ListType(elem).checkEqual(right.accept(this));
	}

	@Override
	public Type visitSign(Exp exp) {
		// to be modified/completed
		return null;
	}

	@Override
	public Type visitIdent(String name) {
		// to be modified/completed
		return null;
	}

	// static semantics of sequences of expressions
	// a type is returned by the visitor

	@Override
	public Type visitSingleExp(Exp exp) {
		return null;
	}

	@Override
	public Type visitMoreExp(Exp first, ExpSeq rest) {
		// to be modified/completed
		return null;
	}

}
