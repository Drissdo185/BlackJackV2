import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;


public class OptionsComponent extends JComponent implements ActionListener{

    public BufferedImage PlayButtonHighlight;
    public BufferedImage HelpButton;
    public BufferedImage QuitButton;

    private JButton btnPlay = new JButton("PLAY");
    private JButton btnExit = new JButton("EXIT");
    private JButton btnRule = new JButton("RULE");
    private JButton btnInfo = new JButton("INFO");
    private static BufferedImage backgroundImage, logo;

    public OptionsComponent() {
        btnPlay.addActionListener(this);
        btnExit.addActionListener(this);
        btnRule.addActionListener(this);
        btnInfo.addActionListener(this);
    }

    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        try {
            backgroundImage = ImageIO.read(new File("images/background.png"));
            logo = ImageIO.read(new File("images/logo.png"));
        }
        catch(IOException e) {}
       
        g2.drawImage(backgroundImage, 0, 0, null);
        g2.drawImage(logo, 220, 20, null);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 30));
        g2.drawString("cờ bạc, người không chơi là người thắng!", 270, 580);

        btnPlay.setBounds(370, 350, 150, 60);
        btnExit.setBounds(370, 450, 150, 60);
        btnRule.setBounds(610, 350, 150, 60);
        btnInfo.setBounds(610, 450, 150, 60);

        btnPlay.setFont(new Font("Times New Roman", Font.BOLD, 36));
        btnExit.setFont(new Font("Times New Roman", Font.BOLD, 36));
        btnRule.setFont(new Font("Times New Roman", Font.BOLD, 36));
        btnInfo.setFont(new Font("Times New Roman", Font.BOLD, 36));

        super.add(btnPlay);
        super.add(btnExit);
        super.add(btnRule);
        super.add(btnInfo);
    }

    public void actionPerformed(ActionEvent e) {
        JButton selectedButton = (JButton)e.getSource();

        if(selectedButton == btnExit) {
            System.exit(0);
        }
        else if(selectedButton == btnPlay) {
            Tester.currentState = Tester.STATE.GAME;
            Tester.menuFrame.dispose();
            Tester.gameRefreshThread.start();
            Tester.gameCheckThread.start();
            //playAmbienceMusic();
        }
        else if(selectedButton == btnRule) {
            JOptionPane.showMessageDialog(this, "1. GOAL: have a hand value closer to 21 than the dealer's hand without exceeding 21." +
                            "\n2. CARD VALUES:" +
                            "\n   - Face cards (King, Queen, Jack) are each worth 10 points." + 
                            "\n   - Aces can be worth either 1 or 11 points, depending on which value benefits the hand more." +
                            "\n3. THE DEAL: You are dealt two cards each, and the dealer receives one card face up and one face down (hole card)." +
                            "\n4. TURN:" +
                            "\n   - You can choose to \"hit\" (take another card) or \"stand\" (keep your current hand)." +
                            "\n   - You can continue to hit until you decide to stand, or until your hand exceeds 21, resulting in a bust." +
                            "\n5. WINNING & LOSING:" +
                            "\n   - You win if your hand is closer to 21 than the dealer's hand without busting." +
                            "\n   - If you bust (exceeds 21), the dealer wins regardless of the dealer's hand." +
                            "\n   - If the dealer busts, you win." +
                            "\n   - If both the player (you) and dealer have the same hand value, it's a push (a tie), and your bet is returned." +
                            "\n6. BLACKJACK: If the player (you) or dealer is dealt 21 from the start (Ace & a 10-value card), player/dealer got a blackjack" , "BLACKJACK'S BASIC GAMEPLAY",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else if(selectedButton == btnInfo) {
            JOptionPane.showMessageDialog(this, "Product of Driss dep trai va nhung nguoi ban" +
                    "\n:>>>>>>>>", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
        }
    }


}
