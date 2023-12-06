package atm;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

public class CredentialsUpdateHandler {

    public CredentialsUpdateHandler(String oldUsername, String newUsername, String newPassword) throws IOException {

        try {
            Connection connection = DbConnection.getConnection();
            // Define the SQL query to update the user's credentials
            String updateQuery = "UPDATE users SET username = ?, password = ? WHERE username = ?";

            try (PreparedStatement preparedStatement =
                            connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, newUsername);
                preparedStatement.setString(2, hashPassword.hash(newPassword));
                preparedStatement.setString(3, oldUsername); 

                // Execute the update statement
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "User credentials updated " +
                            "successfully.", "Success", JOptionPane.INFORMATION_MESSAGE,
                            customImage.createCheckmarkImage());
                    try {
                        new MainMenu(newUsername);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                } else {
                    System.out.println("No user found with the username: " + oldUsername);
                }
            } catch (SQLIntegrityConstraintViolationException e) {
                if (e.getErrorCode() == 1062) {
                    JOptionPane.showMessageDialog(null, "Error: Username already exists. " +
                            "Please choose a different username.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    e.printStackTrace();
                    System.err.println("Error executing the UPDATE statement.");
                }
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Connection to the database failed.");
        }
    }
}
