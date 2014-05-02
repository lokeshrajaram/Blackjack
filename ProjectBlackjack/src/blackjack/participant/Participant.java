package blackjack.participant;

import java.util.ArrayList;
import java.util.List;

import blackjack.card.Card;

/**
 * Used to represent a participant in the blackjack game. A participant can be a
 * player or a dealer.
 * 
 * @author Lokesh Rajaram
 * @see Card
 */
public class Participant {

    private final String name;
    private final String nickName;
    private final ParticipantType participantType;
    private long chips;
    private int cardsSum;
    private List<Card> cardsInHand;

    /**
     * An enum to enforce type safety for participants in the game.
     */
    public enum ParticipantType {
        PLAYER, DEALER;
    }

    /**
     * Used to construct a participant.
     * 
     * @param name
     *            the name of the player or dealer
     * @param participantType
     *            the type of participant
     */
    public Participant(String name, ParticipantType participantType) {
        this(name, name, participantType);
    }

    private Participant(String name, String nickName, ParticipantType playerType) {
        this.name = name;
        this.nickName = nickName;
        this.participantType = playerType;
        this.cardsInHand = new ArrayList<Card>();
        this.chips = 100;
    }

    /**
     * Used to get the participant name.
     * 
     * @return name of the participant
     */
    public String getName() {
        return name;
    }

    /**
     * Used to get the nick name of the participant.
     * 
     * @return nick name of the participant
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Used to get the participant type
     * 
     * @return type of the participant
     */
    public ParticipantType getParticipantType() {
        return participantType;
    }

    /**
     * Used to get the chips count of the player.
     * 
     * @return the chips of the player
     */
    public long getChips() {
        return chips;
    }

    /**
     * Used to set the chips count of the player.
     * 
     * @param chips
     *            the chips count of the player
     */
    public void setChips(long chips) {
        this.chips = chips;
    }

    /**
     * Used to get the sum of cards in hand of the participant.
     * 
     * @return the sum of cards in hand
     */
    public int getCardsSum() {
        return cardsSum;
    }

    /**
     * Used to set the sum of cards in the hand of participant.
     * 
     * @param cardsSum
     *            the cards sum of the participant
     */
    public void setCardsSum(int cardsSum) {
        this.cardsSum = cardsSum;
    }

    /**
     * Used to get the lsit of cards in participants hand.
     * 
     * @return list of cards
     */
    public List<Card> getCardsInHand() {
        return cardsInHand;
    }

    /**
     * used to set the list of cards in players hand.
     * 
     * @param cardsInHand
     *            a list of cards to be set for the player
     */
    public void setCardsInHand(List<Card> cardsInHand) {
        this.cardsInHand = cardsInHand;
    }

}