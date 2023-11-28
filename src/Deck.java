import java.util.ArrayList;
import java.util.Collections;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

public class Deck {

    private ArrayList<Card> deck;


    public Deck(){
        deck = new ArrayList<Card>();
    }


    public Deck(Deck c){
        Collections.copy(this.deck, c.deck);
    }


    


    
}
