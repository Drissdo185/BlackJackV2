import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

public class Hand {


    private ArrayList<Card> hand; // The bai trong tay 
    
    public Hand() {
        hand = new ArrayList<Card>();
    }

    public void takeCard(Card card) {
        hand.add(card);
    }
}


