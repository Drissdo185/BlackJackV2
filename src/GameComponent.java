import javax.swing.JComponent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import java.io.*;



public class GameComponent extends JComponent implements MouseListener {

    private ArrayList<Card> dealerHand; 
    private ArrayList<Card> playerHand;
    private int dealerScore; 
    private int playerScore;
    public boolean faceDown = true; 
    public static boolean betMade = false; 
    private int currentBalance; 
    public static int currentBet; 


    private static BufferedImage chip;
    private static BufferedImage backgroundImage;
    private static BufferedImage board;
    private static BufferedImage backsideOfACard;
    private static BufferedImage box;
    



    public GameComponent(ArrayList<Card> dH, ArrayList<Card> pH) { 

        dealerHand = dH; 
        playerHand = pH;

        dealerScore = 0;
        playerScore = 0;


        addMouseListener(this);
    }



    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;


        try{
            backgroundImage = ImageIO.read(new File("images/background.png"));
            board = ImageIO.read(new File("images/board.png"));
            chip = ImageIO.read(new File("images/chip.png"));
            backsideOfACard = ImageIO.read(new File("images/backsideOfACard.jpg"));
            box = ImageIO.read(new File("images/box.png"));
        }
        catch(IOException e) {}
        


        g2.drawImage(backgroundImage, 0, 0, null);
        g2.drawImage(chip, 40, 320, 230, 230, null);
        g2.drawImage(board, -25, 0, 320, 120, null);
        g2.drawImage(box, 40, 210, 230, 105, null);

        g2.drawImage(backsideOfACard, 655, 80, 66, 115, null);
        g2.drawImage(backsideOfACard, 655, 400,66, 115, null);
        g2.drawImage(backsideOfACard, 740, 80, 66, 115, null);
        g2.drawImage(backsideOfACard, 740, 400,66, 115, null);


        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        

        g2.drawString("DEALER", 550, 60);
        g2.drawString("PLAYER", 550, 380);

                
        g2.setFont(new Font("Arial", Font.BOLD, 23));
        g2.drawString("BALANCE: " + currentBalance, 20, 27);


        g2.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        g2.drawString("Dealer Won: ", 20, 110);
        g2.drawString(Integer.toString(dealerScore), 170, 110);
        g2.drawString("You Won: ", 20, 70);
        g2.drawString(Integer.toString(playerScore), 170, 70);


        g2.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        g2.drawString("CLICK the chip below", 73, 260);
        g2.drawString("to begin each round", 75, 280);



        try {
            for (int i = 0; i < dealerHand.size(); i++) {
                if (i == 0) { 
                    if(faceDown) { 
                        dealerHand.get(i).printCard(g2, true, true, i);
                    }
                    else {
                        dealerHand.get(i).printCard(g2, true, false, i);
                    }
                }
                else {
                    dealerHand.get(i).printCard(g2, true, false, i);
                }
            }
        }
        catch (IOException e) {}


        try {
            for (int i = 0; i < playerHand.size(); i++) {
                playerHand.get(i).printCard(g2, false, false, i);
            }
        }
        catch (IOException e) {}
    }



    public void refresh(int cB, int uS, int dS, boolean fD) {
        currentBalance = cB;
        playerScore = uS;
        dealerScore = dS;
        faceDown = fD;
        this.repaint();
    }



    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();


        if(mouseX>= 50 && mouseX<=250 && mouseY>=320 && mouseY<=550) {
            
            betMade = true;
            String[] options = new String[] {"1", "5", "10", "25", "100", "All In"};
            int response = JOptionPane.showOptionDialog(null, "Please choose your betting amount", "BETTING",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            if(response == 0) {
                currentBet = 1;
                currentBalance -= 1;
            }
            else if(response == 1) {
                currentBet = 5;
                currentBalance -= 5;
            }
            else if(response == 2) {
                currentBet = 10;
                currentBalance -= 10;
            }
            else if(response == 3) {
                currentBet = 25;
                currentBalance -= 25;
            }
            else if(response == 4) {
                currentBet = 100;
                currentBalance -= 100;
            }
            else if(response == 5) { 
                currentBet = currentBalance;
                currentBalance = 0;
            }
            else { 
                currentBet = 0;
                currentBalance -= 0;
            }

            if(currentBalance == 0) {
                JOptionPane.showMessageDialog(null, "You don't have money to play. Please get more money to continue playing.");
                return; 
            }
            
            if(currentBalance == 0) {
                JOptionPane.showMessageDialog(null, "You don't have money to play. Please get more money to continue playing.");
                return; 
            }
    



            Tester.newGame.startGame();
        }
    }
    public void mouseExited(MouseEvent e) {

    }
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseReleased(MouseEvent e) {

    }
    public void mouseClicked(MouseEvent e) {

    }
}