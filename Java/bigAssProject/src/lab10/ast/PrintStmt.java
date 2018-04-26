package lab10.ast;

import static java.util.Objects.requireNonNull;

public class PrintStmt implements Stmt {
	private final Exp exp;

	public PrintStmt(Exp exp) {
		this.exp = requireNonNull(exp);
	}

	public Exp getExp() {
		return exp;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + exp + ")";
	}

}
