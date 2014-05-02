package blackjack;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import blackjack.card.CardTest;
import blackjack.card.KnuthShuffleTest;
import blackjack.card.NDeckTest;
import blackjack.participant.ParticipantTest;
import blackjack.util.BlackjackUtilTest;

/**
 * Test suite for all test cases together for convenience.
 * 
 * @author Lokesh Rajaram
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ CardTest.class, KnuthShuffleTest.class, NDeckTest.class,
        ParticipantTest.class, BlackjackUtilTest.class })
public class BlackjackTestSuite {
    // Refer individual test classes for test related details
}
