
package games.poker;

import java.util.List;

class Game {

		List<Player> players;
		int smallBlindIndex;
		int currentTurnIndex;
		Deck currentDeck;
		List<Card> openCards;
		int minimumBet;
		int pot;
		int currentMaxBet;
		
		public Game(List<Player> players, int smallBlindIndex, int minimumBet){

			//get the game ready for card dealing
			this.players = players;
			this.minimumBet = minimumBet;
			this.smallBlindIndex = smallBlindIndex;
		}

		//One rule-of-thumb: ask yourself "does it make sense to call this method, even if no Obj has been constructed yet?" If so, it should definitely be static.
		public void runRound(){
			GameEngine.setTable(this); //clears Pot, clears board cards, prepares shuffled Deck
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