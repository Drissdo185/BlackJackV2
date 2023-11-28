import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class Person {


    private Hand hand;
    private String name;
    

    public Person(){

        this.hand = new Hand();
        this.name = ""; // empty string
    }

    //Setter and Getter
    public void setHand(Hand hand){
        this.hand = hand;
    }

    public Hand getHand(){
        return hand;
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void showHand(JLabel[] cardPics){

        System.out.println(this.name + "'s hand looks like this:");
        System.out.println(this.hand + " Valued at: " + this.hand.calculatedValue());


        //iterate through each card, update pic, hide remaining
        for(int i = 0; i < 11; i++){
            cardPics[i].setVisible(false);
        }
        for(int i = 0; i < this.hand.getHandSize(); i++){
            String rank = this.hand.getCard(i).getRank().toString();
            String suit = this.hand.getCard(i).getSuit().toString();
            String filename = rank + suit + ".png";
            cardPics[i].setIcon(new ImageIcon(new ImageIcon(Game.IMAGE_DIR+filename).getImage().getScaledInstance(Game.CARD_WIDTH, Game.CARD_HEIGHT, Image.SCALE_SMOOTH)));
            cardPics[i].setVisible(true);
        }

    }
}
