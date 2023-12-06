package atm;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.*;


public class RegisterationHandler {
    public RegisterationHandler(String obtainUsername, String pass) throws IOException,
            URISyntaxException {

        try {
            Connection connection = DbConnection.getConnection();
            String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";

            try (PreparedStatement preparedStatement =
                            connection.prepareStatement(insertQuery)) {
                // Set values for the placeholder
                preparedStatement.setString(1, obtainUsername);
                preparedStatement.setString(2, hashPassword.hash(pass));

                // Execute the INSERT statement
                try {
                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("User Registered successfully!");
                        new MainMenu(obtainUsername);
                    } else {
                        System.out.println("Data insertion failed.");
                    }
                } catch (SQLIntegrityConstraintViolationException e) {
                    if (e.getErrorCode() == 1062) {
                        JOptionPane.showMessageDialog(null, "Error: Username already exists. " +
                                "Please choose a different username.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        System.err.println("Error: Username already exists. Please choose a " +
                                "different username.");
                        new FrontPage();

                    } else {
                        e.printStackTrace();
                        System.err.println("Error executing the INSERT statement.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error preparing the INSERT statement.");
            }

                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Connection to the database failed.");
        }
    }
}
