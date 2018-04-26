package lab10;

import static java.lang.System.err;
import static java.lang.System.out;

import java.io.FileReader;
import java.io.InputStreamReader;

import lab10.ast.Prog;

public class Main {
	public static void main(String[] args) {
		try (Tokenizer tokenizer = new StreamTokenizer(
				args.length > 0 ? new FileReader(args[0]) : new InputStreamReader(System.in))) {
			Parser parser = new StreamParser(tokenizer);
			Prog prog = parser.parseProg();
			out.println("Program correctly parsed: " + prog);
		} catch (ParserException e) {
			err.println(e.getMessage());
		} catch (Throwable e) {
			err.println("Unexpected error. " + e.getMessage());
		}
	}
}
