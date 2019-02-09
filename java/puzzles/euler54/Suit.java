import java.util.HashMap;
import java.util.Map;

enum Suit {
	CLUB ('C'),
	DIAMOND('D'), 
	HEART('H'), 
	SPADE('S');

	private final char letter;
	private static final Map<Character, Suit> charMap = new HashMap<>();

	static {
		for (Suit s: Suit.values()){
			charMap.put(s.getChar(), s);
		}
	}

	private Suit (char c){
		this.letter = c;
	}

	public char getChar(){
		return this.letter;
	}

	public static Suit of(char c){
		return charMap.get(c);
	}

}