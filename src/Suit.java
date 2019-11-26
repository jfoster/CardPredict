public enum Suit {
	Clubs(1),
	Diamonds(2),
	Hearts(3),
	Spades(4);
	
	private int suit;
	
	private Suit(int suit) {
		this.suit = suit;
	}
	
	public int getSuit() {
		return this.suit;
	}
}
