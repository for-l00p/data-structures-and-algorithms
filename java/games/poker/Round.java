
package games.poker;

import java.util.List;
import java.util.ArrayList;

final class Round {

	List<Player> players;
	int smallBlindIndex;
	int minimumBet;

	int currentTurnIndex;
	Deck currentDeck;
	List<Card> openCards;
	int pot;
	int currentMaxBet;
		
	public Round (List<Player> players, int smallBlindIndex, int minimumBet){
		//Parameters for a new round.
		this.players = players;
		this.minimumBet = minimumBet;
		this.smallBlindIndex = smallBlindIndex;

		//Setting state to start of a round.
		this.pot = 0;
		this.openCards = new ArrayList<Card>();
		this.currentDeck = new Deck();
		this.currentDeck.shuffle();
		this.currentTurnIndex = this.smallBlindIndex;
		this.currentMaxBet = this.minimumBet;
	}

	//One rule-of-thumb: ask yourself "does it make sense to call this method, even if no Obj has been constructed yet?" If so, it should definitely be static.
	public void run(){

		GameEngine.setPlayerStatus(this); //clears betting history, clears hole cards, sets round status to all Playing, not had turn etc.
		GameEngine.dealCards(this, 2);
		GameEngine.doBlindBetting(this);
		GameEngine.doBettingRound(this);
		System.out.println("Opening Flop now");
		GameEngine.openCards(this, 3);
		GameEngine.clearBettingHistory(this);
		GameEngine.doBettingRound(this);
		System.out.println("Opening Turn Card");
		GameEngine.openCards(this, 1);
		GameEngine.clearBettingHistory(this);
		GameEngine.doBettingRound(this);
		System.out.println("Opening River Card");
		GameEngine.openCards(this, 1);
		GameEngine.clearBettingHistory(this);
		GameEngine.doBettingRound(this);
		GameEngine.showOfHands(this);
		GameEngine.clearBettingHistory(this);
	} 
}