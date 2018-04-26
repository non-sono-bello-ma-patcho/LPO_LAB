package lab10;

import lab10.ast.Prog;

public interface Parser {

	Prog parseProg() throws ParserException;

}