
package games.poker;


/**Design a poker Game. 

**/
import java.util.List;

class Poker {

	public Poker (int numPlayers, int numRounds){
		List<Player> players = GameEngine.initPlayers(numPlayers);
		int minimumBet = GameEngine.initMinimumBet();
		for (int i = 0; i < numRounds; i ++ ){
			int smallBlindIndex = i % players.size();
			Game game = new Game(players, smallBlindIndex , minimumBet);
			game.runRound();
		}
	}
	
	 
}