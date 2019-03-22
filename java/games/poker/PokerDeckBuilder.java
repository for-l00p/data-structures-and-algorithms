package games.poker;


import java.util.List;
import java.util.ArrayList;


class PokerDeckBuilder implements DeckBuilder {
	
	
	public Deck buildDeck(){
		List<Card> cards = new ArrayList<>();
		for (Suit s: Suit.values()){
			for (Rank r: Rank.values()){
				Card card = new Card(s, r);
				cards.add(card);
			}
		}
		return (new Deck(cards));


	}


}