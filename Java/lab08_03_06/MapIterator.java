package lab08_03_06;

import java.util.Iterator;
import java.util.function.Function;
import static java.util.Objects.requireNonNull;

class MapIterator<A, B> implements Iterator<B> {

	private final Function<A, B> fun; // funzione da A a B: fun.apply(x) corrisponde a fun(x)
	private final Iterator<A> it;

	public MapIterator(Function<A, B> fun, Iterator<A> it) {
		this.it = it;
		this.fun = fun;
	}

	@Override
	public boolean hasNext() {

		return it.hasNext();
	}

	@Override
	public B next() {

		if(!hasNext()) throw new RuntimeException();
		return fun.apply(this.it.next());

}

}
