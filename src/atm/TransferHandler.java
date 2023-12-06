package atm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class TransferHandler {
	String sender;

	TransferHandler(String sender, int transferAmount, String receiver) throws IOException {
		this.sender = sender;

	    try {
			int oldBalance = GetBalance.Get(sender);
			int senderNewBalance = 0;
			String transactionMessage = "";

			// Carry out the transfer transaction
			if (oldBalance - transferAmount < 0) {
					JOptionPane.showMessageDialog(null, "Transfer amount excceeded available balance. Available balance: " + oldBalance);
					return;
				}
			else {
				Integer receiverOldBalance = GetBalance.Get(receiver);

				if (receiverOldBalance == null) { // this checkes whether or not the receiver account exists in the database
					JOptionPane.showMessageDialog(null, "The receiver does not exist. Transaction faild.");
					return;
				}
					int receiverNewBalance = receiverOldBalance + transferAmount; // calculate receiver's balance
					senderNewBalance = oldBalance - transferAmount; // calculate sender's balance
					transactionMessage = "Funds transfered successfully!";
					updateBalance(sender, senderNewBalance, transactionMessage); // update the balance of the sender
					updateBalance(receiver, receiverNewBalance, transactionMessage); // update the balance of the receiver
				}

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.err.println("An error occured while processing the transaction.");
	    }
	}

	public void updateBalance(String user, int newBalance, String transactionMessage) throws IOException {
		
		try (Connection connection = DbConnection.getConnection()) {

			// Update the balance in the database
			String updateQuery = "UPDATE users SET balance = ? WHERE username = ?";
			PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setInt(1, newBalance);
			updateStatement.setString(2, user);

			int rowsAffected = updateStatement.executeUpdate();

			if (rowsAffected > 0) {
				if (this.sender.equals(user)) // This if_statement makes sure that the message is not shown when the receiver's balance is updated.
				{
					System.out.println(transactionMessage + " New Balance: " + newBalance);
					JOptionPane.showMessageDialog(null, transactionMessage + " New Balance: " + newBalance, "Success", JOptionPane.INFORMATION_MESSAGE, customImage.createCheckmarkImage());
				}
			} else {
				System.out.println("Transaction failed.");
				JOptionPane.showMessageDialog(null, "Transaction faild.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error executing the UPDATE statement.");
		}
	}

}
