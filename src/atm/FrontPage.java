package atm;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class FrontPage implements ActionListener {
	private final JFrame mainFrame = new JFrame("Register Panel");
    private final JLabel registerOrLoginText = new JLabel("Enter your credentials to Log In");
    private final JLabel labelUsername = new JLabel("Username: ");
    private final JLabel labelPassword = new JLabel("Password:");
    private final JLabel coverImageLabel;
    private final JLabel labelSignUpNow = new JLabel("Sign Up Now");
    private final JLabel labelLogInNow = new JLabel("Already have an account? Log In");
    private final JTextField usernameField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    private final JButton submitButton = new JButton("Log In");
    private final JPanel statusPanel = new JPanel();
    private String obtainUsername;
    private String obtainPassword;
    private ImageIcon coverImageIcon;

    public FrontPage() throws IOException, URISyntaxException {

        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        mainFrame.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(mainFrame.getWidth(), 16));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        JLabel statusLabel = new JLabel("Application is running.");
        JLabel time = new JLabel();
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(statusLabel);
        statusPanel.add(time);

        // Set the cover image to the label (image holder) and adjust its properties
        coverImageIcon = new ImageIcon(getClass().getResource("../graphics/cover.png"));
        Image coverIamge = coverImageIcon.getImage();
        Image part = coverIamge.getScaledInstance(250, 50, java.awt.Image.SCALE_SMOOTH);
        coverImageIcon = new ImageIcon(part);
        coverImageLabel = new JLabel(coverImageIcon);
        coverImageLabel.setLayout(null);
        coverImageLabel.setBounds(160, -10, 290, 90);
        coverImageLabel.setVisible(true);

        // Main Frame properties
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 500);
		mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);

        registerOrLoginText.setBounds(150, 80, 300, 30);
        registerOrLoginText.setFont(new Font(null, Font.PLAIN, 20));

        // Username field and username lable properties
        labelUsername.setBounds(150, 130, 130, 30);
        labelUsername.setFont(new Font(null, Font.PLAIN, 16));

        usernameField.setBounds(150, 160, 275, 35);
        usernameField.addActionListener(this);

        // Password field and password label properties
        labelPassword.setBounds(150, 200, 130, 30);
        labelPassword.setFont(new Font(null, Font.PLAIN, 16));

        passwordField.setBounds(150, 230, 275, 35);
        passwordField.addActionListener(this);

        // Submit button properties
        submitButton.setBounds(150, 280, 275, 35);
        submitButton.setBackground(Color.decode("#0077be"));
        submitButton.setForeground(Color.white);
        submitButton.setFocusable(false);
        submitButton.addActionListener(this);

        labelLogInNow.setBounds(150, 330, 230, 30);
        labelLogInNow.setVisible(false);
        labelLogInNow.setForeground(Color.decode("#36C"));
        labelLogInNow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                registerOrLoginText.setText("Enter your credentials to Log In");
                submitButton.setText("Log In");
                labelSignUpNow.setVisible(true);
                labelLogInNow.setVisible(false);
                
            }
        });

        labelSignUpNow.setBounds(150, 330, 130, 30);
        labelSignUpNow.setForeground(Color.decode("#36C"));
        labelSignUpNow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                registerOrLoginText.setText("Create a new Account");
                submitButton.setText("Sign Up");
                labelSignUpNow.setVisible(false);
                labelLogInNow.setVisible(true);
                
            }
        });

        mainFrame.add(coverImageLabel);
        mainFrame.add(registerOrLoginText);
        mainFrame.add(labelUsername);
        mainFrame.add(usernameField);
        mainFrame.add(labelPassword);
        mainFrame.add(passwordField);
        mainFrame.add(submitButton);
        mainFrame.add(labelSignUpNow);
        mainFrame.add(labelLogInNow);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == submitButton) {
            obtainUsername = usernameField.getText();
            obtainPassword = String.valueOf(passwordField.getPassword());

            if (obtainUsername.isEmpty() || obtainPassword.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Username or password cannot be empty.");
                return;
            }
            
            if (submitButton.getText().equals("Sign Up")) {
                try {
                    new RegisterationHandler(obtainUsername, obtainPassword);
                } catch (Exception e) {e.printStackTrace();}
                mainFrame.dispose();
            }

            else if (submitButton.getText().equals("Log In")) {
                try {
                    new LoginHandler(obtainUsername, obtainPassword);
                } catch (Exception e) {e.printStackTrace();}
                mainFrame.dispose();
            }
        }
    }
}
