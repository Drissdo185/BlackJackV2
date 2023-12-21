import java.util.ArrayList;
//import java.util.Collection;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> deck;

    public Deck(){
        deck = new ArrayList<Card>();
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 13; j++){
                if(j==0){
                    Card card = new Card(i,j,11); //11 la ace
                    deck.add(card);
                }
                else if(j >= 10){
                    Card card = new Card(i,j, 10); // 10 la JQK
                    deck.add(card);
                }else{
                    Card card = new Card(i,j,j+1);
                    deck.add(card);
                }
            }
        }
    }

        // xao bai
        public void shuffleDeck() {
            Collections.shuffle(deck);
        }
        public Card getCard(int i){
            return deck.get(i);
        }
        public Card removeCard(int i){
            return deck.remove(i);
        }
    }

