package recepebook.com.personalrecipebook.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import recepebook.com.personalrecipebook.db.RecipeDao;
import recepebook.com.personalrecipebook.db.UserDao;
import recepebook.com.personalrecipebook.model.Recipe;
import recepebook.com.personalrecipebook.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletRecipe", value = "/api/v1/recipe")
public class ServletRecipe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String message ="";
        boolean success = false;
        String ingredients = request.getParameter("ingredients");
        String user_id = request.getParameter("user_id");

        JsonObject jsonData = new JsonObject();
        JsonObject jsonResponse = new JsonObject();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        try {
            if(user_id==null){
                message= "login first";
            }else {

                RecipeDao recipeDao = new RecipeDao();
                Recipe recipe= recipeDao.viewRecipe(ingredients,user_id);

                if(recipe!=null){
                    jsonData.addProperty("ingredients",recipe.getIngredients());
                    jsonResponse.add("data",jsonData);
                    success = true;
                }else {
                    message="no recipe found for you";
                }
            }

        }catch (Exception e){
            message="something went wrong";
            e.printStackTrace();
        }

        jsonResponse.addProperty("success",success);
        jsonResponse.addProperty(success? "message": "error",message);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(gson.toJson(jsonResponse));


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message ="";
        boolean success = false;
        String ingredients = request.getParameter("ingredients");
        String user_id = request.getParameter("user_id");

        JsonObject jsonData = new JsonObject();
        JsonObject jsonResponse = new JsonObject();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        try{

            if(user_id==null){
                message="create your account";
            }else {
                RecipeDao recipeDao = new RecipeDao();
                Recipe recipe = recipeDao.createRecipe(user_id, ingredients);

                if(recipe!=null){
                    jsonData.addProperty("ingredients",recipe.getIngredients());
                    jsonResponse.add("data",jsonData);
                    message="recipe created";
                    success=true;
                }else {
                    message="create your own recipe";
                }
            }
        }catch (Exception e){
            message="something went wrong";
            e.printStackTrace();
        }
        jsonResponse.addProperty("success",success);
        jsonResponse.addProperty(success? "message": "error",message);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(gson.toJson(jsonResponse));
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String message ="";
        boolean success = false;
        String ingredients = request.getParameter("ingredients");
        String user_id = request.getParameter("user_id");
        String id = request.getParameter("ingredient_id");


        JsonObject jsonData = new JsonObject();
        JsonObject jsonResponse = new JsonObject();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        try{
           if(user_id==null){

           } else if (ingredients == null) {

           }else {

               RecipeDao recipeDao = new RecipeDao();
               boolean isUpdated= recipeDao.updateRecipe(ingredients,user_id, id);

               if(isUpdated){
                  message = "you updated your recipe";
                  success = true;
               }else {
                   message ="not updated";

               }
           }

        }catch (Exception e){
            message="something went wrong";
            e.printStackTrace();
        }

        jsonResponse.addProperty("success",success);
        jsonResponse.addProperty(success? "message": "error",message);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(gson.toJson(jsonResponse));

    }
}
