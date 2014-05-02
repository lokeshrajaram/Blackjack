package blackjack;

import java.util.ArrayList;
import java.util.Scanner;

import blackjack.card.Card;
import blackjack.participant.Participant;
import blackjack.participant.Participant.ParticipantType;
import blackjack.util.BlackjackUtil;

/**
 * Blackjack or game of 21 is popular game and this implementation provides a
 * way to play the game with Hit and Stand option only for now.
 * 
 * Notice of Rules
 * <ul>
 * <li>Face cards are worth 10
 * <li>Number cards are worth their number
 * <li>Aces are worth 1 or 11
 * <li>Dealer will hit until cards total to 17 or higher
 * <li>Bet can only be a whole number between 1(inclusive) and chips player
 * have(inclusive)
 * <li>Each player starts with $100 chips
 * <li>If a player has no more chips, application has to be restarted to play
 * more
 * <li>If the number of unused cards in the deck goes below 14, the deck is
 * re-initialized
 * <li>
 * <li>If you win a hand, you get double your bet otherwise you lose all you bet
 * <li>A Deal push is considered as a win for the player for the purpose of win
 * percentage calculation
 * <li>Provides a statistics of the games played by the player
 * <li>As part of game setup user can choose how many decks he wants in the
 * game, which is between 1(inclusive) and 8(inclusive)
 * </ul>
 * 
 * @author Lokesh Rajaram
 */
public class Blackjack {

    private final String PLAYER_WINS = "$$$ PLAYER WINS $$$";
    private final String DEALER_WINS = "$$$ DEALER WINS $$$";
    private final String DEAL_PUSHED = "!!! DEAL PUSHED !!!";

    private final BlackjackUtil blackjackUtil;
    private final Participant player;
    private final Participant dealer;

    private long gameCount;
    private long playerWinCount;
    private long dealPushedCount;

    private static Scanner scanner = new Scanner(System.in);

    /**
     * An enum to enforce game options to Hit or Stand only.
     */
    private enum Option {
        H, S;
    }

    /**
     * An enum to enforce play more to yes or no only.
     */
    private enum PlayMore {
        Y, N;
    }

    /**
     * The main function that launches the game.
     * 
     * @param args
     *            runtime parameters which this game does not expect any
     */
    public static void main(String[] args) {

        // used to initialize the game
        Blackjack blackjack = initializeGame();

        // used to print welcome message to player
        blackjack.printWelcomeNotice();

        // actual execution of the game happens here
        blackjack.runGame();

        // no more input read require, hence closing the scanner
        scanner.close();

        // used to print a shutdown message (statistics)
        blackjack.printShutdownNotice(blackjack.player, blackjack.gameCount,
                blackjack.playerWinCount, blackjack.dealPushedCount);

    }

    /**
     * Used for creating an instance of the blackjack and do the game setup.
     * 
     * @return and instance of the blackjack game
     */
    private static Blackjack initializeGame() {

        boolean isInitialized = Boolean.FALSE;
        int numberOfDecks = 0;
        String playerName = null;

        // game setup
        System.out.println("Let's setup the game for you to play");
        System.out.println("------------------------------------");

        do {
            try {
                System.out.println("\nHow many decks the app must have?");
                System.out
                        .println("Please enter number of decks in the range [1, 8] both 1 and 8 inclusive");
                numberOfDecks = Integer.parseInt(scanner.next());

                if (numberOfDecks >= 1 && numberOfDecks <= 8) {
                    isInitialized = Boolean.TRUE;
                }

            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid Option Provided");
            }

        } while (!isInitialized);

        System.out.println("Enter your name:");
        playerName = scanner.next();

        System.out.println("Game setup completed. Let's Play!!!");

        System.out.println("-----------------------------------\n\n");

        // create and return game isntance after game setup feature are obtained
        // from user
        return new Blackjack(numberOfDecks, playerName);

    }

    /**
     * Constructor to initialize the blackjack game.
     * 
     * @param numberOfDecks
     *            number of decks in the game
     * @param playerName
     *            name of the player taking part in the game
     */
    private Blackjack(int numberOfDecks, String playerName) {
        // Initialize game utility
        blackjackUtil = new BlackjackUtil(numberOfDecks);
        scanner = new Scanner(System.in);

        // Initialize counters
        gameCount = 0;
        playerWinCount = 0;

        // Create participants
        player = new Participant(playerName, ParticipantType.PLAYER);
        dealer = new Participant(ParticipantType.DEALER.name(),
                ParticipantType.DEALER);

        issueCards();
    }

    /**
     * Used for issuing cards to the participants. If there are not enough cards
     * the NDeck is re-initialized.
     */
    private void issueCards() {

        // checking the remaining cards in the deck
        int remainingCards = blackjackUtil.getnDeck().getnDeck().length
                - blackjackUtil.getnDeck().getIndexToServe();

        // when the number of cards in the deck goes below 14, the ndeck is
        // reinitialized
        if (remainingCards < 14) {
            System.out
                    .println("\n-----------------------------------------------------------------------------------");
            System.out
                    .println("########### Deck does not have enough cards, let's get it ready for you ###########");
            System.out
                    .println("-----------------------------------------------------------------------------------\n");
            blackjackUtil.reInitializeNDeck();
        }

        // Issue cards to participants
        blackjackUtil.issueCard(player);
        blackjackUtil.issueCard(dealer);
        blackjackUtil.issueCard(player);
        blackjackUtil.issueCard(dealer);
    }

    /**
     * Used to offer options for player to continue playing or quitting. Also,
     * collects details of the game used for computing statistics of the player.
     */
    private void runGame() {
        boolean wantToExit = Boolean.FALSE;
        boolean validOption = Boolean.TRUE;
        do {
            // if user has provided valid option then continue to play game
            if (validOption) {

                // actual game play is in here
                String result = run();

                // counter to keep track of games played
                gameCount++;

                if (result.equals(PLAYER_WINS)) {
                    // counter to keep track of player wins
                    playerWinCount++;
                } else if (result.equals(DEAL_PUSHED)) {
                    // counter to keep track of pushed deals
                    dealPushedCount++;
                }

            }

            try {
                System.out
                        .println("Do you want to play more? Enter Y for (yes) or N for (no)");
                PlayMore playMore = Enum.valueOf(PlayMore.class, scanner.next()
                        .toUpperCase());
                validOption = Boolean.TRUE;
                switch (playMore) {
                    case N:
                        wantToExit = Boolean.TRUE;
                        break;
                    case Y:
                        // if player wants to play more then we have to reset
                        // some instance variables, issue cards
                        if (player.getChips() > 0) {
                            player.setCardsInHand(new ArrayList<Card>());
                            dealer.setCardsInHand(new ArrayList<Card>());
                            player.setCardsSum(0);
                            dealer.setCardsSum(0);
                            issueCards();
                            System.out.println("\n\n-----------------");
                            System.out.println("Issuing next hand");
                            System.out.println("-----------------");
                        } else {
                            wantToExit = Boolean.TRUE;
                            // drop a message to player that he/she ran out of
                            // chips
                            if (player.getChips() == 0) {
                                System.out
                                        .println("\n\n***** You are out of chips. Restart the application to play more *****");
                            }
                        }
                        break;
                }

            } catch (IllegalArgumentException ex) {
                validOption = Boolean.FALSE;
                System.out.println("Invalid Option Provided");
            }

        } while (!wantToExit);

    }

    /**
     * Used for printing the welcome notice to the player.
     */
    private void printWelcomeNotice() {

        System.out
                .println("--------------------------------------------------------------------------------------");
        System.out
                .println("|************************************BLACKJACK***************************************|");
        System.out
                .println("--------------------------------------------------------------------------------------");
        System.out.println("\n---------------");
        System.out.println("Notice of Rules");
        System.out.println("---------------");
        System.out
                .println("1. Face cards are worth 10. Number cards are worth their number. Aces are worth 1 or 11");
        System.out
                .println("2. Dealer will hit until cards total to 17 or higher");
        System.out
                .println("3. Bet can only be a whole number between 1(inclusive) and chips player have(inclusive)");
        System.out.println("4. Each player starts with $100 chips");
        System.out
                .println("5. If you win a hand, you get double your bet otherwise you lose all you bet");
        System.out
                .println("6. A Deal push is considered as a win for the player for the purpose of win percentage calculation");
        System.out
                .println("=======================================================================================");
    }

    /**
     * used to printing the shutdown notice to the player.
     * 
     * @param player
     *            player of the game
     * @param gameCount
     *            number of games played
     * @param playerWinCount
     *            number of games player win
     * @param dealPushedCount
     *            number of games deal pushed
     */
    private void printShutdownNotice(Participant player, long gameCount,
            long playerWinCount, long dealPushedCount) {

        System.out.println("\n\n--------------------------------");
        System.out.println(player.getNickName() + "'s Game Stats");
        System.out.println("--------------------------------");
        System.out.println("Chips in Hand :: " + player.getChips());
        System.out.println("Hands Played  :: " + gameCount);
        System.out.println("Deals Pushed  :: " + dealPushedCount);
        System.out.println("Hands Lost    :: "
                + (gameCount - (dealPushedCount + playerWinCount)));
        System.out.println("Hands Won     :: " + playerWinCount);

        // for the purpose of win percentage calculation a deal pushed is
        // considered as a win for the player
        double winPercentage = ((playerWinCount + dealPushedCount) * 100d)
                / gameCount;

        System.out.println("Win Percentage:: "
                + String.format("%1$,.4f", winPercentage) + "%");

        System.out
                .println("======================================================");

        System.out
                .println("****** BYE!!! BYE!!! Come back and Play More!!! ******");

        System.out
                .println("======================================================");

    }

    /**
     * The actual game logic is implemented in this method.
     * 
     * @return the game result
     */
    private String run() {

        boolean playerStand = Boolean.FALSE;
        boolean dealerStand = Boolean.FALSE;
        boolean playerBusted = Boolean.FALSE;
        boolean dealerBusted = Boolean.FALSE;
        boolean playerBlackjack = Boolean.FALSE;
        boolean dealerBlackjack = Boolean.FALSE;
        boolean validBetProvided = Boolean.FALSE;
        long betChips = 0;
        String gameResult = null;

        // get a valid bet from the user
        do {
            try {
                System.out.println("\nPlease Enter the amount(between 1 and "
                        + player.getChips() + ") you want to bet");
                betChips = Long.parseLong(scanner.next());

                if (betChips > 0 && betChips <= player.getChips()) {
                    validBetProvided = Boolean.TRUE;
                } else {
                    System.out.println("\nYou entered an invalid amount $"
                            + betChips + " not in the range [1, "
                            + player.getChips() + "]");
                }

            } catch (IllegalArgumentException ex) {
                System.out.println("\nInvalid Bet Provided");
            }
        } while (!validBetProvided);

        // display cards in hand once a valid bet is provided
        System.out.println("\n-------------");
        System.out.println("Cards in Hand");
        System.out.println("-------------");
        blackjackUtil.displayCardsInHand(player, true);
        blackjackUtil.displayCardsInHand(dealer, false);

        // check to see if player and/or dealer has blackjack
        if (player.getCardsSum() == 21 || dealer.getCardsSum() == 21) {

            if (player.getCardsSum() == 21 && dealer.getCardsSum() == 21) {
                playerBlackjack = Boolean.TRUE;
                dealerBlackjack = Boolean.TRUE;
            } else if (player.getCardsSum() == 21) {
                playerBlackjack = Boolean.TRUE;
            } else {
                dealerBlackjack = Boolean.TRUE;
            }

        } else {
            if (player.getCardsSum() != 21) {
                while (!playerStand) {

                    Option playerOptionInput = null;
                    boolean validOptionProvided = Boolean.FALSE;

                    // get a valid game option from user
                    do {
                        try {
                            System.out
                                    .println("\nPlease Enter H for (HIT) or S for (STAND)");
                            playerOptionInput = Enum.valueOf(Option.class,
                                    scanner.next().toUpperCase());
                            validOptionProvided = Boolean.TRUE;
                        } catch (IllegalArgumentException ex) {
                            System.out.println("Invalid Option Chosen");
                        }
                    } while (!validOptionProvided);

                    // perform action as user requested
                    switch (playerOptionInput) {
                        case H:
                            System.out.println("\n***Player Hit***");
                            blackjackUtil.issueCard(player);
                            blackjackUtil.displayCardsInHand(player, true);
                            break;
                        case S:
                            System.out.println("\n***Player Stand***");
                            playerStand = Boolean.TRUE;
                            break;
                    }

                    // check to see if player busted
                    if (player.getCardsSum() > 21) {
                        playerBusted = Boolean.TRUE;
                        player.setChips(player.getChips() - betChips);
                        System.out.println("\nPlayer Busted!!!");
                        gameResult = DEALER_WINS;
                        System.out.println(gameResult);
                        break;
                    }

                    // check to see if player has blackjack
                    if (player.getCardsSum() == 21) {
                        playerBlackjack = Boolean.TRUE;
                        break;
                    }
                }
            } else {
                playerBlackjack = Boolean.TRUE;
            }

            if (!playerBusted) {

                // check to see if dealer has blackjack
                if (dealer.getCardsSum() == 21) {
                    dealerBlackjack = Boolean.TRUE;
                }

                if (!playerBlackjack) {
                    while (!dealerStand) {
                        // dealer hits until he reaches 17 or more
                        if (dealer.getCardsSum() < 17) {
                            System.out.println("\n***Dealer Hit***");
                            blackjackUtil.issueCard(dealer);
                            blackjackUtil.displayCardsInHand(dealer, false);
                        } else {
                            // dealer stands on 17 or more
                            System.out.println("\n***Dealer Stand***");
                            dealerStand = Boolean.TRUE;
                        }

                        // check to see if dealer is busted
                        if (dealer.getCardsSum() > 21) {
                            dealerBusted = Boolean.TRUE;
                            player.setChips(player.getChips() + betChips);
                            System.out.println("\nDealer Busted!!!");
                            gameResult = PLAYER_WINS;
                            System.out.println(gameResult);
                            break;
                        }

                        // check to see if dealer has blackjack
                        if (dealer.getCardsSum() == 21) {
                            dealerBlackjack = Boolean.TRUE;
                            break;
                        }

                    }
                }
            }
        }

        // if either player or dealer has blackjack
        if (playerBlackjack || dealerBlackjack) {

            if (playerBlackjack && dealerBlackjack) {
                gameResult = DEAL_PUSHED;
            } else if (playerBlackjack) {
                // player win increase his chip count
                player.setChips(player.getChips() + betChips);
                gameResult = PLAYER_WINS;
            } else {
                // player lost decrease his chip count
                player.setChips(player.getChips() - betChips);
                gameResult = DEALER_WINS;
            }
            System.out.println(gameResult);

        } else {

            // if neither player nor dealer is busted
            if (!playerBusted && !dealerBusted) {

                int playerCardsSum = player.getCardsSum();
                int dealerCardsSum = dealer.getCardsSum();

                if (playerCardsSum > dealerCardsSum) {
                    // player win increase his chip count
                    player.setChips(player.getChips() + betChips);
                    gameResult = PLAYER_WINS;
                } else if (playerCardsSum < dealerCardsSum) {
                    // player lost decrease his chip count
                    player.setChips(player.getChips() - betChips);
                    gameResult = DEALER_WINS;
                } else {
                    gameResult = DEAL_PUSHED;
                }

                System.out.println(gameResult);
            }
        }

        // print the game details for the player to see
        System.out.println("\n------------");
        System.out.println("Game Details");
        System.out.println("------------");
        System.out.println("\t" + gameResult);
        blackjackUtil.displayCardsInHand(player, true);
        blackjackUtil.displayCardsInHand(dealer, true);
        System.out.println("\t" + ParticipantType.PLAYER.name() + " has $"
                + player.getChips() + " chips");
        System.out.println();

        return gameResult;
    }
}