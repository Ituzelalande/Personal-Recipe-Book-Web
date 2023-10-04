package recepebook.com.personalrecipebook.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        String urls = "jdbc:mysql://localhost:3306/personal_recipe_book";
        String userName = "root";
        String passcode = "";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(urls,userName,passcode);

        return connection;
    }
}
