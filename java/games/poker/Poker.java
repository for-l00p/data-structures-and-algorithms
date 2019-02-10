
package games.poker;


/**Design a poker Game. 

**/

import java.util.*;
import java.lang.reflect.Field;

class Poker {
//Root class which has all the classes needed.
	List<Player> players;
	int minimumBet;

	public Poker (int numPlayers, int numRounds){
		players = GameEngine.initPlayers(numPlayers);
		minimumBet = GameEngine.initMinimumBet();
		for (int i = 0; i < numRounds; i ++ ){
			int smallBlindIndex = i % players.size();
			Game game = new Game(players, smallBlindIndex , minimumBet);
			game.runRound();
		}
	}
	
	 
}