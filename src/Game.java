import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game extends JPanel{

    private JLabel text;

    public static final int CARD_HEIGHT = 145;
    public static final int CARD_WIDTH = 100;

    private static int x = 0;
    private static int y = 0;

    private JButton btnHit, btnStand, btnNext, btnPlay, btnExit, btnRule;

    public Game(){
        setupGame();
    }

    public void setupGame(){
        this.setSize(800, 500);

        // Add title to the panel
        text = new JLabel("Black Jack IU");
        text.setBounds(300, 50, 200, 30);
        text.setFont(new Font("Arial", Font.BOLD, 25));
        text.setForeground(Color.BLACK);


        // Add buttons to the panel
        btnPlay = new JButton("Play");
        btnPlay.setBounds(350,200, 80, 30); // x, y, width, height
        btnPlay.setFocusable(false);
        btnPlay.setFont(new Font("Arial", Font.BOLD, 15));
        btnPlay.setBackground(Color.WHITE);


        btnRule = new JButton("Rule");
        btnRule.setBounds(350,250, 80, 30); // x, y, width, height
        btnRule.setFocusable(false);
        btnRule.setFont(new Font("Arial", Font.BOLD, 15));
        btnRule.setBackground(Color.WHITE);

        btnExit = new JButton("Exit");
        btnExit.setBounds(350,300, 80, 30); // x, y, width, height
        btnExit.setFocusable(false);
        btnExit.setFont(new Font("Arial", Font.BOLD, 15));
        
        btnExit.setBackground(Color.WHITE);



        //Add elements to the panel
        this.add(btnPlay);
        this.add(btnRule);
        this.add(btnExit);
        
        this.add(text);
        this.setLayout(null); // giup add button vao vi tri cu the
        this.setVisible(true);


        //Call action for btnPlay

        btnExit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JFrame frame = new JFrame("Black Jack IU");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


                Login blackjack = new Login();
                frame.setSize(800, 600);

                frame.add(blackjack);
                frame.setResizable(false);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                //Close the current window
                SwingUtilities.getWindowAncestor(Game.this).dispose();
                
            }
                



        });
    }}
