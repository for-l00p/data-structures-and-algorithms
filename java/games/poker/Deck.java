class Deck {
		//deque is an implementation of stack. it extends vector.
	private List<Card> cards;

	public Deck(){
		cards = new ArrayList<Card>();
		for (Suit s: Suit.values()){
			for (Rank r: Rank.values()){
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