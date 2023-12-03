import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> deck;

    
    public Deck(){
        deck = new ArrayList<Card>();
        for (int m = 0; m < 4; m++) {
            for (int n = 0; n < 13; n++) {
                if (n == 0) {
                    Card card = new Card(m, n, 11); // number "11" represents Ace
                    deck.add(card);
                }
                else if (n >= 10) {
                    Card card = new Card(m, n, 10); // number "10" means Jack, Queen and King
                    deck.add(card);
                } else{
                    Card card = new Card(m, n, n + 1);
                    deck.add(card);
                }
            }
        }
    }

    
        // shuffle deck
        public void shuffleDeck() {
            Collections.shuffle(deck);
        }
        public Card getCard(int i) {
            return deck.get(i);
        }
        public Card removeCard(int i) {
            return deck.remove(i);
        }
    }

