package controllers;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Main;

import javax.ws.rs.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@Path("todo/")
public class ToDo {
    @GET
    @Path("list")
    public String UsersToDoList(@CookieParam("Token") String Token) {
        System.out.println("Invoked Users.UsersToDosList()");
        JSONArray response = new JSONArray();
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT ToDo, ToDoPriority, ToDoDate, ToDoComplete \n" +
                    "FROM UsersToDos \n" +
                    "JOIN Users ON Users.UserID = UsersToDos.UserID \n" +
                    "WHERE Token = ?");
            ps.setString(1, Token);
            ResultSet results = ps.executeQuery();
            while (results.next() == true) {
                JSONObject row = new JSONObject();
                row.put("ToDo", results.getString(1));
                row.put("ToDoPriority", results.getInt(2));
                row.put("ToDoDate", results.getString(3));
                row.put("ToDoComplete", results.getBoolean(4));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to list items. Please see server console for more information.\"}";
        }
    }
    @POST
    @Path("add")
    public String UsersAdd(@FormDataParam("ToDo")String ToDo, @FormDataParam("ToDoPriority")Integer ToDoPriority, @FormDataParam("ToDoDate") Date ToDoDate, @CookieParam("UserID") Integer UserID) {
        System.out.println("Invoked ToDo.ToDosAdd()");
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO UsersToDos (UserID, ToDo, ToDoPriority, ToDoDate, ToDoComplete) \n" +
                    "VALUES (?,?,?,?,0) WHERE UserID = ?");
            ps.setString(1, ToDo);
            ps.setInt(2, ToDoPriority);
            ps.setDate(3, ToDoDate);
            ps.setInt(4, UserID);
            ps.execute();
            return "{\"OK\": \"Added User.\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to create new item, please see server control for more information.\"}";
        }
    }
}