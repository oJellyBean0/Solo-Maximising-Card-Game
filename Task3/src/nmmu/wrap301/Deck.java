package nmmu.wrap301;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by s2133 on 2017/02/26.
 */
public class Deck {
    static char[] suits = new char[]{'♤','♡','♢','♧'};
    ArrayList<Card> deck = new ArrayList<>();

    public Deck(ArrayList<Card> deck){
        this.deck = deck;
    }

    public Deck(){}

    /**
     * The deck is build here with all the cards needed including two jokers
     */
    public void buildDeck(){
        for (int i = 0; i <suits.length ; i++) {
            for (int j = 1; j <=13; j++) {
                switch (j){
                    case 1: deck.add(new Card(suits[i],"Ace"));
                        break;
                    case 11: deck.add(new Card(suits[i],"Jack"));
                        break;
                    case 12: deck.add(new Card(suits[i],"Queen"));
                        break;
                    case 13: deck.add(new Card(suits[i],"King"));
                        break;
                    default: deck.add(new Card(suits[i],Integer.toString(j)));
                }
            }
        }
        // adding the jokers
        deck.add(new Card('▧', "Joker"));
        deck.add(new Card('▨', "Joker"));
    }

    /**
     * The deck is shuffled randomly
     */
    public void shuffleDeck(){
        Random rand = new Random();
        ArrayList<Card> temp = new ArrayList<>();
        //goes until there are no more cards left in the deck
        while (deck.size()>0) {
            int size = deck.size();
            int random = rand.nextInt(size);
            Card removed = deck.remove(random);
            temp.add(removed);
        }
        deck = temp;
    }

    /**
     * The top card of the deck is removed and returned
     * @return The top card of the deck is returned
     */
    public Card getTopCard(){
        return  deck.remove(0);
    }

    /**
     * The size of the deck
     * @return the size of the deck is returned
     */
    public int size(){
        return deck.size();
    }

    /**
     * The deck is cloned here and returned
     * @return A clone of the deck is returned
     */
    public Deck clone(){
        ArrayList<Card> deckClone = new ArrayList<>();
        for (int i = 0; i < deck.size(); i++) {
            deckClone.add(deck.get(i).clone());

        }
        return new Deck(deckClone);
    }



}
