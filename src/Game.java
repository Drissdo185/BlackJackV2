import java.util.ArrayList,
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.Font;



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
    //JButton btnDouble;
    JButton btnExit;
    SE se = new SE();



    public Game(JFrame f) {

        deck = new Deck();
        deck.shuffleDeck();

        dealerHand = new ArrayList<Card>();
        playerHand = new ArrayList<Card>();

        atmosphereComponent = new GameComponent(dealerHand, playerHand);
        frame = f;

        faceDown = true;
        dealerWon = true;
        roundOver = false;
    }



    public void formGame() {

        frame.setSize(1130, 665);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);


        btnHit = new JButton("HIT");
        btnHit.setBounds(490, 550, 100, 50);
        btnHit.setFont(new Font("Times New Roman", Font.BOLD, 20));


        btnStand = new JButton("STAND");
        btnStand.setBounds(610, 550, 120, 50);
        btnStand.setFont(new Font("Times New Roman", Font.BOLD, 20));


        btnExit = new JButton("EXIT");
        btnExit.setBounds(970, 550, 100, 50);
        btnExit.setFont(new Font("Times New Roman", Font.BOLD, 20));


        frame.add(btnHit);
        frame.add(btnStand);
        frame.add(btnExit);


        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "You have left the casio with " +  Tester.currentBalance + " coins");
                System.exit(0);
            }
        });


        atmosphereComponent = new GameComponent(dealerHand, playerHand); 
        atmosphereComponent.setBounds(0, 0, 1130, 665); 


        frame.add(atmosphereComponent);
        frame.setVisible(true); 
    }



    public void startGame() {
        
        

        for(int i = 0; i<2; i++) {
            dealerHand.add(deck.getCard(i));
        }
        for(int i = 2; i<4; i++) {
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
            public void actionPerformed(ActionEvent e)  {
                playSE("sounds/rutbai.wav");
                addCard(playerHand);
                checkHand(playerHand);


                if (getSumOfHand(playerHand)<17 && getSumOfHand(dealerHand)<17){
                    addCard(dealerHand);
                    checkHand(dealerHand);
                }
            }
        }
        );


        btnStand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                while (getSumOfHand(dealerHand)<17) {
                    addCard(dealerHand);
                    checkHand(dealerHand);
                }

  
                if ((getSumOfHand(dealerHand)<=21) && getSumOfHand(playerHand)<=21) {
                    if(getSumOfHand(playerHand) > getSumOfHand(dealerHand)) {
                        playSE("sounds/winv2.wav");
                        faceDown = false;
                        dealerWon = false;                        
                        JOptionPane.showMessageDialog(frame, "PLAYER WON DUE TO A BETTER HAND!");
                        rest();
                        roundOver = true;
                    }
                    else if(getSumOfHand(playerHand) < getSumOfHand(dealerHand)){
                        playSE("sounds/losev2.wav");
                        faceDown = false;
                        JOptionPane.showMessageDialog(frame, "DEALER HAS WON BECAUSE OF A BETTER HAND!");
                        rest();
                        roundOver = true;
                    }
                    else{
                        playSE("sounds/losev2.wav");
                        faceDown = false;
                        JOptionPane.showMessageDialog(frame, "DEALER HAS WON BECAUSE OF A BETTER HAND!");
                        rest();
                        roundOver = true;
                    }
                }
            }
        });
    }
  


    public void checkHand(ArrayList<Card> hand) {
        if (hand.equals(playerHand)) {
            if (getSumOfHand(hand) == 21) {
                playSE("sounds/winv2.wav");
                faceDown = false;
                dealerWon = false;                
                JOptionPane.showMessageDialog(frame, "PLAYER HAS GOT BLACKJACK! PLAYER HAS WON!");
                rest();
                roundOver = true;
            } else if (getSumOfHand(hand) > 21) {
                playSE("sounds/losev2.wav");
                faceDown = false;           
                JOptionPane.showMessageDialog(frame, "PLAYER HAS BUSTED! DEALER HAS WON!");
                rest();
                roundOver = true;
            }
        } else{
            if (getSumOfHand(hand) == 21) {
                playSE("sounds/losev2.wav");
                faceDown = false;
                JOptionPane.showMessageDialog(frame, "DEALER HAS GOT BLACKJACK! DEALER HAS WON!");
                rest();
                roundOver = true;
            } else if (getSumOfHand(hand) > 21) {
                playSE("sounds/winv2.wav");
                faceDown = false;
                dealerWon = false;
                JOptionPane.showMessageDialog(frame, "DEALER HAS JUST BUSTED! PLAYER HAS WON!");
                rest();
                roundOver = true;
            }
        }
    }    



    public void addCard(ArrayList<Card> hand) {

        hand.add(deck.getCard(0)); 
        deck.removeCard(0); 
        faceDown = true;
    }



    public boolean hasAceInHand(ArrayList<Card> hand) {

        for (int i = 0; i < hand.size(); i++){ 
            if(hand.get(i).getValue() == 11) {
                return true; 
            }
        }
        return false; 
    }



    public int aceCountInHand(ArrayList<Card> hand){
        int aceCount = 0;

        for (int i = 0; i < hand.size(); i++) {
            if(hand.get(i).getValue() == 11) {
                aceCount++;
            }
        }
        return aceCount;
    }


    
    public int getSumWithHighAce(ArrayList<Card> hand) {
        int handSum = 0;

        for (int i = 0; i < hand.size(); i++){
            handSum = handSum + hand.get(i).getValue();
        }
        return handSum; 
    }



    public int getSumOfHand (ArrayList<Card> hand) {
        if(hasAceInHand(hand)) {
            if(getSumWithHighAce(hand) <= 21) {
                return getSumWithHighAce(hand);
            }
            else{
                for (int i = 0; i < aceCountInHand(hand); i++) {
                    int sumOfHand = getSumWithHighAce(hand)-(i+1)*10;
                    if(sumOfHand <= 21) {
                        return sumOfHand;
                    }
                }
            }
        }
        else {
            int sumOfHand = 0;
            for (int i = 0; i < hand.size(); i++) {
                sumOfHand = sumOfHand + hand.get(i).getValue();
            }
            return sumOfHand;
        }
        return 22;
    }
    //Play the sound effect  
    private void playSE(String Sound) {
        
        se.setFile(Sound);
        se.play();	
    }
    //Stop the music
    public void stopSE() {
        
        se.stop();
    }





    public static void rest() {
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e) {}
    }
}
