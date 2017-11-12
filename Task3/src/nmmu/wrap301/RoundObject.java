package nmmu.wrap301;


/**
 * Created by s2133 on 2017/03/05.
 */
public class RoundObject {
    int score, roundNum, totalRounds;
    Deck currentDeck;

    /**
     * Constructor for a special object for every round that is saved in order to be able or load a saved game and rewind
     * @param roundNum The number of the current round
     * @param deck The deck of the current round
     * @param score The score of the current round
     * @param totalRounds The total number of rounds in the game
     */
    public RoundObject(int roundNum, Deck deck, int score, int totalRounds){
        this.roundNum = roundNum;
        this.score = score;
        currentDeck = deck;
        this.totalRounds = totalRounds;
    }
}
