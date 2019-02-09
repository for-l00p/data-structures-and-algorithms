class Player {
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