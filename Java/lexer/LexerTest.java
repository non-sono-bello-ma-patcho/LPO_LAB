package lab05_12_04;

public class LexerTest {
	public static void main(String[] args) {
	    Lexer lexer = new Lexer("/* your regular expression */");
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
