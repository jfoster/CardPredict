import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Game {
	
	static Scanner scanner;
	
	int scrollingDelay = 10;
	
	boolean playAgain;
	boolean shouldQuit;
	boolean deckShuffled;
	
	Deck deck = new Deck();

	String input;
	
	public void start() {
		println("Welcome to Higher or Lower");
		menu();
	}
	
	public void menu() {
		do {
			println("-------- MAIN MENU --------");
			println("Press [P] to play");
			println("Press [S] to shuffle the deck");
			println("Press [Q] to quit");
			
			input = getInput(new String[] { "P", "S", "Q" }).toUpperCase();
			switch (input) {
			case "P":
				play();
				break;
			case "S":
				shuffle();
				break;
			case "Q":
				shouldQuit = true;
				println("See you again soon!");
				break;
			}
		} while (!shouldQuit);
	}
	
	public void play() {
		Card currentCard;
		Card nextCard;
		int correctGuesses;
		
		if (!deckShuffled) {
			println("Deck has not been shuffled.");
			println("Would you like to shuffle the deck? [Y/N]");
			input = getInput(new String[] {"Y", "N"});
			if (input.equalsIgnoreCase("Y")) {
				shuffle();
			}
		}
		
		do {
			correctGuesses = 0;
			currentCard = deck.dealCard();
			
			while(true) {
				println("Your card is the " + currentCard.getCardAsString());
				println("Will the next card be higher or lower? [H/L]");
				input = getInput(new String[] {"H","L"});
				
				nextCard = deck.dealCard();
				println("The next card is the " + nextCard.getCardAsString());
				if (currentCard.getRankAsInteger() < nextCard.getRankAsInteger()) {
					if (input.equalsIgnoreCase("H")) {
						println("Your prediction was correct!");
						correctGuesses++;
					} else {
						println("Your prediction was incorrect.");
						break;
					}
				} else if (currentCard.getRankAsInteger() > nextCard.getRankAsInteger()) {
					if (input.equalsIgnoreCase("L")) {
						println("Your prediction was correct!");
						correctGuesses++;
					} else {
						println("Your prediction was incorrect.");
						break;
					}
				} else {
					println("Oh No! They both have the same value.");
					println("You Lose.");
					break;
				}
				currentCard = nextCard;
				
			}
			
			println("Game Over");
			println("You got " + correctGuesses + " predictions correct.");
			
			println("Please enter your name.");
			input = getInput(null);
			
			println("Would you like to play again? [Y/N]");
			input = getInput(new String[] {"Y","N"});
			if (input.equalsIgnoreCase("Y")) {
				playAgain = true;
			}
			
		} while (playAgain);
	}
	
	public void shuffle() {
		long start = System.nanoTime();
		deck.shuffle();
		deckShuffled = true;
		long end = System.nanoTime() - start;
		println("Deck has been shuffled in: " + end + " nanoseconds");
	}
	
	private void clear() {
		if (System.console() != null) {
//			if (System.getProperty("os.name").contains("Windows"))
//			{
//				try {
//					new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//				} catch (InterruptedException | IOException e) {
//					e.printStackTrace();
//				}
//			} else {
			    try {
			    	Runtime.getRuntime().exec("clear");
				} catch (IOException e) {
					e.printStackTrace();
				}
//			}
		}
	}
	
	private void println(String string) {
		System.out.println(string);
	}

	private String getInput(String[] validKeys) {
		scanner = new Scanner(System.in);
		boolean valid = false;
		String in;
		do {
			in = scanner.next();
			if (validKeys == null) {
				valid = true;
			} else {
				for (String key : validKeys) {
			        if (key.equalsIgnoreCase(in)) {
			        	valid = true;						
			            break;
			        }
			    }
			}
			clear();
			if (!valid) {
				println("Input is invalid. Valid inputs are: " + Arrays.toString(validKeys));
			}
		} while (!valid);
		return in;
	}
	
//	private void sleep(int delay) {
//		try {
//			Thread.sleep(delay);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
}