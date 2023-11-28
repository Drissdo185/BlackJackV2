public class Card implements Comparable<Card> {

    private Suit suit;
    private Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;

    }

    public Card(Card card) {
        this.suit = card.getSuit();
        this.rank = card.getRank();

    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public int getValue() {
        return rank.rankValue;
    }


    // lay ten cua quan bai

    public String toString() {
        return rank.rankName + " of " + suit.suitName + " (" + this.getValue() + ")";

        //example : Ace of Spades (11)
    }


    // so sanh 2 quan bai va sap xep theo thu tu giam dan
    @Override
    public int compareTo(Card card) {
        return card.getValue() - this.getValue();
    }





    
}
