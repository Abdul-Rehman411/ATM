package atm;

import javax.swing.*;

import java.awt.*;

public class Transfer extends JFrame {

    JTextField transferAmountField = new JTextField();
    private JTextField receiverField;
    private JLabel senderLabel = new JLabel("Enter Amount");
    private JLabel receiverLabel = new JLabel("Enter receiver's username");
    private JButton goBack;
    private JButton transferButton;
    private final JLabel imgLabel;
    private ImageIcon image;

    public Transfer(String username) {
        setTitle("Manage Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null); // Center the frame on the screen
        setResizable(false);

        setLayout(null);

        // Create a label for the icon
        image = new ImageIcon(getClass().getResource("../graphics/money-transfer.png"));
        Image icon = image.getImage();
        Image part = icon.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(part);
        imgLabel = new JLabel(image);
        imgLabel.setLayout(null);
        imgLabel.setBounds(160, 0, 320, 150);
        imgLabel.setVisible(true);

        // Create a panel for the input fields and submit button
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        inputPanel.setBackground(Color.WHITE);

        // Username input
        senderLabel.setBounds(170, 5, 260, 40);
        inputPanel.add(senderLabel);
        inputPanel.add(transferAmountField);
        transferAmountField.setBounds(170, 40, 260, 40);

        // Password input
        receiverLabel.setBounds(170, 75, 260, 40);
        inputPanel.add(receiverLabel);
        receiverField = new JTextField(20);
        inputPanel.add(receiverField);
        receiverField.setBounds(170, 110, 260, 40);


        // go back button
        goBack = new JButton("Cancel");
        goBack.setFocusPainted(false);
        goBack.setBackground(Color.PINK);
        goBack.setForeground(Color.WHITE);
        goBack.setBounds(170, 165, 120, 40);
        inputPanel.add(goBack);

        goBack.addActionListener(e -> {
            try {
                new MainMenu(username);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            dispose();
        });
        
        // Transfer button
        transferButton = new JButton("Send");
        transferButton.setFocusPainted(false);
        transferButton.setBackground(Color.decode("#1F7AC4"));
        transferButton.setForeground(Color.WHITE);
        transferButton.setBounds(310, 165, 120, 40);
        inputPanel.add(transferButton);

        transferButton.addActionListener(e -> {
            // Handle the Transfer action here
            try {
                String sender = username;
                int transferAmount = Integer.parseInt(transferAmountField.getText());
                String receiver = receiverField.getText();

            if (transferAmount <= 0 || receiver.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please enter the amount and receiver's name carefully!");
                return;
            }
            if (receiver.equals(username)) {
                JOptionPane.showMessageDialog(null, "You cannot send funds to yourself.");
                return;
            }
            
            new TransferHandler(sender, transferAmount, receiver);
                
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Invalid amount. Try inserting digits.");
            }
        });
        
        // Add the label and the panel to the frame
        add(imgLabel);
        add(inputPanel);
        inputPanel.setBounds(0, 150, 600, 300);

        setVisible(true);
    }
}
