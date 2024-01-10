import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.*;

import javax.swing.*;
import java.awt.Font;



public class Game {

    ArrayList<Card> dealerHand;
    ArrayList<Card> playerHand;


    public boolean faceDown;
    public boolean dealerWon;
    public boolean tie;
    public volatile boolean roundOver;


    JFrame frame;
    Deck deck;
    GameComponent atmosphereComponent;
    GameComponent cardComponent;


    JButton btnHit;
    JButton btnStand;
    //JButton btnDouble;
    JButton btnExit;
    String playerName;
    SE se = new SE();



    public Game(JFrame f, String PlayerName) {

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


        checkBJ(dealerHand);
        checkBJ(playerHand);


        btnHit.addActionListener(new ActionListener() {
                                     public void actionPerformed(ActionEvent e)  {
                                         playSE("sounds/rutbai.wav");
                                         addCard(playerHand);
                                         checkFiveCardP(playerHand);
                                         checkFiveCardLP(playerHand);
                                     }
                                 }
        );


        btnStand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(getSumOfHand(playerHand)>=15){
                    while(getSumOfHand(dealerHand)<= 14){
                        addCard(dealerHand);
                    }
                    if(checkFiveCardD(dealerHand)){
                        playSE("sounds/winv2.wav");
                        faceDown = false;
                        dealerWon = true;
                        JOptionPane.showMessageDialog(frame, "DEALER HAS GOT FIVE CARD TRICK! DEALER HAS WON!");
                        rest();
                        roundOver = true;
                    }
                    else{


                        if(getSumOfHand(playerHand)>21 && getSumOfHand(dealerHand)>21){
                            playSE("sounds/winv2.wav");
                            faceDown = false;
                            tie = true;
                            JOptionPane.showMessageDialog(frame, "WELL DONE, NO ONES LOSES");
                            rest();
                            roundOver = true;
                        } else if (getSumOfHand(playerHand) == getSumOfHand(dealerHand)) {
                            faceDown=false;
                            tie = true;
                            JOptionPane.showMessageDialog(frame, "WELL DONE, NO ONES LOSES");
                            rest();
                            roundOver = true;

                        } else if (getSumOfHand(playerHand)>21 && getSumOfHand(dealerHand)<= 21) {
                            faceDown = false;
                            dealerWon = true;
                            JOptionPane.showMessageDialog(frame, "DEALER HAS WON, PLAYER HAS BUSTED");
                            rest();
                            roundOver = true;
                        } else if (getSumOfHand(dealerHand)>21 && getSumOfHand(playerHand)<= 21) {
                            faceDown = false;
                            dealerWon = false;
                            JOptionPane.showMessageDialog(frame, "PLAYER HAS WON, DEALER HAS BUSTED");
                            rest();
                            roundOver = true;
                        }
                        else if (getSumOfHand(playerHand) < getSumOfHand(dealerHand)) {
                            faceDown = false;
                            dealerWon = true;
                            JOptionPane.showMessageDialog(frame, "DEALER HAS WON");
                            rest();
                            roundOver = true;

                        }
                        else {
                            playSE("sounds/winv2.wav");
                            faceDown = false;
                            dealerWon = false;
                            JOptionPane.showMessageDialog(frame, "PLAYER HAS WON!");
                            rest();
                            roundOver = true;

                        }
                    }}
                else{
                    JOptionPane.showMessageDialog(frame, "YOU CAN'T STAND, YOUR POINT LESS THAN 14");
                }

            }
        });

    }

    public int checkNumberOfCards(ArrayList<Card> hand) {
        return hand.size();
    }


    public void addCard(ArrayList<Card> hand) {

        hand.add(deck.getCard(0));
        deck.removeCard(0);
        faceDown = true;
    }
    // check xi zach
    public void checkBJ(ArrayList<Card> hand){
        if(hand.equals(dealerHand)){
            if(getSumOfHand(dealerHand) ==21){
                playSE("sounds/winv2.wav");
                faceDown = false;
                dealerWon = true;
                JOptionPane.showMessageDialog(frame, "DEALER HAS GOT BLACKJACK! DEALER HAS WON!");
                rest();
                roundOver = true;

            }

        }else{
            if(getSumOfHand(playerHand) ==21) {
                playSE("sounds/winv2.wav");
                faceDown = false;
                dealerWon = false;
                JOptionPane.showMessageDialog(frame, "PLAYER HAS GOT BLACKJACK! PLAYER HAS WON!");
                rest();
                roundOver = true;
            }
        }
    }


    //check ngu linh
    public boolean checkFiveCardD(ArrayList<Card> hand){

        if(getSumOfHand(dealerHand) <=21 && checkNumberOfCards(hand)== 5){

            return true;
        }
        return false;

    }
    public void checkFiveCardP(ArrayList<Card> hand){
        if(getSumOfHand(playerHand) <=21 && checkNumberOfCards(hand)== 5) {
            playSE("sounds/winv2.wav");
            faceDown = false;
            dealerWon = false;
            JOptionPane.showMessageDialog(frame, "PLAYER HAS GOT FIVE CARD TRICK! PLAYER HAS WON!");
            rest();
            roundOver = true;
        }

    }

    public void checkFiveCardLP(ArrayList<Card> hand){
        if(getSumOfHand(playerHand) >=21 && checkNumberOfCards(hand)== 5) {
            playSE("sounds/winv2.wav");
            faceDown = false;
            dealerWon = true;
            JOptionPane.showMessageDialog(frame, "PLAYER HAS LOST");
            rest();
            roundOver = true;
        }

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