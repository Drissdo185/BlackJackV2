import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Game {

    ArrayList<Card> dealerHand;
    ArrayList<Card> playerHand;

    public boolean faceDown;
    public boolean dealerWon;
    public volatile boolean roundOver;

    JFrame frame;
    Deck deck;
    GameCompoment envCompoment;
    GameCompoment cardComponent;

    JButton btnHit, btnStand, btnDouble, btnExit;

    public Game(JFrame frame) {
        System.out.println("Game started");
        frame.setTitle("Blackjack");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);


        btnHit = new JButton("HIT"); //In the following code snippet, we basically initialize our buttons and design them in the way we want them to be.
        btnHit.setBounds(390, 550, 100, 50); //We set their bounds.
        btnHit.setFont(new Font("Comic Sans MS", Font.BOLD, 16));  //We set their font.
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
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Còn thở còn gỡ");
                System.exit(0);

            }
        });

        envCompoment = new GameCompoment(dealerHand, playerHand);
        envCompoment.setBounds(0, 0, 800, 600);
        frame.add(envCompoment);
        frame.setVisible(true);
    }

        public void startGame(){

            for(int i=0; i<2; i++){
                dealerHand.add(deck.getCard(i));
            }
            for(int i =2; i<4; i++) {
                playerHand.add(deck.getCard(i));
            }
            for(int i=0; i<4; i++) {
                deck.removeCard(i);
            }

            cardComponent = new GameCompoment(dealerHand, playerHand);
            cardComponent.setBounds(0, 0, 800, 600);
            frame.add(cardComponent);
            frame.setVisible(true);
        }

        public void checkHand(ArrayList<Card> hand) {
            if (hand.equals(playerHand)) {
                if (getSum(hand) == 21) {
                    faceDown = false;
                    dealerWon = false;

                    JOptionPane.showMessageDialog(frame, "Xì zách rùi, quá đã !luôn");
                    rest();
                    roundOver = true;

                } else if (getSum(hand) > 21) {
                    faceDown = false;
                    dealerWon = true;

                    JOptionPane.showMessageDialog(frame, "Bust, thua rồi, quá đã !luôn");
                    rest();
                    roundOver = true;
                }
            }
    }
            public int getSum(ArrayList < Card > hand) {
                if (hasAce(hand)) {
                    if (getSumwAce(hand) <= 21) {
                        return getSumwAce(hand);
                    } else {
                        for (int i = 0; i < aceCountInHand(hand); i++) {
                            int sumOfHand = getSumwAce(hand) - (i + 1) * 10;
                            if (sumOfHand <= 21) {
                                return sumOfHand;
                            }
                        }
                    }
                } else {
                    int sumOfHand = 0;
                    for (int i = 0; i < hand.size(); i++) {
                        sumOfHand += hand.get(i).getValue();
                    }
                    return sumOfHand;

                }
                return 22;
            }
            public boolean hasAce (ArrayList < Card > hand) {
                for (int i = 0; i < hand.size(); i++) {
                    if (hand.get(i).getValue() == 11) {
                        return true;
                    }
                }
                return false;

            }

            public static void rest(){
                try{
                    Thread.sleep(500);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

            public int getSumwAce(ArrayList<Card> hand){
                int sumOfHand = 0;
                for(int i = 0; i < hand.size(); i++){
                    sumOfHand += hand.get(i).getValue();
                }
                return sumOfHand;
            }

            public int aceCountInHand(ArrayList<Card> hand){
                int aceCount = 0;
                for(int i = 0; i < hand.size(); i++){
                    if(hand.get(i).getValue() == 11){
                        aceCount++;
                    }
                }
                return aceCount;

            }




        }


