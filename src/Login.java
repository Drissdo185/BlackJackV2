import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;

public class Login extends JPanel {
    public static final int CARD_HEIGHT = 145;
    public static final int CARD_WIDTH = 100;
    private JButton btnLogin;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel title, ussernameLabel, passwordLabel ;
    public Login(){
        setupLogin();
    }

    public void setupLogin(){


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
        title.setFont(new Font("Arial", Font.BOLD, 25));
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


        // Add ActionListener to the Login button
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Check if the login is successful
                if (/*checkLogin(username, password)*/true) {
                    // Transition to the game menu (You can open a new frame or panel here)
                    new openGameMenu();
                    // Close the current frame
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Login.this);
                    frame.dispose();


                } else {
                    // Display an error message or handle unsuccessful login
                    JOptionPane.showMessageDialog(null, "Login failed. Please check your credentials.", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }





    /*private boolean checkLogin(String username, String password){
        // Perform the database authentication here
        String dbUrl = "jdbc:mysql://localhost:3300/blackjack";
        String dbUser = "root";
        String dbPassword = "08052003";

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
    }*/
    }


