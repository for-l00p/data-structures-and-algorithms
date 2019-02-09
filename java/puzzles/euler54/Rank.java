import java.util.Map;
import java.util.HashMap;

enum Rank {
	TWO(2, '2'), 
	THREE(3, '3'),
	FOUR(4,'4'),
	FIVE(5,'5'),
	SIX(6,'6'),
	SEVEN(7,'7'), 
	EIGHT(8, '8'),
	NINE(9, '9'),
	TEN(10, 'T'),
	JACK(11, 'J'),
	QUEEN(12, 'Q'),
	KING(13, 'K'),
	ACE(14, 'A');

	private final int value;
	private final Character letter;
	private static final Map<Character, Rank> charMap = new HashMap<>();

	static {
		for (Rank r: Rank.values()){
			charMap.put(r.getChar(), r);
		}
	}

	private Rank (int value){
		this.value = value;
		this.letter = null;
	}


	private Rank (int value, char letter){
		this.value = value;
		this.letter = letter;
	}

	public static Rank of(char c){
		return charMap.get(c);
	}

	public char getChar(){
		return this.letter;
	}

	public int getValue(){
		return this.value;
	}


}