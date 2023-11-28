import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

public class Hand {


    private ArrayList<Card> hand; // The bai trong tay 
    
    public Hand() {
        hand = new ArrayList<Card>();
    }

    public void takeCardFromDeck(Deck deck){
        hand.add(deck.draw());
        
  }
    // co tac dung la lay bai tu deck roi add vao hand
    public void discardedHand(Deck discard){

        discard.addCards(hand);

        hand.clear();
    }
}


