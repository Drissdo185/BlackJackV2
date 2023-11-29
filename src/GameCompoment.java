import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameCompoment extends JComponent implements MouseListener {

    public BufferedImage backgroundImage;
    public BufferedImage logo;
    public BufferedImage chip;
    private ArrayList<Card> dealerHand;
    private ArrayList<Card> playerHand;
    private int dealerScore;
    private int playerSocre;
    public boolean faceDown = true;
    public static boolean betMade = false; // make cai bet
    private int currentBalnace;
    public static int currentBet;

    public GameCompoment(ArrayList<Card> dealerH, ArrayList<Card> playerH){
        dealerHand = dealerH;
        playerHand = playerH;
        dealerScore = 0;
        playerSocre = 0;
        currentBalnace = 1000;
        currentBet = 0;
        addMouseListener(this);
    }

    public void paintComponment(Graphics g){
        Graphics2D g2 = (Graphics2D) g;


        try{
            backgroundImage = ImageIO.read(new File("images/background.jpg"));
            logo = ImageIO.read(new File("images/logo.png"));
            chip = ImageIO.read(new File("images/chip.png"));
        }
        catch(IOException e){}

        //draw compoment
        g2.drawImage(backgroundImage, 0, 0, null);
        g2.drawImage(logo, 0, 0, null);
        g2.drawImage(chip, 0, 0, null);



    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
