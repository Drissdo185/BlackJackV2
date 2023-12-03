import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
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
