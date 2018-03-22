package labo05_12_04;

public class LexerTest {
	public static void main(String[] args) {
	    Lexer lexer = new Lexer("([a-zA-Z][a-zA-Z0-9_]*)|(0(?![0-9])|[1-9][0-9]*)|(\\s+)|(\"+([^\\\\\"]|[\\\\(?=[\\\\\"])])*\"+)");
		int line_num = 1;
		for (String line : args) {
			System.out.println("line " + line_num++);
			lexer.reset(line);
			int lexer_num = 1;
			while (lexer.hasNext()) {
				String lexeme = lexer.next();
				System.out.println("lexeme " + lexer_num++ + ":" + "'" + lexeme + "'" + " type: " + lexer.lexemeType());
			}
		}
	}
}
