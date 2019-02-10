

package games.poker;

import java.util.*;

class GameEngine {	

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

		public static int initMinimumBet(){
			Scanner in = new Scanner(System.in);
			System.out.println("Please enter minimum bet:");
			int minimumBet = in.nextInt();
			return minimumBet;
		}

		public static void setTable (Game game){
			game.pot = 0;
			game.openCards = new ArrayList<Card>();
			game.currentDeck = new Deck();
			game.currentDeck.shuffle();
			game.currentTurnIndex = game.smallBlindIndex;
			game.currentMaxBet = game.minimumBet;
		}

		public static void setPlayerStatus(Game game){
			List<Player> players = game.players;
			int n = players.size();
			Player player;
			for (int i = 0;  i  < n;  i++){
				player = players.get(i);
				player.currentBet = 0;
				player.status = Player.Status.PLAYING;
				player.hadRoundTurn = false;
				player.holeCards = new ArrayList<Card>();

			}

		}
		public static void dealCards(Game game, int numCards){
			for (int i = 1; i <= numCards;i++){
				dealOneCard(game);
			}
		}

		private static void dealOneCard(Game game){
			List<Player> players = game.players;
			int n = players.size();
			int smallBlindIndex = game.smallBlindIndex;
			for (int i = 0;  i  < n;  i++){
				Player nextPlayer = players.get((smallBlindIndex + i) %n);
				Card nextCard = game.currentDeck.pop();
				nextPlayer.addHoleCard(nextCard);
				
			}
		}



		private static boolean checkIfAllPlayersMatchedBet(Game game){
			for (Player player: game.players){
				if (player.status == Player.Status.PLAYING){
					if(player.currentBet != game.currentMaxBet){
						return false;
					}	
				}
				
			}
			return true;
		}



		private static boolean checkIfMoreThanOnePlayerPlaying (Game game){
			int count = 0;
			for (Player player: game.players){
				if(player.status == Player.Status.PLAYING){
					count++;
				}
			}
			return count > 1;

		}


		private static boolean checkIfAllPlayersHadTurn(Game game){
			for (Player player: game.players){
				if(player.hadRoundTurn == false){
					return false;
				}
			}
			return true;
		}


		public static void clearBettingHistory(Game game){
			game.minimumBet = 0;
			game.currentMaxBet = 0;
			game.currentTurnIndex = game.smallBlindIndex;
			for (Player player: game.players){
					player.currentBet = 0;
					player.hadRoundTurn = false;
				}
				
		}
		

		public static void doBlindBetting(Game game){

			Player smallBlindPlayer = game.players.get(game.smallBlindIndex);
			if(smallBlindPlayer.stack < game.minimumBet){
				System.out.println("You do not have enough money to keep playing. Money you have: " + smallBlindPlayer.stack + ". Minimum bet requred: " + game.minimumBet + ". Going all in" );
				smallBlindPlayer.status = Player.Status.ALLIN;
				int playerBet = smallBlindPlayer.stack;
				System.out.println("Player has gone all in");
				makeBet(game, smallBlindPlayer, game.minimumBet, playerBet);
			} else {
				makeBet(game, smallBlindPlayer, game.minimumBet, game.minimumBet);
			}

			game.currentTurnIndex = (game.smallBlindIndex + 1) % game.players.size();
			game.minimumBet = 2*game.minimumBet;


			Player bigBlindPlayer = game.players.get(game.currentTurnIndex);
			if(bigBlindPlayer.stack < game.minimumBet){
				System.out.println("You do not have enough money to keep playing. Money you have: " + bigBlindPlayer.stack + ". Minimum bet requred: " + game.minimumBet + ". Going all in" );
				bigBlindPlayer.status = Player.Status.ALLIN;
				int playerBet = bigBlindPlayer.stack;
				System.out.println("Player has gone all in");
				makeBet(game, bigBlindPlayer, game.minimumBet, playerBet);
			} else {
				makeBet(game, bigBlindPlayer, game.minimumBet, game.minimumBet);
			}

			game.currentMaxBet = game.minimumBet;
			game.currentTurnIndex = (game.currentTurnIndex + 1) % game.players.size();

		}

		public static void doBettingRound(Game game){
			do {
				if(!checkIfMoreThanOnePlayerPlaying(game)){
					System.out.println("All other players folded or all in");
					return;
				}
				Player currentPlayer = game.players.get(game.currentTurnIndex);
				currentPlayer.hadRoundTurn = true;
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
		private static void askForBet(Game game, Player player){
			if(player.status == Player.Status.FOLDED || player.status == Player.Status.ALLIN){
				return;
			} else {
				Scanner in = new Scanner(System.in);
				System.out.println(player.name + ",  your cards are:" + player.holeCards.toString());
				int minBet = game.currentMaxBet - player.currentBet; //
				if (minBet > player.stack){
					System.out.println("You do not have enough money to bet. Money you have: " + player.stack + ". Minimum bet requred: " + minBet + ". Do you want to go All in? (y/n)?" );
					String allIn = in.nextLine();
					if(allIn.equals("y")){
						player.status = Player.Status.ALLIN;
						int playerBet = player.stack;
						System.out.println("Player has gone all in");
						makeBet(game, player, 0, playerBet);
					} else {
						player.status = Player.Status.FOLDED;
					}
				} else {
					//Case when player stack > minBet
					System.out.println(player.name + ", please enter a bet (" + minBet + "-" + player.stack +  "). Entering an amount lower than " + minBet +  " would be considered a FOLD");
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

		private static void makeBet(Game game, Player player, int minBet, int bet ){
			if (bet < minBet ){
				player.status = Player.Status.FOLDED;
				System.out.println(player.name + " has folded");
			} else {
						//Update player state
				player.currentBet = player.currentBet + bet;
				player.stack = player.stack - bet;
						//Update Game state
				game.currentMaxBet = Math.max(player.currentBet, game.currentMaxBet);
				game.pot = game.pot + bet;
				System.out.println(player.name + " has made a bet of " + bet);
				System.out.println("Potsize is now: " + game.pot);
			}

		}


		public static void openCards(Game game, int num){
			for (int i = 1; i <= num; i++){
				Card nextCard = game.currentDeck.pop();
				game.openCards.add(nextCard);
				System.out.println("Next card to be opened is:" + nextCard.toString());
			}
		}

		public static void distributeWinnings(Game game, Player player){
			player.stack = player.stack + game.pot;
			 System.out.println(player.name + " won a pot of " + game.pot + ". His stack now: " + player.stack);
		}



		public static void showOfHands(Game game){
			Hand tempbest = null;
			Player tempWinner = null;
			for (Player player: game.players){
				if (player.status == Player.Status.PLAYING ||  player.status == Player.Status.ALLIN){
					List<Card> playerCards = new ArrayList<Card>();
					playerCards.addAll(player.holeCards);
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
			System.out.println("Winner is: " + tempWinner.name);
		}
}