
package games.poker;


/**Design a poker Game. 

**/
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

final class Poker {

	private Poker (){
		
	}



	private static int askForMinimumBet(){
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter minimum bet:");
		int minimumBet = in.nextInt();
		return minimumBet;
	}

	private static int askForNumberOfPlayers(){
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter number of players:");
		int numPlayers = in.nextInt();
		return numPlayers;
	}

	private static int askForNumberOfRounds(){
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter number of rounds to play:");
		int numRounds = in.nextInt();
		return numRounds;
	}

	private static List<Player> initPlayers(int numPlayers){
		List<Player> players = new ArrayList<>();
		Scanner in = new Scanner(System.in);
		int count = 1;
		do {
			System.out.println("Player " + count + ", please enter your name:");
			String playerName = in.nextLine();
			System.out.println(playerName + ", please enter your buyin:");
			int playerBuyin = in.nextInt();
			in.nextLine();
			players.add(new Player(playerName, playerBuyin));
			count++;
		} while (count <= numPlayers);
		return players; 
	}

	public static void launch() {

		int numPlayers = askForNumberOfPlayers();
		int numRounds = askForNumberOfRounds();
		int minimumBet = askForMinimumBet();
		List<Player> players = initPlayers(numPlayers);
		for (int i = 0; i < numRounds; i ++ ){
			int smallBlindIndex = i % players.size();
			Round round = new Round(players, smallBlindIndex , minimumBet);
			round.run();
		}
	}
	
	 
}