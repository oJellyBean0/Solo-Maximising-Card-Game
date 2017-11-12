package nmmu.wrap301;

/**
 * Created by s2133 on 2017/02/26.
 */
public class Card {
    char suit;
    String rank;

    /**
     * Constructor for the card class
     * @param suit is the suit of the card
     * @param rank is the rank value of the card
     */
    public Card(char suit, String rank){
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * This method gets the point value of a card
     * @return The point value of a card is returned
     */
    public int getCardValue(){
        switch (rank){
            case "Ace": return 11;
            case "Jack":
            case "Queen":
            case "King": return 10;
            case "Joker": return 20;
            //returns the card integer value if it is not higher than 10
            default: return Integer.parseInt(rank);
        }
    }

    /**
     * Creates a clone of a card with the same suit and value
     * @return The clone of the card is returned
     */
     public Card clone(){
         //cloning the cards suit and rank
        return new Card(suit,rank);
     }




}
