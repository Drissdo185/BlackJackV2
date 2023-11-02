import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Game extends JPanel {
    public static final int CARD_HEIGHT = 145;
    public static final int CARD_WIDTH = 100;
    private JButton btnLogin;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel title, ussernameLabel, passwordLabel ;
    public Game(){
        setupMenu();
    }

    public void setupMenu(){


        this.setSize(800, 500);

        // Login Button
        btnLogin = new JButton("Login");
        btnLogin.setBounds(350,430, 80, 30);
        btnLogin.setFocusable(false);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 15));
        btnLogin.setBackground(Color.WHITE);
        btnLogin.setBorder(BorderFactory.createLoweredBevelBorder());
        btnLogin.setForeground(Color.RED);


        // Username Field
        usernameField = new JTextField();
        usernameField.setBounds(300, 200, 200, 50);
        usernameField.setFont(new Font("Arial", Font.BOLD, 15));
        usernameField.setBackground(Color.WHITE);
        usernameField.setBorder(BorderFactory.createLoweredBevelBorder());
        // Username Label
        ussernameLabel = new JLabel("Username");
        ussernameLabel.setBounds(300, 150, 200, 50);
        ussernameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        ussernameLabel.setForeground(Color.BLACK);



        // Password Field
        passwordField = new JPasswordField();
        passwordField.setBounds(300, 300, 200, 50);
        passwordField.setFont(new Font("Arial", Font.BOLD, 15));
        passwordField.setBackground(Color.WHITE);
        passwordField.setBorder(BorderFactory.createLoweredBevelBorder());

        // Password Label
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(300, 250, 200, 50);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 15));
        passwordLabel.setForeground(Color.BLACK);

        // Add title to the panel
        title = new JLabel("Black Jack IU");
        title.setBounds(300, 100, 200, 50);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.BLACK);


        // Add cho nó hiện ra trên panel
        this.add(ussernameLabel);
        this.add(passwordLabel);
        this.add(title);
        this.add(btnLogin);
        this.add(usernameField);
        this.add(passwordField);

        this.setLayout(null);
        this.setVisible(true);

    }


    /*private boolean checkLogin(String username, String password){
        // Perform the database authentication here
        String dbUrl = "jdbc:mysql://localhost:3300/your_database";
        String dbUser = "your_username";
        String dbPassword = "your_password";

        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next(); // If there's a matching user in the database, return true.

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Return false if an error occurs or if no matching user is found.
    }
    }*/

}
