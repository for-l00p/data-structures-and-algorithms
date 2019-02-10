

package games.poker;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

final class GameEngine {	

	private GameEngine(){
		throw new AssertionError("This class is not meant to be instantiated.");
	}

	public static List<Player> initPlayers(int numPlayers){
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

	public static int askForMinimumBet(){
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter minimum bet:");
		int minimumBet = in.nextInt();
		return minimumBet;
	}

	

	public static void setPlayerStatus(Round game){
			List<Player> players = game.players;
			int n = players.size();
			Player player;
			for (int i = 0;  i  < n;  i++){
				player = players.get(i);
				player.resetCurrentBet();
				player.setPlayingStatus(PlayingStatus.PLAYING);
				player.setBettingTurn(false);
				player.resetHoleCards();

			}

	}

	public static void dealCards(Round game, int numCards){
			for (int i = 1; i <= numCards;i++){
				dealOneCard(game);
			}
	}

	private static void dealOneCard(Round game){
			List<Player> players = game.players;
			int n = players.size();
			int smallBlindIndex = game.smallBlindIndex;
			for (int i = 0;  i  < n;  i++){
				Player nextPlayer = players.get((smallBlindIndex + i) %n);
				Card nextCard = game.currentDeck.dealCard();
				nextPlayer.addHoleCard(nextCard);
				
			}
	}



	private static boolean checkIfAllPlayersMatchedBet(Round game){
		for (Player player: game.players){
			if (player.getPlayingStatus() == PlayingStatus.PLAYING){
				if(player.getCurrentBet() != game.currentMaxBet){
					return false;
				}	
			}
				
		}
		return true;
	}



	private static boolean checkIfMoreThanOnePlayerPlaying (Round game){
			int count = 0;
			for (Player player: game.players){
				if(player.getPlayingStatus() == PlayingStatus.PLAYING){
					count++;
				}
			}
			return count > 1;

	}


	private static boolean checkIfAllPlayersHadTurn(Round game){
			for (Player player: game.players){
				if(player.hadBettingTurn() == false){
					return false;
				}
			}
			return true;
	}


	public static void clearBettingHistory(Round game){
			game.minimumBet = 0;
			game.currentMaxBet = 0;
			game.currentTurnIndex = game.smallBlindIndex;
			for (Player player: game.players){
					player.resetCurrentBet();
					player.setBettingTurn(false);
				}
				
	}
		

	public static void doBlindBetting(Round game){

			Player smallBlindPlayer = game.players.get(game.smallBlindIndex);
			int smallBlindStack = smallBlindPlayer.getStackAmount();
			if(smallBlindStack  < game.minimumBet){
				System.out.println("You do not have enough money to keep playing. Money you have: " + smallBlindStack  + ". Minimum bet requred: " + game.minimumBet + ". Going all in" );
				smallBlindPlayer.setPlayingStatus(PlayingStatus.ALLIN);
				int playerBet = smallBlindStack ;
				System.out.println("Player has gone all in");
				makeBet(game, smallBlindPlayer, game.minimumBet, playerBet);
			} else {
				makeBet(game, smallBlindPlayer, game.minimumBet, game.minimumBet);
			}

			game.currentTurnIndex = (game.smallBlindIndex + 1) % game.players.size();
			game.minimumBet = 2*game.minimumBet;


			Player bigBlindPlayer = game.players.get(game.currentTurnIndex);
			int bigBlindPlayerStack = bigBlindPlayer.getStackAmount();
			if(bigBlindPlayerStack  < game.minimumBet){
				System.out.println("You do not have enough money to keep playing. Money you have: " + bigBlindPlayerStack  + ". Minimum bet requred: " + game.minimumBet + ". Going all in" );
				bigBlindPlayer.setPlayingStatus(PlayingStatus.ALLIN);
				int playerBet = bigBlindPlayerStack ;
				System.out.println("Player has gone all in");
				makeBet(game, bigBlindPlayer, game.minimumBet, playerBet);
			} else {
				makeBet(game, bigBlindPlayer, game.minimumBet, game.minimumBet);
			}

			game.currentMaxBet = game.minimumBet;
			game.currentTurnIndex = (game.currentTurnIndex + 1) % game.players.size();

	}

	public static void doBettingRound(Round game){
			do {
				if(!checkIfMoreThanOnePlayerPlaying(game)){
					System.out.println("All other players folded or all in");
					return;
				}
				Player currentPlayer = game.players.get(game.currentTurnIndex);
				currentPlayer.setBettingTurn(true);
				askForBet(game, currentPlayer); //Does nothing if player is folded. 
				game.currentTurnIndex = (game.currentTurnIndex + 1) % game.players.size();						
			} while (
				//do this until all have matchedbet and all have had turn
			   !(checkIfAllPlayersMatchedBet(game) && checkIfAllPlayersHadTurn(game))
			 );
	}

		
		/**
		 * Takes in a a reference to a player and the state of the round (including the maximum bet in the given round, the player's total bet in the round, and the player's leftOverStack and playing status). 

		 * @param game   [description]
		 * @param player [description]
		 */
	private static void askForBet(Round game, Player player){
			if(player.getPlayingStatus() == PlayingStatus.FOLDED || player.getPlayingStatus() == PlayingStatus.ALLIN){
				return;
			} else {
				Scanner in = new Scanner(System.in);
				System.out.println(player.getName() + ",  your cards are:" + player.getHoleCards().toString());
				int minBet = game.currentMaxBet - player.getCurrentBet(); //
				if (minBet > player.getStackAmount()){
					System.out.println("You do not have enough money to bet. Money you have: " + player.getStackAmount() + ". Minimum bet requred: " + minBet + ". Do you want to go All in? (y/n)?" );
					String allIn = in.nextLine();
					if(allIn.equals("y")){
						player.setPlayingStatus(PlayingStatus.ALLIN);
						int playerBet = player.getStackAmount();
						System.out.println("Player has gone all in");
						makeBet(game, player, 0, playerBet);
					} else {
						player.setPlayingStatus(PlayingStatus.FOLDED);
					}
				} else {
					//Case when player stack > minBet
					System.out.println(player.getName() + ", please enter a bet (" + minBet + "-" + player.getStackAmount() +  "). Entering an amount lower than " + minBet +  " would be considered a FOLD");
					int playerBet = in.nextInt();
					makeBet(game, player, minBet, playerBet)	;
				}
			}
	}

		/**
		 * Takes a state of a game, a reference to a player a minimum amount the player has to bet for it to be a valid bet, and his actual bet. 
		 * @param game   [description]
		 * @param player [description]
		 * @param minBet [description]
		 * @param bet    [description]
		 */

	private static void makeBet(Round game, Player player, int minBet, int bet ){
			if (bet < minBet ){
				player.setPlayingStatus(PlayingStatus.FOLDED);
				System.out.println(player.getName() + " has folded");
			} else {
						//Update player state
				player.addToBet(bet);
				player.removeFromStack(bet);
						//Update Round state
				game.currentMaxBet = Math.max(player.getCurrentBet(), game.currentMaxBet);
				game.pot = game.pot + bet;
				System.out.println(player.getName() + " has made a bet of " + bet);
				System.out.println("Potsize is now: " + game.pot);
			}

	}


	public static void openCards(Round game, int num){
			for (int i = 1; i <= num; i++){
				Card nextCard = game.currentDeck.dealCard();
				game.openCards.add(nextCard);
				System.out.println("Next card to be opened is:" + nextCard.toString());
			}
	}

	public static void distributeWinnings(Round game, Player player){
			player.addToStack(game.pot);
			 System.out.println(player.getName() + " won a pot of " + game.pot + ". His stack now: " + player.getStackAmount());
	}



	public static void showOfHands(Round game){
		Hand tempbest = null;
		Player tempWinner = null;
		for (Player player: game.players){
			if (player.getPlayingStatus() == PlayingStatus.PLAYING ||  player.getPlayingStatus() == PlayingStatus.ALLIN){
			List<Card> playerCards = new ArrayList<Card>();
			playerCards.addAll(player.getHoleCards());
			playerCards.addAll(game.openCards);
			Hand playerHand = new Hand(playerCards);
					//System.out.println("HAND: " + playerHand.toString());
				if (playerHand.compareTo(tempbest) > 0){
					tempbest = playerHand;
					tempWinner = player;
				}
			}
		}
		distributeWinnings(game, tempWinner);
		System.out.println("Winner is: " + tempWinner.getName());
	}
}