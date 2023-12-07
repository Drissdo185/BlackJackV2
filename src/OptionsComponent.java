import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;


public class OptionsComponent extends JComponent implements ActionListener{

    private JButton btnPlay = new JButton("PLAY");
    private JButton btnExit = new JButton("EXIT");
    private JButton btnHelp = new JButton("HELP");
    private JButton btnInfo = new JButton("INFO");
    private JButton btnStart = new JButton("Start");
    private static BufferedImage backgroundImage, logo;

    public OptionsComponent() {
        btnPlay.addActionListener(this);
        btnExit.addActionListener(this);
        btnHelp.addActionListener(this);
        btnInfo.addActionListener(this);
    }

    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        try {
            backgroundImage = ImageIO.read(new File("images/background.png"));
            logo = ImageIO.read(new File("src/logo.png"));
        }
        catch(IOException e) {}

        g2.drawImage(backgroundImage, 0, 0, null);
        g2.drawImage(logo, 390, 100, null);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 30));
        g2.drawString("Chơi đi, muốn làm giàu thì chơi đi", 220, 580);

        btnPlay.setBounds(500, 300, 150, 80);
        btnExit.setBounds(500, 400, 150, 80);
        btnHelp.setBounds(80, 75, 150, 80);
        btnInfo.setBounds(900, 75, 150, 80);

        btnPlay.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        btnExit.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        btnHelp.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        btnInfo.setFont(new Font("Comic Sans MS", Font.BOLD, 40));

        super.add(btnPlay);
        super.add(btnExit);
        super.add(btnHelp);
        super.add(btnInfo);
    }

    public void actionPerformed(ActionEvent e) {
        JButton selectedButton = (JButton)e.getSource();

        if(selectedButton == btnExit) {
            playSE(".//restest//click.wav");
            System.exit(0);
        }
        else if(selectedButton == btnPlay) {
            Tester.currentState = Tester.STATE.GAME;
            Tester.menuFrame.dispose();
            Tester.gameRefreshThread.start();
            Tester.gameCheckThread.start();
            //playAmbienceMusic();
            playSE(".//res//nutamthanh.wav");
        }
        else if(selectedButton == btnHelp) {
            playSE(".//res//click.wav");
            JOptionPane.showMessageDialog(this, "The goal of blackjack is to beat the dealer's hand without going over 21." +
                            "\nFace cards are worth 10. Aces are worth 1 or 11, whichever makes a better hand." +
                            "\nEach player starts with two cards, one of the dealer's cards is hidden until the end." +
                            "\nTo 'Hit' is to ask for another card. To 'Stand' is to hold your total and end your turn." +
                            "\nIf you go over 21 you bust, and the dealer wins regardless of the dealer's hand." +
                            "\nIf you go over 21 you bust, and the dealer wins regardless of the dealer's hand." +
                            "\nIf you are dealt 21 from the start (Ace & 10), you got a blackjack.", "QUICK&EASY BLACKJACK HELP",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else if(selectedButton == btnInfo) {
            playSE(".//restest//click.wav");
            JOptionPane.showMessageDialog(this, "Product of Driss dep trai va nhung nguoi ban" +
                    "\n:>>>>>>>>", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
        }
    }

        public void playSE(String Sound) {

            SE.setFile(Sound);
            SE.play();
        }
}
