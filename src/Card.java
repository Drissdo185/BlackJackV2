import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Card {
    private int suit;
    private int rank;
    private int value;
    private int xPostion;
    private int yPostion;

    // default constructor
    public Card(){
        suit=0;
        rank=0;
        value=0;
    }

    
    public Card(int suit, int rank, int value){
        this.suit=suit;
        this.rank=rank;
        this.value=value;
        
    }

    public int getSuit(){
        return suit;
    }
    public int getRank(){
        return rank;
    }
    public int getValue(){
        return value;
    }


    //show picture of card
    // dealerTurn: check co phai toi luot của nha cai khongo
    // faceDown: check la bai up hay lat

    public void printCard(Graphics2D g2, boolean dealerTurn, boolean faceDown, int cardNumber)
            throws IOException {


        BufferedImage deckImg = ImageIO.read(new File("images/cardSpriteSheet.png")); // doc anh tu file


        int imgWidth = 950; //this is the width of the sprite sheet image in pixels.
        int imgHeight = 392; //this is the height of the sprite sheet image in pixels.


        BufferedImage[][] cardPictures = new BufferedImage[4][13];
        /* 4 la cơ, rô, chuồn, bích
         * 13 la 2,3,4,5,6,7,8,9,10,J,Q,K,A
         */
        for (int c =0; c <4; c++){
            for(int r =0; r<13; r++){
               cardPictures[c][r] = deckImg.getSubimage(r*imgWidth/13, c*imgHeight/4, imgWidth/13, imgHeight/4);
        }
        }
        if(dealerTurn){
            yPostion = 75;
        }else{
            yPostion = 400;
        }

        xPostion = 500+ 75*cardNumber;

        if(faceDown){
            g2.drawImage(cardPictures[0][0], xPostion, yPostion, null);}
        else{
            g2.drawImage(cardPictures[suit][rank], xPostion, yPostion, null);
        }
    }
}
