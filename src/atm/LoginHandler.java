package atm;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginHandler {
	
	public LoginHandler(String enteredUsername, String enteredPassword) throws IOException, URISyntaxException {
		String username = enteredUsername;
        String password = enteredPassword;

        // Call the checkLogin method to check the credentials
        boolean loginSuccessful = checkLogin(username, password);

        if (loginSuccessful) {
            System.out.println("Login successful!");
            new MainMenu(username);
        } else {
            System.out.println("Login failed. Incorrect username or password.");
            JOptionPane.showMessageDialog(null, "Error: Username or Password is incorrect. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            new FrontPage();
        }
    }
	public boolean checkLogin(String enteredUsername, String enteredPassword) throws IOException {

	    try (Connection connection = DbConnection.getConnection()) {
			String selectQuery = "SELECT * FROM users WHERE username = ? AND password = ?";
			
			try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
				preparedStatement.setString(1, enteredUsername);
				preparedStatement.setString(2, hashPassword.hash(enteredPassword));

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					return resultSet.next();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false; // Default to false in case of any error
	}
}
