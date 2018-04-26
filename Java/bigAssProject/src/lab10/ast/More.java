package lab10.ast;

import static java.util.Objects.requireNonNull;

public class More<FT,RT> {
	protected final FT first;
	protected final RT rest;

	public More(FT first, RT rest) {
		this.first = requireNonNull(first);
		this.rest = requireNonNull(rest);
	}

	public FT getFirst() {
		return first;
	}

	public RT getRest() {
		return rest;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + first + "," + rest + ")";
	}
}
