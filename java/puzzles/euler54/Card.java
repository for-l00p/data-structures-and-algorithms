

import java.lang.reflect.Field;

class Card implements Comparable<Card> {

	private final Suit suit;
	private final Rank rank;


	public Card (Rank rank, Suit suit ){
		this.suit = suit;
		this.rank = rank;
	}

	public Card (String s){
		this(Rank.of(s.charAt(0)), Suit.of(s.charAt(1)));
	}

	public Rank getRank(){
		return this.rank;
	}

	public Suit getSuit(){
		return this.suit;
	}

	public int getValue(){
		return this.rank.getValue();
	}

	public int compareTo(Card c){
		return rank.compareTo(c.rank);
	}

	@Override
	public String toString() {
  		StringBuilder result = new StringBuilder();
  		String newLine = System.getProperty("line.separator");

  		result.append( this.getClass().getName() );
  		result.append( " Object {" );
  		result.append(newLine);

  		//determine fields declared in this class only (no fields of superclass)
  		Field[] fields = this.getClass().getDeclaredFields();

  			//print field names paired with their values
  		for (Field field: fields) {
    		result.append("  ");
    		try {
      		result.append( field.getName() );
      		result.append(": ");
      	//requires access to private field:
      		result.append( field.get(this) );
    		} catch ( IllegalAccessException ex ) {
      		System.out.println(ex);
    		}
    		result.append(newLine);
  		}
  		result.append("}");
  		return result.toString();
	}

}