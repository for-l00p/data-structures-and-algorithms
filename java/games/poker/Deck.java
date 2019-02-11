
package games.poker;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**

The Deck consists of Cards, but other than basic handling of cards, it shouldn't know too much about the cards. And it shouldn't really know anything about a Player or a Game using the Deck.

For example, a deck should be able to dealCard(). But this should only remove a Card from the deck, and return it to whomever who might use it as they please. It shouldn't know anything about the player to whom the card might eventually go. (Better to rename it as drawCard()).

A Deck would need to know about the Card class. 

Would a Deck need to know about Rank/Suit classes? If it does, we would not be able to use the Deck Class for UnoCards, Pokermon Cards etc.


And should constructing cards be part of deck's respomsbility? 

It might be be better if the Deck constructor did not instantiate the Cards, but rather accept Cards (dependency injection):

public Deck(final List<Card> cards) {
    this.cardDeck = cards;
}

Who will construct the  initialize the cards for Deck then? The client!

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