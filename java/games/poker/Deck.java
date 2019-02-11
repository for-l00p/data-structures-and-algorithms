
package games.poker;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**

The Deck consists of Cards, but other than basic handling of cards, it shouldn't know too much about the cards. And it should definitely not know know anything about a Player or a Game using the Deck.

- Would a Deck need to know about Rank/Suit classes? If it does, we would not be able to use the Deck Class for UnoCards, Pokermon Cards etc. If it doesn't, who will handle the initialization the cards for the Deck then? 

- Should constructing cards be part of deck's respomsbility? 
It might be be better if the Deck constructor did not instantiate the Cards, but rather accept Cards.

public Deck(final List<Card> cards) {
    this.cardDeck = cards;
}



**/

final class Deck {

	private List<Card> cards;

	public Deck(){
		cards = new ArrayList<Card>();
		for (Suit s: Suit.values()){
			for (Rank r: Rank.values()){
				Card card = new Card(s, r);
				cards.add(card);
			}
		}
	}

	public Card drawCard() throws NoSuchElementException {
		if (this.cards.isEmpty()) {
       	 throw new NoSuchElementException();
    	}
		return this.cards.remove(0);
	};

	public void shuffle(){
		Collections.shuffle(cards);
	}	
}