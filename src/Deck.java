import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

public class Deck {

    private ArrayList<Card> deck;


    public Deck(){
        deck = new ArrayList<Card>();
    }


    public Deck(Deck c){
        Collections.copy(this.deck, c.deck);
    }
    
    

    public Deck(boolean makeDeck){
        deck = new ArrayList<Card>();
        if(makeDeck){
            //Go through all the suits
            for(Suit suit : Suit.values()){
                //Go through all the ranks
                for(Rank rank : Rank.values()){
                    //add a new card containing each iterations suit and rank
                    deck.add(new Card(suit, rank));
                }
            }
        }
    }


    // tra lai so luong bai trong deck
    public String toString(){
        
        String output = "";

        for(Card card: deck){
            output += card;
            output += "\n";
        }
        return output;
    }



    //cards dc add vao deck
    public void addCards(Card cards){
        deck.add(cards);
    }
    public void addCards(ArrayList<Card> cards){
        deck.addAll(cards);
    }

    //xao bai
    public void shuffle(){
        Collections.shuffle(deck, new Random());
    }


    // tra la bai lay ra tu deck

    public Card draw(){
        return deck.remove(0);
    }

    public boolean hasCard(){
        if(deck.size() > 0){
            return true;
        }else{
            return false;
        }
    }
    //The number of cards left in the deck     
    public int cardsLeft(){
        return deck.size();
    }

    //return cái arrylist
    public ArrayList<Card> getCards() {
        return deck;
    }

    //Xoa bai trong Deck
    public void emptyDeck(){
        deck.clear();
    }

    public void reloadDeckFromDiscard(Deck discard){
        this.addCards(discard.getCards());
        this.shuffle();
        discard.emptyDeck();
        System.out.println("Ran out of cards, creating new deck from discard pile & shuffling deck.");
    }






    


    
}
