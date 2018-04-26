package lab10.ast;

import static java.util.Objects.requireNonNull;

public class ForEachStmt implements Stmt {
	private final Ident ident;
	private final Exp exp;
	private final StmtSeq block;

	public ForEachStmt(Ident id, Exp exp, StmtSeq block) {
		this.ident = requireNonNull(id);
		this.exp = requireNonNull(exp);
		this.block = requireNonNull(block);
	}

	public Ident getId() {
		return ident;
	}

	public Exp getExp() {
		return exp;
	}

	public StmtSeq getBlock() {
		return block;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + ident + "," + exp + "," + block + ")";
	}

}
