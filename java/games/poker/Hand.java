
package games.poker;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;


/**

Hand is an abstraction of a set of 7 cards, such that they can be compared (Here the comparison is based on Poker hand hierarchy, but we can conceivably change this). 

The Hand Class maps the set-of-7 cards to a {best-of-5-hand, HandRank}, where HandRank is the property of best-of-5-hand such as Flush, Straight etc. The best-of-5-hand are sorted based on Card-Rank (highest first). To compare, the HandRank is first compared. If two sets-of-7 have the same HandRank, then their best-of-5-hands are compared on Card.Rank, starting from the left. 

*/
final class Hand implements Comparable<Hand> {

	private final HandRank rank;
	private final List<Card> bestOfFive = new ArrayList<>();

	public Hand(List<Card> inputCards){
		this.rank = evaluateRank(inputCards);
		System.out.println("Evaluated Hand Rank: " + this.rank);
		System.out.println("Best 5 Cards: " + this.toString());
	}

	public int compareTo(Hand h2){
		if (h2 == null) {return 1;}
		if (this.rank.compareTo(h2.rank) != 0){
			return this.rank.compareTo(h2.rank);
		} else {
			return compareBestOfFive(this.bestOfFive, h2.bestOfFive);
		}
	}

	private int compareBestOfFive(List<Card> h1, List<Card> h2){

		ListIterator<Card> it = h2.listIterator();
		int cmp = 0;
		for (Card c: h1){
			cmp = c.compareTo(it.next());
			if( cmp != 0){
				return cmp;
			}
		}
		return cmp;
	}

	// Early exit pattern: https://www.quora.com/What-s-the-coolest-coding-pattern-you-ve-seen/answer/James-Liu-20?ch=10&share=2f4c64d2&srid=3HW0

	private HandRank evaluateRank (List<Card> cards){
		if (scanForStraightFlush(cards)){
			return HandRank.STRAIGHT_FLUSH;
		} 

		if (scanForQuads(cards)){
			return HandRank.FOUR_OF_A_KIND;
		} 
		if (scanForFullHouse(cards)){
			return HandRank.FULL_HOUSE;
		} 

		if (scanForFlush(cards)){
			return HandRank.FLUSH;
		} 

		if (scanForStraight(cards)){
			return HandRank.STRAIGHT;
		} 

		if (scanForTrips(cards)){
			return HandRank.THREE_OF_A_KIND;
		} 

		if (scanForTwoPair(cards)){
			return HandRank.TWO_PAIR;
		} 

		if (scanForPair(cards)){
			return HandRank.PAIR;
		} 
		
		scanForHighCard(cards);
		return HandRank.HIGH_CARD;
		
	}


	private Map<Suit, List<Card>> getSuitCardMap(List<Card> cards){
		Map<Suit, List<Card>> suitCardMap = new HashMap<>();
		for (Card card: cards){
				//System.out.println("Inspecting");
			List<Card> cardsOfSameSuit;
			if(suitCardMap.containsKey(card.getSuit())){
				cardsOfSameSuit = suitCardMap.get(card.getSuit());
				cardsOfSameSuit.add(card);
			} else {
				cardsOfSameSuit = new ArrayList<>();
				cardsOfSameSuit.add(card);
				suitCardMap.put(card.getSuit(), cardsOfSameSuit);
			}	
		}
		return suitCardMap;
	}


	private Map<Rank, List<Card>> getRankCardMap(List<Card>  cards){
		Map<Rank, List<Card>> rankCardMap = new HashMap<>();
		for (Card card: cards){
			List<Card> cardsOfSameRank;
			if(rankCardMap.containsKey(card.getRank())){
				cardsOfSameRank = rankCardMap.get(card.getRank());
				cardsOfSameRank.add(card);
			} else {
				cardsOfSameRank = new ArrayList<>();
				cardsOfSameRank.add(card);
				rankCardMap.put(card.getRank(), cardsOfSameRank);
			}	
		}
		return rankCardMap;
	}

	private Map<Rank, Integer> getRankFrequency(List<Card> cards){
		Map<Rank, Integer> freqMap = new HashMap<>();

		for (Card c: cards){
			freqMap.compute(c.getRank(), (key, value) -> {
				if (value == null){
					return 1;
				} else {
					return value + 1;
				}
			});
		}
		return freqMap;
	}

	private List<Card> extractHighestStraight(List<Card> cards){
		List<Card> result = new ArrayList<>();
		List<Card> tempList = new ArrayList<>(cards);
		Collections.sort(tempList);
		Collections.reverse(tempList);
		ListIterator<Card> listIterator = tempList.listIterator();
		
		Card currentCard = listIterator.next();
		result.add(currentCard);
		Card nextCard;
		while (listIterator.hasNext()){
			nextCard = listIterator.next();
			if (nextCard.getRank().ordinal() == currentCard.getRank().ordinal() - 1 ){
				result.add(nextCard);
				if (result.size() == 5){
					return result;
				}
					//Check for Wheel Straight
				if (result.size() == 4 && nextCard.getRank().ordinal() == 0){
					if (tempList.get(0).getRank().ordinal() == 12){
						result.add(tempList.get(0));
						return result;
					}
				}

			} else if (nextCard.getRank() == currentCard.getRank()){
					// do nothing 	
			} else {
				result.clear();
				result.add(nextCard);
			}
			currentCard = nextCard;
		}
		return null;
	}

	private boolean scanForStraightFlush (List<Card> cards){
		Map<Suit, List<Card>> suitFreqMap = getSuitCardMap(cards);
			//TreeSet<Card> bestOfFiveFlush = new TreeSet<>()
		for (List<Card> cardsOfSameSuit: suitFreqMap.values()){
			if (cardsOfSameSuit.size() >= 5){
				List<Card> straightFlushCards = extractHighestStraight(cardsOfSameSuit);
				if(straightFlushCards != null){
					this.bestOfFive.addAll(straightFlushCards);
					//this.rank = HandRank.STRAIGHT_FLUSH;
					return true;
				} 		
			}
		}
		return false;
	}



	private boolean scanForQuads (List<Card> cards) {
		this.bestOfFive.clear();
		Map<Rank, List<Card>> rankCardMap = getRankCardMap(cards);
		for (List<Card> cardsOfSameRank: rankCardMap.values()){
			if(cardsOfSameRank.size() == 4){
				this.bestOfFive.addAll(cardsOfSameRank);
				cards.removeAll(cardsOfSameRank);
				Collections.sort(cards);
				Collections.reverse(cards);
				Card kicker = cards.get(0);
				this.bestOfFive.add(kicker);
					//this.rank = HandRank.FOUR_OF_A_KIND;
				return true;
			}	
		}
		return false;
	}


	private boolean scanForFullHouse (List<Card> cards){
		this.bestOfFive.clear();
		List<Card> temp = new ArrayList<>(cards);
		Collections.sort(temp);
		Collections.reverse(temp);  //all the cards in descending order.
		Map<Rank, Integer> rankFreqMap = getRankFrequency(temp); // all the frequency of the card by ranks
		for (Card card: temp){
			if (rankFreqMap.get(card.getRank()) == 3 && this.bestOfFive.size() < 5){
				System.out.println("Inspecting: " + card.toString());
				this.bestOfFive.add(card);
			}
		}

		if (this.bestOfFive.size() == 5){
			return true;
		} 

		for (Card card: temp){
			if (rankFreqMap.get(card.getRank()) == 2 && this.bestOfFive.size() <= 5){
				this.bestOfFive.add(card);
			}	
		}
		if (this.bestOfFive.size() == 5){
			return true;
		} 
		return false;
	}

	private boolean scanForFlush (List<Card> cards){
		this.bestOfFive.clear();
		Map<Suit, List<Card>> suitFreqMap = getSuitCardMap(cards);
		for (List<Card> cardsOfSameSuit: suitFreqMap.values()){
			if (cardsOfSameSuit.size() >= 5){
				Iterator<Card> cardIterator = cardsOfSameSuit.listIterator();
				for (int i = 1; i <= 5; i ++) {
					this.bestOfFive.add(cardIterator.next());
				}
					//this.rank = HandRank.FLUSH;
				return true;
			}	
		}
		return false;
	}

	private boolean scanForStraight (List<Card> cards){
			this.bestOfFive.clear();
			List<Card> temp = extractHighestStraight(cards);
			if (temp == null){
				return false;
			} else {
				this.bestOfFive.addAll(temp);

			}
			return true;

	}

		//guaranteed to have only one trips: otherwise fullHouse should catch it.
	private boolean scanForTrips (List<Card> cards){
			this.bestOfFive.clear();
			List<Card> temp = new ArrayList<>(cards);
			Collections.sort(temp);
			Collections.reverse(temp);
			Map<Rank, Integer> rankFreqMap = getRankFrequency(temp);   // all the frequency of the card by ranks
			for (Card card: temp){
				if (rankFreqMap.get(card.getRank()) == 3){
					this.bestOfFive.add(card);
				}
			}
			if(this.bestOfFive.size() == 3){
				temp.removeAll(this.bestOfFive);
				this.bestOfFive.add(temp.remove(0));
				this.bestOfFive.add(temp.remove(0));
				return true;
			}
			return false;
			
	}

	private boolean scanForTwoPair (List<Card> cards){
			this.bestOfFive.clear();
			List<Card> temp = new ArrayList<>(cards);
			Collections.sort(temp);
			Collections.reverse(temp);  //all the cards in descending order.
			Map<Rank, Integer> rankFreqMap = getRankFrequency(temp); // all the frequency of the card by ranks
			for (Card card: temp){
				//System.out.println("Inspecting: " + card.toString());
				if (rankFreqMap.get(card.getRank()) == 2 && this.bestOfFive.size() < 4){
					this.bestOfFive.add(card);
				}
			}

			if(this.bestOfFive.size() == 4){
				temp.removeAll(this.bestOfFive);
				this.bestOfFive.add(temp.remove(0));
				return true;
			} else {
				return false;
			}
			
	}

	private boolean scanForPair(List<Card> cards){
			this.bestOfFive.clear();
			List<Card> temp = new ArrayList<>(cards);
			Collections.sort(temp);
			Collections.reverse(temp);  //all the cards in descending order.
			Map<Rank, Integer> rankFreqMap = getRankFrequency(temp); // all the frequency of the card by ranks
			for (Card card: temp){
				if (rankFreqMap.get(card.getRank()) == 2 && this.bestOfFive.size() <=5){
					this.bestOfFive.add(card);
				}

			}
			if (this.bestOfFive.size() == 2){
				temp.removeAll(this.bestOfFive);
				this.bestOfFive.add(temp.remove(0));
				this.bestOfFive.add(temp.remove(0));
				this.bestOfFive.add(temp.remove(0));
				return true;
			}
			return false;
	}

	private void scanForHighCard (List<Card> cards){
			List<Card> temp = new ArrayList<>(cards);
			Collections.sort(temp);
			Collections.reverse(temp); 
			ListIterator<Card> tempIterator = temp.listIterator();
			for (int i = 0; i < 5; i++){
				this.bestOfFive.add(tempIterator.next());
			}

	}

	@Override
	public String toString() {
  		StringBuilder result = new StringBuilder();
  		String newLine = System.getProperty("line.separator");

  		result.append( this.getClass().getName() );
  		result.append( " Object {" );
  		result.append(newLine);

  			//determine fields declared in this class only (no fields of superclass)
  			//print field names paired with their values
  		for ( Card card : this.bestOfFive ) {
    		result.append(card);
    		result.append(newLine);
  		}
  		result.append("}");
  		return result.toString();
	}

}