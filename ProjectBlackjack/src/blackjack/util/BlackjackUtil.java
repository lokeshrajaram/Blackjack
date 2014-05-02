package blackjack.util;

import java.util.List;

import blackjack.card.Card;
import blackjack.card.Card.Rank;
import blackjack.card.NDeck;
import blackjack.participant.Participant;
import blackjack.participant.Participant.ParticipantType;

/**
 * A helper class to support the blackjack game functionality implementation.
 * 
 * @author Lokesh Rajaram
 * @see NDeck
 */
public class BlackjackUtil {

    private NDeck nDeck;
    private final int numberOfDecks;

    /**
     * Constructs the helper class with NDeck.
     * 
     * @param numberOfDecks
     *            the number of decks
     */
    public BlackjackUtil(int numberOfDecks) {
        this.numberOfDecks = numberOfDecks;
        this.nDeck = new NDeck(numberOfDecks);
    }

    /**
     * Used to get the shuffled NDeck.
     * 
     * @return the shuffled Ndeck
     */
    public NDeck getnDeck() {
        return nDeck;
    }

    /**
     * used for re-initializing the NDeck. This is particularly useful when the
     * number of cards in the NDeck is not enough to play a game.
     */
    public void reInitializeNDeck() {
        this.nDeck = new NDeck(numberOfDecks);
    }

    /**
     * Used to issue a card to the participant.
     * 
     * @param participant
     *            participant of the game
     */
    public void issueCard(Participant participant) {
        List<Card> cardsInHand = participant.getCardsInHand();
        cardsInHand.add(getnDeck().getNextCard());
        participant.setCardsInHand(cardsInHand);
        setSumOfCardsInHand(participant);
    }

    /**
     * Used to compute and set the sum of cards in the hand of the participant.
     * 
     * @param participant
     *            participant of the game
     */
    public void setSumOfCardsInHand(Participant participant) {

        int cardSumAceAs1or11 = 0;
        int acesCount = 0;

        for (Card card : participant.getCardsInHand()) {

            if (card.getRank() == Rank.Ace) {
                cardSumAceAs1or11 += 11;
                acesCount++;
            } else {
                cardSumAceAs1or11 += card.getRank().getValue();
            }

        }

        // if cards sum exceeds 21 and has Ace in the set
        while (cardSumAceAs1or11 > 21 && acesCount > 0) {
            cardSumAceAs1or11 -= 10;
            acesCount--;
        }

        participant.setCardsSum(cardSumAceAs1or11);

    }

    /**
     * Used to display in the participants hand.
     * 
     * @param participant
     *            participant of the game
     * @param showFirstCard
     *            flag to show first card
     * @return the cards string
     */
    public String displayCardsInHand(Participant participant,
            boolean showFirstCard) {
        StringBuilder cardsString = new StringBuilder("\t");

        if (participant.getParticipantType() == ParticipantType.DEALER) {
            cardsString.append(participant.getParticipantType().name());
        } else {
            cardsString.append(participant.getName());
        }

        cardsString.append("'s cards ");
        cardsString.append("[");
        int numberOfcards = participant.getCardsInHand().size();

        for (int i = 0; i < numberOfcards; i++) {

            if (i == 0 && !showFirstCard) {
                cardsString.append("************");
            } else {
                cardsString.append(participant.getCardsInHand().get(i)
                        .toString());
            }
            if (i < numberOfcards - 1) {
                cardsString.append(", ");
            }
        }

        cardsString.append("]");

        System.out.println(cardsString.toString());

        return cardsString.toString();
    }
}