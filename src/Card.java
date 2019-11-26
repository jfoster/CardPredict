public class Card {
	
	private Suit suit;
	private Rank rank;
	
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public String getCardAsString() {
		return getRankAsString() + " of " + getSuitAsString();
	}
	
	public String getSuitAsString() {
        /*switch (suit) {
	        case Clubs:
	        	return "\u2666";
	        case Diamonds:
	        	return "\u2663";
	        case Hearts:
	        	return "\u2665";
	        case Spades:
	        	return "\u2660";
        }*/
		return suit.name();
	}
	
	public Integer getSuitAsInteger() {
		return suit.getSuit();
	}
	
	public String getRankAsString() {
		switch(rank) {
		case Ace:
		case Jack:
		case Queen:
		case King:
			return rank.name();
		default:
			return getRankAsInteger().toString();
		}
	}

	public Integer getRankAsInteger() {
		return rank.ordinal() + 1;
	}
}
