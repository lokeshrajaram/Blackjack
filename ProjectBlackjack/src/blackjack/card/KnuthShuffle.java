package blackjack.card;

import java.util.Random;

/**
 * Used for performing a uniform random permutation. This implementation is
 * based on Prof. Robert Sedgewick Coursera class.
 * 
 * @author Lokesh Rajaram
 */
public class KnuthShuffle {

    /**
     * Used to shuffle deck of cards. This method provides uniform random
     * distribution based shuffling.
     * 
     * @param cards
     *            an array of cards
     */
    public static void shuffle(Card[] cards) {
        int numberOfCards = cards.length;
        Random random = new Random();
        for (int i = 0; i < numberOfCards; i++) {
            int randomIndex = random.nextInt(i + 1);
            Card temp = cards[randomIndex];
            cards[randomIndex] = cards[i];
            cards[i] = temp;
        }
    }

}