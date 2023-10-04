package recepebook.com.personalrecipebook.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import recepebook.com.personalrecipebook.db.UserDao;
import recepebook.com.personalrecipebook.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletLogin", value = "/api/v1/login")
public class ServletLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String message ="";
        boolean success =false;

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        JsonObject jsonData = new JsonObject();
        JsonObject jsonResponse = new JsonObject();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        try{

            if(email==null||email.length()==0){
                message="email is required";

            } else if (password == null || password.length() == 0) {
                message="password is required";
            } else {
                UserDao userDao = new UserDao();
                User user = userDao.loginUser(email,password);
                if(user==null){
                    message="email and password do not exist";
                }else {
                    User user1 = userDao.loginUser(email,password);

                    if(user1!=null){
                        message="successfully logged in";
                        success = true;

                        jsonData.addProperty("names",user1.getNames());
                        jsonData.addProperty("email",user1.getEmail());
                        jsonResponse.add("data",jsonData);
                    }
                }
            }

        }catch (Exception e){

            message = "something went wrong";
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

    }
}
