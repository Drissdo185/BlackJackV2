import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



public class Tester {
    JButton btnStart;
    public static JFrame Login = new JFrame();
    public static JFrame menuFrame = new JFrame();
    public static JFrame gameFrame = new JFrame();
    public static ImageIcon icon = new ImageIcon("images/icon.png");

    //private static BufferedImage backgroundImage;

    private static int playerScore = 0;
    private static int dealerScore = 0;
    public static int currentBalance;


    public static Game newGame = new Game(gameFrame);
    private static boolean isFirstTime = true;



    public static enum STATE{
        MENU,
        GAME
    };

    public static STATE currentState = STATE.MENU;



    public static void main(String[] args) throws InterruptedException {

        if(currentState == STATE.MENU) {
            setCommonProperties();
            openMenu();
        }    
    }



    public static void setCommonProperties() {
        Login.setIconImage(icon.getImage());
        Login.setTitle("xì dách phương Tây");

        menuFrame.setIconImage(icon.getImage());
        menuFrame.setTitle("xì dách phương Tây");

        gameFrame.setIconImage(icon.getImage());
        gameFrame.setTitle("xì dách phương tây");
    }
    
    

    public static void openMenu() {   

        try{
            Login.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("images/backgroundMenu.png")))));
   
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Login.setSize(800, 600);
        Login.setLocationRelativeTo(null);
        Login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Login.setResizable(false);        



        JTextField namePlayer = new JTextField();
        namePlayer.setBounds(330, 200, 200, 30);
        namePlayer.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        namePlayer.setHorizontalAlignment(JTextField.CENTER);
        

        JLabel label = new JLabel("Enter your name: ");
        label.setBounds(120, 200, 200, 30);
        label.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.WHITE);


        JTextField balanceTextField = new JTextField();
        balanceTextField.setBounds(330, 300, 200, 30);
        balanceTextField.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        balanceTextField.setHorizontalAlignment(JTextField.CENTER);        
        

        JLabel label2 = new JLabel("Enter your balance: ");
        label2.setBounds(120, 300, 200, 30);
        label2.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setForeground(Color.WHITE);



        //button
        JButton btnStart = new JButton("START");
        btnStart.setBounds(320, 400, 200, 50);
        btnStart.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        btnStart.setBackground(Color.WHITE);
        btnStart.setForeground(Color.BLACK);


        Login.add(label2);
        Login.add(balanceTextField);
        Login.add(label);
        Login.add(namePlayer);
        Login.add(btnStart);
        Login.setVisible(true);

        
        btnStart.addActionListener(e -> {
            String name = namePlayer.getText();
            String balance = balanceTextField.getText();
            if (name.equals("") || balance.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter your name and balance!");
            } else {
                currentBalance = Integer.parseInt(balance);
                Login.setVisible(false);
                menuFrame.setSize(1130, 665);
                menuFrame.setLocationRelativeTo(null);
                menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                menuFrame.setResizable(false);

                OptionsComponent beginningComponent = new OptionsComponent();
                menuFrame.add(beginningComponent);
                menuFrame.setVisible(true);
            }
        }); 
    }
    




    public static Thread gameRefreshThread = new Thread () {
        public void run () {
            while(true){
                
                newGame.atmosphereComponent.refresh(currentBalance, playerScore, dealerScore-1, newGame.faceDown);

            }
        }
    };

    public static Thread gameCheckThread = new Thread () {
        public void run () {
            while(true) {
                if (isFirstTime||newGame.roundOver) {
                    if (newGame.dealerWon){
                        dealerScore++; 
                        currentBalance-= GameComponent.currentBet;
                        }
                    else{
                        playerScore++;
                        currentBalance+= GameComponent.currentBet;
                    }
                
                
                gameFrame.getContentPane().removeAll();
                newGame = new Game(gameFrame); 
                newGame.formGame();

                isFirstTime = false;
            }
      }
    }
  };
}
