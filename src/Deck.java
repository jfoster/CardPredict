import java.util.Arrays;
import java.util.Collections;

public class Deck {
	
	private Card[] deck;
	private int cardsUsed;
	
	public Deck() {
		deck = new Card[52];
		
		int temp = 0;
		for (Suit s : Suit.values()) {
			for (Rank r : Rank.values()) {
				deck[temp] = new Card(s, r);
				temp++;
			}
		}
		cardsUsed = 0;
	}
	
	public void shuffle() {		
		Collections.shuffle(Arrays.asList(deck));
	}
	
	public Card dealCard() {
		cardsUsed++;
		
		if (cardsUsed > 52) {
			cardsUsed = 1;
		}
		return deck[cardsUsed - 1];
	}
}
