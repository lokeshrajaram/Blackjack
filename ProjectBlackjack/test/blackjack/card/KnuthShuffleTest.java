package blackjack.card;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import blackjack.card.Card.Rank;
import blackjack.card.Card.Suit;

/**
 * Test class for testing KnuthShuffle related implementation.
 * 
 * @author Lokesh Rajaram
 */
public class KnuthShuffleTest {

    @Test
    public void testShuffle() {

        Card[] cards = null;

        // generate 1 deck of cards
        cards = new Card[1 * 52];
        for (int index = 0; index < cards.length;) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    Card card = new Card(suit, rank);
                    cards[index++] = card;
                }
            }
        }

        assertEquals("Must be 52 cards total before shuffling as", 52,
                cards.length);

        // shuffle 1deck Array
        KnuthShuffle.shuffle(cards);

        assertEquals("Must be 52 cards total after ashuffling as well", 52,
                cards.length);

        // generate 4 deck of cards
        cards = new Card[4 * 52];
        for (int index = 0; index < cards.length;) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    Card card = new Card(suit, rank);
                    cards[index++] = card;
                }
            }
        }

        assertEquals("Must be 208 cards total before shuffling as", 208,
                cards.length);

        // shuffle 4deck Array
        KnuthShuffle.shuffle(cards);

        assertEquals("Must be 208 cards total after ashuffling as well", 208,
                cards.length);

        // generate 8 deck of cards
        cards = new Card[8 * 52];
        for (int index = 0; index < cards.length;) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    Card card = new Card(suit, rank);
                    cards[index++] = card;
                }
            }
        }

        assertEquals("Must be 416 cards total before shuffling as", 416,
                cards.length);

        // shuffle 4deck Array
        KnuthShuffle.shuffle(cards);

        assertEquals("Must be 416 cards total after ashuffling as well", 416,
                cards.length);

    }

}