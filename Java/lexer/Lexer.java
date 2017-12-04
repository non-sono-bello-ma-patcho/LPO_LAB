package lab05_12_04;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
	private final Matcher matcher; // il matcher usato dal lexer
	private int lexemeType; // il gruppo dell'ultimo lessema riconosciuto; 0 indica che non e` stato
							// riconosciuto alcun lessema


	public Lexer(CharSequence line, String regExp) {
	}

	/*
	 * inizializza il Lexer con l'input vuoto
	 */
	public Lexer(String regExp) {
	}

	/*
	 * restituisce il gruppo dell'ultima lessema riconosciuto solleva un'eccezione
	 * di tipo IllegalStateException se non e` stato riconosciuto alcun lessema
	 */
	public int lexemeType() {
	}

	/*
	 * assegna al Lexer un nuovo input e dimentica il gruppo dell'ultimo lessema
	 * riconosciuto (usare il metodo reset della classe Matcher)
	 */
	public void reset(CharSequence line) {
	}

	/*
	 * restituisce true se e solo se la regione del matcher non e` vuota, ossia, il
	 * lexer non ha ancora consumato tutto l'input
	 */
	public boolean hasNext() {
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
	}

}
