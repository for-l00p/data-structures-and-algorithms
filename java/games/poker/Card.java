
package games.poker;


/**
 
The parent Card class should be rather empty, and allow for subclasses to specify the criteria of that card. Like ordinary playing cards, or a Uno deck, or Pokemon deck, and so on.

interface Suitable<T> {
    booleanIsSameSuit(T other);
}
interface Rankable<T> {
booleanIsConsecutive(T other);

NormalPlayingCard extends Card implements Suitable<Card>, Rankable<Card>, Comparable<FrenchPlayingCard>

}
 
*/


final class Card implements Comparable<Card> {

	private final Suit suit;
	private final Rank rank;


	public Card (Suit suit, Rank rank){
		this.suit = suit;
		this.rank = rank;
	}

	public Rank getRank(){
		return this.rank;
	}


	public Suit getSuit(){
		return this.suit;
	}


	public int compareTo(Card c){
		return rank.compareTo(c.rank);
	}


  @Override
  public String toString(){
      return "Card {"  + this.rank + " of "  + this.suit + "}";
  }


}