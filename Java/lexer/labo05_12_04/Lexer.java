package labo05_12_04;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
	private final Matcher matcher; // il matcher usato dal lexer
	private int lexemeType; // il gruppo dell'ultimo lessema riconosciuto; 0 indica che non e` stato
							// riconosciuto alcun lessema

	private static int getGroup(Matcher matcher) {
		for (int group = 1; group <= matcher.groupCount(); group++)
			if (matcher.group(group) != null)
				return group;
		throw new AssertionError(); // should never fail
	}

	public Lexer(CharSequence line, String regExp) {
		matcher = Pattern.compile(regExp).matcher(line);
	}

	/*
	 * inizializza il Lexer con l'input vuoto
	 */
	public Lexer(String regExp) {
		matcher = Pattern.compile(regExp).matcher("");
	}

	/*
	 * restituisce il gruppo dell'ultima lessema riconosciuto solleva un'eccezione
	 * di tipo IllegalStateException se non e` stato riconosciuto alcun lessema
	 */
	public int lexemeType() {
		if(this.lexemeType == 0) throw new IllegalStateException();
		return this.lexemeType;
	}

	/*
	 * assegna al Lexer un nuovo input e dimentica il gruppo dell'ultimo lessema
	 * riconosciuto (usare il metodo reset della classe Matcher)
	 */
	public void reset(CharSequence line) {
		this.matcher.reset(line);
		this.lexemeType = 0;
	}

	/*
	 * restituisce true se e solo se la regione del matcher non e` vuota, ossia, il
	 * lexer non ha ancora consumato tutto l'input
	 */
	public boolean hasNext() {
		return this.matcher.lookingAt();
	}

	/*
	 * restituisce il prossimo lessema e corrispondentemente fa avanzare la regione
	 * dell'input
	 * 
	 * solleva un'eccezione di tipo IllegalStateException se l'input e` stato gia`
	 * tutto consumato
	 * 
	 * solleva un'eccezione di tipo RuntimeException se nessun lessema viene
	 * riconosciuto
	 */
	public String next() {
		if(!this.hasNext()) throw new IllegalStateException();
		String lexeme = matcher.group(); // creazione del lessema;
		this.lexemeType = getGroup(this.matcher);
		matcher.region(matcher.end(), matcher.regionEnd()); // avanzamento della regione
		if(this.lexemeType() == 0){
			throw new RuntimeException();
		}
		return lexeme;
	}

}
