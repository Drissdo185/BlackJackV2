import javax.swing.*;

public class Tester {
    public static JFrame Stater = new JFrame("Blackjack");
    public static JFrame menuGameFrame = new JFrame("Blackjack");
    public static JFrame gameFrame = new JFrame("Blackjack");

    public static Game newGame = new Game(gameFrame); //we initialize a 'Game' in order to control, start, and calculate the blackjack game.
    private static boolean isFirstTime = true; //this boolean value will check if the game is newly started for the first time.

    public static enum STATE{ //This enum represents the state of the game which is either menu or game. While it is menu, we will show the user the menu. While it is game, we will show the user the game.
        MENU,
        GAME,
        START
    };
}
