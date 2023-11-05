import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Black Jack IU");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Login blackjack = new Login();
        frame.setSize(800, 600);

        frame.add(blackjack);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }
}
