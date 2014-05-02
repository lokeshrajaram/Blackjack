package blackjack.card;

/**
 * This class represents a card. A card is a combinational part of a suit and a
 * rank.
 * 
 * @author Lokesh Rajaram
 */
public class Card {

    /**
     * Represents the suit part of a card.
     */
    private final Suit suit;

    /**
     * Represents the rank part of a card.
     */
    private final Rank rank;

    /**
     * An enum to introduce type safety for Card Suit.
     */
    public enum Suit {
        Spades, Hearts, Diamonds, Clubs;
    }

    /**
     * An enum to introduce type safety for Card Rank.
     */
    public enum Rank {
        Ace(1), Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(
                9), Ten(10), Jack(10), Queen(10), King(10);

        private final int value;

        private Rank(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    /**
     * Used to constructed a card. A card must be constructed using a suit and a
     * rank.
     * 
     * @param suit
     *            the type of the suit
     * @param rank
     *            the type of the rank
     */
    public Card(final Suit suit, final Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Used to obtain the suit of the card.
     * 
     * @return the suit of the card.
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Used to obtain the rank of the card.
     * 
     * @return the rank of the card.
     */
    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return getRank() + " of " + getSuit();
    }

}