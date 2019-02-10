
package games.poker;


/**Design a poker Game. 

**/
import java.util.List;

final class Poker {

	public Poker (int numPlayers, int numRounds){
		List<Player> players = GameEngine.initPlayers(numPlayers);
		int minimumBet = GameEngine.askForMinimumBet();
		for (int i = 0; i < numRounds; i ++ ){
			int smallBlindIndex = i % players.size();
			Round round = new Round(players, smallBlindIndex , minimumBet);
			round.run();
		}
	}
	
	 
}