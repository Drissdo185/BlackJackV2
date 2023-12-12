import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D; 


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
    BufferedImage deckImg = ImageIO.read(new File("images/cardSpriteSheet.png")); 

    int imgWidth = 960;    
    int imgHeight = 462; 

    BufferedImage[][] cardPictures = new BufferedImage[4][13]; 
    BufferedImage backOfACard = ImageIO.read(new File("images/backsideOfACard.jpg")); 
    
    for (int c = 0; c < 4; c++) { 
      for (int r = 0; r < 13; r++) {
        cardPictures[c][r] = deckImg.getSubimage(r*imgWidth/13, c*imgHeight/4, imgWidth/13, imgHeight/4);  
      }
    }
    
    if (dealerTurn) { 
      yPosition = 80;
    }
    else { 
      yPosition = 400;
    }
    
    xPosition = 650 + 85*cardNumber; 
    
    if (faceDown) {
      g2.drawImage(backOfACard, xPosition, yPosition, null ); 
    }
    else {
      g2.drawImage(cardPictures[suit][rank], xPosition, yPosition, null); 
  }
}}
