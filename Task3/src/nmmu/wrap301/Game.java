package nmmu.wrap301;

import java.io.*;
import java.util.*;

/**
 * Created by s2133 on 2017/02/26.
 */
public class Game {
    Deck newDeck = new Deck();
    ArrayList<Card> Player1 = new ArrayList<>();
    ArrayList<Card> Computer = new ArrayList<>();
    ArrayList<Card> tempMatch = new ArrayList<>();
    ArrayList<RoundObject> rounds = new ArrayList<>();
    int maxNumOfRounds = 9;
    int roundCounter = 1;
    int score = 0;
    int totalRounds;

    /**
     * Main entry point of the game in order to start a round
     */
    public void runGame() {
        newDeck.buildDeck();
        newDeck.shuffleDeck();
        Menu gameControls = gameControls();
        gameControls.run();


    }

    /**
     * The menu displayed before each round allowing the player to play next round, save, rewind, restart and exit
     *
     * @return The menu is returned to be displayed
     */
    public Menu gameControls() {
        Menu gameControls = new Menu("Game Controls", false);

        gameControls.addChoice(new Pair<>("Play a round ", () -> roundOfGame()));
        gameControls.addChoice(new Pair<>("Save game ", () -> save()));
        gameControls.addChoice(new Pair<>("Rewind ", () -> rewind()));
        gameControls.addChoice(new Pair<>("Restart ", () -> restartGame()));

        return gameControls;
    }

    /**
     * A round of the game is run in this method
     */
    public void roundOfGame() {

        if (roundCounter <= maxNumOfRounds) {
            dealCards();
            totalRounds = rounds.size();
            RoundObject round = new RoundObject(roundCounter, newDeck.clone(), score, totalRounds);
            rounds.add(round);
            //continues until the player and computer's hands are empty
            while (Player1.size() != 0) {
                System.out.println("\nRound " + roundCounter + "\n");
                displayCards();
                Scanner scanner = new Scanner(System.in);
                int index;
                boolean fail=false;
                do {
                    if (fail) {
                        System.out.println("Invalid option - Please enter a valid option");
                    }
                    System.out.println("\nEnter card number of player to play: ");
                    index = scanner.nextInt();
                    scanner.nextLine();
                    fail=true;
                } while (index > Player1.size()||index<=0);

                runPlayerCard(index - 1);

                fail=false;
                do {
                    if (fail) {
                        System.out.println("Invalid option - Please enter a valid option");
                    }
                    System.out.println("\nEnter card number of computer to play: ");
                    index = scanner.nextInt();
                    scanner.nextLine();
                    fail=true;
                } while (index > Computer.size()||index<=0);

                runComputerCard(index - 1);
                Card playerChosenCard = tempMatch.get(0);
                Card computerChosenCard = tempMatch.get(1);
                matchCards(playerChosenCard, computerChosenCard);
                tempMatch.clear();
            }
            clearHands();
            roundCounter++;

        } else {
            System.out.println("End of game");
            System.out.println("Final score: " + score+ "\n");
        }


    }

    /**
     * Displays the cards in the players hands and the computers hands
     */
    public void displayCards() {
        System.out.println("Player cards:");
        for (int i = 0; i < Player1.size(); i++) {
            System.out.println(i + 1 + ": " + getPlayerCardText(i));
        }
        System.out.println("Computer cards:");
        for (int i = 0; i < Computer.size(); i++) {
            System.out.println(i + 1 + ": " + getComputerCardText(i));
        }
    }

    /**
     * Deals with a specific card in the player's hand
     *
     * @param index the position of the card that is dealt with
     */
    public void runPlayerCard(int index) {
        Card card = getPlayerCard(index);
        //Clears the temp array so that a new match can occur between two new cards
        clearTemp();
        addCardToTemp(card);
        removePlayerCardFromHand(card);
        //runComputerCard(index);
    }

    /**
     * Deals with a specific card in the computer's hand
     *
     * @param index the position of the card that is dealt with
     */
    public void runComputerCard(int index) {
        Card card = getComputerCard(index);
        addCardToTemp(card);
        removeComputerCardFromHand(card);

    }

    /**
     * Clears the hands of the player and computer
     */
    public void clearHands() {
        Player1.clear();
        Computer.clear();
    }

    /**
     * Deals the cards to the player and computer
     */
    public void dealCards() {
        for (int i = 0; i < 3; i++) {
            Player1.add(newDeck.getTopCard());
        }
        for (int i = 0; i < 3; i++) {
            Computer.add(newDeck.getTopCard());
        }
    }

    /**
     * Matches the two cards passed through and calculates the score
     *
     * @param playerCard   The player's card passed through
     * @param computerCard The computer's cars passed through
     */
    public void matchCards(Card playerCard, Card computerCard) {
        if (playerCard.suit == computerCard.suit || playerCard.getCardValue() >= computerCard.getCardValue() || playerCard.rank.equals("Joker")) {
            increaseScore(playerCard.getCardValue());
        } else if (computerCard.rank.equals("Joker") && computerCard.rank.equals(playerCard.rank))
            increaseScore(playerCard.getCardValue());


        else decreaseScore(playerCard, computerCard);


        System.out.println("\nTotal score = " + score + "\n");

        //removes the card from the player and computer's hand
        Player1.remove(playerCard);
        Computer.remove(computerCard);
    }

    /**
     * Increases the score by the given amount of points
     *
     * @param addPoints the points to add to the score
     */
    public void increaseScore(int addPoints) {
        score += addPoints;
        System.out.println("\nYou win :)");
        System.out.println("Score increased by: " + addPoints);

    }

    /**
     * Decreases the score by the given amount of points from the cards sent through
     *
     * @param playerCard   The card by which the points need to be decreased
     * @param computerCard The card by which the points need to be decreased
     */
    public void decreaseScore(Card playerCard, Card computerCard) {
        score -= playerCard.getCardValue();
        score -= computerCard.getCardValue();
        System.out.println("\nYou lose :(");
        System.out.println("Score decrease by: " + (playerCard.getCardValue() + computerCard.getCardValue()));
    }

    /**
     * Gets the rank and suit of the player card at a certain position in the player's hand
     *
     * @param index The index at which the card is in the player's hand
     * @return
     */
    public String getPlayerCardText(int index) {
        return Player1.get(index).suit + " " + Player1.get(index).rank;
    }

    /**
     * Gets the rank and suit of the computer card at a certain position in the computer's hand
     *
     * @param index The index at which the card is in the computer's hand
     * @return
     */
    public String getComputerCardText(int index) {
        return Computer.get(index).suit + " " + Computer.get(index).rank;
    }

    /**
     * Clears the temp array to allow for new cards to be entered to be matched
     */
    public void clearTemp() {
        tempMatch.clear();
    }

    /**
     * Get's the player card at a given index
     *
     * @param index the position of the card in the player's hand
     * @return returns the card at a given index in the player's hand
     */
    public Card getPlayerCard(int index) {
        return Player1.get(index);
    }

    /**
     * Get's the computer card at a given index
     *
     * @param index the position of the card in the computer's hand
     * @return returns the card at a given index in the computer's hand
     */
    public Card getComputerCard(int index) {
        return Computer.get(index);
    }

    /**
     * Adds the given card to the temp arrayList
     *
     * @param newCard The card to be added
     */
    public void addCardToTemp(Card newCard) {
        tempMatch.add(newCard);
    }

    /**
     * Removes a given card from the player's hand
     *
     * @param cardToRemove card to be removed
     */
    public void removePlayerCardFromHand(Card cardToRemove) {
        Player1.remove(cardToRemove);
    }

    /**
     * Removes a given card from the computer's hand
     *
     * @param cardToRemove card to be removed
     */
    public void removeComputerCardFromHand(Card cardToRemove) {
        Computer.remove(cardToRemove);
    }

    /**
     * Rewinds the game by the set number given from the user and then allows the game to continue
     */
    public void rewind() {
        System.out.println("Enter number of games to rewind");
        Scanner scanner = new Scanner(System.in);
        int rewindNum = scanner.nextInt();
        scanner.nextLine();
        if (rewindNum >= rounds.size()) {
            System.out.println("You rewound to the start of the game");
            score = 0;
            roundCounter = 1;
            roundOfGame();
        } else {
            score = rounds.get(roundCounter - rewindNum - 1).score;
            for (int i = 0; i < rewindNum; i++) {
                rounds.remove(rounds.size() - 1);
            }
            newDeck = rounds.get(rounds.size() - 1).currentDeck;

            score = score - (rewindNum * 5);
            System.out.println("5 points is deducted for every round gone back");
            System.out.println("You lost " + (rewindNum * 5) + " points for rewinding\n");
            roundCounter = rounds.get(rounds.size() - 1).roundNum + 1;

        }
    }

    /**
     * Saves the game to a file. Enough data is saved to allow for the game to be loaded later
     */
    public void save() {
        try {
            File file = new File("Task3/Game.txt");
            PrintWriter writer = new PrintWriter(file);
            writer.println(rounds.size());
            for (int i = 0; i < rounds.size(); i++) {
                RoundObject round = rounds.get(i);
                int roundNumber = i + 1;
                Deck currentDeck = round.currentDeck;
                int score = round.score;
                for (int k = 0; k < totalRounds; k++) {
                    writer.println(roundNumber);
                    writer.println(score);
                    writer.println(currentDeck.size());
                    for (int j = 0; j < currentDeck.size(); j++) {
                        writer.println(currentDeck.deck.get(j).suit);
                        writer.println(currentDeck.deck.get(j).rank);
                    }
                }

            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the game from a file and allows the user to continue with the game
     */
    public void load() {
        try {
            RoundObject newRound;
            rounds.clear();
            Scanner scanner = new Scanner(new File("Task3/Game.txt"));
            int totalRounds = Integer.parseInt(scanner.nextLine());
            for (int j = 0; j < totalRounds; j++) {
                int roundNum = Integer.parseInt(scanner.nextLine());
                int fileScore = Integer.parseInt(scanner.nextLine());
                int numOfCards = Integer.parseInt(scanner.nextLine());
                ArrayList<Card> deck = new ArrayList<>();
                for (int i = 0; i < numOfCards; i++) {
                    String suitTemp = scanner.nextLine();
                    char suit = suitTemp.charAt(0);
                    String rank = scanner.nextLine();
                    Card newCard = new Card(suit, rank);
                    deck.add(newCard);
                }
                newRound = new RoundObject(roundNum, new Deck(deck), fileScore, totalRounds);

                rounds.add(newRound);
                if (j == totalRounds - 1) {
                    newDeck = new Deck(deck);
                    score = fileScore;
                    roundCounter = roundNum + 1;
                    Menu gameControls = gameControls();
                    gameControls.run();
                }

            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("No saved game found");
        }

    }

    /**
     * Restarts the game from the beginning
     */
    public void restartGame() {
        score = 0;
        roundCounter = 1;
        System.out.println("The game is restarted");
        roundOfGame();
    }

}
