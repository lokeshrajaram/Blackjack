package blackjack.card;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import blackjack.card.Card.Rank;
import blackjack.card.Card.Suit;

/**
 * Test class for testing Card related implementation.
 * 
 * @author Lokesh Rajaram
 */
public class CardTest {

    @Test
    public void testGetSuit() {
        Card card = new Card(Suit.Clubs, Rank.Ace);
        assertEquals("Must be clubs", Suit.Clubs, card.getSuit());

        card = new Card(Suit.Diamonds, Rank.Five);
        assertEquals("Must be Diamonds", Suit.Diamonds, card.getSuit());

        card = new Card(Suit.Hearts, Rank.Nine);
        assertEquals("Must be Hearts", Suit.Hearts, card.getSuit());

        card = new Card(Suit.Spades, Rank.King);
        assertEquals("Must be Spades", Suit.Spades, card.getSuit());
    }

    @Test
    public void testGetRank() {
        Card card = new Card(Suit.Spades, Rank.Four);
        assertEquals("Must be Four", Rank.Four, card.getRank());

        card = new Card(Suit.Diamonds, Rank.Five);
        assertEquals("Must be Five", Rank.Five, card.getRank());

        card = new Card(Suit.Hearts, Rank.Nine);
        assertEquals("Must be Nine", Rank.Nine, card.getRank());

        card = new Card(Suit.Spades, Rank.King);
        assertEquals("Must be King", Rank.King, card.getRank());

        card = new Card(Suit.Diamonds, Rank.Seven);
        assertEquals("Must be Seven", Rank.Seven, card.getRank());

        card = new Card(Suit.Hearts, Rank.Jack);
        assertEquals("Must be Jack", Rank.Jack, card.getRank());

        card = new Card(Suit.Spades, Rank.Two);
        assertEquals("Must be Two", Rank.Two, card.getRank());
    }

    @Test
    public void testToString() {
        Card card = new Card(Suit.Clubs, Rank.Ace);
        assertEquals("Must be Ace of Clubs",
                card.getRank() + " of " + card.getSuit(), card.toString());

        card = new Card(Suit.Spades, Rank.Seven);
        assertEquals("Must be Seven of Spades",
                card.getRank() + " of " + card.getSuit(), card.toString());

        card = new Card(Suit.Hearts, Rank.Queen);
        assertEquals("Must be Queen of Hearts",
                card.getRank() + " of " + card.getSuit(), card.toString());

        card = new Card(Suit.Diamonds, Rank.Three);
        assertEquals("Must be Three of Diamonds", card.getRank() + " of "
                + card.getSuit(), card.toString());

        card = new Card(Suit.Spades, Rank.King);
        assertEquals("Must be King of Spades",
                card.getRank() + " of " + card.getSuit(), card.toString());
    }

}