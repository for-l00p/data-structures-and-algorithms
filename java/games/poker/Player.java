
package games.poker;

import java.util.List;
import java.util.ArrayList;

final class Player {

	private String name;
	private int stack;
	private List<Card> holeCards;
	private PlayingStatus status; //could be PLAYING, ALL IN, FOLDED.
	private int currentBet; // The amount of money the player has put in this particular round of betting. This helps in checking the minimum amount a player has to bet to stay in the game (minBet = game.currentMaximumBet - player.currentBet). Also helps in checking terminal condition of the loop in doBettingRound(): whether a player has matched the maximum bet in this round of betting.
	private boolean hadRoundTurn; //Whether the player has had its turn in this particular round of betting. This helps in checking a terminal condition of the loop in doBettingRound(): whether next card can be opened or not. If any player has not had his turn, then the nextCard is not opened. 

	public Player(String name, int buyin){
		this.name = name;
		this.stack = buyin;
	}

	public String getName(){
		return this.name;
	}

	public int getCurrentBet(){
		return this.currentBet;
	}

	public void resetCurrentBet(){
		this.currentBet = 0;
	}

	public void addToBet(int bet){
		this.currentBet = bet + this.currentBet;
	}

	public PlayingStatus getPlayingStatus(){
		return this.status;
	}

	public void setPlayingStatus(PlayingStatus status){
		this.status = status;
	}

	public List<Card> getHoleCards(){
		return this.holeCards;
	}

	public void resetHoleCards(){
		this.holeCards = new ArrayList<Card>();
	}

	public void addHoleCard(Card card){
		holeCards.add(card);
	} 

	public int getStackAmount(){
		return this.stack;
	}

	public void addToStack(int winning){
		this.stack = this.stack + winning;
	}

	public void removeFromStack(int bet){
		this.stack = this.stack - bet;
	}



	public boolean hadRoundTurn(){
		return this.hadRoundTurn;
	}

	public void setRoundTurn(boolean turnStatus){
		this.hadRoundTurn = turnStatus;
	}

	






}