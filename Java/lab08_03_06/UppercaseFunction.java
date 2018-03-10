package lab08_03_06;

import java.util.function.Function;

public class UppercaseFunction implements Function<String, String> {

	@Override
	public String apply(String t) {
		return t.toUpperCase();
	}

}
