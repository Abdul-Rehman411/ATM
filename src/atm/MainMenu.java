package atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends Thread implements ActionListener {
    private final JFrame mainFrame = new JFrame("Dashboard");
    private final JButton manageAccountsButton = new JButton("Manage Account");
    private final JButton checkBalanceButton = new JButton("Check Balance");
    private final JButton withdrawButton = new JButton("Withdraw");
    private final JButton depositButton = new JButton("Deposit");
    private final JButton transferButton = new JButton("Transfer");
    private final JButton feedbackButton = new JButton("Feedback");
    private final JButton logoutButton = new JButton("Logout");
    private final JLabel welcomeTitle;
    private final JButton helpButton;
    private final String username;

    public MainMenu(String username) {
        // Set up the frame
        this.username = username;
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 500);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setBackground(Color.WHITE);

        // Create a panel for the top section with the welcome message
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.decode("#0F4C81"));
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.decode("#0F4C81"));
        welcomeTitle = new JLabel("Welcome, " + username);
        welcomeTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        welcomeTitle.setForeground(Color.WHITE);
        titlePanel.add(welcomeTitle);
        topPanel.add(titlePanel, BorderLayout.CENTER);

        // help button top right corner
        JPanel helpPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        helpPanel.setBackground(Color.decode("#0F4C81"));
        URL helpUrl1 = getClass().getResource("../graphics/help1.png");
        URL helpUrl2 = getClass().getResource("../graphics/help2.png");
        helpButton = new JButton();
        helpButton.setIcon(new ImageIcon(helpUrl1));
        helpButton.setFocusPainted(false);
        helpButton.setBackground(Color.decode("#0F4C81"));
        helpButton.addActionListener(this);
        helpButton.setBorder(null);
        helpButton.setContentAreaFilled(false);
        helpPanel.add(helpButton);
        topPanel.add(helpPanel, BorderLayout.EAST);

        helpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                helpButton.setIcon(new ImageIcon(helpUrl2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                helpButton.setIcon(new ImageIcon(helpUrl1));
            }
        });

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 15, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        buttonPanel.setBackground(Color.decode("#F0F0F0"));

        // Customize the buttons
        JButton[] buttons = {manageAccountsButton, checkBalanceButton, withdrawButton, depositButton, transferButton, feedbackButton};
        for (JButton button : buttons) {
            button.setFocusPainted(false);
            button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
            button.setBackground(Color.decode("#1F7AC4"));
            button.setForeground(Color.WHITE);
        }

        // Add action listeners to the buttons
        manageAccountsButton.addActionListener(this);
        checkBalanceButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        depositButton.addActionListener(this);
        transferButton.addActionListener(this);
        feedbackButton.addActionListener(this);

        // Add the buttons to the button panel
        for (JButton button : buttons) {
            buttonPanel.add(button);
        }

        // Add the panels to the mainFrame
        mainFrame.add(topPanel, BorderLayout.NORTH);
        mainFrame.add(buttonPanel, BorderLayout.CENTER);

        // Customize the logout button
        logoutButton.setFocusPainted(false);
        logoutButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(Color.decode("#FFA500"));
        logoutButton.addActionListener(this);

        // Set the icon property of the logout button
        URL logoutURL = getClass().getResource("../graphics/logout.png");
        ImageIcon logoutButtonIcon = new ImageIcon(logoutURL);
        logoutButton.setIcon(logoutButtonIcon);
        logoutButton.setHorizontalAlignment(SwingConstants.CENTER);
        logoutButton.setVerticalAlignment(SwingConstants.BOTTOM);
        logoutButton.setHorizontalTextPosition(SwingConstants.CENTER);
        logoutButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        mainFrame.add(logoutButton, BorderLayout.SOUTH);

        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Mouse entered, change the background color or perform other actions
                logoutButton.setBackground(Color.decode("#CD6155"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Mouse exited, revert to the default background color or perform other actions
                logoutButton.setBackground(Color.decode("#FFA500"));
            }
        });

        mainFrame.add(logoutButton, BorderLayout.SOUTH);

        // Load the icons for the buttons
        loadIcons();

        mainFrame.setVisible(true);
    }

    // A method to load the icons for the buttons
    private void loadIcons() {
        // An array of icon file names
        String[] iconFiles = {"../graphics/manage.png", "../graphics/cash.png", "../graphics" +
                "/deposit.png", "../graphics/withdraw.png", "../graphics/transfer.png",
                "../graphics" + "/feedback.png"};
        JButton[] buttons = {manageAccountsButton, checkBalanceButton, withdrawButton, depositButton, transferButton, feedbackButton};
        // A loop to load each icon and set it to the corresponding button
        for (int i = 0; i < iconFiles.length; i++) {
            final int buttonIndex = i;
            URL iconURL = getClass().getResource(iconFiles[i]);
            ImageIcon icon = new ImageIcon(iconURL);
            Image image = icon.getImage();
            Image scaledImage = image.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledImage);
            buttons[i].setIcon(icon);
            buttons[i].setHorizontalAlignment(SwingConstants.CENTER);
            buttons[i].setVerticalAlignment(SwingConstants.BOTTOM);
            buttons[i].setHorizontalTextPosition(SwingConstants.CENTER);
            buttons[i].setVerticalTextPosition(SwingConstants.BOTTOM);

            buttons[i].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                buttons[buttonIndex].setBackground(Color.decode("#27AE60"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttons[buttonIndex].setBackground(Color.decode("#1F7AC4"));
            }
        });
        }
    }

    @Override
    // Perform the corresponding action when each of the button is clicked inside the mainFrame
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == withdrawButton) {
            // withdraw code here
        }
        if (e.getSource() == checkBalanceButton) {
            try {
                JOptionPane.showMessageDialog(null,
                        "Available account balance: $" + GetBalance.Get(username), "Balance Inquiry!",
                        JOptionPane.INFORMATION_MESSAGE, customImage.createCheckmarkImage());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == manageAccountsButton) {
            try {new ManageAccount(username);} catch (Exception exception) {
                exception.printStackTrace();
            }
            mainFrame.dispose();
        }
        if (e.getSource() == depositButton) {
            // deposit code here
        }
        if (e.getSource() == logoutButton) {
            int n = JOptionPane.showConfirmDialog(null,
            "Are you sure you want to log out?",
            "Log Out", 
            JOptionPane.YES_NO_OPTION);
            if (n == 0) {
                try {
                    new FrontPage();
                } catch (IOException | URISyntaxException ex) {throw new RuntimeException(ex);}
                mainFrame.dispose();
            }
        }
        if (e.getSource() == transferButton) {
            new Transfer(username);
        }
        if (e.getSource() == feedbackButton) {
            // feedback code here
        }
        if (e.getSource() == helpButton) {
            // show help code here
        }
    }

}
