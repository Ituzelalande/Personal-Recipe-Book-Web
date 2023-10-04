package recepebook.com.personalrecipebook.db;

import recepebook.com.personalrecipebook.model.Recipe;
import recepebook.com.personalrecipebook.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RecipeDao {

    public Recipe createRecipe (String user_id, String ingredients) throws SQLException, ClassNotFoundException {

        Recipe recipe = null;

        Statement statement = DatabaseUtils.getConnection().createStatement();

        String query = "insert into `recipes` (`user_id`,`ingredients`) values ('"+user_id+"','"+ingredients+"')";

        statement.execute(query);

        if(true){

            recipe = new Recipe();

            recipe.setUser_id(user_id);
            recipe.setIngredients(ingredients);
        }

        return recipe;
    }

    public Recipe viewRecipe(String ingredients,String user_id) throws SQLException, ClassNotFoundException {

        Recipe recipe = null;

        Statement statement = DatabaseUtils.getConnection().createStatement();

        String query = "select * from recipes where `user_id` = '"+user_id+"' ";
        ResultSet resultSet = statement.executeQuery(query);

        if(resultSet.next()){
            recipe = new Recipe();

            recipe.setIngredients(resultSet.getString("ingredients"));

        }
        return recipe;
    }

    public boolean updateRecipe(String ingredients, String user_id,String id) throws SQLException, ClassNotFoundException {
        boolean isUpdated=false;

        Statement statement = DatabaseUtils.getConnection().createStatement();

        String query = "update `recipes` set `ingredients`='"+ingredients+"' where `user_id`='"+user_id+"'";
        statement.execute(query);

        if(true){

           boolean recipe= true;

        }
        return isUpdated;
    }

}
