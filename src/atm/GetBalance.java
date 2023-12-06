package atm;

import java.io.IOException;
import java.sql.*;
import java.sql.SQLException;

public class GetBalance {
    public static Integer Get(String user) throws IOException {

        Integer oldBalance = null;

        try (Connection connection = DbConnection.getConnection()) {
            String selectQuery = "SELECT balance FROM users WHERE username = ?";

            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
                selectStatement.setString(1, user);

                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    if (resultSet.next()) {
                        oldBalance = resultSet.getInt("balance");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.err.println("Error retrieving old balance.");
                    oldBalance = -1;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error executing the SELECT statement.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return oldBalance;
    }
}
