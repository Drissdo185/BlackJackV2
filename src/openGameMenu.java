import javax.swing.*;

public class openGameMenu extends JFrame{
    private JLabel text;

    public openGameMenu(){
        openGameMenu();
    }

    public void openGameMenu() {
        setTitle("Black Jack Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);

        // Create a panel
        JPanel gameMenu = new JPanel();
        // Add text to the panel
        text = new JLabel("Welcome to Black Jack IU");

        // Add the panel to the frame
        add(gameMenu);

        this.setVisible(true);


    }
}
