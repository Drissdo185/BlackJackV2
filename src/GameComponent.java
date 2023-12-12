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

    public BufferedImage chip;
    private ArrayList<Card> dealerHand;


    private ArrayList<Card> dealerHand; 

    private ArrayList<Card> playerHand;
    private int dealerScore;
    private int playerScore;

    public boolean faceDown = true;
    public static boolean betMade = false;
    private int currentBalance;
    public static int currentBet;
    public BufferedImage backgroundImage;


    public GameComponent(ArrayList<Card> dH, ArrayList<Card> pH) {
        dealerHand = dH;

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
        currentBalance = 0;




        addMouseListener(this);
    }



    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;


        try{
            backgroundImage = ImageIO.read(new File("images/background.png"));
            //logo = ImageIO.read(new File("images/blackjackLogo.png"));
            chip = ImageIO.read(new File("images/chip.png"));
            //dealerImage = ImageIO.read(new File("images/dealer__.png"));
            //playerImage = ImageIO.read(new File("images/player_.png"));
        }
        catch(IOException e) {}

        g2.drawImage(backgroundImage, 0, 0, null); //we draw these images to the component.


        g2.drawImage(chip, 100, 430, null);
        g2.setColor(Color.WHITE); //we set the colors.
        g2.setFont(new Font("Comic Sans MS", Font.BOLD, 40)); //we change their fonts.
        g2.drawString("DEALER", 600, 60); //we draw these strings which visualize the game.
        g2.drawString("PLAYER", 600, 380);


        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.drawString("CURRENT COINS :   " + currentBalance, 10, 110);
        g2.drawString("Dealer Won: ", 10, 30);
        g2.drawString(Integer.toString(dealerScore), 150, 30); //we draw the dealer's score accordingly.
        g2.drawString("You Won: ", 10, 70);
        g2.drawString(Integer.toString(playerScore), 150, 70); //we draw the player's score accordingly.

        g2.setFont(new Font("Comic Sans MS", Font.BOLD, 15)); //we set the font again.
        g2.drawString("Each round must begin after", 70, 330);
        g2.drawString("you've finished making a bet ", 70, 360);
        g2.drawString("by clicking the chips under.", 70, 390);
        //g2.drawString("The best gaming experience is ", 830, 550);
        //g2.drawString("when you play with sound on!", 830, 570);

        /*Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        g2.drawString(sdf.format(cal.getTime()), 1020, 20);*/


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
        g2.drawString(Integer.toString(dealerScore), 160, 110);
        g2.drawString("You Won: ", 120, 70);
        g2.drawString(Integer.toString(playerScore), 230, 70);


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


        if(mouseX>= 100 && mouseX<=250 && mouseY>=430 && mouseY<=580) {//we will only do something if the x and y coordinates fall on top of the chip image. The coordinates you see below give the end points of the chip image.

            betMade = true; //if the user clicks on the chip image, then the bet is made.
            String[] options = new String[] {"1", "5", "10", "25", "100"}; //we declare an array of options that will be given when the user clicks the chip and and JOptionPane dialog comes up.
            int response = JOptionPane.showOptionDialog(null, "Please enter the amount of coin that you want to spend", "BETTING", //This is the dialog that will popup when user clicks the chip.
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            if(response == 0) {//if the user selects the first response, he selected the bet as 1.
                currentBet = 1; //we assign 1 to the current bet.
                currentBalance -= 1; //we decrement the current balance by the current bet.


        if(mouseX>= 50 && mouseX<=250 && mouseY>=320 && mouseY<=550) {
            
            betMade = true;
            String[] options = new String[] {"1", "5", "10", "25", "100"};
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

            else {
                currentBet = 1;
                currentBalance -= 1;
                //System.out.println("You haven't selected a proper bet. Thus, the bet is taken as 1.");
            }

            System.out.println("You have made your bet: " + currentBet + "." + " If you beat the dealer, you will increase your current balance by " + currentBet*2 +
                    "; if the dealer beats you you will decrease your current balance by " + currentBet + ".");
            Tester.newGame.startGame();
        }

            else { 
                currentBet = 0;
                currentBalance -= 0;
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
