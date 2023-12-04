import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
<<<<<<< HEAD
import java.io.IOException;
import java.awt.Graphics2D; 
=======
import javax.swing.*;


public class Card {
    private int category;
    private int level;
    private int amount;
    private int xPos;
    private int yPos;

    
    // default constructor
    public Card() {
        category = 0;
        level = 0;
        amount = 0;
    }
>>>>>>> 30df52101fe92486f8ab4d369b62b8ec6e57a78d

public class Card {  
  private int suit; 
  private int rank; 
  private int value; 
  private int xPosition; 
  private int yPosition; 
  
  public Card() { 
    suit = 0;
    rank = 0; 
    value = 0; 
  }
  
  public Card(int s, int r, int v) { 
    suit = s;
    rank = r;
    value = v;
  }
  
  public int getSuit() { 
    return suit; 
  }
  public int getRank() { 
    return rank;
  }
  public int getValue() { 
    return value; 
  }
  
  public void printCard(Graphics2D g2, boolean dealerTurn, boolean faceDown, int cardNumber) throws IOException {
    
<<<<<<< HEAD
    BufferedImage deckImg = ImageIO.read(new File("images/cardSpriteSheet.png")); 
    int imgWidth = 950;
    int imgHeight = 392; 
    BufferedImage[][] cardPictures = new BufferedImage[4][13]; 
    BufferedImage backOfACard = ImageIO.read(new File("images/backsideOfACard.jpg")); 
    for (int c = 0; c < 4; c++) { 
      for (int r = 0; r < 13; r++) {
        cardPictures[c][r] = deckImg.getSubimage(r*imgWidth/13, c*imgHeight/4, imgWidth/13, imgHeight/4);  
      }
    }
    
    if (dealerTurn) { 
      yPosition = 75;
    }
    else { 
      yPosition = 400;
    }
    
    xPosition = 500 + 75*cardNumber; 
    
    if (faceDown) {
      g2.drawImage(backOfACard, xPosition, yPosition, null ); 
    }
    else {
      g2.drawImage(cardPictures[suit][rank], xPosition, yPosition, null); 
  }
}}
=======
    public Card(int suit, int rank, int value){
        this.category = category;
        this.level = level;
        this.amount = amount;        
    }

    
    public int getCategory() {
        return category;
    }
    public int getLevel() {
        return level;
    }
    public int getAmount() {
        return amount;
    }

    /* show picture of card
    * dealerTurn: check dealer's turn
    * faceDown: check cards are faceup or facedown
    */

    public void printCard(Graphics2D g2, boolean dealerTurn, boolean faceDown, int cardNumber)
            throws IOException {

        BufferedImage deckImg = ImageIO.read(new File("images/cardSpriteSheet.png")); // read image from file


        int imgWidth = 950; // width of sprite sheet image in pixels
        int imgHeight = 392; // height of sprite sheet image in pixels


        BufferedImage[][] cardPictures = new BufferedImage[4][13];
        BufferedImage backOfACard = ImageIO.read(new File("images/backsideOfACard.jpg"));

        /* number "4" represents the Spade, Diamond, Club, and Heart
         * number "13" means Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen and King
         */
                
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 13; r++) {
               cardPictures[c][r] = deckImg.getSubimage(r*imgWidth/13, c*imgHeight/4, imgWidth/13, imgHeight/4); // assign relative card pictures to 2-D array
              }
            }
        
        if (dealerTurn){
            yPos = 75;
        } else{
            yPos = 400;
        }

        xPos = 500+ 75*cardNumber;

        if (faceDown){
            g2.drawImage(cardPictures[0][0], xPos, yPos, null);
        } else{
            g2.drawImage(cardPictures[category][level], xPos, yPos, null);
        }
    }
}
>>>>>>> 30df52101fe92486f8ab4d369b62b8ec6e57a78d
