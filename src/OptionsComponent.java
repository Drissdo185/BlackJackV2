import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.desktop.SystemEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OptionsComponent extends JComponent implements ActionListener {



    private JButton btnPlay = new JButton("PLAY");
    private JButton btnExit = new JButton("EXIT");
    private JButton btnHelp = new JButton("HELP");
    private JButton btnInfo = new JButton("INFO");
    private JButton btnStart = new JButton("START");
    private static BufferedImage backgroundImage;

    public OptionsComponent(){
        btnPlay.addActionListener(this);
        btnExit.addActionListener(this);
        btnHelp.addActionListener(this);
        btnInfo.addActionListener(this);
    }

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        try{
            backgroundImage = ImageIO.read(new File("images/background.jpg"));
        }
        catch(IOException e){}

        g2.drawImage(backgroundImage, 0, 0, null);
        g2.setFont(new Font("Comic Sans MS", Font.BOLD, 100)); //In these codes, we will add the title of our game and its font and color.
        g2.setColor(Color.WHITE);
        g2.drawString("Welcome", 380, 100);
        g2.drawString("to", 530, 180);
        g2.drawString("BLACKJACK!", 290, 260);

        g2.setFont(new Font("Arial", Font.BOLD, 30));
        g2.drawString("This game is brought to you by Ongun Uzay Macar", 220, 580); //Here, we add a small description to the component.

        btnPlay.setBounds(500, 300, 150, 80); //we set the bounds of the buttons.
        btnExit.setBounds(500, 400, 150, 80);
        btnHelp.setBounds(80, 75, 150, 80);
        btnInfo.setBounds(900, 75, 150, 80);

        btnPlay.setFont(new Font("Comic Sans MS", Font.BOLD, 40)); //we set the fonts of writings on the buttons.
        btnExit.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        btnHelp.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        btnInfo.setFont(new Font("Comic Sans MS", Font.BOLD, 40));

        super.add(btnPlay); //super refers to the JComponent. Thus, with these codes, we add the four buttons to the component.
        super.add(btnExit);
        super.add(btnHelp);
        super.add(btnInfo);
    }

    public void actionPerformed(ActionEvent e){
        JButton selectedButton = (JButton)e.getSource();

        if(selectedButton == btnExit){
            System.exit(0);
        }
        else if(selectedButton == btnStart);
            Tester.currentState = Tester.STATE.START;
    }

}
