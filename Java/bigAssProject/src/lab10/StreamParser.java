package lab10;

import static lab10.TokenType.*;

import lab10.ast.*;

/*
Prog ::= StmtSeq 'EOF'
 StmtSeq ::= Stmt (';' StmtSeq)?
 Stmt ::= 'var'? ID '=' Exp | 'print' Exp |  'for' ID ':' Exp '{' StmtSeq '}'
 ExpSeq ::= Exp (',' ExpSeq)?
 Exp ::= Add ('::' Exp)?
 Add ::= Mul ('+' Mul)*
 Mul::= Atom ('*' Atom)*
 Atom ::= '-' Atom | '[' ExpSeq ']' | NUM | ID | '(' Exp ')'
*/

public class StreamParser implements Parser {

	private final Tokenizer tokenizer;

	private void tryNext() throws ParserException {
		try {
			tokenizer.next();
		} catch (TokenizerException e) {
			throw new ParserException(e);
		}
	}

	private void match(TokenType expected) throws ParserException {
		final TokenType found = tokenizer.tokenType();
		if (found != expected)
			throw new ParserException(
					"Expecting " + expected + ", found " + found + "('" + tokenizer.tokenString() + "')");
	}

	private void consume(TokenType expected) throws ParserException {
		match(expected);
		tryNext();
	}

	private void unexpectedTokenError() throws ParserException {
		throw new ParserException("Unexpected token " + tokenizer.tokenType() + "('" + tokenizer.tokenString() + "')");
	}

	public StreamParser(Tokenizer tokenizer) {
		this.tokenizer = tokenizer;
	}

	@Override
	public Prog parseProg() throws ParserException {
		tryNext(); // one look-ahead symbol
		Prog prog = new ProgClass(parseStmtSeq());
		match(EOF);
		return prog;
	}

	private StmtSeq parseStmtSeq() throws ParserException {
		// to be completed
		return null; // to be modified
	}

	private ExpSeq parseExpSeq() throws ParserException {
		// to be completed
		Exp exp = parseExp();
		while(tokenizer.tokenType() == EXP_SEP){
			tryNext();
			exp = (Exp) new MoreExp(exp, parseExpSeq());
		}
		return (ExpSeq) exp; // to be modified
	}

	private Stmt parseStmt() throws ParserException {
		switch (tokenizer.tokenType()) {
		default:
			unexpectedTokenError();
		case PRINT:
			return parsePrintStmt();
		case VAR:
			return parseVarStmt();
		case IDENT:
			return parseAssignStmt();
		case FOR:
			return parseForEachStmt();
		}
	}

	private PrintStmt parsePrintStmt() throws ParserException {
		// to be completed
		return null; // to be modified
	}

	private VarStmt parseVarStmt() throws ParserException {
		// to be completed
		return null; // to be modified
	}

	private AssignStmt parseAssignStmt() throws ParserException {
		// to be completed
		return null; // to be modified
	}

	private ForEachStmt parseForEachStmt() throws ParserException {
		// to be completed
		return null; // to be modified
	}

	private Exp parseExp() throws ParserException {
		// to be completed
		Exp exp = parseAdd();
		while(tokenizer.tokenType() == PREFIX){
			tryNext();
			exp = new Prefix(exp, parseAdd());
		}
		return exp; // to be modified
	}

	private Exp parseAdd() throws ParserException {
		// to be completed
		Exp exp = parseMul();
		while(tokenizer.tokenType() == PLUS){
			tryNext();
			exp = new Add(exp, parseMul());
		}
		return exp; // to be modified
	}

	private Exp parseMul() throws ParserException {
		// to be completed
		Exp exp = parseAtom();
		while(tokenizer.tokenType() == TIMES){ //
			tryNext();
			exp = new Mul(exp, parseAtom());
		}
		return exp; // to be modified
	}

	private Exp parseAtom() throws ParserException {
		switch (tokenizer.tokenType()) {
		default:
			unexpectedTokenError();
		case NUM:
			return parseNum();
		case IDENT:
			return parseIdent();
		case MINUS:
			return parseMinus();
		case OPEN_LIST:
			return parseList();
		case OPEN_PAR:
			return parseRoundPar();
		}
	}

	private IntLiteral parseNum() throws ParserException {
		// to be completed
		return new IntLiteral(tokenizer.intValue()); // to be modified -- avrà senso?
	}

	private Ident parseIdent() throws ParserException {
		// to be completed
		return new SimpleIdent(tokenizer.tokenString()); // to be modified
	}

	private Sign parseMinus() throws ParserException {
		// to be completed
		return new Sign(tokenizer.); // to be modified
	}

	private ListLiteral parseList() throws ParserException {
		// to be completed
		return null; // to be modified
	}

	private Exp parseRoundPar() throws ParserException {
		// to be completed
		return null; // to be modified
	}

}
