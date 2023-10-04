package recepebook.com.personalrecipebook.db;

import recepebook.com.personalrecipebook.model.User;
import recepebook.com.personalrecipebook.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {

    public User createUser(String names, String email, String password) throws SQLException, ClassNotFoundException {

        User user =null;

        Statement statement = DatabaseUtils.getConnection().createStatement();

        String query = "INSERT INTO `users` (`names`,`email`,`password`) VALUE ('"+names+"','"+email+"','"+password+"')";
        statement.execute(query);

        if(true){

            user = new User();

            user.setNames(names);
            user.setEmail(email);
            user.setPassword(password);
        }

        return user;
    }


    public User loginUser ( String email, String password ) throws SQLException, ClassNotFoundException {

        User user = null;

       Statement statement = DatabaseUtils.getConnection().createStatement();

        String query = "select `names`,`email`,`password` from `users` where `email`='"+email+"' and `password`='"+password+"'";

        ResultSet resultSet =statement.executeQuery(query);

        if(resultSet.next()){

            user = new User();

            user.setNames(resultSet.getString("names"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
        }

        return user;
    }


    public User viewUser ( String email, String password , String names) throws SQLException, ClassNotFoundException {

        User user = null;

        Statement statement = DatabaseUtils.getConnection().createStatement();

        String query = "select * from `users` where `email`='"+email+"' and `password`='"+password+"'";

        ResultSet resultSet =statement.executeQuery(query);

        if(resultSet.next()){

            user = new User();

            user.setNames(resultSet.getString("names"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
        }

        return user;


    }

    public User selectUser ( String email ) throws SQLException, ClassNotFoundException {

        User user = null;

        Statement statement = DatabaseUtils.getConnection().createStatement();

        String query = "select `names`,`email`,`password` from `users` where `email`='"+email+"'";

        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {

            user = new User();

            user.setNames(resultSet.getString("names"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
        }

        return user;
    }
}
