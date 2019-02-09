
import java.util.Map;
import java.util.HashMap;

enum Rank {
	TWO('2'), 
	THREE('3'),
	FOUR('4'),
	FIVE('5'),
	SIX('6'),
	SEVEN('7'), 
	EIGHT('8'),
	NINE('9'),
	TEN('T'),
	JACK('J'),
	QUEEN('Q'),
	KING('K'),
	ACE('A');

	private final Character letter;
	private static final Map<Character, Rank> charMap = new HashMap<>();

	static {
		for (Rank r: Rank.values()){
			charMap.put(r.getChar(), r);
		}
	}


	private Rank (char letter){
		this.letter = letter;
	}

	public static Rank of(char c){
		return charMap.get(c);
	}

	public char getChar(){
		return this.letter;
	}


}