package blackjack.participant;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import blackjack.card.Card;
import blackjack.card.Card.Rank;
import blackjack.card.Card.Suit;
import blackjack.participant.Participant.ParticipantType;
import blackjack.util.BlackjackUtil;

/**
 * Test class for testing Participant related implementation.
 * 
 * @author Lokesh Rajaram
 */
public class ParticipantTest {

    @Test
    public void testGetName() {
        Participant player = new Participant("Lokesh", ParticipantType.PLAYER);
        assertEquals("Must be Lokesh", "Lokesh", player.getName());

        player = new Participant("Insight", ParticipantType.PLAYER);
        assertEquals("Must be Insight", "Insight", player.getName());
    }

    @Test
    public void testGetNickName() {
        Participant player = new Participant("Lokesh", ParticipantType.PLAYER);
        assertEquals("Must be Lokesh", "Lokesh", player.getNickName());

        player = new Participant("Insight", ParticipantType.PLAYER);
        assertEquals("Must be Insight", "Insight", player.getNickName());
    }

    @Test
    public void testGetPlayerType() {
        Participant player = new Participant("Lokesh", ParticipantType.PLAYER);
        assertEquals("Must be Lokesh", ParticipantType.PLAYER,
                player.getParticipantType());

        player = new Participant("Dealer", ParticipantType.DEALER);
        assertEquals("Must be Dealer", ParticipantType.DEALER,
                player.getParticipantType());
    }

    @Test
    public void testGetChips() {
        Participant player = new Participant("Lokesh", ParticipantType.PLAYER);
        assertEquals("Must be 100", 100, player.getChips());

        player.setChips(40);
        assertEquals("Must be 40", 40, player.getChips());
    }

    @Test
    public void testSetChips() {
        Participant player = new Participant("Lokesh", ParticipantType.PLAYER);
        assertEquals("Must be 100", 100, player.getChips());

        player.setChips(69);
        assertEquals("Must be 69", 69, player.getChips());
    }

    @Test
    public void testGetCardsSum() {

        BlackjackUtil util = new BlackjackUtil(2);

        Participant player = new Participant("Lokesh", ParticipantType.PLAYER);

        List<Card> cardsInHand = new ArrayList<Card>();

        Card card1 = new Card(Suit.Spades, Rank.Eight);
        Card card2 = new Card(Suit.Hearts, Rank.Ace);

        cardsInHand.add(card1);
        cardsInHand.add(card2);

        player.setCardsInHand(cardsInHand);
        util.setSumOfCardsInHand(player);
        assertEquals("Must be 19", 19, player.getCardsSum());

        Card card3 = new Card(Suit.Diamonds, Rank.Three);
        cardsInHand.add(card3);
        player.setCardsInHand(cardsInHand);
        util.setSumOfCardsInHand(player);
        assertEquals("Must be 12", 12, player.getCardsSum());

        Card card4 = new Card(Suit.Clubs, Rank.Jack);
        cardsInHand.add(card4);
        player.setCardsInHand(cardsInHand);
        util.setSumOfCardsInHand(player);
        assertEquals("Must be 22", 22, player.getCardsSum());

        Card card5 = new Card(Suit.Spades, Rank.Five);
        cardsInHand.add(card5);
        player.setCardsInHand(cardsInHand);
        util.setSumOfCardsInHand(player);
        assertEquals("Must be 27", 27, player.getCardsSum());

        Card card6 = new Card(Suit.Hearts, Rank.Nine);
        cardsInHand.add(card6);
        player.setCardsInHand(cardsInHand);
        util.setSumOfCardsInHand(player);
        assertEquals("Must be 36", 36, player.getCardsSum());

        Card card7 = new Card(Suit.Diamonds, Rank.Ace);
        cardsInHand.add(card7);
        player.setCardsInHand(cardsInHand);
        util.setSumOfCardsInHand(player);
        assertEquals("Must be 37", 37, player.getCardsSum());

    }

    @Test
    public void testSetCardsSum() {
        BlackjackUtil util = new BlackjackUtil(2);

        Participant player = new Participant("Lokesh", ParticipantType.PLAYER);

        List<Card> cardsInHand = new ArrayList<Card>();

        Card card1 = new Card(Suit.Spades, Rank.Eight);
        Card card2 = new Card(Suit.Hearts, Rank.Ace);

        cardsInHand.add(card1);
        cardsInHand.add(card2);

        player.setCardsInHand(cardsInHand);
        util.setSumOfCardsInHand(player);
        assertEquals("Must be 19", 19, player.getCardsSum());

        Card card3 = new Card(Suit.Diamonds, Rank.Two);
        cardsInHand.add(card3);
        player.setCardsInHand(cardsInHand);
        util.setSumOfCardsInHand(player);
        assertEquals("Must be 21", 21, player.getCardsSum());

        Card card4 = new Card(Suit.Clubs, Rank.Ace);
        cardsInHand.add(card4);
        player.setCardsInHand(cardsInHand);
        util.setSumOfCardsInHand(player);
        assertEquals("Must be 12", 12, player.getCardsSum());

        Card card5 = new Card(Suit.Spades, Rank.Ace);
        cardsInHand.add(card5);
        player.setCardsInHand(cardsInHand);
        util.setSumOfCardsInHand(player);
        assertEquals("Must be 13", 13, player.getCardsSum());

        Card card6 = new Card(Suit.Hearts, Rank.Ace);
        cardsInHand.add(card6);
        player.setCardsInHand(cardsInHand);
        util.setSumOfCardsInHand(player);
        assertEquals("Must be 14", 14, player.getCardsSum());

        Card card7 = new Card(Suit.Diamonds, Rank.Ace);
        cardsInHand.add(card7);
        player.setCardsInHand(cardsInHand);
        util.setSumOfCardsInHand(player);
        assertEquals("Must be 15", 15, player.getCardsSum());
    }

    @Test
    public void testGetCardsInHand() {
        Participant player = new Participant("Lokesh", ParticipantType.PLAYER);

        List<Card> cardsInHand = new ArrayList<Card>();

        Card card1 = new Card(Suit.Spades, Rank.Eight);
        Card card2 = new Card(Suit.Hearts, Rank.Ace);

        cardsInHand.add(card1);
        cardsInHand.add(card2);

        player.setCardsInHand(cardsInHand);

        assertEquals("Must be 2", 2, player.getCardsInHand().size());
    }

    @Test
    public void testSetCardsInHand() {
        Participant player = new Participant("Lokesh", ParticipantType.PLAYER);

        List<Card> cardsInHand = new ArrayList<Card>();

        Card card1 = new Card(Suit.Spades, Rank.Eight);
        Card card2 = new Card(Suit.Hearts, Rank.Ace);

        cardsInHand.add(card1);
        cardsInHand.add(card2);

        player.setCardsInHand(cardsInHand);

        assertEquals("Must be 2", 2, player.getCardsInHand().size());

        Card card3 = new Card(Suit.Hearts, Rank.Ace);
        cardsInHand.add(card3);
        player.setCardsInHand(cardsInHand);

        assertEquals("Must be 3", 3, player.getCardsInHand().size());
    }

}