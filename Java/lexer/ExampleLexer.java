package lab05_12_04;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExampleLexer {

	private static int getGroup(Matcher matcher) {
		for (int group = 1; group <= matcher.groupCount(); group++)
			if (matcher.group(group) != null)
				return group;
		throw new AssertionError(); // should never fail
	}

	public static void main(String[] args) {
		if (args.length == 0)
			throw new IllegalArgumentException();
		// ([a-zA-Z]+)|([0-9]+)|(\\s+)
		Matcher matcher = Pattern.compile("([a-zA-Z]+([a-zA-Z]|[0-9]|[_])*").matcher(args[0]);
		System.out.println("Input: " + args[0]);
		while (matcher.lookingAt()) {
			System.out.print("Lexeme '" + matcher.group() + "'");
			System.out.println(" group " + ExampleLexer.getGroup(matcher));
			matcher.region(matcher.end(), matcher.regionEnd());
		}
		/*
		 * attenzione: matcher.hitEnd() restituisce true se il matcher arriva in fondo
		 * all'input anche se l'ultimo match non ha avuto successo, quindi funziona solo
		 * per espressioni regolari "semplici"
		 */
		if (matcher.regionStart() == matcher.regionEnd())
			System.out.println("All lexems succesfully matched");
		else {
			System.err.print("Unmatched lexem ");
			matcher.usePattern(Pattern.compile(".*"));
			matcher.lookingAt();
			System.err.println(matcher.group());
		}
	}

}
