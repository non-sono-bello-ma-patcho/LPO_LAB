package lab10.ast;

import static java.util.Objects.requireNonNull;

public class ProgClass implements Prog {
	private final StmtSeq stmtSeq;

	public ProgClass(StmtSeq stmtSeq) {
		this.stmtSeq = requireNonNull(stmtSeq);
	}

	public StmtSeq getStmtSeq() {
		return stmtSeq;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + stmtSeq + ")";
	}

}
