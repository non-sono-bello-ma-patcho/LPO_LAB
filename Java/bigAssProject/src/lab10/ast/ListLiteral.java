package lab10.ast;

import static java.util.Objects.requireNonNull;

public class ListLiteral implements Exp {
	private final ExpSeq exps;

	public ListLiteral(ExpSeq exps) {
		this.exps = requireNonNull(exps);
	}

	public ExpSeq getExps() {
		return exps;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + exps + ")";
	}

}
