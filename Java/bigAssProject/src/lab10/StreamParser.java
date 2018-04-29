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

	private StmtSeq parseStmtSeq() throws ParserException { //parse a stmts sequence -->  Stmt (';' StmtSeq)?
		Stmt stmt= parseStmt(); // there will be at least one stmt Take a look to the regular expression!
		if(tokenizer.tokenType()==STMT_SEP)  // if the next token is ';'
		{
			tryNext(); // goes to the next token
			StmtSeq stmtseq=new MoreStmt(stmt,parseStmtSeq()); // will be a sequences of multiple stmts
			return stmtseq;
		}
		return new SingleStmt(stmt); // there is only one stmt
	}

	private ExpSeq parseExpSeq() throws ParserException { // parse exprseq --> Exp (',' ExpSeq)?
		Exp exp= parseExp(); //// there will be at least one  expression! Take a look to the regular expression!
		if(tokenizer.tokenType()==EXP_SEP) // if the next token is ','
		{
			tryNext(); // goes to the next token
			ExpSeq expseq=new MoreExp(exp,parseExpSeq()); //will be a sequences of multiple expressions
			return expseq;
		}
		return new SingleExp(exp); // there is only one expression
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

	private PrintStmt parsePrintStmt() throws ParserException { // parse print stmt --> 'print' Exp
		consume(PRINT); //check if the token in 'PRINT' , if it is, trynext() will be called, otherwise throws ParserEexception
		Exp exp= parseExp(); // creates a new expression
		return new PrintStmt(exp);
	}

	private VarStmt parseVarStmt() throws ParserException { // parse the assignment stmt --> 'var'? ID '=' Exp
		consume(VAR); //check if the token in 'VAR' , if it is, trynext() will be called, otherwise throws ParserEexception
		Ident id=parseIdent(); // creates a new id
		consume(ASSIGN); //check if the token in '=' , if it is, trynext() will be called, otherwise throws ParserEexception
		Exp exp=parseExp();// creates a new expression
		return new VarStmt(id,exp);
	}


	private AssignStmt parseAssignStmt() throws ParserException { // parse the assignment stmt --> 'var'? ID '=' Exp
		Ident id=parseIdent(); // creates a new id object
		consume(ASSIGN); //check if the token in '=' , if it is, trynext() will be called, otherwise throws ParserEexception
		Exp exp=parseExp();//creates a new expression
		return new AssignStmt(id,exp);
	}

	private ForEachStmt parseForEachStmt() throws ParserException { //parse FOR stmt --> FOR ID : EXPR { EXPRSEQ }
		consume(FOR); //check if the token in "FOR", if it is, trynext() will be called, otherwise throws ParserEexception
		Ident id=parseIdent(); // creates a new id! (trynext() or an exception)
		consume(IN); //check if the token in ':' , if it is, trynext() will be called, otherwise throws ParserEexception
		Exp exp=parseExp();// creates a new expr!
		consume(OPEN_BLOCK); //check if the token in '{' , if it is, trynext() will be called, otherwise throws ParserEexception
		StmtSeq stmt=parseStmtSeq(); //cretes a new stmtseq!
		consume(CLOSE_BLOCK); // check if the token in '}' , if it is, trynext() will be called, otherwise throws ParserEexception
		return new ForEachStmt(id,exp,stmt);
	}

	private Exp parseExp() throws ParserException { //parse the expression --> add ( '::' expr)?
		Exp exp= parseAdd(); // there will be at least one Add expression! Take a look to the regular expression!
		if(tokenizer.tokenType()==PREFIX) //if there is '::' after te add expression
		{
			tryNext(); //goes to the next token
			exp=new Prefix(exp,parseExp());
		}
		return exp;
	}

	private Exp parseAdd() throws ParserException { // parse add --> Mul ( '+' MUl)*
		Exp exp= parseMul(); // there will be at least one Mul expression! Take a look to the regular expression!
		while(tokenizer.tokenType()==PLUS) //while the token is "*" keep looping
		{
			tryNext(); // goes to the next token
			exp=new Add(exp,parseMul()); // exp will be Mul + Mul (and so on...)
		}
		return exp;
	}

	private Exp parseMul() throws ParserException { // parse mul --> atomic expression  ( '*' atomic expression )*
		Exp exp= parseAtom(); //there will be at least one atomic expression! take a look to the regular expression!
		while(tokenizer.tokenType()==TIMES) //while the token is "*" keep looping
		{
			tryNext(); //goes to the next token
			exp=new Mul(exp,parseMul()); // exp  will be atom * atom ( and so on..)
		}
		return exp;
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
		int n=tokenizer.intValue(); // if  the token is not NUM throws a ParserExeption otherwise n = tokenizer.intValue
		IntLiteral intlit=new IntLiteral(n);
		tryNext(); // obviously goes to the next token
		return intlit;
	}

	private Ident parseIdent() throws ParserException { //parse easly the ids (name of variables)
		String s= tokenizer.tokenString(); //s = the string rappresenting the id (if s = null an exception will be raised)
		Ident ident= new SimpleIdent(s); //putting it in a new id object
		tryNext(); //obviously goes to the next token
		return ident;
	}

	private Sign parseMinus() throws ParserException { //parse  "-" atomic expression --> -NUM | -[ exprseq] | -(expr) | -ID
		consume(MINUS); //if there is a "-" trynext() otherwise throws a syntax exception (ParserExeption)
		Exp atom=parseAtom(); //parse the atomic expression
		return new Sign(atom);
	}

	private ListLiteral parseList() throws ParserException { //parse a list --> [expr,expr,expr] --> "[" ExprSeq "]"
		consume(OPEN_LIST); //if there is a "[" trynext() otherwise throws a syntax exception (ParserExeption)
		ExpSeq list=parseExpSeq(); //parse the exprSeq that is between the square pars
		consume(CLOSE_LIST); //if there is a "]" trynext() otherwise throws a syntax exception (ParserExeption)
		return new ListLiteral(list);
	}

	private Exp parseRoundPar() throws ParserException { //parse a round par --> "(" expr ")"
		consume(OPEN_PAR); //if there is a "(" trynext() otherwise throws a syntax exception (ParserExeption)
		Exp exp=parseExp(); // parse the expr that is between the pars
		consume(CLOSE_PAR);//if there is a ")" trynext() otherwise throws a syntax exception (ParserExeption)
		return exp;
	}
}
