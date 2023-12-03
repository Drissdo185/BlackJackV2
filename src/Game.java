import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Font;
import java.io.FileInputStream;

import java.io.*;

public class Game {

    
    ArrayList<Card> dealerHand;
    ArrayList<Card> playerHand;

    
    public boolean faceDown;
    public boolean dealerWon;
    public volatile boolean roundOver;


    JFrame frame;
    Deck deck;
    GameComponent atmosphereComponent;
    GameComponent cardComponent;

    JButton btnHit;
    JButton btnStand;
    JButton btnDouble;
    JButton btnExit;

    
    public Game(JFrame f) {
        deck = new Deck();
        deck.shuffleDeck(); //we randomize the deck.
        dealerHand = new ArrayList<Card>();
        playerHand = new ArrayList<Card>();
        atmosphereComponent = new GameComponent(dealerHand, playerHand);
        frame = f;
        faceDown = true;
        dealerWon = true;
        roundOver = false;
    }

    
    public void formGame() {

        System.out.println("GAME FORMED");
        frame.setTitle("BLACKJACK!");
        frame.setSize(1130, 665);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        
        btnHit = new JButton("HIT");
        btnHit.setBounds(390, 550, 100, 50);
        btnHit.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        btnStand = new JButton("STAND");
        btnStand.setBounds(520, 550, 100, 50);
        btnStand.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        btnDouble = new JButton("DOUBLE");
        btnDouble.setBounds(650, 550, 100, 50);
        btnDouble.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        btnExit = new JButton("EXIT CASINO");
        btnExit.setBounds(930, 240, 190, 50);
        btnExit.setFont(new Font("Comic Sans MS", Font.BOLD, 16));

        
        frame.add(btnHit);
        frame.add(btnStand);
        frame.add(btnDouble);
        frame.add(btnExit);

        
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "You have left the casino with " +  Tester.currentBalance + ".");
                System.exit(0);
            }
        });

        
        atmosphereComponent = new GameComponent(dealerHand, playerHand); // initialize GameComponent that will be overall atmosphere of our game
        atmosphereComponent.setBounds(0, 0, 1130, 665);  // set bounds of the component
        frame.add(atmosphereComponent); // add component to frame
        frame.setVisible(true); // make frame visible
    }

    
    public void startGame() {
        for (int i = 0; i<2; i++) {
            dealerHand.add(deck.getCard(i));
        }
        for (int i = 2; i < 4; i++) {
            playerHand.add(deck.getCard(i));
        }
        for (int i = 0; i < 4; i++) {
            deck.removeCard(0);
        }

        
        cardComponent = new GameComponent(dealerHand, playerHand);
        cardComponent.setBounds(0, 0, 1130, 665);
        frame.add(cardComponent);
        frame.setVisible(true);

        
        checkHand(dealerHand);
        checkHand(playerHand);

        
        btnHit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCard(playerHand); // first, add a card to player's hand
                checkHand(playerHand); // then check the player's hand because it could be round over
                if (getSumOfHand(playerHand)<17 && getSumOfHand(dealerHand)<17){ // if round has not yet ended & total value of dealer's hand is less than 17, add a card to dealer's hand
                    addCard(dealerHand);
                    checkHand(dealerHand); // check dealer hand for any potential round over situations, as is customary
                }
            }
        });

        
        btnDouble.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                addCard(playerHand);
                addCard(playerHand);
                checkHand(playerHand);
                if (getSumOfHand(playerHand) < 17 && getSumOfHand(dealerHand) < 17) {
                    addCard(dealerHand);
                    checkHand(dealerHand);
                }
            }
        });

        
        btnStand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                while (getSumOfHand(dealerHand)<17) {
                    addCard(dealerHand);
                    checkHand(dealerHand);
                }

                
                if ((getSumOfHand(dealerHand)<21) && getSumOfHand(playerHand)<21) {
                    if (getSumOfHand(playerHand) > getSumOfHand(dealerHand)) {
                        faceDown = false;
                        dealerWon = false;
                        JOptionPane.showMessageDialog(frame, "PLAYER HAS WON BECAUSE OF A BETTER HAND!");
                        rest();
                        roundOver = true;
                    }                        
                    else {
                        faceDown = false;
                        JOptionPane.showMessageDialog(frame, "DEALER HAS WON BECAUSE OF A BETTER HAND!");
                        rest();
                        roundOver = true;
                    }
                }
            }
        });
    }

    
    public void checkHand (ArrayList<Card> hand) { // check hand for a blackjack or bust
        if (hand.equals(playerHand)) { // checks if parameter is player's hand
            if (getSumOfHand(hand) == 21){ // if it is 21, player has done blackjack and the game is over
                faceDown = false;
                dealerWon = false; // set it to false because user won
                JOptionPane.showMessageDialog(frame, "PLAYER HAS DONE BLACKJACK! PLAYER HAS WON!"); // print out result ot JOptionPane
                rest();
                roundOver = true;
            }                
            else if (getSumOfHand(hand) > 21) { // if it is bigger than 21, then player hand has busted, dealer has won
                faceDown = false; JOptionPane.showMessageDialog(frame, "PLAYER HAS BUSTED! DEALER HAS WON!");
                rest();
                roundOver = true;
            }
        }
            
        else { // else condition checks if it is dealer's hand
            if (getSumOfHand(hand) == 21) { // look for same things we looked for the player's hand
                faceDown = false;
                JOptionPane.showMessageDialog(frame, "DEALER HAS DONE BLACKJACK! DEALER HAS WON!");
                rest();
                roundOver = true;
            }
            else if (getSumOfHand(hand) > 21) {
                faceDown = false;
                dealerWon = false;
                JOptionPane.showMessageDialog(frame, "DEALER HAS JUST BUSTED! PLAYER HAS WON!");
                rest();
                roundOver = true;
            }
        }
    }

    
    public void addCard(ArrayList<Card> hand) { //add a card to hand
        hand.add(deck.getCard(0)); // get a card from deck to hand
        deck.removeCard(0); // remove card from deck
        faceDown = true;
    }

    
    public boolean hasAceInHand(ArrayList<Card> hand) { // check if the hand has ace
        for (int i = 0; i < hand.size(); i++){ // go through hand that is given as a parameter and check for a card with a value of 11(Ace)
            if (hand.get(i).getAmount() == 11) {
                return true; // return true if there is any
            }
        }
        return false; // return false if not
    }

    
    public int aceCountInHand(ArrayList<Card> hand){
        int aceCount = 0;
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getAmount() == 11) {
                aceCount++;
            }
        }
        return aceCount;
    }

    
    public int getSumWithHighAce(ArrayList<Card> hand) {
        int handSum = 0;
        for (int i = 0; i < hand.size(); i++){
            handSum = handSum + hand.get(i).getAmount();
        }
        return handSum; // return integer
    }

    
    public int getSumOfHand (ArrayList<Card> hand) {
        if(hasAceInHand(hand)) {
            if(getSumWithHighAce(hand) <= 21) {
                return getSumWithHighAce(hand);
            }
                
            else {
                for (int i = 0; i < aceCountInHand(hand); i++) {
                    int sumOfHand = getSumWithHighAce(hand) - (i + 1)*10;
                    if(sumOfHand <= 21) {
                        return sumOfHand;
                    }
                }
            }
        }
            
        else {
            int sumOfHand = 0;
            for (int i = 0; i < hand.size(); i++) {
                sumOfHand = sumOfHand + hand.get(i).getAmount();
            }
            return sumOfHand;
        }
        return 22;
    }

    
    public static void rest() { // sleeps program. It basically serves as a time duration between events
        try {
            Thread.sleep(500); // sleeps program for 1000 miliseconds which is equal to 1 second
        }
        catch (InterruptedException e) {}
    }
}
