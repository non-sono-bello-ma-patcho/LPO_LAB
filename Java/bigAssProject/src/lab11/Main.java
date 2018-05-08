package lab11;

import static java.lang.System.err;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import lab11.parser.Parser;
import lab11.parser.ParserException;
import lab11.parser.StreamParser;
import lab11.parser.StreamTokenizer;
import lab11.parser.Tokenizer;
import lab11.parser.ast.Prog;
import lab11.visitors.evaluation.Eval;
import lab11.visitors.evaluation.EvaluatorException;
import lab11.visitors.typechecking.TypeCheck;
import lab11.visitors.typechecking.TypecheckerException;

public class Main {
	public static void main(String[] args) {
		try (Tokenizer tokenizer = new StreamTokenizer(
				args.length > 0 ? new FileReader(args[0]) : new InputStreamReader(System.in))) {
			Parser parser = new StreamParser(tokenizer);
			Prog prog = parser.parseProg();
			prog.accept(new TypeCheck());
			prog.accept(new Eval());
		} catch (ParserException e) {
			err.println("Syntax error: " + e.getMessage());
		} catch (IOException e) {
			err.println("I/O error: " + e.getMessage());
		} catch (TypecheckerException e) {
			err.println("Static error: " + e.getMessage());
		} catch (EvaluatorException e) {
			err.println("Dynamic error: " + e.getMessage());
		} catch (Throwable e) {
			err.println("Unexpected error.");
			e.printStackTrace();
		}
	}
}
