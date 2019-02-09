
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
	//Static class?
	static class Player {
		String name;
		int stack;
		private List<Card> holeCards;
		Status status; //could be PLAYING, ALL IN, FOLDED.
		int currentBet; // The amount of money the player has put in this particular round of betting. This helps in checking the minimum amount a player has to bet to stay in the game (minBet = game.currentMaximumBet - player.currentBet). Also helps in checking terminal condition of the loop in doBettingRound(): whether a player has matched the maximum bet in this round of betting.
		boolean hadRoundTurn; //Whether the player has had its turn in this particular round of betting. This helps in checking a terminal condition of the loop in doBettingRound(): whether next card can be opened or not. If any player has not had his turn, then the nextCard is not opened. 

		public Player(String name, int buyin){
			this.name = name;
			this.stack = buyin;

		}

		public void addHoleCard(Card card){
			holeCards.add(card);
		} 

		enum Status {PLAYING, FOLDED, ALLIN};

	}



	static class Deck {
		//deque is an implementation of stack. it extends vector.
		private List<Card> cards;

		public Deck(){
			cards = new ArrayList<Card>();
			for (Card.Suit s: Card.Suit.values()){
				for (Card.Rank r: Card.Rank.values()){
					Card card = new Card(s, r);
					cards.add(card);
				}
			}
		}

		public Card pop(){
			return cards.remove(0);
		};

		public void shuffle(){
			Collections.shuffle(cards);
		}	
	}

	static class Card implements Comparable<Card> {
		private final Suit suit;
		private final Rank rank;


		public Card (Suit suit, Rank rank){
			this.suit = suit;
			this.rank = rank;
		}


		enum Suit {CLUB, DIAMOND, HEART, SPADE;}
		enum Rank {
			TWO(2), THREE(3),FOUR(4),FIVE(5),SIX(6),SEVEN(7), EIGHT(8),NINE(9),TEN(10),JACK(11),QUEEN(12),KING(13), ACE(14);
			private final int value;

			private Rank (int value){
				this.value = value;
			}


		}

		public Rank getRank(){
			return this.rank;
		}

		public Suit getSuit(){
			return this.suit;
		}

		public int getValue(){
			return this.rank.value;
		}

		public int compareTo(Card c){
			return rank.compareTo(c.rank);
		}

		@Override
		public String toString() {
  			StringBuilder result = new StringBuilder();
  			String newLine = System.getProperty("line.separator");

  			result.append( this.getClass().getName() );
  			result.append( " Object {" );
  			result.append(newLine);

  			//determine fields declared in this class only (no fields of superclass)
  			Field[] fields = this.getClass().getDeclaredFields();

  			//print field names paired with their values
  			for ( Field field : fields  ) {
    			result.append("  ");
    			try {
      			result.append( field.getName() );
      			result.append(": ");
      		//requires access to private field:
      			result.append( field.get(this) );
    			} catch ( IllegalAccessException ex ) {
      			System.out.println(ex);
    			}
    			result.append(newLine);
  			}
  			result.append("}");

  			return result.toString();
			}

	}

	
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





	static class GameEngine {	

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

	/**
	 * Hand is an abstraction of a set of 7 cards, such that they can be compared (Here the comparison is based on Poker hand hierarchy, but we can conceivably change this). 

	  The Hand Class maps the set-of-7 cards to a {best-of-5-hand, HandRank}, where HandRank is the property of best-of-5-hand such as Flush, Straight etc. The best-of-5-hand are sorted based on Card-Rank (highest first). To compare, the HandRank is first compared. If two sets-of-7 have the same HandRank, then their best-of-5-hands are compared on Card.Rank, starting from the left. 


	  the evaluateHand method and generates/stores the HandRank, the primary card, the secondary card and the kicker. This method maps each Hand object to a (HandRank, primaryCard, secondaryCard, Kicker).

	  If two hands have the same HandRank (e.g. two hands mapping to two pair), then the primaryCard is used to break the tie. If the tie is still not broken(same primary card), then the secondaryCard is used. 

	 */
	
	static class Hand implements Comparable<Hand> {

		private HandRank rank;
		private List<Card> bestOfFive = new ArrayList<>();

		enum HandRank {
			HIGH_CARD,
			PAIR,
			TWO_PAIR,
			THREE_OF_A_KIND,
			STRAIGHT,
			FLUSH,
			FULL_HOUSE,
			FOUR_OF_A_KIND,
			STRAIGHT_FLUSH
		}

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

		public HandRank evaluateRank (List<Card> cards){
			if (scanForStraightFlush(cards)){
				return HandRank.STRAIGHT_FLUSH;
			} else if (scanForQuads(cards)){
				return HandRank.FOUR_OF_A_KIND;
			} else if (scanForFullHouse(cards)){
				return HandRank.FULL_HOUSE;
			} else if (scanForFlush(cards)){
				return HandRank.FLUSH;
			} else if (scanForStraight(cards)){
				return HandRank.STRAIGHT;
			} else if (scanForTrips(cards)){
				return HandRank.THREE_OF_A_KIND;
			} else if (scanForTwoPair(cards)){
				return HandRank.TWO_PAIR;
			} else if (scanForPair(cards)){
				return HandRank.PAIR;
			} else {
				scanForHighCard(cards);
				return HandRank.HIGH_CARD;
			}
		}


		private Map<Card.Suit, List<Card>> getSuitCardMap(List<Card> cards){
			Map<Card.Suit, List<Card>> suitCardMap = new HashMap<>();
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


		private Map<Card.Rank, List<Card>> getRankCardMap(List<Card>  cards){
			Map<Card.Rank, List<Card>> rankCardMap = new HashMap<>();
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

		private Map<Card.Rank, Integer> getRankFrequency(List<Card> cards){
			Map<Card.Rank, Integer> freqMap = new HashMap<>();

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

		public List<Card> extractHighestStraight(List<Card> cards){
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
				if (nextCard.getValue() == currentCard.getValue() - 1 ){
					result.add(nextCard);
					if (result.size() == 5){
						return result;
					}
					//Check for Wheel Straight
					if (result.size() == 4 && nextCard.getValue() == 2){
						if (tempList.get(0).getValue() == 14){
							result.add(tempList.get(0));
							return result;
						}
					}

				} else if (nextCard.getValue() == currentCard.getValue()){
					// do nothing 	
				} else {
					result.clear();
					result.add(nextCard);
				}
				currentCard = nextCard;
			}
			
			return null;
		}

		public boolean scanForStraightFlush (List<Card> cards){
			Map<Card.Suit, List<Card>> suitFreqMap = getSuitCardMap(cards);
			//TreeSet<Card> bestOfFiveFlush = new TreeSet<>()
			for (List<Card> cardsOfSameSuit: suitFreqMap.values()){
				if (cardsOfSameSuit.size() >= 5){
					List<Card> straightFlushCards = extractHighestStraight(cardsOfSameSuit);
					if(straightFlushCards != null){
						this.bestOfFive.addAll(straightFlushCards);
						this.rank = HandRank.STRAIGHT_FLUSH;
						return true;
					} 		
				}
			}
			return false;
		}



		public boolean scanForQuads (List<Card> cards) {
			this.bestOfFive.clear();
			Map<Card.Rank, List<Card>> rankCardMap = getRankCardMap(cards);
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


	public boolean scanForFullHouse (List<Card> cards){
			this.bestOfFive.clear();
			List<Card> temp = new ArrayList<>(cards);
			Collections.sort(temp);
			Collections.reverse(temp);  //all the cards in descending order.
			Map<Card.Rank, Integer> rankFreqMap = getRankFrequency(temp); // all the frequency of the card by ranks
			for (Card card: temp){

				if (rankFreqMap.get(card.rank) == 3 && this.bestOfFive.size() < 5){
					System.out.println("Inspecting: " + card.toString());
					this.bestOfFive.add(card);
				}
			}

			if (this.bestOfFive.size() == 5){
				return true;
			} 

			for (Card card: temp){
				if (rankFreqMap.get(card.rank) == 2 && this.bestOfFive.size() <= 5){
					this.bestOfFive.add(card);
				}	
			}
			if (this.bestOfFive.size() == 5){
				return true;
			} 
			return false;
		}

		public boolean scanForFlush (List<Card> cards){
			this.bestOfFive.clear();
			Map<Card.Suit, List<Card>> suitFreqMap = getSuitCardMap(cards);
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

		public boolean scanForStraight (List<Card> cards){
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
		public boolean scanForTrips (List<Card> cards){
			this.bestOfFive.clear();
			List<Card> temp = new ArrayList<>(cards);
			Collections.sort(temp);
			Collections.reverse(temp);
			Map<Card.Rank, Integer> rankFreqMap = getRankFrequency(temp);   // all the frequency of the card by ranks
			for (Card card: temp){
				if (rankFreqMap.get(card.rank) == 3){
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

		public boolean scanForTwoPair (List<Card> cards){
			this.bestOfFive.clear();
			List<Card> temp = new ArrayList<>(cards);
			Collections.sort(temp);
			Collections.reverse(temp);  //all the cards in descending order.
			Map<Card.Rank, Integer> rankFreqMap = getRankFrequency(temp); // all the frequency of the card by ranks
			for (Card card: temp){
				//System.out.println("Inspecting: " + card.toString());
				if (rankFreqMap.get(card.rank) == 2 && this.bestOfFive.size() < 4){
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

		public boolean scanForPair(List<Card> cards){
			this.bestOfFive.clear();
			List<Card> temp = new ArrayList<>(cards);
			Collections.sort(temp);
			Collections.reverse(temp);  //all the cards in descending order.
			Map<Card.Rank, Integer> rankFreqMap = getRankFrequency(temp); // all the frequency of the card by ranks
			for (Card card: temp){
				if (rankFreqMap.get(card.rank) == 2 && this.bestOfFive.size() <=5){
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

		public void scanForHighCard (List<Card> cards){
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


	public static void main(String args[]){
		
		Poker poker = new Poker(3, 2); // Input Parameters: numPlayers, numRounds 
		
	}


}