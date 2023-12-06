package atm;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

    public static Connection getConnection() throws IOException, SQLException {
        FileReader reader = new FileReader("credentials.properties");
        Properties credentials = new Properties();
        credentials.load(reader);

        String jdbcURL = credentials.getProperty("link");
        String username = credentials.getProperty("user");
        String password = credentials.getProperty("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Database driver not found.", e);
        }

        Connection connection = DriverManager.getConnection(jdbcURL, username, password);
        if (connection != null) {
            return connection;
        } else {
            throw new SQLException("Connection to the database failed.");
        }
    }
}