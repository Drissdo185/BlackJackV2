import javax.swing.*;

public class openGameMenu extends JFrame{
    private JLabel text;

    public openGameMenu(){
        openGameMenu();
    }

    public void openGameMenu() {
        //Create and set up the window.
        JFrame frame = new JFrame("Black Jack Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        

        
        //Create gameMenu
        Game gameMenu = new Game();
        this.setLocationRelativeTo(null);
        this.add(gameMenu);
        this.setVisible(true);
        
    }
}