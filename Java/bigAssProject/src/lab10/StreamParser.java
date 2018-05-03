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

	private StmtSeq parseStmtSeq() throws ParserException { // recognize single instruction
		// to be completed
        Stmt stmt = parseStmt();
        if(tokenizer.tokenType() == STMT_SEP){
            consume(STMT_SEP);
            return new MoreStmt(stmt, parseStmtSeq()); // recursive call in order to manage multiple statement programs...
        }
		return new SingleStmt(stmt); // to be modified
	}

	private ExpSeq parseExpSeq() throws ParserException { // recognized expression sequence as 'int a = (10+221)'
		// to be completed
		Exp exp = parseExp();
		if(tokenizer.tokenType() == EXP_SEP){
			tryNext();
			return new MoreExp(exp, parseExpSeq()); // return evaluation of current token [, parsed token]?
		}
		return new SingleExp(exp); // to be modified
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
		consume(PRINT); // I could use tryNext();
		return new PrintStmt(parseExp()); // to be modified
	}

	private VarStmt parseVarStmt() throws ParserException {
		// to be completed
        consume(VAR); // consume 'var'
        Ident ID = parseIdent();
        consume(ASSIGN); // consume '='
        Exp e = parseExp();
		return new VarStmt(ID, e); // to be modified
	}

	private AssignStmt parseAssignStmt() throws ParserException {
		// to be completed
        Ident id = parseIdent();
        consume(ASSIGN); // tryNext();
        Exp e = parseExp();
		return new AssignStmt(id, e); // to be modified
	}

	private ForEachStmt parseForEachStmt() throws ParserException {
		// to be completed
        consume(FOR);
        Ident id = parseIdent();
        consume(IN);
        Exp e = parseExp();
        consume(OPEN_BLOCK);
        StmtSeq stmt = parseStmtSeq();
        consume(CLOSE_BLOCK);
		return new ForEachStmt(id, e, stmt); // to be modified
	}

	private Exp parseExp() throws ParserException {
		// to be completed
		Exp exp = parseAdd();
		while(tokenizer.tokenType() == PREFIX){
			tryNext();
			exp = new Prefix(exp, parseExp()); // we're expecting Exp after '::'...
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
        int n = tokenizer.intValue();
		consume(NUM);
		return new IntLiteral(n); // to be modified -- avrà senso?
	}

	private Ident parseIdent() throws ParserException { // evaluate symbol as indentation
		// to be completed
        String id = tokenizer.tokenString();
		consume(IDENT);
		return new SimpleIdent(id); // to be modified
	}

	private Sign parseMinus() throws ParserException { // evaluate symbol as a minus
		// to be completed
		consume(MINUS);
		return new Sign(parseAtom()); // to be modified
	}

	private ListLiteral parseList() throws ParserException { // evaluate tokens as a list
		// to be completed
		consume(OPEN_LIST);
		ExpSeq es = parseExpSeq();
		consume(CLOSE_LIST);
		return new ListLiteral(es); // to be modified
	}

	private Exp parseRoundPar() throws ParserException { // evaluate expression as round-parred expression
		// to be completed
		consume(OPEN_PAR);
		Exp exp = parseExp();
		consume(CLOSE_PAR);
		return exp;// to be modified
	}
}
