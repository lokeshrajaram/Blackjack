package blackjack.card;

import blackjack.card.Card.Rank;
import blackjack.card.Card.Suit;

/**
 * This class represents a shuffled N-deck cards. A N-deck is a collection of
 * n-array-52 cards. Each set of 52 cards is a collection of the following:
 * <p>
 * Thirteen cards of Spades from Rank A through K
 * </p>
 * <p>
 * Thirteen cards of Hearts from Rank A through K
 * </p>
 * <p>
 * Thirteen cards of Diamonds from Rank A through K
 * </p>
 * <p>
 * Thirteen cards of Clubs from Rank A through K
 * </p>
 * 
 * @author Lokesh Rajaram
 * @see Suit
 * @see Rank
 */
public class NDeck {

    private Card[] nDeck;
    private int indexToServe = 0;

    /**
     * Used to constructed a NDeck. To construct a NDeck number of decks must be
     * passed.
     * 
     * @param numberOfDecks
     *            the number of decks the NDeck must have
     */
    public NDeck(int numberOfDecks) {

        setupNDeck(numberOfDecks);

    }

    /**
     * Used to create a NDeck and shuffle the created NDeck.
     * 
     * @param numberOfDecks
     */
    public void setupNDeck(int numberOfDecks) {
        // validate deck numbers
        if (numberOfDecks < 1 || numberOfDecks > 8) {
            throw new IllegalArgumentException(
                    "Invalid number provided for number of decks argument");
        }

        this.nDeck = new Card[numberOfDecks * 52];

        // generate ndeck cards
        int index = 0;
        for (int i = 0; i < numberOfDecks; i++) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    Card card = new Card(suit, rank);
                    nDeck[index++] = card;
                }
            }
        }

        // shuffle NDeck Array
        KnuthShuffle.shuffle(nDeck);

        // re-initilaize index to zero
        indexToServe = 0;
    }

    /**
     * Returns the number of decks in NDeck.
     * 
     * @return Card[] an array of cards
     */
    public Card[] getnDeck() {
        return nDeck;
    }

    /**
     * Returns the index that in the NDeck array that is being served.
     * 
     * @return the index of the NDeck to be served.
     */
    public int getIndexToServe() {
        return indexToServe;
    }

    /**
     * Used to obtain the next available card to serve a player/dealer
     * 
     * @return the next card to be issued for a player/dealer.
     */
    public Card getNextCard() {

        if (nDeck.length == indexToServe) {
            throw new IndexOutOfBoundsException("No more cards in the deck");
        }

        Card card = nDeck[indexToServe];

        // to avoid java loitering
        nDeck[indexToServe++] = null;

        return card;
    }

}