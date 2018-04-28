package lab10_04_24;

import static lab10_04_24.TokenType.*;

import lab10_04_24.ast.*;

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

	private StmtSeq parseStmtSeq() throws ParserException { // recognize single instruction. testing phase
		// to be completed
        Stmt temp = parseStmt();
        while(tokenizer.tokenType() == STMT_SEP){
            tryNext();
            temp = (Stmt) new  MoreStmt(temp,parseStmtSeq());
        }
		return (StmtSeq) temp;
	}

	private ExpSeq parseExpSeq() throws ParserException { // recognized expression sequence as 'int a = (10+221)'
		// to be completed
        Exp exp = parseExp();
        while(tokenizer.tokenType() == EXP_SEP){
            tryNext();
            exp = (Exp) new MoreExp(exp, parseExpSeq()); // return evaluation of current token [, parsed token]?
        }
        return (ExpSeq) exp;
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

	private PrintStmt parsePrintStmt() throws ParserException { //testing phase
		// to be completed
        tryNext();
		return new PrintStmt(parseExp()); // to be modified
	}

	private VarStmt parseVarStmt() throws ParserException { //testing phase
		// to be completed
        tryNext();
		return new VarStmt(parseIdent(),parseExp()); // to be modified
	}

	private AssignStmt parseAssignStmt() throws ParserException { //testing phase
		// to be completed
        tryNext();
		return new AssignStmt(parseIdent(),parseExp()); // to be modified
	}

	private ForEachStmt parseForEachStmt() throws ParserException { //testing phase
		// to be completed
        tryNext();
		return new ForEachStmt(parseIdent(),parseExp(),parseStmtSeq()); // to be modified
	}

	private Exp parseExp() throws ParserException {
		// to be completed
        Exp exp = parseAdd();
        while(tokenizer.tokenType() == PREFIX){
            tryNext();
            exp = new Prefix(exp, parseAdd()); // returns evaluation of current token [ + parsed token]?
        }
        return exp; // to be modified -- final exp is an add expression;
	}

	private Exp parseAdd() throws ParserException {
		// to be completed
        Exp exp = parseMul();
        while(tokenizer.tokenType() == PLUS){
            tryNext();
            exp = new Add(exp, parseMul());
        }
        return exp; // to be modified -- final exp is a mul expression;
	}

	private Exp parseMul() throws ParserException {
		// to be completed
        Exp exp = parseAtom();
        while(tokenizer.tokenType() == TIMES){ //
            tryNext();
            exp = new Mul(exp, parseAtom());
        }
        return exp; // to be modified -- final exp is an atom expression;
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

	private IntLiteral parseNum() throws ParserException { // evaluate symbol as a number
		// to be completed
        tryNext();
        return new IntLiteral(tokenizer.intValue()); // to be modified -- avrà senso? --> da errore con expr 9+2
	}

	private Ident parseIdent() throws ParserException { // evaluate symbol as indentation
		// to be completed
        tryNext();
        return new SimpleIdent(tokenizer.tokenString());
	}

	private Sign parseMinus() throws ParserException { // evaluate symbol as a minus
        tryNext();
        return new Sign(parseAtom());
	}

	private ListLiteral parseList() throws ParserException {// evaluate tokens as a list
        tryNext();
        return new ListLiteral(parseExpSeq());
	}

	private Exp parseRoundPar() throws ParserException { // evaluate expression as round-parred expression
        tryNext();
        return parseExp();
	}

}
