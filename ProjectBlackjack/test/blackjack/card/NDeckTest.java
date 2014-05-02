package blackjack.card;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test class for testing NDeck related implementation.
 * 
 * @author Lokesh Rajaram
 */
public class NDeckTest {

    @Test(expected = IllegalArgumentException.class)
    public void testSetupNDeckInvalidNumberOfDecksHigherThanRange() {
        @SuppressWarnings("unused")
        NDeck nDeck = new NDeck(10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetupNDeckInvalidNumberOfDecksLowerThanRange() {
        @SuppressWarnings("unused")
        NDeck nDeck = new NDeck(-1);
    }

    @Test
    public void testSetupNDeck() {
        NDeck nDeck = new NDeck(1);
        assertEquals("Must be 52 cards total", 52, nDeck.getnDeck().length);

        nDeck = new NDeck(2);
        assertEquals("Must be 104 cards total", 104, nDeck.getnDeck().length);

        nDeck = new NDeck(4);
        assertEquals("Must be 208 cards total", 208, nDeck.getnDeck().length);

        nDeck = new NDeck(5);
        assertEquals("Must be 260 cards total", 260, nDeck.getnDeck().length);

        nDeck = new NDeck(7);
        assertEquals("Must be 364 cards total", 364, nDeck.getnDeck().length);

    }

    @Test
    public void testGetnDeck() {
        NDeck nDeck = new NDeck(1);
        assertEquals("Must be 52 cards total", 52, nDeck.getnDeck().length);

        nDeck = new NDeck(3);
        assertEquals("Must be 156 cards total", 156, nDeck.getnDeck().length);

        nDeck = new NDeck(6);
        assertEquals("Must be 312 cards total", 312, nDeck.getnDeck().length);
    }

    @Test
    public void testGetIndexToServe() {
        NDeck nDeck = new NDeck(1);
        assertEquals("Must be 52 cards total", 52, nDeck.getnDeck().length);

        assertEquals("Must be 0", 0, nDeck.getIndexToServe());

        nDeck.getNextCard();
        nDeck.getNextCard();
        nDeck.getNextCard();
        nDeck.getNextCard();

        assertEquals("Must be 4", 4, nDeck.getIndexToServe());

        nDeck.getNextCard();
        nDeck.getNextCard();
        nDeck.getNextCard();
        nDeck.getNextCard();
        nDeck.getNextCard();
        nDeck.getNextCard();
        nDeck.getNextCard();
        nDeck.getNextCard();
        nDeck.getNextCard();

        assertEquals("Must be 13", 13, nDeck.getIndexToServe());

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetNextCardWhenDeckIsEmpty() {
        NDeck nDeck = new NDeck(4);
        assertEquals("Must be 208 cards total", 208, nDeck.getnDeck().length);

        for (int i = 0; i <= nDeck.getnDeck().length; i++) {
            nDeck.getNextCard();
        }

    }

    @Test
    public void testGetNextCard() {
        NDeck nDeck = new NDeck(3);

        for (int i = 0; i < nDeck.getnDeck().length; i++) {
            nDeck.getNextCard();
        }

        assertEquals("Must be 156", 156, nDeck.getIndexToServe());
    }

}