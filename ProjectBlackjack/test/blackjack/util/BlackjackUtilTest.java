package blackjack.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import blackjack.participant.Participant;
import blackjack.participant.Participant.ParticipantType;

/**
 * Test class for testing BlackjackUtil related implementation.
 * 
 * @author Lokesh Rajaram
 */
public class BlackjackUtilTest {

    @Test
    public void testGetnDeck() {
        BlackjackUtil util = new BlackjackUtil(5);
        assertFalse(util.getnDeck().equals(null));
        assertEquals("Must be 260", 260, util.getnDeck().getnDeck().length);
    }

    @Test
    public void testReInitializeNDeck() {
        BlackjackUtil util = new BlackjackUtil(4);
        assertFalse(util.getnDeck().equals(null));
        assertEquals("Must be 208", 208, util.getnDeck().getnDeck().length);
    }

    @Test
    public void testIssueCard() {
        BlackjackUtil util = new BlackjackUtil(4);
        Participant player = new Participant("Lokee", ParticipantType.PLAYER);

        util.issueCard(player);
        util.issueCard(player);
        util.issueCard(player);
        util.issueCard(player);
        util.issueCard(player);

        assertEquals("Must be 5", 5, player.getCardsInHand().size());
    }

    @Test
    public void testDisplayCardsInHand() {
        BlackjackUtil util = new BlackjackUtil(4);
        Participant player = new Participant("Lokee", ParticipantType.PLAYER);

        util.issueCard(player);

        assertFalse(util.displayCardsInHand(player, true).equals(null));
    }

}